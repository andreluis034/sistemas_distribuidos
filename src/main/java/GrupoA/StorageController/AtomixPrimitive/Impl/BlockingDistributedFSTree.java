package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AsyncDistributedFSTree;
import GrupoA.StorageController.AtomixPrimitive.DistributedFSTree;
import io.atomix.primitive.Synchronous;

public class BlockingDistributedFSTree extends Synchronous<AsyncDistributedFSTree> implements DistributedFSTree {

    private final AsyncDistributedFSTree asyncFSTree;
    private final long operationTimeoutMillis;

    public BlockingDistributedFSTree(AsyncDistributedFSTree asyncFSTree, long operationTimeoutMillis) {
        super(asyncFSTree);
        this.asyncFSTree = asyncFSTree;
        this.operationTimeoutMillis = operationTimeoutMillis;
    }

    @Override
    public AsyncDistributedFSTree async() {
        return asyncFSTree;
    }
}
