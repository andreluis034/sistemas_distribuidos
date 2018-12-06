package GrupoA.StorageController.AtomixPrimitive.Impl;


import GrupoA.StorageController.AtomixPrimitive.DistributedFSTreeType;

public class DefaultDistributedFSTreeService extends AbstractAtomicFSTreeService {
    public DefaultDistributedFSTreeService() {
        super(DistributedFSTreeType.instance());
    }
}
