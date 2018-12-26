package GrupoA.StorageController.gRPCService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GRPCServer<T extends io.grpc.BindableService> {

    io.grpc.Server server;
    public GRPCServer(io.grpc.Server server) {
        this.server = server;
    }
    public void start() throws Exception {
        this.server.start();
        System.err.println("[FileSystemServer] start");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("[FileSystemServer] shutting down gRPC server since JVM is shutting down");
                GRPCServer.this.stop();
                System.err.println("[FileSystemServer] server shut down");
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}