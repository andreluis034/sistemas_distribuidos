package GrupoA.StorageController.AtomixPrimitive.Impl;


import io.atomix.primitive.operation.Command;

public interface AtomicFSTreeService {

    @Command("mkdir")
    boolean mkDir(String path);

    @Command("rmdir")
    void rmDir(String path);

    @Command("mkfile")
    void mkFile(String path);

    @Command("rmfile")
    void rmFile(String path);

    @Command("ls")
    void ls(String path);
}
