package GrupoA.StorageController.RaftServices.FileSystem.Commands;


import GrupoA.StorageController.FileSystem.FSTree;

import java.io.Serializable;

public abstract class FileSystemCommand<T> implements Serializable {

    public abstract T execute(FSTree context);


}
