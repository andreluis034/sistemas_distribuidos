package GrupoA.StorageController.AtomixPrimitive;

import GrupoA.StorageController.AtomixPrimitive.Impl.DefaultDistributedFSTreeBuilder;
import GrupoA.StorageController.AtomixPrimitive.Impl.DefaultDistributedFSTreeService;
import io.atomix.primitive.PrimitiveManagementService;
import io.atomix.primitive.PrimitiveType;
import io.atomix.primitive.service.PrimitiveService;
import io.atomix.primitive.service.ServiceConfig;

import static com.google.common.base.MoreObjects.toStringHelper;

public class DistributedFSTreeType
        implements PrimitiveType<DistributedFSTreeBuilder, DistributedFSTreeConfig, DistributedFSTree> {
    private static final String NAME = "FSTree";
    private static final DistributedFSTreeType INSTANCE = new DistributedFSTreeType();

    @Override
    public DistributedFSTreeConfig newConfig() {
        return new DistributedFSTreeConfig();
    }

    @Override
    public DistributedFSTreeBuilder newBuilder
            (String name, DistributedFSTreeConfig config, PrimitiveManagementService managementService) {
        return new DefaultDistributedFSTreeBuilder(name, config, managementService);
    }

    @Override
    public PrimitiveService newService(ServiceConfig serviceConfig) {
        return new DefaultDistributedFSTreeService();
    }

    @Override
    public String name() {
        return NAME;
    }

    public static DistributedFSTreeType instance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("name", name())
                .toString();
    }
}
