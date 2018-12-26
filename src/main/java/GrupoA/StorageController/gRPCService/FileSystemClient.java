package GrupoA.StorageController.gRPCService;

import GrupoA.AppServer.Models.AttributeUpdateRequest;
import GrupoA.AppServer.Models.DirectoryContents;
import GrupoA.StorageController.gRPCService.FileSystem.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.HashMap;

public class FileSystemClient {

    private final ManagedChannel channel;
    private final FileSystemGrpc.FileSystemBlockingStub blockingStub;

    private final static HashMap<AttributeUpdateRequest.UpdateType, ProtoAttributeUpdateRequestType> map = new HashMap<>();

    static {
        map.put(AttributeUpdateRequest.UpdateType.CHMOD, ProtoAttributeUpdateRequestType.CHMOD);
        map.put(AttributeUpdateRequest.UpdateType.UPDATEACCESSTIME, ProtoAttributeUpdateRequestType.UPDATEACCESSTIME);
    }

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

    public iNodeAttributes GetAttributes(String path) {
        return this.blockingStub.getAttr(pathOnlyArgs.newBuilder().setFilePath(path).build());
    }

    public boolean UpdateAttribute(String path, long value, AttributeUpdateRequest.UpdateType updateType) {
        return this.blockingStub.setAttr(UpdateAttribute.newBuilder()
                .setPath(path)
                .setValue(value)
                .setType(map.get(updateType)).build()).getResult();
    }

    public DirectoryContents ReadDir(String path) {
        DirContents cont =  this.blockingStub.readDir(pathOnlyArgs.newBuilder().setFilePath(path).build());
        return DirectoryContents.fromDirContents(cont);
    }

    public Boolean Create(String path, long mode, long uid, long gid) {
        return this.blockingStub.createFile(CreateFileArgs.newBuilder()
                .setPath(path)
                .setMode(mode)
                .setUid(uid)
                .setGid(gid)
                .build()).getResult();
    }

}
