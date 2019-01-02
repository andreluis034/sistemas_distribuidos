package GrupoA.StorageController.RaftServices.CrushMap;

import GrupoA.AppServer.Routes.FileRoute;
import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.StorageController.Crush.CrushMap;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.FileSystem.FSTree;
import GrupoA.StorageController.RaftServices.CrushMap.Commands.CreateNewCrushMapService;
import GrupoA.StorageController.RaftServices.CrushMap.Commands.CrushMapCommand;
import GrupoA.StorageController.RaftServices.FileSystem.FileSystemService;
import GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse;
import GrupoA.StorageController.gRPCService.OSDListener.OSDDetails;
import org.jgroups.JChannel;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.Role;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.raft.RaftHandle;
import org.jgroups.util.Bits;
import org.jgroups.util.ByteArrayDataOutputStream;
import org.jgroups.util.Util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static GrupoA.AppServer.Routes.FileRoute.getWriteBlockDatas;

public class CrushMapService implements StateMachine, RAFT.RoleChange {
    private static CrushMapService service = null;
    protected JChannel ch;
    protected RaftHandle raft;
    protected long replyTimeout = 20 * 1000; // 20 seconds

    public boolean isLeader = false;
    public CrushMap latestMap;
    public long latestVersion = 0;
    public final HashMap<Long, CrushMap> mapOfMaps = new HashMap<>();

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
        latestMap = new CrushMap(latestVersion, new LinkedList<>());
        this.setChannel(ch);
    }

    public void setChannel(JChannel ch) {
        this.ch=ch;
        this.raft=new RaftHandle(this.ch, this);
        raft.addRoleListener(this);
    }

    protected <T> T invoke(CrushMapCommand<T> command) throws Exception {

        byte[] buffer = Util.objectToByteBuffer(command);
        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream(buffer.length);
        out.write(buffer);
        byte[] rsp = raft.set(out.buffer(), 0, out.position(), replyTimeout, TimeUnit.MILLISECONDS);

        return Util.objectFromByteBuffer(rsp);
    }

    @Override
    public byte[] apply(byte[] bytes, int offset, int length) throws Exception {
        try {
            CrushMapCommand command = Util.objectFromByteBuffer(bytes, offset, length);
            System.out.println(command.getClass());
            byte[] ret = Util.objectToByteBuffer(command.execute(this));
            command.journal(this);
            return ret;
        } catch(Exception e) {
            e.printStackTrace(); //TODO throw it?
            System.out.println("Returning 0 byte array");
        }
        return new byte[0];
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
            long key = Bits.readLong(dataInput);
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
        for (Map.Entry<Long, CrushMap> entry : mapOfMaps.entrySet()) {
            CrushMap value = entry.getValue();
            byte[] buf = Util.objectToByteBuffer(value);

            Bits.writeLong(entry.getKey(), dataOutput);
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

    public CrushMap addOSD(ObjectStorageDaemon OSD) {
        List<ObjectStorageDaemon> OSDs =  latestMap.getOSDsCopy();
        if (OSDs.contains(OSD)) {
            System.out.println("Attempted to add duplicate PG");
            return latestMap;
        }
        try {
            OSDs.add(OSD);
            CrushMap cm = this.createNewMap(OSDs);
            return cm;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return latestMap;
    }

    public CrushMap removeOSD(OSDDetails details) {

        List<ObjectStorageDaemon> osds = this.latestMap.getOSDsCopy();
        for (ObjectStorageDaemon osd : osds ) {
            if(osd.getHost().equals(details.getAddress()) && osd.getPort() == details.getPort()) {
                osds.remove(osd);
                break;
            }
        }
        try {
            CrushMap cm = this.createNewMap(osds);
            return cm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latestMap;
    }

    public CrushMap createNewMap(List<ObjectStorageDaemon> OSDs) throws Exception {
        return invoke(new CreateNewCrushMapService(OSDs)) ;
    }


    public synchronized ObjectStorageDaemon getNewOSD(List<ObjectStorageDaemon> oldOSDs, List<ObjectStorageDaemon> newOSDs) {
        for (ObjectStorageDaemon newOSD : newOSDs) {
            if(oldOSDs.contains(newOSD))
                return newOSD;
        }
        return null;
    }

    public synchronized void updateFilesLocations(CrushMap newCrushMap) throws Exception {
        CrushMapResponse new_cmr = newCrushMap.toCrushMapResponse();
        List<FSTree.FileNode> files = FileSystemService.getInstance().getFsTree().getAllFileNodes();

        // Recreate file blocks and check their hashes
        for (FSTree.FileNode file : files) { //TODO lock files
            System.out.println("Processing " + file.getPath());
            if(file.fileSize == 0)
                continue;
            if(!mapOfMaps.containsKey(file.getCrushMapVersion()) && file.fileSize != 0){
                System.out.println("[Couldn't migrate]Missing crushmap with version " + file.getCrushMapVersion());
                continue;
            }
            CrushMapResponse old_cmr = mapOfMaps.get(file.getCrushMapVersion()).toCrushMapResponse();
            //TODO handle jerasure
            List<FileRoute.WriteBlockData> datas = getWriteBlockDatas(file.getPath(), 0, file.fileSize,
                    file.getRedundancy());

            for (FileRoute.WriteBlockData wbd : datas){
                wbd.read(old_cmr);
                wbd.tryDeleteAllIndividually(old_cmr);
                wbd.write(new_cmr);
                //Easier to delete all and write them again... to replicate files to new OSD and etc
                /*
                CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto
                        old = wbd.getOSD(old_cmr);
                CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto
                        newOSD = wbd.getOSD(new_cmr);

                if(!old.getAddress().equals(newOSD.getAddress())) { //TODO if a PG got more OSDs replicate stuff

                }*/
            }
            FileSystemService.getInstance().setCrushMapVersion(file.getPath(), new_cmr.getVersion());
        }
    }

    @Override
    public void roleChanged(Role role) {
        System.out.println("[CrushMapService]-> Changed role to " + role);

        if (role.equals(Role.Leader)) {
            System.out.println("I'm the leader");
            isLeader = true;
            monitor();
        } else {
            isLeader = false;
        }
    }

    private void monitor() {
        Timer timer = new Timer();
        System.out.println("Starting monitor");
        timer.schedule(new TimerTask() {
            public void run() {
                if (!isLeader){
                    timer.cancel();
                    return;
                }

                System.out.println("Checking up OSDs");
                List<ObjectStorageDaemon> offlineOSDs = new LinkedList<>();

                for (ObjectStorageDaemon osd : latestMap.getOSDsCopy()) {
                    String[] split = osd.getAddress().split(":");
                    OSDClient client = new OSDClient(split[0], Integer.parseInt(split[1]));
                    if (!client.ping()) {
                        offlineOSDs.add(osd);
                    }
                    try {
                        client.shutdown();
                        client.awaitTermination();
                    } catch (InterruptedException e) {

                    }
                }

                if (!offlineOSDs.isEmpty()) {
                    List<ObjectStorageDaemon> newOSDs = latestMap.getOSDsCopy();
                    newOSDs.removeAll(offlineOSDs);

                    try {
                        createNewMap(newOSDs);
                    } catch (Exception e) {
                        try {
                            Files.write(Paths.get(latestMap.journal_path),
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
