package GrupoA.ConfigurationHandler;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ConfigurationHandlerServer {
    public static final int DEFAULT_PORT = 7000;

    private Server server;

    public void start() throws IOException {
        /* The port on which the server should run */
        this.server = ServerBuilder.forPort(DEFAULT_PORT)
                .addService(new ConfigurationHandlerImpl())
                .build()
                .start();
        System.err.println("*** server start");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                ConfigurationHandlerServer.this.stop();
                System.err.println("*** server shut down");
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
