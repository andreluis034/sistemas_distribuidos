package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AsyncAtomicFSTree;
import GrupoA.StorageController.AtomixPrimitive.AtomicFSTree;
import io.atomix.primitive.Synchronous;

public class BlockingAtomicFSTree extends Synchronous<AsyncAtomicFSTree> implements AtomicFSTree {

    private final AsyncAtomicFSTree asyncFSTree;
    private final long operationTimeoutMillis;

    public BlockingAtomicFSTree(AsyncAtomicFSTree asyncFSTree, long operationTimeoutMillis) {
        super(asyncFSTree);
        this.asyncFSTree = asyncFSTree;
        this.operationTimeoutMillis = operationTimeoutMillis;
    }

    @Override
    public AsyncAtomicFSTree async() {
        return asyncFSTree;
    }
}
