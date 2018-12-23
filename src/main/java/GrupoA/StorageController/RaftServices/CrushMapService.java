package GrupoA.StorageController.RaftServices;

import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.StorageController.Crush.CrushMap;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.Crush.PlacementGroup;
import org.jgroups.JChannel;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.Role;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.raft.RaftHandle;
import org.jgroups.util.Bits;
import org.jgroups.util.Util;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CrushMapService implements StateMachine, RAFT.RoleChange {
    private static CrushMapService service = null;
    protected JChannel ch;
    protected RaftHandle raft;
    protected long replyTimeout = 20 * 1000; // 20 seconds

    private boolean isLeader = false;
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
        int messageSize = dataInput.readInt();
        for (int i = 0; i < messageSize; i++) {
            int key = Bits.readInt(dataInput);
            int objectSize = Bits.readInt(dataInput);

            byte[] buf = new byte[objectSize];
            dataInput.readFully(buf);
            CrushMap value = Util.objectFromByteBuffer(buf);

            mapOfMaps.put(key, value);
        }
    }

    @Override
    public void writeContentTo(DataOutput dataOutput) throws Exception {
        dataOutput.write(mapOfMaps.size());
        for (Map.Entry<Integer, CrushMap> entry : mapOfMaps.entrySet()) {
            CrushMap value = entry.getValue();
            byte[] buf = Util.objectToByteBuffer(value);

            Bits.writeInt(entry.getKey(), dataOutput);
            Bits.writeInt(buf.length, dataOutput);
            dataOutput.write(buf);
        }
    }

    public CrushMap getMap(int version) {
        return this.mapOfMaps.get(version);
    }

    public CrushMap getLatestMap() {
        return this.latestMap;
    }

    public CrushMap createNewMap(List<ObjectStorageDaemon> OSDs) throws Exception {
        return (CrushMap)invoke(OSDs);
    }

    @Override
    public void roleChanged(Role role) {
        System.out.println("[CrushMapService]-> Changed role to " + role);

        if (role == Role.Leader) {
            isLeader = true;
            monitor();
        } else {
            isLeader = false;
        }
    }

    private void monitor() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                if (!isLeader)
                    timer.cancel();

                List<ObjectStorageDaemon> offlineOSDs = new LinkedList<>();

                for (ObjectStorageDaemon osd : latestMap.getOSDs()) {
                    String[] split = osd.getAddress().split(":");
                    OSDClient client = new OSDClient(split[0], Integer.parseInt(split[1]));
                    if (!client.ping()) {
                        offlineOSDs.add(osd);
                    }
                }

                if (!offlineOSDs.isEmpty()) {
                    List<ObjectStorageDaemon> newOSDs = latestMap.getOSDs();
                    newOSDs.removeAll(offlineOSDs);

                    try {
                        createNewMap(newOSDs);
                    } catch (Exception e) {
                        try {
                            Files.write(latestMap.journal_path,
                                    Collections.singletonList("Couldn't create new CRUSH map:\n" + e.toString()));
                        } catch (IOException e1) {
                            System.out.println("Couldn't create new CRUSH map:" + e.toString());
                            System.err.println("Couldn't write to log file\n");
                        }
                    }
                }
            }
        }, 0, replyTimeout);
    }
}
