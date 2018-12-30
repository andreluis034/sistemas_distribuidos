package GrupoA.StorageController.Crush;

import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;
import GrupoA.StorageController.RaftServices.CrushMap.ICrushMap;
import GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CrushMap implements ICrushMap, Serializable {
    // Default replication value
    private static final int REPLICATION = 2; //TODO move this to config

    private long version = 0;
    private List<ObjectStorageDaemon> OSDs = new LinkedList<>();
    private List<PlacementGroup> PGs = new LinkedList<>();
    public String journal_path = "";

    private void createJournal() {
        File journal = new File("CRUSH_journal_" + version + ".txt");

        try {
            if (!journal.exists())
                if (!journal.createNewFile())
                    throw new IOException("Couldn't create log file");

            journal_path = ("CRUSH_journal.txt");
            Files.write(Paths.get(journal_path), Collections.singleton("Logs started @ " + new Date()));
        } catch (IOException ignored) {
            System.err.println("Couldn't create log file\n");
        }
    }

    public CrushMap(long version, List<ObjectStorageDaemon> OSDs) {
        this.version = version;
        this.createJournal();
        this.OSDs = OSDs;
        System.out.println(OSDs.size());
        if(OSDs.size() == 0)
            return;

        ArrayList<ObjectStorageDaemon> copiedOSDs = new ArrayList<>(OSDs.size());
        //make a copy of the OSD so that we don't change OSDs from previous maps
        for (ObjectStorageDaemon osd : OSDs) {
            copiedOSDs.add(new ObjectStorageDaemon(osd));
        }
        this.OSDs = copiedOSDs;
        int numberOfOSDs = copiedOSDs.size();
        int leftover = numberOfOSDs % REPLICATION;
        // Calculate PGs, based on the OSDs
        int pgCount = (int)Math.ceil(((double) copiedOSDs.size())/ REPLICATION);
        for (int i = 0; i < pgCount; i++) {
            List<ObjectStorageDaemon> OSDsOfPG = new ArrayList(copiedOSDs.subList((i) * REPLICATION,
                    Math.min((i+1) * REPLICATION, copiedOSDs.size())));
            System.out.println(OSDsOfPG.size());
            OSDsOfPG.get(0).isLeader = true; //Set one to leader
            PlacementGroup pg = new PlacementGroup(i, OSDsOfPG);
            PGs.add(pg);
        }
        if(leftover != 0 && PGs.size() > 1){
            // Insert the remaining OSDs into a PG, increasing the replication of that PG
            PlacementGroup pgToRemove = PGs.remove(PGs.size() - 1);

            List<ObjectStorageDaemon> newOSDList = pgToRemove.getOSDs();
            newOSDList.addAll(copiedOSDs.subList(numberOfOSDs - leftover, numberOfOSDs));

            PlacementGroup pgToInsert = new PlacementGroup(pgToRemove.getPgID(), newOSDList);
            PGs.add(pgToInsert);
        }
        for (PlacementGroup pg : PGs) {
            pg.fixBelongingPGs();
        }

    }

    public List<PlacementGroup> getPGs() {
        return this.PGs;
    }

    public List<ObjectStorageDaemon> getOSDsCopy() {
        List<ObjectStorageDaemon> osds = new ArrayList<>(this.OSDs);
        return osds; // Return a list that can be modified by anyone without affecting the crushmap
    }

    @Override
    public long getVersion() {
        return this.version;
    }

    @Override
    public long getPgForHash(long hash) {
        return Math.abs(hash % PGs.size());
    }

    /**
     * Choose the OSD with the highest hash value of its address
     * @param pgID ID of the placement group
     * @return The address of the leader OSD
     */
    @Override
    public String getLeaderOsdOfPg(int pgID) {
        long leaderOSD_hash = -1;
        String leaderOSD_address = "";

        for (PlacementGroup pg : PGs) {
            if (pg.getPgID() == pgID) {
                for (ObjectStorageDaemon osd : pg.getOSDs()) {
                    long osdHash = GrupoA.Utility.Jenkins.hash64(osd.getAddress().getBytes());
                    if (leaderOSD_hash == -1) {
                        leaderOSD_hash = osdHash;
                        leaderOSD_address = osd.getAddress();
                    }
                    else if (osdHash > leaderOSD_hash) {
                        leaderOSD_hash = osdHash;
                        leaderOSD_address = osd.getAddress();
                    }
                }
            }
        }

        // Need to check if address is an empty string
        return leaderOSD_address;
    }

    public CrushMapResponse toCrushMapResponse() {
        CrushMapResponse.Builder builder = CrushMapResponse.newBuilder().setVersion(-1);
        try {
            builder.setVersion(this.getVersion());
            for (PlacementGroup pg : this.getPGs()) {
                CrushMapResponse.PlacementGroupProto.Builder pgBuilder = CrushMapResponse.PlacementGroupProto.newBuilder();
                pgBuilder.setPGNumber(pg.getPgID());
                for(ObjectStorageDaemon osd : pg.getOSDs()){
                    CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto.Builder
                            osdBuilder = CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto.newBuilder();
                    osdBuilder.setAddress(osd.getAddress()).setIsLeader(osd.isLeader);
                    pgBuilder.addOSDs(osdBuilder);
                }

                builder.addPGs(pgBuilder);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return builder.build();
    }

    public void printPGs() {
        for(PlacementGroup pg : this.getPGs()) {
            System.out.println("PG " + pg.getPgID()+": " + pg.getOSDs().size());
        }
    }
    public void printOSDs() {
        for (ObjectStorageDaemon osd : this.OSDs) {
            System.out.println(osd.getAddress()+ ": " + osd.getBelongingPG().getPgID());
        }
    }

    public int getOSDCount () {
        return this.OSDs.size();
    }

    public int getPGCount () {
        return this.PGs.size();
    }
}
