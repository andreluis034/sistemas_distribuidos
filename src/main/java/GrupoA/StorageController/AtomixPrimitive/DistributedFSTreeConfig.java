package GrupoA.StorageController.AtomixPrimitive;

import io.atomix.primitive.PrimitiveType;
import io.atomix.primitive.config.PrimitiveConfig;

public class DistributedFSTreeConfig
        extends PrimitiveConfig<DistributedFSTreeConfig> {

    @Override
    public PrimitiveType getType() {
        return DistributedFSTreeType.instance();
    }
}
