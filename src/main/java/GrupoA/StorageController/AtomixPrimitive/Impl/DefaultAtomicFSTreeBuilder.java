package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AsyncAtomicFSTree;
import GrupoA.StorageController.AtomixPrimitive.AtomicFSTree;
import GrupoA.StorageController.AtomixPrimitive.AtomicFSTreeBuilder;
import GrupoA.StorageController.AtomixPrimitive.AtomicFSTreeConfig;
import io.atomix.primitive.PrimitiveManagementService;
import io.atomix.primitive.service.ServiceConfig;

import java.util.concurrent.CompletableFuture;

public class DefaultAtomicFSTreeBuilder extends AtomicFSTreeBuilder {
    public DefaultAtomicFSTreeBuilder(String name, AtomicFSTreeConfig config, PrimitiveManagementService managementService) {
        super(name, config, managementService);
    }

    @Override
    @SuppressWarnings("unchecked")
    public CompletableFuture<AtomicFSTree> buildAsync() {
        return newProxy(AtomicFSTreeService.class, new ServiceConfig())
                .thenCompose(proxy -> new AtomicFSTreeProxy(proxy, managementService.getPrimitiveRegistry()).connect())
                .thenApply(AsyncAtomicFSTree::sync);
    }
}