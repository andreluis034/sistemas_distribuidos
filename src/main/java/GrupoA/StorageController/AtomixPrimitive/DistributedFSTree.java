package GrupoA.StorageController.AtomixPrimitive;

import io.atomix.primitive.SyncPrimitive;

public interface DistributedFSTree extends SyncPrimitive {
    /**
        TODO: Call stuff here
     */
    void mkDir(String path);
    void mkFile(String path);
    void rmDir(String path);
    void rmFile(String path);
    void ls(String path);

    @Override
    AsyncDistributedFSTree async();
}
