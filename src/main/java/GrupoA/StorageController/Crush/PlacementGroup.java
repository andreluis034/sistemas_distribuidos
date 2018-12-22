package GrupoA.StorageController.Crush;

import java.util.LinkedList;
import java.util.List;

public class PlacementGroup {
    private int pgID = 0;
    private List<ObjectStorageDaemon> OSDs = new LinkedList<>();

    public int getPgID(){
        return this.pgID;
    }

    public List<ObjectStorageDaemon> getOSDs() {
        return this.OSDs;
    }
}
