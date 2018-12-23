package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

import java.io.Serializable;


public class GetINodeCommand extends FileSystemCommand<FSTree.Node> {

    private long iNode;

    public GetINodeCommand(long iNode) {
        this.iNode = iNode;
    }
    @Override
    public FSTree.Node execute(FSTree context) {
        return context.getNode(this.iNode);
    }
}
