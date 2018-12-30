package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;

public class TryLockCommand extends FileSystemCommand<Boolean> {

    public String path;
    public long id;
    public long time;
    public TryLockCommand(String path, long id, long time){
        this.path = path;
        this.id = id;
        this.time = time;
    }
    @Override
    public Boolean execute(FSTree context) {
        FSTree.Node node = context.getNode(path);
        if(!node.getNodeType().equals(FSTree.NodeType.FileNode)) {
            return false;
        }
        return node.getLock(id, time);
    }
}
