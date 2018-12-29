package GrupoA.FuseSupport;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

    private HashMap<String, List<WriteRequest>> pendingWrites = new HashMap<>();
    private synchronized WriteRequest addWriteRequest(String path, Pointer buf, @size_t long size, @off_t long offset) {
        List<WriteRequest> pendingWritesList = pendingWrites.get(path);
        if (pendingWritesList == null) {
            pendingWritesList = new ArrayList<>();
            pendingWrites.put(path, pendingWritesList);
        }
        WriteRequest wr = new WriteRequest();
        wr.path = path;
        wr.data = new byte[(int)size]; //todo/issue Alternative for 4GB+ size files?
        wr.offset = offset;


        buf.get(0, wr.data,0, wr.data.length);
        pendingWritesList.add(wr);

        return wr;

    }

    private synchronized void mergeRequests(String path) { //TODO make sure the list is sorted
        List<WriteRequest> pendingWritesList = pendingWrites.get(path);
        for (int i = 0; i < pendingWritesList.size(); ++i) {
            WriteRequest wri = pendingWritesList.get(i);
            for(int j = i; j < pendingWritesList.size(); ++j) {
                WriteRequest wrj = pendingWritesList.get(j);
                if(wri.offset + wri.data.length < wrj.offset)
                    continue;
                int collision = (int) (wri.offset + wri.data.length - wrj.offset);
                byte[] data = new byte[wri.data.length + wrj.data.length - collision];
                System.arraycopy(wri.data, 0, data, 0, wri.data.length - collision);
                System.arraycopy(wrj.data, 0, data, wri.data.length - collision, wrj.data.length);
                wri.data = data;
                pendingWrites.remove(wrj);
            }
        }
    }

    @Override
    public int write(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi) {
        NodeAttributes attr = restClient.getAttribute(path);
        if (attr == null)
            return -ErrorCodes.ENOENT();
        if(attr._FileType.equals(FileType.DIR))
            return -ErrorCodes.EISDIR();
        WriteRequest wr =  addWriteRequest(path, buf, size, offset);
        int writeResult = restClient.write(path, wr);

        return writeResult;
    }


    @Override
    public int read(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi) {
        System.out.println("----------------------------------------------------------");
        System.out.printf("read(\"%s\", size: %d, offset: %d)\n", path, size, offset);
        ReadFileResponse rfr = restClient.readFile(path, size, offset);
        if(rfr.Status < 0)
            return rfr.Status;
        synchronized (this) {
            buf.put(0, rfr.Data, 0, rfr.Data.length);
        }
        System.out.println("----------------------------------------------------------");
        return rfr.Data.length;
    }


    @Override
    public int truncate(String path, @off_t long size) {
        System.out.println("truncate(\""+path+"\", " +offset    +")");
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
        List<WriteRequest> pendingWritesList = pendingWrites.get(path);
        for (WriteRequest wr : pendingWritesList) {
            pendingWritesList.remove(wr);
        }
        return 0;
    }*/

}