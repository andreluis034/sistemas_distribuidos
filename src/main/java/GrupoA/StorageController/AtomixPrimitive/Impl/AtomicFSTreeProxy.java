package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AsyncAtomicFSTree;
import GrupoA.StorageController.AtomixPrimitive.AtomicFSTree;
import io.atomix.primitive.AbstractAsyncPrimitive;
import io.atomix.primitive.PrimitiveRegistry;
import io.atomix.primitive.PrimitiveState;
import io.atomix.primitive.proxy.ProxyClient;

import java.time.Duration;

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
    public AtomicFSTree sync(Duration operationTimeout) {
        return new BlockingAtomicFSTree(this, operationTimeout.toMillis());
    }

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
