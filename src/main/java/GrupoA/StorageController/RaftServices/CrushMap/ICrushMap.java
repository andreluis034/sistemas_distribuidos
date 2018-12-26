package GrupoA.StorageController.RaftServices.CrushMap;

public interface ICrushMap {

    /**
     * Gets the version of the CrushMap
     * When an Object Storage Daemon gets added or removed a new CrushMap is created with a different version
     * This helps keep track of the files which still haven't been moved.
     * @return the version of this crushmap
     */
    int getVersion();

    /**
     * Returns the PG where an hash should be stored
     * @param hash the hash of the file/block
     * @return the PG number where the block should be stored
     */
    long getPgForHash(long hash);

    /**
     * Gets the leader Object Storage Daemon for the PG
     * @param pg the pg number
     * @return the OSD
     */
    String getLeaderOsdOfPg(int pg);

}
