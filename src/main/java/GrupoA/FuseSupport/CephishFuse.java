package GrupoA.FuseSupport;

import GrupoA.AppServer.Models.AttributeUpdateRequest;
import GrupoA.AppServer.Models.DirectoryContents;
import GrupoA.AppServer.Models.NodeAttributes;
import jnr.ffi.Pointer;
import jnr.ffi.types.*;
import ru.serce.jnrfuse.ErrorCodes;
import ru.serce.jnrfuse.FuseFillDir;
import ru.serce.jnrfuse.FuseStubFS;
import ru.serce.jnrfuse.NotImplemented;
import ru.serce.jnrfuse.struct.FileStat;
import ru.serce.jnrfuse.struct.FuseFileInfo;
import ru.serce.jnrfuse.struct.Timespec;

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
        if (attr != null)
            return -ErrorCodes.EEXIST();
        if( restClient.mkDir(path, mode, 1000, 1000))
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
        if (attr != null)
            return -ErrorCodes.EEXIST();
        boolean result = restClient.createFile(path, mode, 1000, 1000);
        if (result)
            return 0;

        return -ErrorCodes.ENOTDIR();
    }

    @Override
    public int utimens(String path, Timespec[] timespec) { //TODO set access time
        restClient.updateAttribute(path, UPDATEACCESSTIME, timespec[0].tv_sec.longValue());
        return 0;
    }

}
