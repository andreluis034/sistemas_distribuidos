package GrupoA.StorageController.Crush;

import GrupoA.StorageController.RaftServices.ICrushMap;

import java.util.LinkedList;
import java.util.List;

public class CrushMap implements ICrushMap {
    private int Version = 0;

    private List<ObjectStorageDaemon> OSDHosts = new LinkedList<>();
    private List<PlacementGroup> PGs = new LinkedList<>();

    public CrushMap(int version) {
        this.Version = version;
    }


    @Override
    public int getVersion() {
        return this.Version;
    }

    @Override
    public long getPgForHash(long hash) {
        return hash % PGs.size();
    }

    @Override
    public int getLeaderOsdOfPg(int pg) {
        return 0;
    }
}
