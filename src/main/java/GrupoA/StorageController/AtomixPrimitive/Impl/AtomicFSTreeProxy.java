package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AsyncAtomicFSTree;
import GrupoA.StorageController.AtomixPrimitive.AtomicFSTree;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.atomix.primitive.AbstractAsyncPrimitive;
import io.atomix.primitive.PrimitiveRegistry;
import io.atomix.primitive.PrimitiveState;
import io.atomix.primitive.proxy.ProxyClient;
import io.atomix.utils.time.Version;

import java.time.Duration;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;

public class AtomicFSTreeProxy
        extends AbstractAsyncPrimitive<AsyncAtomicFSTree, AtomicFSTreeService>
        implements AsyncAtomicFSTree, AtomicFSTreeClient {

    public AtomicFSTreeProxy(ProxyClient<AtomicFSTreeService> proxy, PrimitiveRegistry registry) {
        super(proxy, registry);
        proxy.addStateChangeListener(this::onStateChange);
    }

    private void onStateChange(PrimitiveState state) {
    }

    @Override
    public CompletableFuture<Boolean> mkDir(String path) {
        return getProxyClient().applyBy(name(), service -> service.mkDir(path));
    }

    @Override
    public CompletableFuture<Boolean> rmDir(String path) {
        return getProxyClient().applyBy(name(), service -> service.rmDir(path));
    }

    @Override
    public CompletableFuture<Boolean> mkFile(String path, int fileSize, int blocks, long hash) {
        return getProxyClient().applyBy(name(), service -> service.mkFile(path, fileSize, blocks, hash));
    }

    @Override
    public CompletableFuture<Boolean> rmFile(String path) {
        return getProxyClient().applyBy(name(), service -> service.rmFile(path));
    }

    @Override
    public CompletableFuture<LinkedList<String>> ls(String path) {
        return getProxyClient().applyBy(name(), service -> service.ls(path));
    }

    @Override
    public AtomicFSTree sync(Duration operationTimeout) {
        return new BlockingAtomicFSTree(this, operationTimeout.toMillis());
    }

    //TODO THIS
    @Override
    public void mdDir(String path) {

    }

    @Override
    public void rmdDir(String path) {

    }

    @Override
    public void mdFile(String path) {

    }

    @Override
    public void rmdFile(String path) {

    }
}
