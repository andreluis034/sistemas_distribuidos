package GrupoA.StorageController.AtomixPrimitive;

import GrupoA.StorageController.AtomixPrimitive.Impl.DefaultAtomicFSTreeBuilder;
import GrupoA.StorageController.AtomixPrimitive.Impl.DefaultAtomicFSTreeService;
import io.atomix.primitive.PrimitiveManagementService;
import io.atomix.primitive.PrimitiveType;
import io.atomix.primitive.service.PrimitiveService;
import io.atomix.primitive.service.ServiceConfig;

import static com.google.common.base.MoreObjects.toStringHelper;

public class AtomicFSTreeType implements PrimitiveType<AtomicFSTreeBuilder, AtomicFSTreeConfig, AtomicFSTree> {
    private static final String NAME = "atomic-FSTree";
    private static final AtomicFSTreeType INSTANCE = new AtomicFSTreeType();

    /**
     * Returns a new distributed lock type.
     *
     * @return a new distributed lock type
     */
    public static AtomicFSTreeType instance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public PrimitiveService newService(ServiceConfig config) {
        return new DefaultAtomicFSTreeService();
    }

    @Override
    public AtomicFSTreeConfig newConfig() {
        return new AtomicFSTreeConfig();
    }

    @Override
    public AtomicFSTreeBuilder newBuilder
            (String name, AtomicFSTreeConfig config, PrimitiveManagementService managementService) {
        return new DefaultAtomicFSTreeBuilder(name, config, managementService);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("name", name())
                .toString();
    }
}
