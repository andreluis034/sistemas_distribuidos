package OSD;

import io.grpc.stub.StreamObserver;

public class OSDImpl extends OSDGrpc.OSDImplBase {

    @Override
    public void putFile(FileData request, StreamObserver<EmptyMessage> responseObserver) {
        request.getHash();
        super.putFile(request, responseObserver);
    }
}
