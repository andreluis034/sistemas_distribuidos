package GrupoA.OSD.OSDClient;

import GrupoA.AppServer.Routes.FileRoute;
import GrupoA.OSD.OSDService.*;
import GrupoA.StorageController.Crush.ObjectStorageDaemon;
import GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OSDClient {
    private final ManagedChannel channel;
    private final OSDGrpc.OSDBlockingStub blockingStub;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public OSDClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                //.maxInboundMessageSize((int) (Math.pow(2,20) + Math.pow(2,16)))
                .build());
    }

    /** Construct client for accessing HelloWorld server using the existing channel. */
    OSDClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = OSDGrpc.newBlockingStub(channel);
    }
    private void putObject(ObjectData od){

        this.blockingStub.putObject(od);
    }

    private static byte[] HashToBytes(long a, int c) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES + Integer.BYTES);
        buffer.putLong(a);
        buffer.putInt(c);
        return buffer.array();
    }
    // 1235324534534_5345534534534
    // A2FFD3_DSDS354_1
    public void putObject(byte[] data, String path, int part) {
       // long dataHash = GrupoA.Utility.Jenkins.hash64(data);
        long pathHash = GrupoA.Utility.Jenkins.hash64(path.getBytes());
        long finalHash = GrupoA.Utility.Jenkins.hash64(HashToBytes(pathHash, part));

        this.putObject(data, finalHash);
    }

    public void putObject(byte[] data, long hash) {
      //  this.putObject(ObjectData.newBuilder().setHash(hash).setObjectData(ByteString.copyFrom(data)).build());
    }

    private ObjectData getObject(long hash) {
        return this.blockingStub.getObject(GetObjectArgs.newBuilder().setHash(hash).build());
    }

    public ObjectData getObject(String path, int part) {
        long pathHash = GrupoA.Utility.Jenkins.hash64(path.getBytes());
        long finalHash = GrupoA.Utility.Jenkins.hash64(HashToBytes(pathHash, part));
        return this.getObject(finalHash);
    }

    public void deleteObject(Long hash, boolean hasDuplicate) {
        this.blockingStub.deleteObject(GetObjectArgs.newBuilder().setHash(hash).setHasDuplicate(hasDuplicate).build());
    }

    public void WriteMiniObject(Long hash, FileRoute.WriteBlockData wbd) {
        this.WriteMiniObject(MiniObject.newBuilder()
                .setHash(hash)
                .setObjectData(ByteString.copyFrom(wbd.Data))
                .setStartOffset(wbd.startRelativeOffset)
                .setEndOffset(wbd.endRelativeOffset)
                .setDuplicate(wbd.red.equals(RedundancyProto.Replication))
                .build());
    }

    public void WriteMiniObject(MiniObject mo) {
        this.blockingStub.writeMiniObject(mo);
    }

    public ByteString ReadMiniObject(long hash, long relativeOffset, long size) {
        return this.blockingStub.readMiniObject(GetObjectArgs.newBuilder()
                .setHash(hash)
                .setRelativeOffset(relativeOffset)
                .setSize(size).
                        build())
                .getObjectData();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void shutdownNow() throws InterruptedException {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void awaitTermination() throws InterruptedException {
        channel.awaitTermination(5, TimeUnit.SECONDS);
    }

    public void ForceUpdate(List<ObjectStorageDaemon> OSDs) {
        OSDInSamePaG.Builder builder = OSDInSamePaG.newBuilder();
        for (ObjectStorageDaemon osd : OSDs) {
            OSDDetails.Builder osdBuilder = OSDDetails.newBuilder();
            osdBuilder.setAddress(osd.getHost()).setPort(osd.getPort()).setLeader(osd.isLeader);
            builder.addOSDs(osdBuilder);
        }
        this.blockingStub.pushMapUpdate(builder.build());
    }
    public boolean ping() {
        try {
            return this.blockingStub.ping(EmptyMessage.newBuilder().build()).getResult();
        } catch (Exception e) {
            return false;
        }
    }
}

