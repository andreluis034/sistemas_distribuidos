package GrupoA.StorageController.AtomixPrimitive;

import io.atomix.primitive.PrimitiveType;
import io.atomix.primitive.config.PrimitiveConfig;

public class AtomicFSTreeConfig extends PrimitiveConfig<AtomicFSTreeConfig> {
    @Override
    public PrimitiveType<AtomicFSTreeBuilder, AtomicFSTreeConfig, AtomicFSTree> getType() {
        return AtomicFSTreeType.instance();
    }
}
