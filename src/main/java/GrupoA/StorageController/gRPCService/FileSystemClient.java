package GrupoA.StorageController.gRPCService;

import GrupoA.OSD.OSDService.OSDGrpc;
import GrupoA.StorageController.gRPCService.FileSystem.FileSystemGrpc;
import io.grpc.ManagedChannel;

public class FileSystemClient {

    private final ManagedChannel channel;
    private final FileSystemGrpc.FileSystemBlockingStub blockingStub;

    public FileSystemClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = FileSystemGrpc.newBlockingStub(channel);
    }
}
