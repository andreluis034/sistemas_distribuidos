package GrupoA.StorageController.JGroupsRaft;

import GrupoA.StorageController.FSTree;
import org.jgroups.Channel;
import org.jgroups.JChannel;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.Role;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.raft.RaftHandle;

import java.io.*;
import java.util.LinkedList;

public class FileSystemService implements StateMachine, RAFT.RoleChange {
    protected JChannel ch;
    protected RaftHandle raft;
    public FSTree fsTree;
    protected long replyTimeout = 20 * 1000; // 20 seconds
    public FileSystemService(JChannel ch){
        this.setChannel(ch);
    }

    protected enum Command {mkDir, rmDir, mkFile, rmFile, ls}

    public boolean mkDir(String path) {
        return true;
    }

    public boolean rmDir(String path) {
        return true;
    }

    public boolean mkFile(String path) {
        return true;
    }

    public boolean rmFile(String path) {
        return true;
    }

    public LinkedList<String> ls(String path) {
        return new LinkedList<>();
    }

    public void setChannel(JChannel ch) {
        this.ch=ch;
        this.raft=new RaftHandle(this.ch, this);
        raft.addRoleListener(this);
    }
    @Override
    public byte[] apply(byte[] bytes, int i, int i1) throws Exception {
        return new byte[0];
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

    }
}
