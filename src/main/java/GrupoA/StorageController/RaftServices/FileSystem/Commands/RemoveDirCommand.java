package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

public class RemoveDirCommand extends FileSystemCommand<Integer> {

    private String path;

    public RemoveDirCommand(String path) {
        this.path = path;
    }

    @Override
    public Integer execute(FSTree context) {
        return context.rmDir(path);
    }
}
