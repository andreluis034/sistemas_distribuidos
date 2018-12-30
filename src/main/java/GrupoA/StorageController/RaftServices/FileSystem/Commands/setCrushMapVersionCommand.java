package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

public class setCrushMapVersionCommand extends FileSystemCommand<Boolean>{

    private String path;
    private long version;

    public setCrushMapVersionCommand(String path, long version) {
        this.path = path;
        this.version = version;
    }


    @Override
    public Boolean execute(FSTree context) {
        FSTree.Node node = context.getNode(path);
        if (node == null || node.getNodeType() != FSTree.NodeType.FileNode)
            return false;

        ((FSTree.FileNode)node).setCrushMapVersion(version);
        return true;
    }
}
