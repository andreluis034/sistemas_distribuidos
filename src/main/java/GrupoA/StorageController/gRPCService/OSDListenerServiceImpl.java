package GrupoA.StorageController.gRPCService;

import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;
import GrupoA.StorageController.gRPCService.OSDListener.EmptyMessage;
import GrupoA.StorageController.gRPCService.OSDListener.OSDDetails;
import GrupoA.StorageController.gRPCService.OSDListener.OSDListenerGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class OSDListenerServiceImpl extends OSDListenerGrpc.OSDListenerImplBase {
    @Override
    public void receiveAnnouncement(OSDDetails args, StreamObserver<EmptyMessage> resp) {
        EmptyMessage em = EmptyMessage.newBuilder().build();
        OSDClient client = new OSDClient(args.getAddress(), args.getPort());
        if(client.ping()) {
            CrushMapService.getInstance().addOSD(new ObjectStorageDaemon(args.getAddress()+":"+args.getPort()));
        } else {
            System.out.println("Received request from OSD " + args.getAddress() + ":"+args.getPort()
                    + "to join but can't verify its availability reach it");
        }

        resp.onNext(em);
        resp.onCompleted();
    }

    public static final int DEFAULT_PORT = 50053;

    public static Server getServer(int port){
            return ServerBuilder.forPort(port)
                    .addService(new OSDListenerServiceImpl())
                    .build();
    }
}
