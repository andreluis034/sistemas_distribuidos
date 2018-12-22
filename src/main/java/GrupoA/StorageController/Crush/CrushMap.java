package GrupoA.StorageController.Crush;

import GrupoA.StorageController.RaftServices.ICrushMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CrushMap implements ICrushMap {
    private int Version;

    private List<ObjectStorageDaemon> OSDHosts;
    private List<PlacementGroup> PGs = new LinkedList<>();
    private File journal;
    public Path journal_path;

    public CrushMap(int version, List<ObjectStorageDaemon> OSDs) {
        this.Version = version;
        this.OSDHosts = OSDs;

        journal = new File("CRUSH_journal.txt");

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

    @Override
    public int getVersion() {
        return this.Version;
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
                    long osdHash = GrupoA.Utility.Jenkins.hash64(osd.Address.getBytes());
                    if (leaderOSD_hash == -1) {
                        leaderOSD_hash = osdHash;
                        leaderOSD_address = osd.Address;
                    }
                    else if (osdHash > leaderOSD_hash) {
                        leaderOSD_hash = osdHash;
                        leaderOSD_address = osd.Address;
                    }
                }
            }
        }

        // Need to check if address is an empty string
        return leaderOSD_address;
    }
}
