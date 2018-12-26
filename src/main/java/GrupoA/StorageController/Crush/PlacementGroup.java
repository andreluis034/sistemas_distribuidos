package GrupoA.StorageController.Crush;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PlacementGroup implements Serializable {
    private int pgID = 0;
    private List<ObjectStorageDaemon> OSDs;

    public PlacementGroup(int id, List<ObjectStorageDaemon> OSDs) {
        this.pgID = id;
        this.OSDs = OSDs;
        for (ObjectStorageDaemon osd : OSDs) {
            osd.belongingPG = this;
        }
    }

    public int getPgID(){
        return this.pgID;
    }

    public List<ObjectStorageDaemon> getOSDs() {
        return this.OSDs;
    }
}
