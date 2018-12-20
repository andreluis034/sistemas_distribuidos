package GrupoA.StorageController.JGroupsRaft;

import java.util.LinkedList;

public class FileSystemImpl implements IFileSystem {
    protected final FileSystemService fsService;

    public FileSystemImpl(FileSystemService fsService) {
        this.fsService = fsService;
    }

    @Override
    public boolean mkDir(String path) {
        try {
            return fsService.mkDir(path);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean mkFile(String path, int fileSize, int blocks, long hash) {
        try {
            return fsService.mkFile(path, fileSize, blocks, hash);
        } catch(Exception e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public boolean rmDir(String path) {
        try {
            return fsService.rmDir(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean rmFile(String path) {
        try {
            return fsService.rmFile(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LinkedList<String> ls(String path) {
        try {
            return fsService.ls(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
