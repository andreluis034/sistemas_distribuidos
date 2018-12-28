package GrupoA.StorageController.FileSystem;

import GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto;
import GrupoA.Utility.Jenkins;

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
    public Path journal_path;

    public long nextiNode = 1;

    private Hashtable<Long, Node> inodeTable = new Hashtable<Long, Node>();

    public FSTree() {
        root = new DirNode("/");
        root.iNode = getNextINode();
        inodeTable.put(root.iNode, root);
        File journal = new File("FSTree_journal.txt");

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
     * @param uid the uid of the file
     * @param gid the group of the file
     * @param permission the permissions of the file
     * @return true if the directory was created
     */
    public synchronized Boolean mkDir(String path, long uid, long gid, long creationTime, long permission) {
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

        newDir.UserId = uid;
        newDir.GroupId = gid;
        this.addNode((DirNode)parent, newDir);
        newDir.UserPermissions = (byte)((permission & (0x7 << 6)) >> 6);
        newDir.GroupPermissions = (byte)((permission & (0x7 <<3)) >> 3);
        newDir.OthersPermissions = (byte)((permission & 0x007) >> 0);
        newDir.creationTime = creationTime;
        newDir.modifiedTime = creationTime;
        return true;

    }

    /**
     * Removes an empty dir from the Tree
     * @param path the directory to remove
     * @return Linux System Errors
     */
    public synchronized Integer rmDir(String path) {
        try {
            Files.write(journal_path, Collections.singleton("FSTree.rmDir(\"" + path + "\")"));
        } catch (IOException ignored) {
            System.out.println("FSTree.rmDir(\"" + path + "\")");
            System.err.println("Couldn't write to log file");
        }

        // Can't remove the root
        if (path.equals("/"))
            return -1; // Operation not permited

        Node node = this.getNode(path);
        if(node == null)
            return -2; //No such file or dir
        if(node.getNodeType() != NodeType.DirNode)
            return -20; //Not a directory
        DirNode dirNode = (DirNode)node;
        if(dirNode.children.size() != 0) //Cannot remove non-empty dir
            return -39;


        this.removeNode(dirNode.Parent, dirNode);
        return 0;
    }

    /**
     * Creates a file
     * @param path the path where to create the file
     * @param uid the uid the file belongs to
     * @param gid the gid the file belongs to
     * @param permission the permissions of the file
     * @return true if the file was created
     */
    public synchronized Boolean mkFile(String path, long uid, long gid, long permission, long creationTime, FileNode.Redundancy red) {
        try {
            Files.write(journal_path, Collections.singleton("FSTree.mkFile(\"" + path + "\"" + ")"));
        } catch (IOException ignored) {
            System.out.println("FSTree.mkFile(\"" + path + "\"" + ")");
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
        FileNode newFile = new FileNode(fileName);
        if(((DirNode)parent).getChild(fileName) != null) // duplicate Entry
            return false;

        newFile.UserId = uid;
        newFile.GroupId = gid;
        this.addNode((DirNode)parent, newFile);
        ((FileNode)newFile).RedundancyType = red;
        newFile.creationTime = creationTime;
        newFile.modifiedTime = creationTime;
        newFile.UserPermissions = (byte)((permission & (0x7 << 6)) >> 6);
        newFile.GroupPermissions = (byte)((permission & (0x7 <<3)) >> 3);
        newFile.OthersPermissions = (byte)((permission & 0x007) >> 0);
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
        public byte UserPermissions = 7;
        public byte GroupPermissions = 7;
        public byte OthersPermissions = 7;
        public long UserId = 1000L;
        public long GroupId = 1000L;

        public long accessTime = System.currentTimeMillis();
        public long creationTime = System.currentTimeMillis();
        public long modifiedTime = System.currentTimeMillis();

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

        public long getParentINode () {
            if (Parent == null)
                return 0;
            return Parent.iNode;
        }
    }

    public static class FileNode extends Node {
        Long hash;
        public Long fileSize, blocks;

        int CrushMapVersion = 0; //TODO set this


        public FileNode(String name) {
            this.nodeName = name;
            this.hash = Jenkins.hash64(name.getBytes());
            this.fileSize = 0L;
            this.blocks = 0L;
        }

        public static enum Redundancy {
            ForwardErrorCorrection,
            Replication,
            None // Use at your own risk
            ;
            public static Redundancy fromProto(RedundancyProto proto) {
                switch (proto){

                    case ForwardErrorCorrection:
                        return ForwardErrorCorrection;
                    case Replication:
                        return Replication;
                    case None:
                        return None;
                    case UNRECOGNIZED:
                       return null;
                }
                return null;
            }

            public RedundancyProto toProto() {
                switch (this){

                    case ForwardErrorCorrection:
                        return RedundancyProto.ForwardErrorCorrection;
                    case Replication:
                        return RedundancyProto.Replication;
                    case None:
                        return RedundancyProto.None;
                }
                return RedundancyProto.UNRECOGNIZED;
            }
        }

        public Redundancy RedundancyType = Redundancy.Replication;

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
