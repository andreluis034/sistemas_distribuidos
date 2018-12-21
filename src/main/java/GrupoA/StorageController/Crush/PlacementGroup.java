package GrupoA.StorageController.Crush;

import java.util.LinkedList;
import java.util.List;

public class PlacementGroup {
    private int pgNumber = 0;
    private List<ObjectStorageDaemon> OSDs = new LinkedList<>();

    public int getPgNumber(){
        return this.pgNumber;
    }
}
