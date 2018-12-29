package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

public class RemoveFileCommand extends FileSystemCommand<Integer> {

    private String path;

    public RemoveFileCommand(String path) {
        this.path = path;
    }

    @Override
    public Integer execute(FSTree context) {
        return context.rmFile(path);
    }
}