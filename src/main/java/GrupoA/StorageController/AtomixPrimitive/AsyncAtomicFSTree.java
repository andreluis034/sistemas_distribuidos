package GrupoA.StorageController.AtomixPrimitive;

import GrupoA.StorageController.AtomixPrimitive.AtomicFSTree;
import io.atomix.primitive.AsyncPrimitive;
import io.atomix.primitive.DistributedPrimitive;

import java.time.Duration;

public interface AsyncAtomicFSTree extends AsyncPrimitive {
    @Override
    default AtomicFSTree sync() {
        return sync(Duration.ofMillis(DistributedPrimitive.DEFAULT_OPERATION_TIMEOUT_MILLIS));
    }

    @Override
    AtomicFSTree sync(Duration operationTimeout);
}
