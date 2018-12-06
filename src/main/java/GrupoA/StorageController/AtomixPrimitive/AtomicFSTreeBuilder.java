package GrupoA.StorageController.AtomixPrimitive;

import io.atomix.primitive.PrimitiveBuilder;
import io.atomix.primitive.PrimitiveManagementService;
import io.atomix.primitive.protocol.PrimitiveProtocol;
import io.atomix.primitive.protocol.ProxyCompatibleBuilder;
import io.atomix.primitive.protocol.ProxyProtocol;

public abstract class AtomicFSTreeBuilder
        extends PrimitiveBuilder<AtomicFSTreeBuilder, AtomicFSTreeConfig, AtomicFSTree>
        implements ProxyCompatibleBuilder<AtomicFSTreeBuilder> {

    protected AtomicFSTreeBuilder(String name, AtomicFSTreeConfig config, PrimitiveManagementService managementService) {
        super(AtomicFSTreeType.instance(), name, config, managementService);
    }

    @Override
    public AtomicFSTreeBuilder withProtocol(ProxyProtocol protocol) {
        return withProtocol((PrimitiveProtocol) protocol);
    }
}
