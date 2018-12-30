package GrupoA.FuseSupport;

import GrupoA.AppServer.ApplicationServer;
import GrupoA.AppServer.Models.*;
import GrupoA.StorageController.gRPCService.FileSystem.FileType;
import jnr.ffi.Pointer;
import jnr.ffi.types.*;
import ru.serce.jnrfuse.ErrorCodes;
import ru.serce.jnrfuse.FuseFillDir;
import ru.serce.jnrfuse.FuseStubFS;
import ru.serce.jnrfuse.struct.FileStat;
import ru.serce.jnrfuse.struct.FuseFileInfo;
import ru.serce.jnrfuse.struct.Timespec;

import java.lang.reflect.Array;
import java.util.*;

import static GrupoA.AppServer.Models.AttributeUpdateRequest.UpdateType.UPDATEACCESSTIME;

//Functions not implemented: getxattr
public class CephishFuse extends FuseStubFS {
    private RestClient restClient;

    public CephishFuse(String Address) {
        restClient = new RestClient(Address);
    }

    @Override
    public int getattr(String path, FileStat stat){
        NodeAttributes attr = restClient.getAttribute(path);
        if(attr == null) {
            return -ErrorCodes.ENOENT();
        }
        attr.toFuseStat(stat);

        return 0;
    }

    @Override
    public int chmod(String path, @mode_t long mode) {
        if( restClient.updateAttribute(path, AttributeUpdateRequest.UpdateType.CHMOD, mode & 0x1FF))
            return 0;
        return -ErrorCodes.ENOENT();
    }

    @Override
    public int chown(String path, @uid_t long uid, @gid_t long gid) {
        if ( restClient.updateAttribute(path, uid, gid))
            return 0;
        return -ErrorCodes.ENOENT();
    }

    @Override
    public int mkdir(String path, @mode_t long mode) {
        NodeAttributes attr = restClient.getAttribute(path);
        long permissions = mode & 0x1FF;
        if (attr != null)
            return -ErrorCodes.EEXIST();
        System.out.println("creating dir");
        if( restClient.mkDir(path, mode, 1000, 1000, permissions))
            return 0;
        return -ErrorCodes.ENOENT();
    }

    @Override
    public int readdir(String path, Pointer buf, FuseFillDir filter, @off_t long offset, FuseFileInfo fi) {
        DirectoryContents dc = restClient.readDir(path);
        if(!dc.Valid) {
            return -ErrorCodes.ENOTDIR();
        }

        filter.apply(buf, ".", null, 0);
        filter.apply(buf, "..", null, 0);
        for (int i = 1; i < dc.Contents.size(); ++i) { // First index is itself
            filter.apply(buf, dc.Contents.get(i).Name, null, 0);
            System.out.println(dc.Contents.get(i).Name);
        }
        return 0;
    }

    //TODO locks?
    //TODO file create and modified time
    //TODO set creation time and modified time in here
    @Override
    public int create(String path, @mode_t long mode, FuseFileInfo fi) {
        NodeAttributes attr = restClient.getAttribute(path);
        long permissions = mode & 0x1FF;
        if (attr != null)
            return -ErrorCodes.EEXIST();
        boolean result = restClient.createFile(path, mode, 1000, 1000, permissions);
        if (result)
            return 0;

        return -ErrorCodes.ENOTDIR();
    }

    @Override
    public int utimens(String path, Timespec[] timespec) { //TODO set access time
        restClient.updateAttribute(path, UPDATEACCESSTIME, timespec[0].tv_sec.longValue());
        return 0;
    }

    @Override
    public int rmdir(String path) {
        return restClient.removeDir(path);
    }

    @Override
    public int unlink(String path) {
        NodeAttributes attr = restClient.getAttribute(path);
        if (attr == null)
            return -ErrorCodes.ENOENT();
        if(attr._FileType.equals(FileType.DIR))
            return -ErrorCodes.EISDIR();

        return restClient.removeFile(path);
    }

