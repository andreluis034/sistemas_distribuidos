package GrupoA.StorageController.Crush;

import GrupoA.StorageController.RaftServices.CrushMap.ICrushMap;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CrushMap implements ICrushMap, Serializable {
    // Default replication value
    private static final int REPLICATION = 2; //TODO move this to config

    private int version;
    private List<ObjectStorageDaemon> OSDs;
    private List<PlacementGroup> PGs = new LinkedList<>();
    public Path journal_path;

    public CrushMap(int version, List<ObjectStorageDaemon> OSDs) {
        this.version = version;
        this.OSDs = OSDs;
        if(OSDs.size() == 0)
            return;

        ArrayList<ObjectStorageDaemon> copiedOSDs = new ArrayList<>(OSDs.size());
        //make a copy of the OSD so that we don't change OSDs from previous maps
        for (ObjectStorageDaemon osd : OSDs) {
            copiedOSDs.add(new ObjectStorageDaemon(osd));
        }
        int numberOfOSDs = OSDs.size();
        int leftover = numberOfOSDs % REPLICATION;
        // Calculate PGs, based on the OSDs
        for (int i = 1; i <= OSDs.size() / REPLICATION; i++) {
            List<ObjectStorageDaemon> OSDsOfPG = OSDs.subList((i - 1) * REPLICATION, i * REPLICATION);
            OSDsOfPG.get(0).isLeader = true; //Set one to leader
            PlacementGroup pg = new PlacementGroup(i, OSDsOfPG);
            PGs.add(pg);
        }

        // Insert the remaining OSDs into a PG, increasing the replication of that PG
        PlacementGroup pgToRemove = PGs.remove(PGs.size() - 1);

        List<ObjectStorageDaemon> newOSDList = pgToRemove.getOSDs();
        newOSDList.addAll(OSDs.subList(numberOfOSDs - leftover, numberOfOSDs));

        PlacementGroup pgToInsert = new PlacementGroup(pgToRemove.getPgID(), newOSDList);
        PGs.add(pgToInsert);

        File journal = new File("CRUSH_journal_" + version + ".txt");

        try {
            if (!journal.exists())
                if (!journal.createNewFile())
                    throw new IOException("Couldn't create log file");

            journal_path = Paths.get("CRUSH_journal.txt");
            Files.write(journal_path, Collections.singleton("Logs started @ " + new Date()));
        } catch (IOException ignored) {
            System.err.println("Couldn't create log file\n");
        }
    }

    public List<PlacementGroup> getPGs() {
        return this.PGs;
    }

    public List<ObjectStorageDaemon> getOSDs() {
        List<ObjectStorageDaemon> osds = new ArrayList<>(this.OSDs);
        return osds; // Return a list that can be modified by anyone without affecting the crushmap
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public long getPgForHash(long hash) {
        return hash % PGs.size();
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
}
