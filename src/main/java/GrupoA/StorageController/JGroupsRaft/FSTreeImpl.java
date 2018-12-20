package GrupoA.StorageController.JGroupsRaft;

import java.util.LinkedList;

public class FSTreeImpl implements IFileSystem {
    protected final FileSystemService fsService;

    public FSTreeImpl(FileSystemService fsService) {
        this.fsService = fsService;
    }

    @Override
    public boolean mkDir(String path) {
        return fsService.mkDir(path);
    }

    @Override
    public boolean mkFile(String path) {
        return fsService.mkFile(path);
    }

    @Override
    public boolean rmDir(String path) {
        return fsService.rmDir(path);
    }

    @Override
    public boolean rmFile(String path) {
        return fsService.rmFile(path);
    }

    @Override
    public LinkedList<String> ls(String path) {
        return fsService.ls(path);
    }
}
