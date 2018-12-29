package GrupoA.StorageController.gRPCService;

import GrupoA.StorageController.Crush.CrushMap;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.Crush.PlacementGroup;
import GrupoA.StorageController.FileSystem.FSTree;
import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;
import GrupoA.StorageController.RaftServices.FileSystem.FileSystemService;
import GrupoA.StorageController.gRPCService.FileSystem.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.List;

import static GrupoA.AppServer.Models.CreateRequest.CreateRequestType.FILE;

class FileSystemServiceImpl extends FileSystemGrpc.FileSystemImplBase {

    @Override
    public void rmDir(pathOnlyArgs request, StreamObserver<IntArg> response) {
        IntArg.Builder reply = IntArg.newBuilder().setResult(-1);
        try {
            reply.setResult(FileSystemService.getInstance().removeDirectory(request.getFilePath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.onNext(reply.build());
        response.onCompleted();
    }

    @Override
    public void getAttr(pathOnlyArgs request, StreamObserver<iNodeAttributes> responseObserver) { //TODO don't dirty read from tree?
        iNodeAttributes.Builder reply = iNodeAttributes.newBuilder();
        reply.setRedundancy(RedundancyProto.None);
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
            reply.setSize(0);
            if(node.getNodeType().equals(FSTree.NodeType.FileNode)){
                reply.setRedundancy(((FSTree.FileNode)node).RedundancyType.toProto());
                reply.setSize(((FSTree.FileNode) node).fileSize);
                System.out.println(((FSTree.FileNode) node).fileSize);
            }
            reply.setCtime(node.creationTime);
            reply.setMtime(node.modifiedTime);
            reply.setAtime(node.accessTime);
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
    public void createNode(NodeArgs args, StreamObserver<BooleanMessage> rsp) {
        BooleanMessage.Builder status = BooleanMessage.newBuilder();
        status.setResult(false);
        try {
            if (args.getType().equals(FileType.FILE)) {
                status.setResult(FileSystemService.getInstance()
                        .mkFile(
                                args.getPath(), args.getMode(),
                                args.getUid(), args.getGid(), args.getPermissions(),
                                args.getCreationTime(), FSTree.FileNode.Redundancy.fromProto(args.getRedundancy())));
            } else {
                status.setResult(FileSystemService.getInstance()
                        .mkDir(
                                args.getPath(), args.getMode(),
                                args.getUid(), args.getGid(), args.getPermissions(),
                                args.getCreationTime()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        rsp.onNext(status.build());
        rsp.onCompleted();
    }

    @Override
    public void getLatestMap(EmptyMessage args, StreamObserver<CrushMapResponse> rsp ){
        CrushMapResponse.Builder builder = CrushMapResponse.newBuilder().setVersion(-1);
        try {
            CrushMap cm = CrushMapService.getInstance().getLatestMap();
            for (PlacementGroup pg : cm.getPGs()) {
                CrushMapResponse.PlacementGroupProto.Builder pgBuilder = CrushMapResponse.PlacementGroupProto.newBuilder();
                pgBuilder.setPGNumber(pg.getPgID());
                for(ObjectStorageDaemon osd : pg.getOSDs()){
                    CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto.Builder
                            osdBuilder = CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto.newBuilder();
                    osdBuilder.setAddress(osd.getAddress()).setIsLeader(osd.isLeader);
                    pgBuilder.addOSDs(osdBuilder);
                }

                builder.addPGs(pgBuilder);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        rsp.onNext(builder.build());
        rsp.onCompleted();

    }

    @Override
    public void setWriteLock(LockArgs args, StreamObserver<LockResponse> rsp) {
        LockResponse.Builder builder = LockResponse.newBuilder().setResult(false).setMapOutdated(true);
        try {
            CrushMap cm = CrushMapService.getInstance().getLatestMap();
            if (cm.getVersion() == args.getCrushMapVersion()) { //TODO locks
                builder.setResult(true).setMapOutdated(false);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        rsp.onNext(builder.build());
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
