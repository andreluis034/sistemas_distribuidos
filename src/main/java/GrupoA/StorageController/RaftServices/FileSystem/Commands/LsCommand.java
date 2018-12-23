package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class LsCommand extends FileSystemCommand<List<String>> {

    private Boolean searchByString = true;
    private String path = "";
    private Long iNode = 0L;

    public LsCommand(String path) {
        this.path = path;
    }

    @Override
    public List<String> execute(FSTree context) {
        List<String> ret = context.ls(this.path);
        if (ret == null)
            ret = Collections.singletonList("/");
        return ret;
    }
}