    private HashMap<String, SortedSet<WriteRequest>> pendingWrites = new HashMap<>();
    private HashMap<String, Long> pendingSize = new HashMap<>();
    private synchronized Long addWriteRequest(String path, Pointer buf, @size_t long size, @off_t long offset) {
        SortedSet<WriteRequest> pendingWritesList = pendingWrites.get(path);
        if (pendingWritesList == null) {
            pendingSize.put(path, 0L);
            pendingWritesList = new TreeSet<>();
            pendingWrites.put(path, pendingWritesList);
        }
        WriteRequest wr = new WriteRequest();
        wr.path = path;
        wr.data = new byte[(int)size]; //todo/issue Alternative for 4GB+ size files?
        wr.offset = offset;


        buf.get(0, wr.data,0, wr.data.length);
        pendingWritesList.add(wr);
        pendingSize.replace(path, pendingSize.get(path) + size);

        return pendingSize.get(path) + size;

    }

    @Override
    public int flush(String path, FuseFileInfo fi) {
        System.out.println("flushing");
        List<WriteRequest> wrs = this.mergeRequests(path);
        for (WriteRequest wr : wrs) {
            restClient.write(path, wr);
        }
        return 0;
    }

    private synchronized List<WriteRequest> mergeRequests(String path) {
        SortedSet<WriteRequest> pendingWritesList = pendingWrites.get(path);
        if(pendingWritesList.size() == 0) {
            pendingSize.replace(path, 0L);
            return new LinkedList<>();
        }
        WriteRequest merged = pendingWritesList.first();
        pendingWritesList.remove(merged);

        WriteRequest[] requests = new WriteRequest[pendingWritesList.size()];
        pendingWritesList.toArray(requests);
        for (WriteRequest request : requests) {
            if (merged.offset + merged.data.length != request.offset)
                continue;
            byte[] backup = merged.data;
            merged.data = new byte[backup.length + request.data.length];
            System.arraycopy(backup, 0, merged.data, 0, backup.length);
            System.arraycopy(request.data, 0, merged.data, backup.length, request.data.length);
            pendingWritesList.remove(request);
        }
        List<WriteRequest> reqs = this.mergeRequests(path);
        reqs.add(merged);
        return reqs;
    }

    @Override
    public int write(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi) {
        if(!pendingSize.containsKey(path) || pendingSize.get(path) == 0 ) {
            NodeAttributes attr = restClient.getAttribute(path);
            if (attr == null)
                return -ErrorCodes.ENOENT();
            if(attr._FileType.equals(FileType.DIR))
                return -ErrorCodes.EISDIR();
        }
        Long pendingSize = addWriteRequest(path, buf, size, offset);
        if(pendingSize >= ApplicationServer.subBlockSize) {
            try {
                int status = this.flush(path, fi);
                if (status < 0)
                    return status;
            } catch (Exception e){
                e.printStackTrace();
                return -ErrorCodes.EIO();
            }
        }
        return (int) size;
    }


    @Override
    public int read(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi) {
        ReadFileResponse rfr = restClient.readFile(path, size, offset);
        if(rfr.Status < 0)
            return rfr.Status;
        synchronized (this) {
            buf.put(0, rfr.Data, 0, rfr.Data.length);
        }
        return rfr.Data.length;
    }


    @Override
    public int truncate(String path, @off_t long size) {
        System.out.println("truncate(\""+path+"\", " +size    +")");
        NodeAttributes attr = restClient.getAttribute(path);
        if (attr == null)
            return -ErrorCodes.ENOENT();
        if (attr._FileType.equals(FileType.DIR))
            return -ErrorCodes.EISDIR();

        return restClient.truncate(path, size);
    }
/*
    @Override
    public int release(String path, FuseFileInfo fi) {
        List<WriteRequest> pendingWritesList = this.mergeRequests(path);
        for (WriteRequest wr : pendingWritesList) {
            pendingWritesList.remove(wr);
        }
        return 0;
    }*/

}