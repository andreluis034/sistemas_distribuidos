package GrupoA.StorageController;

import javax.annotation.Nullable;
import java.util.SortedSet;

public class FSTree {
    private DirNode root;

    FSTree() {
        root = new DirNode("/");
        root.children = null;
    }

    public DirNode getRoot() {
        return root;
    }

    public enum NodeType {
        FileNode,
        DirNode
    }

    public static abstract class Node implements Comparable<Node> {
        public abstract NodeType getNodeType();
        String nodeName;

        public int compareTo(@Nullable Node node) {
            if (node == null) {
                return 1;
            }

            return nodeName.compareTo(node.nodeName);
        }

        public String getNodeName() {
            return nodeName;
        }
    }

    public static class FileNode extends Node {
        Long hash;
        Integer fileSize, blocks;

        @Override
        public NodeType getNodeType() {
            return NodeType.FileNode;
        }
    }

    public static class DirNode extends Node {
        SortedSet<Node> children;

        public DirNode(String name) {
            this.nodeName = name;
        }

        @Override
        public NodeType getNodeType() {
            return NodeType.DirNode;
        }

        public SortedSet<Node> getChildren() {
            return children;
        }

        public void setChildren(SortedSet<Node> children) {
            this.children = children;
        }

        public SortedSet<Node> addChildren(DirNode parent, Node child) {
            parent.getChildren().add(child);

            return parent.getChildren();
        }
    }
}
