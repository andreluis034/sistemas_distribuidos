package GrupoA.StorageController.AtomixPrimitive.Impl;

import io.atomix.primitive.operation.Command;

import java.util.LinkedList;

public interface AtomicFSTreeService {

    @Command("mkdir")
    boolean mkDir(String path);

    @Command("rmdir")
    boolean rmDir(String path);

    @Command("mkfile")
    boolean mkFile(String path, int fileSize, int blocks, long hash);

    @Command("rmfile")
    boolean rmFile(String path);

    @Command("ls")
    LinkedList<String> ls(String path);
}
