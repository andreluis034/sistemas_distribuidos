package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;
import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;

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
        FSTree.Node node;
        if(byPath)
            node = context.getNode(this.path);
        else
            node = context.getNode(this.iNode);

        if ( node != null && node.getNodeType().equals(FSTree.NodeType.FileNode) && ((FSTree.FileNode)node).fileSize == 0) {
            ((FSTree.FileNode)node).setCrushMapVersion(CrushMapService.getInstance().getLatestMap().getVersion());
        }
        return node;
    }

}
