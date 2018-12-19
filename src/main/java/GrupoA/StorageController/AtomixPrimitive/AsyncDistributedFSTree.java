package GrupoA.StorageController.AtomixPrimitive;

import io.atomix.primitive.AsyncPrimitive;
import io.atomix.primitive.DistributedPrimitive;

import java.time.Duration;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;

public interface AsyncDistributedFSTree extends AsyncPrimitive {
    default DistributedFSTree sync() {
        return sync(Duration.ofMillis(DistributedPrimitive.DEFAULT_OPERATION_TIMEOUT_MILLIS));
    }

    CompletableFuture<Boolean> mkDir(String path);
    CompletableFuture<Boolean> rmDir(String path);
    CompletableFuture<Boolean> mkFile(String path, int fileSize, int blocks, long hash);
    CompletableFuture<Boolean> rmFile(String path);
    CompletableFuture<LinkedList<String>> ls(String path);

    @Override
    DistributedFSTree sync(Duration operationTimeout);
}
