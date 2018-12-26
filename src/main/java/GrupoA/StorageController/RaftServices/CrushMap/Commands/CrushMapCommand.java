package GrupoA.StorageController.RaftServices.CrushMap.Commands;

import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;

import java.io.Serializable;

public abstract class CrushMapCommand<T> implements Serializable {

    public abstract T execute(CrushMapService context);

    public abstract void journal(CrushMapService context);
}
