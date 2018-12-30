package GrupoA.StorageController.RaftServices.CrushMap.Commands;

import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.StorageController.Crush.CrushMap;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

class MoveFiles implements Runnable {

    private CrushMapService service;
    private CrushMap target;

    public MoveFiles(CrushMapService service, CrushMap target){
        this.service = service;
        this.target = target;
    }

    @Override
    public void run() {
        System.out.println("Starting thread to move files");
        try {
            service.updateFilesLocations(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished moving files");

    }
}

public class CreateNewCrushMapService extends CrushMapCommand<CrushMap> {

    private List<ObjectStorageDaemon> OSDs;

    public CreateNewCrushMapService(List<ObjectStorageDaemon> OSDs) {
        this.OSDs = OSDs;
    }

    @Override
    public CrushMap execute(CrushMapService context) {
        try {
            context.latestVersion++;
            System.out.println("Creating map with with version " + context.latestVersion);
            context.latestMap = new CrushMap(context.latestVersion, OSDs);
            context.mapOfMaps.put(context.latestVersion, context.latestMap);
            context.latestMap.printPGs();
            context.latestMap.printOSDs();
            if(context.isLeader) {
                Thread thread = new Thread(new MoveFiles(context, context.latestMap));
                thread.start();
                for (ObjectStorageDaemon osd : context.latestMap.getOSDsCopy()) {
                    OSDClient client = new OSDClient(osd.getHost(), osd.getPort());
                    client.ForceUpdate(osd.getBelongingPG().getOSDs());
                    client.shutdown();
                    client.awaitTermination();
                }
            }

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
