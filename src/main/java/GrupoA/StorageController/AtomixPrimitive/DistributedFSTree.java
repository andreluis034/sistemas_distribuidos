package GrupoA.StorageController.AtomixPrimitive;

import GrupoA.StorageController.FSTree;
import io.atomix.primitive.SyncPrimitive;

public interface DistributedFSTree extends SyncPrimitive, FSTree {
    /*
    Implement stuff
     */

    @Override
    AsyncDistributedFSTree async();
}
