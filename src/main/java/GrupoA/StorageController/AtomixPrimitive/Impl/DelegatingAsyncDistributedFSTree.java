package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AsyncAtomicFSTree;
import GrupoA.StorageController.AtomixPrimitive.AsyncDistributedFSTree;
import GrupoA.StorageController.AtomixPrimitive.DistributedFSTree;
import io.atomix.primitive.impl.DelegatingAsyncPrimitive;

import java.time.Duration;

public class DelegatingAsyncDistributedFSTree
        extends DelegatingAsyncPrimitive<AsyncAtomicFSTree>
        implements AsyncDistributedFSTree {
    public DelegatingAsyncDistributedFSTree(AsyncAtomicFSTree atomicFSTree) {
        super(atomicFSTree);
    }

    @Override
    public DistributedFSTree sync(Duration operationTimeout) {
        return new BlockingDistributedFSTree(this, operationTimeout.toMillis());
    }
}
