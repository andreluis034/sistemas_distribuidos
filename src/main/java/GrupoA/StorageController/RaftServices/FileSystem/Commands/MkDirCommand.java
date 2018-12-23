package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

import java.io.Serializable;

public class MkDirCommand extends FileSystemCommand<Boolean> {

    private Boolean searchByString = true;
    private String path = "";
    private Long iNode = 0L;

    public MkDirCommand(String path) {
        this.path = path;
    }

    public MkDirCommand(Long iNode) {
        this.iNode = iNode;
        this.searchByString = false;
    }

    @Override
    public Boolean execute(FSTree context) {
        if (searchByString)
            return context.mkDir(path);
        return false;
    }
}
