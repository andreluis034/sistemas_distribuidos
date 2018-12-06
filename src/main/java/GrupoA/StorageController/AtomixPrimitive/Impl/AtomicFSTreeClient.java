package GrupoA.StorageController.AtomixPrimitive.Impl;

import io.atomix.primitive.event.Event;

public interface AtomicFSTreeClient {
    @Event("mddir")
    void mdDir(String path);

    @Event("rmddir")
    void rmdDir(String path);

    @Event("mdfile")
    void mdFile(String path);

    @Event("rmdfile")
    void rmdFile(String path);
}
