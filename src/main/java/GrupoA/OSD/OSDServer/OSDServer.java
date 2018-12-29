package GrupoA.OSD.OSDServer;

import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.OSD.OSDService.*;

import GrupoA.StorageController.gRPCService.OSDListenerClient;
import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

class OSDImpl extends OSDGrpc.OSDImplBase {

    enum State {
        Initializing,
        Ready
    }
    State state = State.Initializing;

    @Override
    public void ping(EmptyMessage args, StreamObserver<BooleanMessage> response) {
        response.onNext(BooleanMessage.newBuilder().setResult(true).build());
        response.onCompleted();
    }

    @Override
    public void writeMiniObject(MiniObject args, StreamObserver<EmptyMessage> response) {
        EmptyMessage reply = EmptyMessage.newBuilder().build();
        if(OSDServer.getInstance().IsLeader && args.getDuplicate()) {
            for (OSDDetails osd : OSDServer.getInstance().OSDs) {
                if(osd.getLeader())
                    continue;
                OSDClient client = new OSDClient(osd.getAddress(), osd.getPort());
                client.WriteMiniObject(args);
                try {
                    client.shutdown();
                    client.awaitTermination();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            int size = args.getEndOffset() - args.getStartOffset();
            RandomAccessFile file = new RandomAccessFile(Long.toHexString(args.getHash()), "rw");
            file.seek(args.getStartOffset());
            file.write(args.getObjectData().toByteArray(), args.getStartOffset(), size);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.onNext(reply);
        response.onCompleted();
    }

    @Override
    public void pushMapUpdate(OSDInSamePaG args, StreamObserver<EmptyMessage> response) {
        EmptyMessage reply = EmptyMessage.newBuilder().build();

        OSDDetails osd = null;
        for (OSDDetails _osd : args.getOSDsList()){
            if (_osd.getPort() == OSDServer.myPort && _osd.getAddress().equals(OSDServer.myHost)) {
                osd = _osd;
                break;
            }
        }
        if(osd != null) {
            OSDServer.getInstance().OSDs = args.getOSDsList();
            OSDServer.getInstance().IsLeader = osd.getLeader();
            OSDServer.getInstance().printOSDs();
        }
        response.onNext(reply);
        response.onCompleted();
    }


    public void readMiniObject(GetObjectArgs args, StreamObserver<ObjectData> data) {
        ObjectData.Builder reply = ObjectData.newBuilder().setSuccess(false);

        try {
            byte[] dataBuff = new byte[(int) args.getSize()];
            RandomAccessFile file = new RandomAccessFile(Long.toHexString(args.getHash()), "rw");
            file.seek(args.getRelativeOffset());
            file.read(dataBuff);
            reply.setObjectData(ByteString.copyFrom(dataBuff));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.onNext(reply.build());
        data.onCompleted();
    }
    
    @Override
    public void deleteObject(GetObjectArgs args, StreamObserver<EmptyMessage> response) {
        EmptyMessage reply = EmptyMessage.newBuilder().build();

        File file = new File(Long.toHexString(args.getHash()));
        file.delete();

        response.onNext(reply);
        response.onCompleted();
    }
}


public class OSDServer {

    public List<OSDDetails> OSDs = new LinkedList<OSDDetails>();
    public boolean IsLeader = false;

    public static String myHost = "";
    public static int myPort = 50051;

    private Server server;
    private OSDImpl impl = null;
    private OSDListenerClient client;

    private static OSDServer instance = null;
    public static synchronized OSDServer getInstance() {
        if(instance == null)
            instance = new OSDServer();
        return instance;
    }

    public OSDServer() {
        this.impl = new OSDImpl();
        client = new OSDListenerClient("192.168.10.70");
    }
    private void start() throws IOException {
        /* The port on which the server should run */
        server = ServerBuilder.forPort(myPort)
                .addService(impl)
                .build()
                .start();
        System.err.println("*** server start");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    System.out.println("Announcing departure");
                    client.leave(myHost, myPort);
                    client.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                OSDServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
        System.out.println("Announcing "+ myHost + ":" + 50051 + " to " + "192.168.10.70");
        client.announce(myHost, myPort);

    }
    void printOSDs () {
        System.out.println(OSDs.size());

        for (OSDDetails osd : OSDs) {
            if(osd.getAddress().equals(myHost) && osd.getPort() == myPort){
                IsLeader = osd.getLeader();
            }
            System.out.println(osd.getAddress() + ":" + osd.getPort() + " leader: " + osd.getLeader());
        }
    }
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        myHost = args[0];
        OSDServer.getInstance().start();

        OSDServer.getInstance().blockUntilShutdown();
    }
}
