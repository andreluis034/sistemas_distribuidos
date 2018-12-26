package GrupoA.StorageController.gRPCService;

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
