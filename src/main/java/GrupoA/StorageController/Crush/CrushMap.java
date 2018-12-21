package GrupoA.StorageController.Crush;

import java.util.LinkedList;
import java.util.List;

public class CrushMap {
    public int Version = 0;

    private List<ObjectStorageDaemon> OSDHosts = new LinkedList<>();
    private List<PlacementGroup> PGs = new LinkedList<>();

    public CrushMap(int version) {
        this.Version = version;
    }


}
