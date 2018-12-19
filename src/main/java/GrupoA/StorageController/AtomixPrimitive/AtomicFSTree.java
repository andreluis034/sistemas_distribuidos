package GrupoA.StorageController.AtomixPrimitive;

import io.atomix.primitive.SyncPrimitive;

import java.util.LinkedList;

public interface AtomicFSTree extends SyncPrimitive {
    Boolean mkDir(String path);
    Boolean rmDir(String path);
    Boolean mkFile(String path, int fileSize, int blocks, long hash);
    Boolean rmFile(String path);
    LinkedList<String> ls(String path);

    @Override
    AsyncAtomicFSTree async();
}
