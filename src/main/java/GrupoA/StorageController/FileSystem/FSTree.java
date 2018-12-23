package GrupoA.StorageController.FileSystem;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FSTree implements Serializable {
    private DirNode root;
    private File journal;
    public Path journal_path;

    public long nextiNode = 1;

    private Hashtable<Long, Node> inodeTable = new Hashtable<Long, Node>();

    public FSTree() {
        root = new DirNode("/");
        root.iNode = getNextINode();
        inodeTable.put(root.iNode, root);
        journal = new File("FSTree_journal.txt");

        try {
            if (!journal.exists())
                if (!journal.createNewFile())
                    throw new IOException("Couldn't create log file");

            journal_path = Paths.get("FSTree_journal.txt");
            Files.write(journal_path, Collections.singleton("Logs started @ " + new Date()));
        } catch (IOException ignored) {
            System.err.println("Couldn't create log file\n");
        }
    }

    public DirNode getRoot() {
        return root;
    }

    public enum NodeType {
        FileNode,
        DirNode
    }

    /**
     * Gets the node that corresponds to the give path
     * @param path the path to search for
     * @return the node that corresponds to the path, if it doesn't exist returns null
     */
    public synchronized Node getNode(String path)  {
        if(path.equals("/"))
            return this.getRoot();

        String[] splittedPath  = path.split("/");
        Node currentNode = this.getRoot();
        for (int i = 0; i < splittedPath.length; ++i) {
            if(i == 0 && splittedPath[i].equals("")) //It's the rootNode
                continue;
            if(currentNode == null)
                return null;
            if(currentNode.getNodeType() == NodeType.FileNode && i != splittedPath.length - 1)
                return null;
            currentNode = ((DirNode)currentNode).getChild(splittedPath[i]);
        }

        return currentNode;
    }

    public synchronized Node getNode(long iNode) {
        System.out.println("Getting iNode " + iNode);
        System.out.println(this.inodeTable.get(iNode));
        return this.inodeTable.get(iNode);
    }

    private static String joinPathExceptLastN(String[] parts, int N) {
        String output = "";
        for(int i = 0; i < parts.length - N; ++i) {
            if(i == 0 && parts[i].equals("")) //It's the rootNode
                continue;
            output += "/" + parts[i];
        }
        return output;
    }

    /**
     * Creates the given directory
     * @param path the full path of the directory to create
     * @return true if the directory was created
     */
    public synchronized Boolean mkDir(String path) {
        try {
            Files.write(journal_path, Collections.singleton("FSTree.mkDir(\"" + path + "\")"));
        } catch (IOException ignored) {
            System.out.println("FSTree.mkDir(\"" + path + "\")");
            System.err.println("Couldn't write to log file");
        }

        // Can't create the root
        if (path.equals("/"))
            return false;

        String[] parts = path.split("/");
        String dirName = parts[parts.length - 1]; //TODO Pode have um path que termine em barra e isto da porcaria
        Node parent = this.getNode(joinPathExceptLastN(parts, 1));
        if(parent == null || parent.getNodeType() != NodeType.DirNode)
            return false;
        DirNode newDir = new DirNode(dirName);
        if(((DirNode)parent).getChild(dirName) != null)
            return false;

        this.addNode((DirNode)parent, newDir);
        return true;

    }

    /**
     * Removes an empty dir from the Tree
     * @param path the directory to remove
     * @return True if the directory was removed
     */
    public synchronized Boolean rmDir(String path) {
        try {
            Files.write(journal_path, Collections.singleton("FSTree.rmDir(\"" + path + "\")"));
        } catch (IOException ignored) {
            System.out.println("FSTree.rmDir(\"" + path + "\")");
            System.err.println("Couldn't write to log file");
        }

        // Can't remove the root
        if (path.equals("/"))
            return false;

        Node node = this.getNode(path);
        if(node == null || node.getNodeType() != NodeType.DirNode)
            return false;
        DirNode dirNode = (DirNode)node;
        if(dirNode.children.size() != 0) //Cannot remove non-empty dir
            return false;


        this.removeNode(dirNode.Parent, dirNode);
        return true;
    }

    /**
     * Creates a file
     * @param path the path where to create the file
     * @param fileSize the fileSize of the file
     * @param blocks the number of blocks that represent this file
     * @param hash the hash of the path?
     * @return true if the file was created
     */
    public synchronized Boolean mkFile(String path, int fileSize, int blocks, long hash) {
        try {
            Files.write(journal_path, Collections.singleton("FSTree.mkFile(\"" + path + "\", " + fileSize
                    + ", " + blocks + ", " + hash + ")"));
        } catch (IOException ignored) {
            System.out.println("FSTree.mkFile(\"" + path + "\", " + fileSize + ", " + blocks + ", " + hash + ")");
            System.err.println("Couldn't write to log file");
        }

        // Can't create the root
        if (path.equals("/"))
            return false;

        String[] parts = path.split("/");
        String fileName = parts[parts.length - 1]; //TODO Pode have um path que termine em barra e isto da porcaria
        Node parent = this.getNode(joinPathExceptLastN(parts, 1));
        if(parent == null || parent.getNodeType() != NodeType.DirNode)
            return false;
        FileNode newFile = new FileNode(fileName, hash, fileSize, blocks);
        if(((DirNode)parent).getChild(fileName) != null) // duplicate Entry
            return false;


        this.addNode((DirNode)parent, newFile);
        return true;
    }

    /**
     * Deletes the given file
     * @param path the absolute path of the file to the delete
     * @return true if file has been deleted, false if otherwise
     */
    public synchronized Boolean rmFile(String path) {
        try {
            Files.write(journal_path, Collections.singleton("FSTree.rmFile(\"" + path + "\")"));
        } catch (IOException ignored) {
            System.out.println("FSTree.rmFile(\"" + path + "\")");
            System.err.println("Couldn't write to log file");
        }

        if (path.equals("/"))
            return false;

        Node node = this.getNode(path);
        if(node == null || node.getNodeType() != NodeType.FileNode)
            return false;

        this.removeNode(node.Parent, node);
        return true;
    }


    /**
     * Creates a list of all files and directories in the given path
     * @param path to read from
     * @return the list of files and directories
     */
    public synchronized List<String> ls(String path) {
        try {
            Files.write(journal_path, Collections.singleton("FSTree.ls(" + path + ")"));
        } catch (IOException ignored) {
            System.out.println("FSTree.ls(\"" + path + "\")");
            System.err.println("Couldn't write to log file");
        }

        Node node = this.getNode(path);
        if(node == null)
            return null;

        List<String> output = new LinkedList<>();
        if(node.getNodeType() == NodeType.FileNode){
            output.add("File: " + node.nodeName);
            return output;
        }
        Collection<Node> children = ((DirNode)node).getChildren();
        for(Node _node : children) {
            if(_node.getNodeType() == NodeType.DirNode)
                output.add("Dir: " + _node.nodeName);
            else
                output.add("File: " + _node.nodeName);
        }
        return output;
    }


    private synchronized long getNextINode() {
        return this.nextiNode++;
    }

    private synchronized void addNode(DirNode parent, Node child) {
        child.iNode = getNextINode();
        this.inodeTable.put(child.iNode, child);
        parent.addChild(child);
    }

    private synchronized void removeNode(DirNode parent, Node child) {
        this.inodeTable.remove(child.iNode);
        parent.removeChild(child);
    }

    public static abstract class Node implements Comparable<Node>, Serializable {

        public long iNode = 0;
        public Byte UserPermissions = 7;
        public Byte GroupPermissions = 7;
        public Byte OthersPermissions = 7;
        public Long OwnerId = 1000L;
        public Long GroupId = 1000L;

        public abstract NodeType getNodeType();
        String nodeName;
        public DirNode Parent;

        public int compareTo(@Nullable Node node) {
            if (node == null) {
                return 1;
            }

            return nodeName.compareTo(node.nodeName);
        }

        public String getNodeName() {
            return nodeName;
        }

        public String getPath() {
            if(nodeName.equals("/"))
                return "/";
            if(Parent == null) //deleted nodes?
                return nodeName;
            if(this.Parent.nodeName.equals("/"))
                return "/" + this.nodeName;
            return this.Parent.getPath() + "/" + this.nodeName;
        }
    }

    public static class FileNode extends Node {
        Long hash;
        public Integer fileSize, blocks;

        int CrushMapVersion = 0;

        public enum RedundancyType {
            Erasue,
            Duplicate
        }

        public RedundancyType redundancy;

        public FileNode(String name, long hash, int fileSize, int blocks) {
            this.nodeName = name;
            this.hash = hash;
            this.fileSize = fileSize;
            this.blocks = blocks;
            this.redundancy = RedundancyType.Duplicate;
        }

        @Override
        public NodeType getNodeType() {
            return NodeType.FileNode;
        }

        public int getCrushMapVersion() {
            return this.CrushMapVersion;
        }

        public int setCrushMapVersion(int version) {
            return this.CrushMapVersion = version;
        }
    }

    public static class DirNode extends Node {
        HashMap<String, Node> children = new HashMap<>();

        public DirNode(String name) {
            this.nodeName = name;
        }

        @Override
        public NodeType getNodeType() {
            return NodeType.DirNode;
        }

        public Collection<Node> getChildren() {
            return this.children.values();
        }

        public Node getChild(String name) {
            return this.children.get(name);
        }

        public void addChild(Node node) {
            //TODO nao deixar nodes com /, nem nomes vazios
            Node existingNode = this.getChild(node.nodeName);

            if(existingNode!= null)
                throw new AttemptToAddDuplicateNodeException(node, existingNode);
            node.Parent = this;
            this.children.put(node.nodeName, node);
        }

        public void removeChild(Node node) {
            node.Parent = null;
            this.children.remove(node.nodeName);
        }
    }
}
