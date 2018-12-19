package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AsyncAtomicFSTree;
import GrupoA.StorageController.AtomixPrimitive.AsyncDistributedFSTree;
import GrupoA.StorageController.AtomixPrimitive.DistributedFSTree;
import io.atomix.primitive.impl.DelegatingAsyncPrimitive;

import java.time.Duration;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;

public class DelegatingAsyncDistributedFSTree
        extends DelegatingAsyncPrimitive<AsyncAtomicFSTree>
        implements AsyncDistributedFSTree {

    public DelegatingAsyncDistributedFSTree(AsyncAtomicFSTree atomicFSTree) {
        super(atomicFSTree);
    }

    @Override
    public CompletableFuture<Boolean> mkDir(String path)
    {
        return delegate().mkDir(path);
    }
    @Override
    public CompletableFuture<Boolean> rmDir(String path)
    {
        return delegate().rmDir(path);
    }
    @Override
    public CompletableFuture<Boolean> mkFile(String path, int fileSize, int blocks, long hash)
    {
        return delegate().mkFile(path, fileSize, blocks, hash);
    }
    @Override
    public CompletableFuture<Boolean> rmFile(String path)
    {
        return delegate().rmFile(path);
    }
    @Override
    public CompletableFuture<LinkedList<String>> ls(String path)
    {
        return delegate().ls(path);
    }

    @Override
    public DistributedFSTree sync(Duration operationTimeout) {
        return new BlockingDistributedFSTree(this, operationTimeout.toMillis());
    }
}
