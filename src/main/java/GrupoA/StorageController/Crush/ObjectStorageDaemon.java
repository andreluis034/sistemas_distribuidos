package GrupoA.StorageController.Crush;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ObjectStorageDaemon implements Serializable {
    private PlacementGroup belongingPG;
    private String Address;

    public ObjectStorageDaemon(PlacementGroup belongingPG, String Address) {
        this.belongingPG = belongingPG;
        this.Address = Address;
    }

    public PlacementGroup getBelongingPG() {
        return this.belongingPG;
    }

    public String getAddress() {
        return this.Address;
    }
}
