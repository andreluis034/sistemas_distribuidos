package GrupoA.StorageController.gRPCService;

import GrupoA.StorageController.gRPCService.FileSystem.FileSystemGrpc;
import GrupoA.StorageController.gRPCService.OSDListener.OSDDetails;
import GrupoA.StorageController.gRPCService.OSDListener.OSDListenerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

public class OSDListenerClient {
    private final ManagedChannel channel;
    private final OSDListenerGrpc.OSDListenerBlockingStub blockingStub;

    public OSDListenerClient(String host) {
        this(host, OSDListenerServiceImpl.DEFAULT_PORT);
    }

    public OSDListenerClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                //.maxInboundMessageSize((int) (Math.pow(2,20) + Math.pow(2,16)))
                .build());
    }

    public OSDListenerClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = OSDListenerGrpc.newBlockingStub(channel);
    }

    public List<OSDDetails> announce(String address, int port) {
        return this.blockingStub.receiveAnnouncement(OSDDetails.newBuilder().setAddress(address).setPort(port).build()).getOSDsList();
    }
}
