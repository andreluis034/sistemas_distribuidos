package GrupoA.ConfigurationHandler;

import GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigHandlerGrpc;
import GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse;
import GrupoA.ConfigurationHandler.ConfigHandlerService.Requester;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class ConfigurationHandlerClient {

    public static final int DEFAULT_PORT = ConfigurationHandlerServer.DEFAULT_PORT;
    private final ManagedChannel channel;
    private final ConfigHandlerGrpc.ConfigHandlerBlockingStub blockingStub;

    public ConfigurationHandlerClient(String host) {
        this(host, DEFAULT_PORT);
    }

    public ConfigurationHandlerClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                //.maxInboundMessageSize((int) (Math.pow(2,20) + Math.pow(2,16)))
                .build());
    }

    ConfigurationHandlerClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = ConfigHandlerGrpc.newBlockingStub(channel);
    }
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void shutdownNow() throws InterruptedException {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void awaitTermination() throws InterruptedException {
        channel.awaitTermination(5, TimeUnit.SECONDS);
    }


    public ConfigReponse joinFileSystemConfig(String IPAddress) {
        Requester.Builder req = Requester.newBuilder().setIp(IPAddress);
        return this.blockingStub.joinFileSystemConfig(req.build());
    }

    public ConfigReponse joinCrushMapConfig(String IPAddress) {
        Requester.Builder req = Requester.newBuilder().setIp(IPAddress);
        return this.blockingStub.joinCrushMapConfig(req.build());
    }

    public void leaveFileSystemConfig(String IPAddress) {
        this.blockingStub.leaveFileSystemConfig(Requester.newBuilder().setIp(IPAddress).build());
    }

    public void leaveCrushMapConfig(String IPAddress) {
        this.blockingStub.leaveCrushMapConfig(Requester.newBuilder().setIp(IPAddress).build());
    }
}
