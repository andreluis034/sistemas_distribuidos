package GrupoA.StorageController.JGroupsRaft;

import org.jgroups.Channel;
import org.jgroups.JChannel;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.Role;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.raft.RaftHandle;

import java.io.DataInput;
import java.io.DataOutput;

public class FileSystemService implements StateMachine, RAFT.RoleChange {
    protected JChannel ch;
    protected RaftHandle raft;
    protected long replyTimeout = 20 * 1000; // 20 seconds
    public FileSystemService(JChannel ch){
        this.setChannel(ch);
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

    }

    @Override
    public void writeContentTo(DataOutput dataOutput) throws Exception {

    }

    @Override
    public void roleChanged(Role role) {

    }
}
