package GrupoA.OSD.OSDClient;

import GrupoA.OSD.OSDService.GetObjectArgs;
import GrupoA.OSD.OSDService.ObjectData;
import GrupoA.OSD.OSDService.OSDGrpc;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.nio.ByteBuffer;
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
    public void putObject(byte[] data, long hash){
        this.putObject(ObjectData.newBuilder().setHash(hash).setObjectData(ByteString.copyFrom(data)).build());
    }

    private ObjectData getObject(long hash) {
        return this.blockingStub.getObject(GetObjectArgs.newBuilder().setHash(hash).build());
    }

    public ObjectData getObject(String path, int part) {
        long pathHash = GrupoA.Utility.Jenkins.hash64(path.getBytes());
        long finalHash = GrupoA.Utility.Jenkins.hash64(HashToBytes(pathHash, part));
        return this.getObject(finalHash);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}

