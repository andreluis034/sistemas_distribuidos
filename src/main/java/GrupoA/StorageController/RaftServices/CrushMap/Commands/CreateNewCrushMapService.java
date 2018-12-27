package GrupoA.StorageController.RaftServices.CrushMap.Commands;

import GrupoA.StorageController.Crush.CrushMap;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CreateNewCrushMapService extends CrushMapCommand<CrushMap> {

    private List<ObjectStorageDaemon> OSDs;

    public CreateNewCrushMapService(List<ObjectStorageDaemon> OSDs) {
        this.OSDs = OSDs;
    }

    @Override
    public CrushMap execute(CrushMapService context) {
        try {
            context.latestVersion++;
            context.latestMap = new CrushMap(context.latestVersion, OSDs);
            context.mapOfMaps.put(context.latestVersion, context.latestMap);
            context.latestMap.printPGs();
            context.latestMap.printOSDs();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return context.latestMap;
    }

    @Override
    public void journal(CrushMapService context) {
        try {
            Files.write(Paths.get(context.latestMap.journal_path),
                    Collections.singleton("Created new CRUSH map, version " + context.latestVersion
                            + ", with " + OSDs.size() + " OSDs\n"));
        } catch (IOException ignored) {
            System.out.println("Created new CRUSH map, version " + context.latestVersion + "\n");
            System.err.println("Couldn't write to log file\n");
        }

    }
}
