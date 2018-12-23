package GrupoA.FuseSupport;

import GrupoA.AppServer.Models.AttributeUpdateRequest;
import GrupoA.AppServer.Models.NodeAttributes;
import jnr.ffi.Pointer;
import jnr.ffi.types.mode_t;
import jnr.ffi.types.size_t;
import ru.serce.jnrfuse.ErrorCodes;
import ru.serce.jnrfuse.FuseStubFS;
import ru.serce.jnrfuse.struct.FileStat;

//Functions not implemented: getxattr
public class CephishFuse extends FuseStubFS {
    private RestClient restClient;

    public CephishFuse(String Address) {
        restClient = new RestClient(Address);
    }

    @Override
    public int getattr(String path, FileStat stat){
        System.out.println("Getting attribute");
        NodeAttributes attr = restClient.getAttribute(path);
        System.out.println("Attribute: " + attr);
        if(attr == null) {
            return -ErrorCodes.ENONET();
        }
        attr.toFuseStat(stat);

        return 0;
    }

    @Override
    public int chmod(String path, @mode_t long mode) {
        if( restClient.updateAttribute(path, AttributeUpdateRequest.UpdateType.CHMOD, mode & 0x1FF))
            return 0;
        return -ErrorCodes.ENONET();
    }
}
