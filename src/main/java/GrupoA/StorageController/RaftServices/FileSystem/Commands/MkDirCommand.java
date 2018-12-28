package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MkDirCommand extends FileSystemCommand<Boolean> {

    private Boolean searchByString = true;
    private String path = "";
    private Long iNode = 0L;
    private long mode, uid, gid;
    private long permission = 0x124; //default read only
    private long creationTime;

    public MkDirCommand(String path, long mode, long uid, long gid, long permission, long creationTime) {
        this.path = path;
        this.mode = mode;
        this.uid = uid;
        this.gid = gid;
        this.permission = permission;
        this.creationTime = creationTime;
    }

    public MkDirCommand(Long iNode) {
        this.iNode = iNode;
        this.searchByString = false;
    }

    @Override
    public Boolean execute(FSTree context) {
        /*
        if (searchByString)
            return context.mkDir(path);
        return false;
        */
        Path _path = Paths.get(this.path);
        if(context.getNode(this.path) != null)
            return false;
        FSTree.Node node = context.getNode(_path.getParent().toString());
        if(node.getNodeType() != FSTree.NodeType.DirNode)
            return false;

        return context.mkDir(this.path, this.uid, this.gid, this.creationTime, this.permission);
    }
}
