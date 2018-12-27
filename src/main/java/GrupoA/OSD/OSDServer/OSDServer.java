package GrupoA.OSD.OSDServer;

import GrupoA.OSD.OSDService.*;

import GrupoA.StorageController.gRPCService.OSDListenerClient;
import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class OSDImpl extends OSDGrpc.OSDImplBase {

    enum State {
        Initializing,
        Ready
    }
    State state = State.Initializing;


    @Override
    public void putObject(ObjectData request, StreamObserver<EmptyMessage> responseObserver) {
        EmptyMessage reply = EmptyMessage.newBuilder().build();

        if(state == State.Initializing) {
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            return;
        }
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

    @Override
    public void getObject(GetObjectArgs args, StreamObserver<ObjectData> responseObserver) {
        ObjectData.Builder reply = ObjectData.newBuilder();

        try {
            Path path = Paths.get( Long.toHexString(args.getHash()));
            byte[] fileContents =  Files.readAllBytes(path);
            reply
                    .setHash(args.getHash())
                    .setObjectData(ByteString.copyFrom(fileContents));
        } catch (Exception e) { //TODO FILE NOT FOUND

        }
        responseObserver.onNext(reply.build());
        responseObserver.onCompleted();
    }

    @Override
    public void ping(EmptyMessage args, StreamObserver<BooleanMessage> response) {
        response.onNext(BooleanMessage.newBuilder().setResult(true).build());
        response.onCompleted();
    }


}


public class OSDServer {

    private Server server;
    private OSDImpl impl = null;

    public OSDServer() {
        this.impl = new OSDImpl();
    }
    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(impl)
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
        OSDListenerClient client = new OSDListenerClient("192.168.10.70");
        client.announce(args[0], 50051);
        server.blockUntilShutdown();
    }
}
