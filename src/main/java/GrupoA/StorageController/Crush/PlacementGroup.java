package GrupoA.StorageController.Crush;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PlacementGroup implements Serializable {
    private int pgID = 0;
    private List<ObjectStorageDaemon> OSDs = new LinkedList<>();

    public PlacementGroup(int id, List<ObjectStorageDaemon> OSDs) {
        System.out.println("new PG (): " + id + ", " + OSDs.size());
        this.pgID = id;
        this.OSDs = OSDs;
        for (ObjectStorageDaemon osd : OSDs) {
            osd.setBelongingPG(this);
        }
    }

    public void fixBelongingPGs() {
        for (ObjectStorageDaemon osd : OSDs) {
            osd.setBelongingPG(this);
        }
    }
    public int getPgID(){
        return this.pgID;
    }

    public List<ObjectStorageDaemon> getOSDs() {
        return this.OSDs;
    }
}
