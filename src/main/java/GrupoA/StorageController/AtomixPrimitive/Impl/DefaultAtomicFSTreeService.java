package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AtomicFSTreeType;


public class DefaultAtomicFSTreeService extends AbstractAtomicFSTreeService {
    public DefaultAtomicFSTreeService() {
        super(AtomicFSTreeType.instance());
    }
}
