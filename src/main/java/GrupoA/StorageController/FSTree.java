package GrupoA.StorageController;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.SortedSet;

public class FSTree {
    private DirNode root;

    public FSTree() {
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

        public FileNode(String name, long hash, int fileSize, int blocks) {
            this.nodeName = name;
            this.hash = hash;
            this.fileSize = fileSize;
            this.blocks = blocks;
        }

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
    }

    public synchronized Boolean mkDir(String path) {
        // Can't create the root
        if (path.compareTo("/") == 0) {
            return false;
        }

        int flag;
        String[] parts = path.split("/");

        FSTree.DirNode currentNode = this.getRoot();
        SortedSet<FSTree.Node> set = currentNode.getChildren();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            flag = 0;

            for (FSTree.Node node : set) {
                if (part.compareTo(node.getNodeName()) == 0) {
                    if (node.getNodeType() != FSTree.NodeType.DirNode)
                        return false;

                    // There already is a node with that path
                    if (i == parts.length - 1)
                        return false;

                    currentNode = (FSTree.DirNode)node;
                    set = currentNode.getChildren();
                    flag = 1;

                    break;
                }
            }

            // It only enters this 'if' if a part of the path doesn't exist;
            // or it was able to reach the final part of the path,
            // which means it is ready to create the directory.
            if (flag == 0) {

                // Everything was OK
                if (i == parts.length - 1) {
                    FSTree.DirNode newNode = new FSTree.DirNode(part);
                    set.add(newNode);
                    currentNode.setChildren(set);

                    return true;
                }

                // Couldn't create the directory, as there is
                // something wrong with the provided path.
                break;
            }
        }

        return false;
    }

    public synchronized Boolean rmDir(String path) {
        // Can't remove the root
        if (path.compareTo("/") == 0) {
            return false;
        }

        int flag;
        String[] parts = path.split("/");

        FSTree.DirNode currentNode = this.getRoot();
        SortedSet<FSTree.Node> set = currentNode.getChildren();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            flag = 0;

            for (FSTree.Node node : set) {
                if (part.compareTo(node.getNodeName()) == 0) {
                    if (node.getNodeType() != FSTree.NodeType.DirNode)
                        return false;

                    if (i == parts.length - 1) {
                        set.remove(node);
                        currentNode.setChildren(set);

                        return true;
                    }

                    currentNode = (FSTree.DirNode)node;
                    set = currentNode.getChildren();
                    flag = 1;

                    break;
                }
            }

            // It only enters this 'if' if a part of the path doesn't exist;
            if (flag == 0) {
                break;
            }
        }

        return false;
    }

    public synchronized Boolean mkFile(String path, int fileSize, int blocks, long hash) {
        // Can't create the root
        if (path.compareTo("/") == 0) {
            return false;
        }

        int flag;
        String[] parts = path.split("/");

        FSTree.DirNode currentNode = this.getRoot();
        SortedSet<FSTree.Node> set = currentNode.getChildren();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            flag = 0;

            for (FSTree.Node node : set) {
                if (part.compareTo(node.getNodeName()) == 0) {
                    // There already is a node with that path
                    if (i == parts.length - 1)
                        return false;

                        // Somewhere in the path, the node isn't a DirNode
                    else if (node.getNodeType() != FSTree.NodeType.DirNode)
                        return false;

                    currentNode = (FSTree.DirNode)node;
                    set = currentNode.getChildren();
                    flag = 1;

                    break;
                }
            }

            // It only enters this 'if' if either a part of the path doesn't exist;
            // or it was able to reach the final part of the path,
            // which means it is ready to create the file
            if (flag == 0) {

                // Everything was OK
                if (i == parts.length - 1) {
                    FSTree.FileNode newNode = new FSTree.FileNode(part, hash, fileSize, blocks);
                    set.add(newNode);
                    currentNode.setChildren(set);

                    return true;
                }

                break;
            }
        }

        return false;
    }

    public synchronized Boolean rmFile(String path) {
        // Can't remove the root
        if (path.compareTo("/") == 0) {
            return false;
        }

        int flag;
        String[] parts = path.split("/");

        FSTree.DirNode currentNode = this.getRoot();
        SortedSet<FSTree.Node> set = currentNode.getChildren();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            flag = 0;

            for (FSTree.Node node : set) {
                if (part.compareTo(node.getNodeName()) == 0) {
                    if (i == parts.length - 1) {
                        // Can't remove DirNodes, only FileNodes
                        if (node.getNodeType() == FSTree.NodeType.DirNode)
                            return false;

                        set.remove(node);
                        currentNode.setChildren(set);

                        return true;
                    }

                    if (node.getNodeType() != FSTree.NodeType.DirNode)
                        return false;

                    currentNode = (FSTree.DirNode)node;
                    set = currentNode.getChildren();
                    flag = 1;

                    break;
                }
            }

            // It only enters this 'if' if a part of the path doesn't exist;
            if (flag == 0) {
                break;
            }
        }

        return false;
    }

    private LinkedList<String> getNodeSetForLs(SortedSet<FSTree.Node> set) {
        LinkedList<String> list = new LinkedList<>();

        for (FSTree.Node node : set) {
            if (node.getNodeType() == FSTree.NodeType.DirNode)
                list.add("dir:" + node.getNodeName());
            else
                list.add("file:" + node.getNodeName());
        }

        return list;
    }

    public synchronized LinkedList<String> ls(String path) {
        FSTree.DirNode currentNode = this.getRoot();
        SortedSet<FSTree.Node> set = currentNode.getChildren();

        if (path.equals("/")) {
            return getNodeSetForLs(set);
        } else {
            int flag;
            String[] parts = path.split("/");

            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                flag = 0;

                for (FSTree.Node node : set) {
                    if (part.compareTo(node.getNodeName()) == 0) {
                        if (i == parts.length - 1) {
                            currentNode = (FSTree.DirNode)node;
                            set = currentNode.getChildren();

                            return getNodeSetForLs(set);
                        }

                        if (node.getNodeType() != FSTree.NodeType.DirNode)
                            return null;

                        currentNode = (FSTree.DirNode)node;
                        set = currentNode.getChildren();
                        flag = 1;

                        break;
                    }
                }

                // It only enters this 'if' if a part of the path doesn't exist;
                if (flag == 0) {
                    break;
                }
            }
        }

        return null;
    }
}
