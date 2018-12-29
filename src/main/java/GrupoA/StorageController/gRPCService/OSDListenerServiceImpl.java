package GrupoA.StorageController.gRPCService;

import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.StorageController.Crush.CrushMap;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.Crush.PlacementGroup;
import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;
import GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage;
import GrupoA.StorageController.gRPCService.OSDListener.OSDDetails;
import GrupoA.StorageController.gRPCService.OSDListener.OSDInSamePaG;
import GrupoA.StorageController.gRPCService.OSDListener.OSDListenerGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class  OSDListenerServiceImpl extends OSDListenerGrpc.OSDListenerImplBase {

    private void getOSDsSameAsMyPG(CrushMap map, OSDDetails args, OSDInSamePaG.Builder builder){
        List<ObjectStorageDaemon> OSDs = map.getOSDsCopy();
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
                builder.addOSDs(OSDDetails.newBuilder().setAddress(OSD.getHost())
                        .setPort(OSD.getPort())
                        .setLeader(OSD.isLeader));

            }
        }
    }
    @Override
    public void receiveAnnouncement(OSDDetails args, StreamObserver<EmptyMessage> resp) {
        EmptyMessage.Builder em = EmptyMessage.newBuilder();
        OSDClient client = new OSDClient(args.getAddress(), args.getPort());
        if(client.ping()) {
            CrushMap map = CrushMapService.getInstance().addOSD(new ObjectStorageDaemon(args.getAddress()+":"+args.getPort()));
        } else {
            System.out.println("Received request from OSD " + args.getAddress() + ":"+args.getPort()
                    + "to join but can't verify its availability reach it");
        }

        resp.onNext(em.build());
        resp.onCompleted();
    }


    @Override
    public void leave(OSDDetails args, StreamObserver<EmptyMessage> resp) { //TODO
        EmptyMessage.Builder em = EmptyMessage.newBuilder();
        CrushMapService.getInstance().removeOSD(args);
        resp.onNext(em.build());
        resp.onCompleted();
    }

    @Override
    public void getUpdatedInfo(OSDDetails args, StreamObserver<OSDInSamePaG> resp) {
        OSDInSamePaG.Builder em = OSDInSamePaG.newBuilder();
        CrushMap map = CrushMapService.getInstance().getLatestMap();
        this.getOSDsSameAsMyPG(map, args, em);
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
