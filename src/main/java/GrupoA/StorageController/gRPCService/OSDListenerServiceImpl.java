package GrupoA.StorageController.gRPCService;

import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.StorageController.Crush.CrushMap;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.Crush.PlacementGroup;
import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;
import GrupoA.StorageController.gRPCService.OSDListener.OSDDetails;
import GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG;
import GrupoA.StorageController.gRPCService.OSDListener.OSDListenerGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class OSDListenerServiceImpl extends OSDListenerGrpc.OSDListenerImplBase {
    @Override
    public void receiveAnnouncement(OSDDetails args, StreamObserver<OSDInSamePaG> resp) {
        OSDInSamePaG.Builder em = OSDInSamePaG.newBuilder();
        OSDClient client = new OSDClient(args.getAddress(), args.getPort());
        if(client.ping()) {
            CrushMap map = CrushMapService.getInstance().addOSD(new ObjectStorageDaemon(args.getAddress()+":"+args.getPort()));
            List<ObjectStorageDaemon> OSDs = map.getOSDs();
            ObjectStorageDaemon addedOSD = null;
            for (ObjectStorageDaemon OSD : OSDs) {
                if (OSD.getAddress().equals(args.getAddress() + ":" + args.getPort())) {
                    addedOSD = OSD;
                    break;
                }
            }
            if(addedOSD != null) {
                PlacementGroup pg = addedOSD.getBelongingPG();
                OSDs =  pg.getOSDs();
                for (ObjectStorageDaemon OSD : OSDs) {
                    em.addOSDs(OSDDetails.newBuilder().setAddress(OSD.getHost())
                        .setPort(OSD.getPort())
                        .setLeader(OSD.isLeader));

                }
            }

        } else {
            System.out.println("Received request from OSD " + args.getAddress() + ":"+args.getPort()
                    + "to join but can't verify its availability reach it");
        }

        resp.onNext(em.build());
        resp.onCompleted();
    }

    public static final int DEFAULT_PORT = 50053;

    public static Server getServer(int port){
            return ServerBuilder.forPort(port)
                    .addService(new OSDListenerServiceImpl())
                    .build();
    }
}
