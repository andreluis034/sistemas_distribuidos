package GrupoA.OSD.OSDServer;

import GrupoA.OSD.OSDService.EmptyMessage;
import GrupoA.OSD.OSDService.FileData;
import GrupoA.OSD.OSDService.OSDGrpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.FileOutputStream;
import java.io.IOException;

class OSDImpl extends OSDGrpc.OSDImplBase {

    @Override
    public void putObject(FileData request, StreamObserver<EmptyMessage> responseObserver) {
        EmptyMessage reply = EmptyMessage.newBuilder().build();

        System.out.println(request.getObjectData().size());
        String hash = Long.toHexString(request.getHash());
        System.out.println(hash);

        try {
            FileOutputStream fos = new FileOutputStream(hash);
            fos.write(request.getObjectData().toByteArray());
            fos.close();
        } catch (Exception e) {

        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}


public class OSDServer {

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new OSDImpl())
                .build()
                .start();
        System.err.println("*** server start");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                OSDServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final OSDServer server = new OSDServer();
        server.start();
        server.blockUntilShutdown();
    }
}
