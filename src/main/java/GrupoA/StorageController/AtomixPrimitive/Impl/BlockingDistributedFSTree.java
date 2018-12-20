package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AsyncDistributedFSTree;
import GrupoA.StorageController.AtomixPrimitive.DistributedFSTree;
import io.atomix.primitive.Synchronous;

import java.util.LinkedList;

public class BlockingDistributedFSTree extends Synchronous<AsyncDistributedFSTree> implements DistributedFSTree {

    private final AsyncDistributedFSTree asyncFSTree;
    private final long operationTimeoutMillis;

    public BlockingDistributedFSTree(AsyncDistributedFSTree asyncFSTree, long operationTimeoutMillis) {
        super(asyncFSTree);
        this.asyncFSTree = asyncFSTree;
        this.operationTimeoutMillis = operationTimeoutMillis;
    }

    /**
     * TODO: Call stuff
     *
     * @param path
     */
    @Override
    public Boolean mkDir(String path)
    {
        return this.async().mkDir(path).join();
    }
    @Override
    public Boolean rmDir(String path)
    {
        return this.async().rmDir(path).join();
    }
    @Override
    public Boolean mkFile(String path, int fileSize, int blocks, long hash) {
        return this.async().mkFile(path, fileSize, blocks, hash).join();
    }
    @Override
    public Boolean rmFile(String path)
    {
        return this.async().rmFile(path).join();
    }

    @Override
    public LinkedList<String> ls(String path)
    {
        return this.async().ls(path).join();
    }

    @Override
    public AsyncDistributedFSTree async() {
        return asyncFSTree;
    }
}
