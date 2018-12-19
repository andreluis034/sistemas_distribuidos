package GrupoA.StorageController.AtomixPrimitive.Impl;

import io.atomix.primitive.operation.Command;

import java.util.LinkedList;

public interface AtomicFSTreeService {

    @Command("mkdir")
    Boolean mkDir(String path);

    @Command("rmdir")
    Boolean rmDir(String path);

    @Command("mkfile")
    Boolean mkFile(String path, int fileSize, int blocks, long hash);

    @Command("rmfile")
    Boolean rmFile(String path);

    @Command("ls")
    LinkedList<String> ls(String path);
}
