package GrupoA.AppServer.Models;


import GrupoA.AppServer.ApplicationServer;
import GrupoA.StorageController.gRPCService.FileSystem.FileType;
import jnr.ffi.Runtime;
import ru.serce.jnrfuse.struct.FileStat;
import ru.serce.jnrfuse.struct.Timespec;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NodeAttributes {
    public String Name;

    public FileType _FileType;
    public Integer UserPermissions = 0;
    public Integer GroupPermissions = 0;
    public Integer OthersPermissions = 0;
    public Long UserId = 0L;
    public Long GroupId = 0L;
    public Long INodeNumber = 1L;
    public Long Size = 0L;
    public Long ParentINodeNumber = -1L;
    public Integer BlockSize = ApplicationServer.maxBlockSize;


    public FileStat toFuseStat(FileStat stat) {
        long mode;
        if(_FileType == FileType.FILE) {
            stat.st_nlink.set(1);
            mode = FileStat.S_IFREG;
        } else {
            mode = FileStat.S_IFDIR;
            stat.st_nlink.set(2);
        }
        mode |= (UserPermissions << 6) | (GroupPermissions << 3) | OthersPermissions;
        stat.st_mode.set(mode);
        stat.st_uid.set(this.UserId);
        stat.st_gid.set(this.GroupId);
        stat.st_ino.set(this.INodeNumber);
        stat.st_size.set(this.Size);
        stat.st_blksize.set(this.BlockSize);

        return stat;
    }
}