package GrupoA.StorageController.gRPCService;

import GrupoA.StorageController.gRPCService.FileSystem.FileSystemGrpc;
import GrupoA.StorageController.gRPCService.FileSystem.LongArg;
import GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class FileSystemClient {

    private final ManagedChannel channel;
    private final FileSystemGrpc.FileSystemBlockingStub blockingStub;

    public FileSystemClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                //.maxInboundMessageSize((int) (Math.pow(2,20) + Math.pow(2,16)))
                .build());
    }

    public FileSystemClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = FileSystemGrpc.newBlockingStub(channel);
    }

    public iNodeAttributes GetAttributes(long iNode) {
        return this.blockingStub.getAttr(LongArg.newBuilder().setINode(iNode).build());
    }
}
