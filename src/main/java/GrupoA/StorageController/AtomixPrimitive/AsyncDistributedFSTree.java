package GrupoA.StorageController.AtomixPrimitive;

import io.atomix.primitive.AsyncPrimitive;
import io.atomix.primitive.DistributedPrimitive;

import java.time.Duration;

public interface AsyncDistributedFSTree extends AsyncPrimitive {
    default DistributedFSTree sync() {
        return sync(Duration.ofMillis(DistributedPrimitive.DEFAULT_OPERATION_TIMEOUT_MILLIS));
    }

    @Override
    DistributedFSTree sync(Duration operationTimeout);
}
