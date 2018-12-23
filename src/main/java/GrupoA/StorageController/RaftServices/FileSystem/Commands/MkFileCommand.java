package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MkFileCommand extends FileSystemCommand<Boolean> {

    private String path;
    private long mode;
    private long uid;
    private long gid;

    public MkFileCommand(String path, long mode, long uid, long gid) {
        this.path = path;
        this.mode = mode;
        this.uid = uid;
        this.gid = gid;
    }

    @Override
    public Boolean execute(FSTree context) {
        Path _path = Paths.get(this.path);
        if(context.getNode(this.path) != null)
            return false;
        FSTree.Node node = context.getNode(_path.getParent().toString());
        if(node.getNodeType() != FSTree.NodeType.DirNode) {
            return false;
        }
        return context.mkFile(this.path, uid, gid);
    }
}