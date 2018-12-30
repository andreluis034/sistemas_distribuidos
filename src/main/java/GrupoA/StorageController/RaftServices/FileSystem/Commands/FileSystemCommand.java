package GrupoA.StorageController.RaftServices.FileSystem.Commands;


import GrupoA.StorageController.FileSystem.FSTree;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Collections;

public abstract class FileSystemCommand<T> implements Serializable {


    public abstract T execute(FSTree context);

    public void journal(FSTree context, Object result) {
        String str = "Return value of command '"+this.getClass().getName()+"' was: " + result.toString() + "\n";
        try {
            Files.write(context.journal_path,
                    Collections.singleton(str));
        } catch (IOException ignored) {
            System.out.println(str);
            System.err.println("Couldn't write to log file\n");
        }

    }


}
