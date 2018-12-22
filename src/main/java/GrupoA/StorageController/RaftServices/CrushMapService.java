package GrupoA.StorageController.RaftServices;

import GrupoA.StorageController.Crush.CrushMap;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import org.jgroups.JChannel;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.Role;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.raft.RaftHandle;
import org.jgroups.util.Util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CrushMapService implements StateMachine, RAFT.RoleChange {
    private static CrushMapService service = null;
    protected JChannel ch;
    protected RaftHandle raft;
    protected long replyTimeout = 20 * 1000; // 20 seconds

    private CrushMap latestMap;
    private int latestVersion;
    private final HashMap<Integer, CrushMap> mapOfMaps = new HashMap<>();

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
        List<ObjectStorageDaemon> OSDs = Util.objectFromByteBuffer(bytes, offset, length);

        latestVersion++;
        latestMap = new CrushMap(latestVersion, OSDs);
        mapOfMaps.put(latestVersion, latestMap);

        try {
            Files.write(latestMap.journal_path,
                    Collections.singleton("Created new CRUSH map, version " + latestVersion
                            + ", with " + OSDs.size() + " OSDs\n"));
        } catch (IOException ignored) {
            System.out.println("Created new CRUSH map, version " + latestVersion + "\n");
            System.err.println("Couldn't write to log file\n");
        }

        return Util.objectToByteBuffer(latestMap);
    }

    protected Object invoke(List<ObjectStorageDaemon> OSDs) throws Exception {
        byte[] buf = Util.objectToByteBuffer(OSDs);
        byte[] rsp = raft.set(buf, 0, buf.length, replyTimeout, TimeUnit.MILLISECONDS);

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

    public ICrushMap createNewMap(List<ObjectStorageDaemon> OSDs) throws Exception {
        return (ICrushMap)invoke(OSDs);
    }

    @Override
    public void roleChanged(Role role) {
        System.out.println("[CrushMapService]-> Changed role to " + role);
    }
}
