package GrupoA.StorageController.RaftServices;

import GrupoA.StorageController.AtomixPrimitive.DistributedFSTree;

public class DistributedCrushMap implements ICrushMap {

    private final int version;

    private final int pgCount;

    public DistributedCrushMap(int version, int pgCount) {
        this.version = version;
        this.pgCount = pgCount;
    }
    /**
     * Gets the version of the CrushMap
     * When an Object Storage Daemon gets added or removed a new CrushMap is created with a different version
     * This helps keep track of the files which still haven't been moved.
     *
     * @return the version of this crushmap
     */
    @Override
    public int getVersion() {
        return this.version;
    }

    /**
     * Returns the PG where an hash should be stored
     *
     * @param hash the hash of the file/block
     * @return the PG number where the block should be stored
     */
    @Override
    public long getPgForHash(long hash) {
        return (int)(hash%this.pgCount);
    }

    /**
     * Gets the leader Object Storage Daemon for the PG
     *
     * @param pg the pg number
     * @return the OSD
     */
    @Override
    public int getLeaderOsdOfPg(int pg) {
        return 0;//TODO
    }
}
