package GrupoA.StorageController.RaftServices;

import org.jgroups.JChannel;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.Role;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.raft.RaftHandle;
import org.jgroups.util.AsciiString;
import org.jgroups.util.Bits;
import org.jgroups.util.ByteArrayDataOutputStream;
import org.jgroups.util.Util;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class CrushMapService implements StateMachine, RAFT.RoleChange {
    private static CrushMapService service = null;
    protected JChannel ch;
    protected RaftHandle raft;
    protected long replyTimeout = 20 * 1000; // 20 seconds

    protected enum Command {leaderOSDofPG, }

    private ICrushMap latestMap;
    private final HashMap<Integer, ICrushMap> mapOfCrushMaps = new HashMap<>();
    private int nextVersion = 0;
    public synchronized static CrushMapService getInstance() {
        return service;
    }
    public synchronized static CrushMapService getInstance(String config, String raftId) throws Exception {
        if(service == null) {
            JChannel ch = new JChannel(config).name(raftId);
            service = new CrushMapService(ch);
            ch.connect("CrushMapCluster");
        }
        return service;
    }

    private CrushMapService(JChannel ch){
        this.setChannel(ch);
    }

    public void setChannel(JChannel ch) {
        this.ch=ch;
        this.raft=new RaftHandle(this.ch, this);
        raft.addRoleListener(this);
    }

    @Override
    public byte[] apply(byte[] bytes, int offset, int length) throws Exception {
        return new byte[0];
    }

    public int getLeaderOsdOfPg(int pg) throws Exception {
        return (int)invoke(pg);
    }

    protected Object invoke(int pg) throws Exception {
        // Size: sizeof(int)(= 4) + 1
        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream(5);
        try {
            out.writeByte(pg);
        } catch(Exception ex) {
            throw new Exception("Serialization failure (PgID = " + pg + ")");
        }

        byte[] buf = out.buffer();
        byte[] rsp = raft.set(buf, 0, out.position(), replyTimeout, TimeUnit.MILLISECONDS);

        return Util.objectFromByteBuffer(rsp);
    }

    @Override
    public void readContentFrom(DataInput dataInput) throws Exception {

    }

    @Override
    public void writeContentTo(DataOutput dataOutput) throws Exception {

    }

    public ICrushMap getMap(int version) {
        return this.mapOfMaps.get(version);
    }

    public ICrushMap getLatestMap() {
        return this.latestMap;
    }

    @Override
    public void roleChanged(Role role) {
        System.out.println("[CrushMapService]-> Changed role to " + role);
    }
}
