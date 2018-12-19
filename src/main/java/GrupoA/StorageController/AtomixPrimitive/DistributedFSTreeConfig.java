package GrupoA.StorageController.AtomixPrimitive;

import io.atomix.primitive.PrimitiveType;
import io.atomix.primitive.config.PrimitiveConfig;

public class DistributedFSTreeConfig
        extends PrimitiveConfig<DistributedFSTreeConfig> {

    @Override
    public PrimitiveType<DistributedFSTreeBuilder, DistributedFSTreeConfig, DistributedFSTree>  getType() {
        return DistributedFSTreeType.instance();
    }
}
