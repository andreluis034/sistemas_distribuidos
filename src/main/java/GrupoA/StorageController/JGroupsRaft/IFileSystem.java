package GrupoA.StorageController.JGroupsRaft;

import GrupoA.StorageController.FSTree;

import java.util.LinkedList;
import java.util.List;

public interface IFileSystem {
    boolean mkDir(String path);

    boolean mkFile(String path);

    boolean rmDir(String path);

    boolean rmFile(String path);

    LinkedList<String> ls(String path);
}
