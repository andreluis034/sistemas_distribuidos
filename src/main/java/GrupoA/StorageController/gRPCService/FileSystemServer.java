package GrupoA.StorageController.gRPCService;

import GrupoA.StorageController.FileSystem.FSTree;
import GrupoA.StorageController.RaftServices.FileSystem.FileSystemService;
import GrupoA.StorageController.gRPCService.FileSystem.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.List;

class FileSystemServiceImpl extends FileSystemGrpc.FileSystemImplBase {
    @Override
    public void mkDir(pathOnlyArgs request, StreamObserver<BooleanMessage> responseObserver) { //TODO

    }

    @Override
    public void getAttr(pathOnlyArgs request, StreamObserver<iNodeAttributes> responseObserver) { //TODO don't dirty read from tree?
        iNodeAttributes.Builder reply = iNodeAttributes.newBuilder();
        try {
            FSTree.Node node =  FileSystemService.getInstance().getNode(request.getFilePath());//.getNode(request.getNode());
            if (node == null) {
                reply.setINodeNumber(-1);
                responseObserver.onNext(reply.build());
                responseObserver.onCompleted();
                return;
            }
            reply.setName(node.getNodeName());
            reply.setFileType( node.getNodeType() == FSTree.NodeType.FileNode ? FileType.FILE : FileType.DIR);
            reply.setUserPermissions(node.UserPermissions);
            reply.setGroupPermissions(node.GroupPermissions);
            reply.setOtherPermissions(node.OthersPermissions);
            reply.setOwnerId(node.UserId);
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

    @Override
    public void setAttr(UpdateAttribute request,  StreamObserver<BooleanMessage> responseObserver){
        BooleanMessage.Builder status = BooleanMessage.newBuilder();
        status.setResult(false);
        try {
            status.setResult(FileSystemService.getInstance().updateAttribute(request.getPath(), request));
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseObserver.onNext(status.build());
        responseObserver.onCompleted();
    }

    @Override
    public void readDir(pathOnlyArgs args, StreamObserver<DirContents> response) {
        DirContents.Builder status = DirContents.newBuilder();
        status.setSuccess(false);
        try {
            List<FSTree.Node> nodes = FileSystemService.getInstance().readDir(args.getFilePath());
            if(nodes.size() != 0) {
                status.setINode(nodes.get(0).iNode);
                status.setParentINode(nodes.get(0).getParentINode());
                for (FSTree.Node node: nodes) {
                    DirContents.Content.Builder contentBuilder = DirContents.Content.newBuilder();
                    contentBuilder.setContentName(node.getNodeName());
                    contentBuilder.setINode(node.iNode);
                    contentBuilder.setContentFileType(node.getNodeType() == FSTree.NodeType.DirNode ? FileType.DIR : FileType.FILE);
                    status.addContents(contentBuilder);
                }
                status.setSuccess(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.onNext(status.build());
        response.onCompleted();
    }

    @Override
    public void createFile(CreateFileArgs args, StreamObserver<BooleanMessage> rsp) {

        BooleanMessage.Builder status = BooleanMessage.newBuilder();
        status.setResult(false);
        try {
            status.setResult(FileSystemService.getInstance()
                    .mkFile(
                            args.getPath(), args.getMode(),
                            args.getUid(), args.getGid()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        rsp.onNext(status.build());
        rsp.onCompleted();
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
    }
}
