package GrupoA.AppServer.Models;


import GrupoA.AppServer.ApplicationServer;
import fuse.StatConstants;
import fuse.Timespec;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NodeAttributes {
    public String Name;

    public Integer UserPermissions = 0;
    public Integer GroupPermissions = 0;
    public Integer OthersPermissions = 0;
    public Long OwnerId = 0L;
    public Long GroupId = 0L;
    public Long INodeNumber = 1L;
    public Long Size = 0L;
    public Long ParentINodeNumber = -1L;
    public Integer BlockSize = ApplicationServer.maxBlockSize;


    public fuse.Stat toFuseStat() {
        fuse.Stat stat = new fuse.Stat();
        stat.setNlink(2);
        stat.setMode((StatConstants.IFDIR | 511));
        stat.setCtim(new Timespec());
        stat.setGid(this.GroupId);
        stat.setUid(this.OwnerId);
        stat.setIno(this.INodeNumber);
        stat.setSize(this.Size);
        stat.setBlksize(this.BlockSize);
        //stat.setMode((UserPermissions << 6) | (GroupPermissions << 3) | OthersPermissions);
        stat.setAtim(new Timespec());
        return stat;
    }
}