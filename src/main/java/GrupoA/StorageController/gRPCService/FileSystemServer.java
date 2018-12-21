package GrupoA.StorageController.gRPCService;

import GrupoA.StorageController.gRPCService.FileSystem.FileSystemGrpc;
import GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage;
import GrupoA.StorageController.gRPCService.FileSystem.pathOnlyArgs;
import io.grpc.stub.StreamObserver;

class FileSystemServiceImpl  extends FileSystemGrpc.FileSystemImplBase {
    @Override
    public void mkDir(pathOnlyArgs request, WStreamObserver<BooleanMessage> responseObserver) {

    }

}

public class FileSystemServer {
}
