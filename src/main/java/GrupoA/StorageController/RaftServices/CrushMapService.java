package GrupoA.StorageController.RaftServices;

import org.jgroups.JChannel;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.Role;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.raft.RaftHandle;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.HashMap;

public class CrushMapService implements StateMachine, RAFT.RoleChange {

    private static CrushMapService service = null;
    protected JChannel ch;
    protected RaftHandle raft;

    private ICrushMap latestMap;
    private final HashMap<Integer, ICrushMap> mapOfMaps = new HashMap<>();

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
    public byte[] apply(byte[] bytes, int i, int i1) throws Exception {
        return new byte[0];
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
