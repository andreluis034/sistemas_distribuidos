package GrupoA.StorageController.gRPCService;

import GrupoA.StorageController.FileSystem.FSTree;
import GrupoA.StorageController.RaftServices.FileSystem.FileSystemService;
import GrupoA.StorageController.gRPCService.FileSystem.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

class FileSystemServiceImpl extends FileSystemGrpc.FileSystemImplBase {
    @Override
    public void mkDir(pathOnlyArgs request, StreamObserver<BooleanMessage> responseObserver) {

    }

    @Override
    public void getAttr(LongArg request, StreamObserver<iNodeAttributes> responseObserver) { //TODO don't dirty read from tree?
        iNodeAttributes.Builder reply = iNodeAttributes.newBuilder();
        try {
            FSTree.Node node =  FileSystemService.getInstance().getINode(request.getINode());
            if (node == null) {
                reply.setINodeNumber(-1);
                responseObserver.onNext(reply.build());
                responseObserver.onCompleted();
                return;
            }
            reply.setName(node.getNodeName());
            reply.setUserPermissions(node.UserPermissions);
            reply.setGroupPermissions(node.GroupPermissions);
            reply.setOtherPermissions(node.OthersPermissions);
            reply.setOwnerId(node.OwnerId);
            reply.setGroupId(node.GroupId);
            reply.setINodeNumber(node.iNode);
            reply.setSize(node.getNodeType() == FSTree.NodeType.DirNode ? 0 : ((FSTree.FileNode)node).fileSize);
            reply.setParentINodeNumber(node.Parent == null ? 0 : node.Parent.iNode);


            responseObserver.onNext(reply.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //FileSystemService.getInstance()
    }

}

public class FileSystemServer {
    private Server server;
    public static final int DEFAULT_PORT = 50052;
    public void start() throws IOException {
        int port = DEFAULT_PORT;
        server = ServerBuilder.forPort(port)
                .addService(new FileSystemServiceImpl())
                .build().start();
        System.err.println("[FileSystemServer] start");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("[FileSystemServer] shutting down gRPC server since JVM is shutting down");
                FileSystemServer.this.stop();
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
    }}
