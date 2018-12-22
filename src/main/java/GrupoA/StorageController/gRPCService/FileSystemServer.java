package GrupoA.StorageController.gRPCService;

import GrupoA.StorageController.RaftServices.FileSystemService;
import GrupoA.StorageController.gRPCService.FileSystem.*;
import io.grpc.stub.StreamObserver;

class FileSystemServiceImpl extends FileSystemGrpc.FileSystemImplBase {
    @Override
    public void mkDir(pathOnlyArgs request, StreamObserver<BooleanMessage> responseObserver) {

    }

    @Override
    public void getAttr(IntArg request, StreamObserver<iNodeAttributes> responseObserver) { //TODO don't dirty read from tree?
        //FileSystemService.getInstance()
    }

}

public class FileSystemServer {
}
