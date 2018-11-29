package GrupoA.OSD.OSDClient;

import GrupoA.OSD.OSDService.FileData;
import GrupoA.OSD.OSDService.OSDGrpc;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

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
    private void putFile(FileData fd){

        this.blockingStub.putFile(fd);
    }
    public void putFile(byte[] data ) {
        this.putFile(data, GrupoA.Utility.Jenkins.hash64(data));
    }
    public void putFile(byte[] data, long hash){
        this.putFile(FileData.newBuilder().setHash(hash).setObjectData(ByteString.copyFrom(data)).build());
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
/*
    public static void main(String[] args) throws Exception {
        OSDClient client = new OSDClient("localhost", 50051);

        FileData fd = FileData.newBuilder().setHash("Cenas").setObjectData(ByteString.EMPTY).build();
        try {
            client.blockingStub.putFile(fd);

        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        } finally {
            client.shutdown();
        }
    }*/
}

