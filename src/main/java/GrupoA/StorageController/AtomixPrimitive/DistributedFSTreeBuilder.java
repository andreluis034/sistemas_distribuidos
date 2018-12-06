package GrupoA.StorageController.AtomixPrimitive;

import io.atomix.primitive.PrimitiveBuilder;
import io.atomix.primitive.PrimitiveManagementService;
import io.atomix.primitive.protocol.PrimitiveProtocol;
import io.atomix.primitive.protocol.ProxyCompatibleBuilder;
import io.atomix.primitive.protocol.ProxyProtocol;

public abstract class DistributedFSTreeBuilder
        extends PrimitiveBuilder<DistributedFSTreeBuilder, DistributedFSTreeConfig, DistributedFSTree>
        implements ProxyCompatibleBuilder<DistributedFSTreeBuilder> {

    public DistributedFSTreeBuilder(String name, DistributedFSTreeConfig config, PrimitiveManagementService managementService) {
        super(DistributedFSTreeType.instance(), name, config, managementService);
    }

    @Override
    public DistributedFSTreeBuilder withProtocol(ProxyProtocol proxyProtocol) {
        return withProtocol((PrimitiveProtocol) proxyProtocol);
    }
}
