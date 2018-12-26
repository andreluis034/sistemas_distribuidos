package GrupoA.StorageController.Crush;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ObjectStorageDaemon implements Serializable {
    PlacementGroup belongingPG; //TODO you need to set this when you create the PGs
    public boolean isLeader = false;
    private String Address;

    public ObjectStorageDaemon(ObjectStorageDaemon osd) {
        this.belongingPG = osd.belongingPG;
        this.Address = osd.Address;
    }

    public ObjectStorageDaemon(String Address) {
        this.belongingPG = belongingPG;
        this.Address = Address;
    }

    public PlacementGroup getBelongingPG() {
        return this.belongingPG;
    }

    public String getAddress() {
        return this.Address;
    }

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof ObjectStorageDaemon))return false;

        return ((ObjectStorageDaemon) other).getAddress().equals(this.Address);
    }

    public String getHost() {
        return this.Address.split(":")[0];
    }

    public int getPort() {
        return Integer.parseInt(this.Address.split(":")[1]);
    }
}
