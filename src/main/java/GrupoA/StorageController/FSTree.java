package GrupoA.StorageController;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

public class FSTree {
    private DirNode root;

    public FSTree() {
        root = new DirNode("/");
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
            this.children = new TreeSet<>();
        }

        @Override
        public NodeType getNodeType() {
            return NodeType.DirNode;
        }

        public SortedSet<Node> getChildren() {
            return this.children;
        }

        public void setChildren(SortedSet<Node> children) {
            this.children = children;
        }
    }

    public synchronized Boolean mkDir(String path) {
        System.out.println("FSTree.mkDir(" + path + ")");

        // Can't create the root
        if (path.compareTo("/") == 0) {
            return false;
        }
        int flag;
        String[] parts = path.split("/");

        System.out.println("Parts:"+parts.length);
        for (String part : parts) {
            System.out.println("Part: " + part);
        }
        FSTree.DirNode currentNode = this.getRoot();

        for (int i = 0; i < parts.length; i++) {
            System.out.println("Starting mkDir loop "+i);
            String part = parts[i];
            System.out.println("For '"+part+"'");
            if(part.equals(""))
                continue;
            boolean foundDirectory = false;
            SortedSet<FSTree.Node> set = currentNode.getChildren();
            System.out.println(set);

            for (FSTree.Node node : set) {
                System.out.println("node: " +node);
                if (part.equals(node.getNodeName())) {
                    //somewhere a long the path we found a file that
                    //was assumed to be a directory
                    if (node.getNodeType() != FSTree.NodeType.DirNode)
                        return false;

                    // There already is a node with that path
                    if (i == parts.length - 1)
                        return false;

                    currentNode = (FSTree.DirNode)node;
                    foundDirectory = true;
                    break;
                }
            }

            // It only enters this 'if' if a part of the path doesn't exist;
            // or it was able to reach the final part of the path,
            // which means it is ready to create the directory.
            System.out.println("foundDirectory: " + foundDirectory);
            if (!foundDirectory) {

                // Everything was OK
                System.out.println("i: " + i);
                System.out.println("parts.length: " + i);
                if (i == parts.length - 1) {
                    System.out.println("Finally creating: " + part);
                    FSTree.DirNode newNode = new FSTree.DirNode(part);
                    set.add(newNode);
                    System.out.println("Set: ");
                    System.out.println(set);
                    //currentNode.setChildren(set);

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
        System.out.println("FSTree.rmDir(" + path + ")");

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
        System.out.printf("FSTree.mkFile(%s, %d, %d, %l)\n", path, fileSize, blocks, hash);

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
        System.out.println("FSTree.rmFile(" + path + ")");

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
        System.out.println("FSTree.ls(" + path + ")");

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
