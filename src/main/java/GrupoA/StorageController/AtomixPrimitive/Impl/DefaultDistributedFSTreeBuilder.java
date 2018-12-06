package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.DistributedFSTree;
import GrupoA.StorageController.AtomixPrimitive.DistributedFSTreeBuilder;
import GrupoA.StorageController.AtomixPrimitive.DistributedFSTreeConfig;
import io.atomix.primitive.PrimitiveManagementService;
import io.atomix.primitive.service.ServiceConfig;

import java.util.concurrent.CompletableFuture;

public class DefaultDistributedFSTreeBuilder extends DistributedFSTreeBuilder {
    public DefaultDistributedFSTreeBuilder(String name, DistributedFSTreeConfig config, PrimitiveManagementService managementService) {
        super(name, config, managementService);
    }

    @Override
    @SuppressWarnings("unchecked")
    public CompletableFuture<DistributedFSTree> buildAsync() {
        return newProxy(AtomicFSTreeService.class, new ServiceConfig())
                .thenCompose(proxy -> new AtomicFSTreeProxy(proxy, managementService.getPrimitiveRegistry()).connect())
                .thenApply(lock -> new DelegatingAsyncDistributedFSTree(lock).sync());
    }
}
