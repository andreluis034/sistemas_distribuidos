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
        map.put(AttributeUpdateRequest.UpdateType.CHUID, ProtoAttributeUpdateRequestType.CHUID);
        map.put(AttributeUpdateRequest.UpdateType.CHGID, ProtoAttributeUpdateRequestType.CHGID);
        map.put(AttributeUpdateRequest.UpdateType.UPDATEACCESSTIME, ProtoAttributeUpdateRequestType.UPDATEACCESSTIME);
        map.put(AttributeUpdateRequest.UpdateType.CHANGE_SIZE, ProtoAttributeUpdateRequestType.CHANGE_SIZE);
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

    public Boolean CreateFile(String path, long mode, long uid, long gid, long permission,
                              long creationTime, RedundancyProto redundancyProto) {
        return this.blockingStub.createNode(NodeArgs.newBuilder()
                .setPath(path)
                .setMode(mode)
                .setUid(uid)
                .setGid(gid)
                .setType(FileType.FILE)
                .setPermissions(permission)
                .setCreationTime(creationTime)
                .setRedundancy(redundancyProto)
                .build()).getResult();
    }

    public Boolean CreateDir(String path, long mode, long uid, long gid, long permission, long creationTime) {
        return this.blockingStub.createNode(NodeArgs.newBuilder()
                .setPath(path)
                .setMode(mode)
                .setUid(uid)
                .setGid(gid)
                .setType(FileType.DIR)
                .setPermissions(permission)
                .setCreationTime(creationTime)
                .build()).getResult();
    }

    public int RemoveDir(String path) {
        return this.blockingStub.rmDir(pathOnlyArgs.newBuilder().setFilePath(path).build()).getResult();
    }

    public int RemoveFile(String path) {
        return this.blockingStub.rmFile(pathOnlyArgs.newBuilder().setFilePath(path).build()).getResult();
    }

    public LockResponse SetLock(String path, long id, long version) {
        return this.blockingStub.setLock(LockArgs.newBuilder()
                .setPath(path)
                .setId(id)
                .setCrushMapVersion(version)
                .setCurrentTime(System.currentTimeMillis())
                .build());
    }

    public LockResponse UpdateLock(String path, long id, long version) {
        return this.blockingStub.updateLock(LockArgs.newBuilder()
                .setPath(path)
                .setId(id)
                .setCrushMapVersion(version)
                .setCurrentTime(System.currentTimeMillis())
                .build());
    }

    public LockResponse ReleaseLock(String path, long id) {
        return this.blockingStub.releaseLock(LockArgs.newBuilder()
                .setPath(path)
                .setId(id)
                .setCurrentTime(System.currentTimeMillis())
                .build());
    }

    public CrushMapResponse GetLatestCrushMap() {
        return this.blockingStub.getLatestMap(EmptyMessage.newBuilder().build());
    }

}
