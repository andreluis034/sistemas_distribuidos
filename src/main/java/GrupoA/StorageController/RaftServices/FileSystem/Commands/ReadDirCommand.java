package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ReadDirCommand extends  FileSystemCommand<List<FSTree.Node>> {

    private String path;

    public ReadDirCommand(String path) {
        this.path = path;
    }

    @Override
    public List<FSTree.Node> execute(FSTree context) {
        FSTree.Node node = context.getNode(this.path);
        if(node == null || node.getNodeType() != FSTree.NodeType.DirNode)
            return new LinkedList<>();
        FSTree.DirNode dir = (FSTree.DirNode) node;
        List<FSTree.Node> list = new ArrayList<>(dir.getChildren().size() + 1);
        list.add(node);
        Collection<FSTree.Node> children = dir.getChildren();
        for(FSTree.Node child : children) {
            list.add(child);
        }
        return list;
    }
}
