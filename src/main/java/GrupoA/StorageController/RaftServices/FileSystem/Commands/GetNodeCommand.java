package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

import java.io.Serializable;


public class GetNodeCommand extends FileSystemCommand<FSTree.Node> {

    private long iNode;
    private String path;
    private boolean byPath = true;

    public GetNodeCommand(long iNode) {
        this.iNode = iNode;
        this.byPath = false;
    }

    public GetNodeCommand(String path) {
        this.path = path;
    }
    @Override
    public FSTree.Node execute(FSTree context) {
        if(byPath)
            return context.getNode(this.path);
        return context.getNode(this.iNode);
    }
}
