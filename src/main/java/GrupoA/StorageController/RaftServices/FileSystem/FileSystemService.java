package GrupoA.StorageController.RaftServices.FileSystem;

import GrupoA.StorageController.FileSystem.FSTree;
import GrupoA.StorageController.RaftServices.FileSystem.Commands.*;
import GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute;
import org.jgroups.JChannel;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.Role;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.raft.RaftHandle;
import org.jgroups.util.*;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileSystemService implements StateMachine, RAFT.RoleChange {
    private JChannel ch;
    private RaftHandle raft;
    private FSTree fsTree = new FSTree();
    private final long replyTimeout = 20 * 1000; // 20 seconds

    private static FileSystemService service = null;
    public synchronized static FileSystemService getInstance() {
        return service;
    }
    public synchronized static FileSystemService getInstance(String config, String raftId) throws Exception {

        if(service == null) {
            JChannel ch = new JChannel(config).name(raftId);
            service = new FileSystemService(ch);
            ch.connect("FSTreeCluster0");
        }
        return service;
    }

    private FileSystemService(JChannel ch){
        this.setChannel(ch);
    }

    private void setChannel(JChannel ch) {
        this.ch=ch;
        this.raft=new RaftHandle(this.ch, this);
        raft.addRoleListener(this);
    }

    protected enum Command {mkDir, rmDir, mkFile, rmFile, ls, getINode}

    public boolean mkDir(String path, long mode, long uid, long gid, long permission, long creation) throws Exception {
        return invoke(new MkDirCommand(path, mode, uid, gid, permission, creation));
    }

    public boolean mkFile(String path, long mode, long uid, long gid, long permission,
                          long creation, FSTree.FileNode.Redundancy redundancy) throws Exception {
        return invoke(new MkFileCommand(path, mode, uid, gid, permission, creation, redundancy));
    }

    public int removeDirectory(String path) throws Exception {
        return this.invoke(new RemoveDirCommand(path));
    }

    public int removeFile(String path) throws Exception {
        return this.invoke(new RemoveFileCommand(path));
    }

    public boolean updateAttribute(String path, UpdateAttribute update) throws Exception {
        return this.invoke(new UpdateAttributeCommand(path, update));
    }

    public List<String> ls(String path) throws Exception {
        return (List<String>)this.invoke(new LsCommand(path));
    }

    public List<FSTree.Node> readDir(String path) throws Exception  {
        return this.invoke(new ReadDirCommand(path));
    }

    public FSTree.Node getNode(long iNode) throws Exception {
        GetNodeCommand command = new GetNodeCommand(iNode);
        FSTree.Node node = (FSTree.Node) this.invoke(command);
        return node;
    }

    //TODO allow 'Dirty' Reads
    public FSTree.Node getNode(String path) throws Exception {
        System.out.println("Getting: " + path);
        GetNodeCommand command = new GetNodeCommand(path);
        FSTree.Node node = (FSTree.Node) this.invoke(command);
        return node;
    }

    public boolean TryGetLock(String path, long id, long time) throws Exception {
        return invoke(new TryLockCommand(path, id, time));
    }

    public boolean  TryReleaseLock(String path, long id, boolean force) throws Exception {
        return invoke(new TryReleaseLockCommand(path, id, force));
    }

    @Override
    public byte[] apply(byte[] bytes, int offset, int length) throws Exception {
        try {
            FileSystemCommand<?> command = Util.objectFromByteBuffer(bytes, offset, length);
            System.out.println(command.getClass());
            Object result = command.execute(fsTree);
            command.journal(fsTree, result);
            return Util.objectToByteBuffer(result);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    protected <T> T invoke(FileSystemCommand<T> command) throws Exception {

        byte[] buffer = Util.objectToByteBuffer(command);
        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream(buffer.length);
        out.write(buffer);
        byte[] rsp = raft.set(out.buffer(), 0, out.position(), replyTimeout, TimeUnit.MILLISECONDS);
        return (T)Util.objectFromByteBuffer(rsp);
    }

    @Override
    public void readContentFrom(DataInput dataInput) throws Exception {
        int arrayLength = dataInput.readInt();
        byte[] byteArray = new byte[arrayLength];

        dataInput.readFully(byteArray);
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        ObjectInput in = new ObjectInputStream(bis);

        fsTree = (FSTree)in.readObject();
    }

    @Override
    public void writeContentTo(DataOutput dataOutput) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(fsTree);
        out.close();

        byte[] byteArray = bos.toByteArray();

        dataOutput.write(byteArray.length);
        dataOutput.write(byteArray);
    }

    @Override
    public void roleChanged(Role role) {
        System.out.println("[FileSystemService]-> Changed role to " + role);
    }
}
