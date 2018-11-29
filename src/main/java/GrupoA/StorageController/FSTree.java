package GrupoA.StorageController;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.SortedSet;

class FSTree {
    public Node root;

    enum NodeType {
        FileNode,
        DirNode
    }

    abstract class Node implements Comparable<Node> {
        abstract NodeType getNodeType();
        String nodeName;

        public int compareTo(Node node) {
            if (node == null) {
                return 1;
            }
            return nodeName.compareTo(node.nodeName);
        }
    }

    class FileNode extends Node {
        Long hash;
        Integer fileSize, blocks;

        @Override
        public NodeType getNodeType() {
            return NodeType.FileNode;
        }
    }

    class DirNode extends Node {
        String dir;
        SortedSet<Node> children;

        @Override
        public NodeType getNodeType() {
            return NodeType.DirNode;
        }
    }


}
