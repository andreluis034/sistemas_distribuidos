package GrupoA.ConfigurationHandler;

import GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigHandlerGrpc;
import GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse;
import GrupoA.ConfigurationHandler.ConfigHandlerService.Requester;
import GrupoA.Utility.Jenkins;
import io.grpc.stub.StreamObserver;
import org.jgroups.protocols.raft.CLIENT;
import org.jgroups.util.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ConfigurationHandlerImpl extends ConfigHandlerGrpc.ConfigHandlerImplBase {

    private List<String> FileSystemMembers = new LinkedList<>();
    private static final Integer FileSystemPort = 45588;
    private static final Integer ClientFileSystemPort = 1965;
    private List<String> CrushMapMembers = new LinkedList<>();
    private String Config;
    private static final Integer CrushMapPort = 45589;
    private static final Integer ClientCrushMapPort = 1966;

    public ConfigurationHandlerImpl(boolean useGoogleConfig){
        if(useGoogleConfig)
            Config = GOOGLE_CLOUD;
        else
            Config = WITH_MULTICAST;
        System.out.println("Using the following config:");
        System.out.println(Config);
    }

    private static String hashString(String string) {
        return Long.toHexString(Jenkins.hash64(string.getBytes()));
    }

    private String generateConfig(String[] members, String member, Integer port, Integer clientPort) {
        return Config
                .replace("${RAFT_PORT}", port.toString())
                .replace("${MEMBERS}", String.join(",", members))
                .replace("${RAFT_ID}", member)
                .replace("${CLIENT_PORT}", clientPort.toString());
    }
    private static String[] getMembers(List<String> serviceMembers, String prefix) {
        String[] members = new  String[serviceMembers.size()];
        serviceMembers.toArray(members);
        for(int i = 0; i < members.length; ++i) {
            members[i] =  hashString(prefix + members[i]);
        }
        return members;
    }
    private String generateFileSystemConfig(String member) {
        String[] members = getMembers(FileSystemMembers, "FS_");
        return generateConfig(members, hashString("FS_" + member), FileSystemPort, ClientFileSystemPort);
    }
    private String generateCrushMapConfig(String member) {
        String[] members = getMembers(FileSystemMembers, "CM_");
        return generateConfig(members, hashString("CM_" + member), CrushMapPort, ClientCrushMapPort);
    }


    private void addRemoveServer(String activeServerIP, String serverID, CLIENT.RequestType type, int port) throws Throwable {
        try(Socket sock=new Socket(activeServerIP, port);
            DataInputStream in=new DataInputStream(sock.getInputStream());
            DataOutputStream out=new DataOutputStream(sock.getOutputStream())) {

            out.writeByte((byte)type.ordinal());
            byte[] buf=Util.stringToBytes(serverID);
            out.writeInt(buf.length);
            out.write(buf, 0, buf.length);

            type=CLIENT.RequestType.values()[in.readByte()];
            if(type != CLIENT.RequestType.rsp)
                throw new IllegalStateException(String.format("expected type %s but got %s", CLIENT.RequestType.rsp, type));
            int len=in.readInt();
            if(len == 0)
                return;
            buf=new byte[len];
            in.readFully(buf);

            Object response=Util.objectFromByteBuffer(buf);
            if(response instanceof Throwable)
                throw (Throwable)response;
            System.out.println("response = " + response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void joinFileSystemConfig(Requester request, StreamObserver<ConfigReponse> responseObserver) {
        ConfigReponse.Builder builder = ConfigReponse.newBuilder();
        builder.setConfiguration("");
        builder.setId(hashString("FS_" + request.getIp()));
        if(!FileSystemMembers.contains(request.getIp())) {
            try {
                if(FileSystemMembers.size() >= 2){
                    for (int i = 0; i < 3; ++i){
                        try { //Wait for leader to get selected
                            addRemoveServer(FileSystemMembers.get(0), builder.getId(),
                                    CLIENT.RequestType.add_server, ClientFileSystemPort);
                            break;
                        } catch (Exception e) {
                            if(i + 1 == 3) {
                                throw e;
                            }
                            Thread.sleep(1000);
                        }
                    }
                }

                FileSystemMembers.add(request.getIp());
            } catch (Throwable throwable) {
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                return;
            }
        }
        builder.setMemberCount(FileSystemMembers.size());
        builder.setConfiguration(generateFileSystemConfig(request.getIp()));
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void joinCrushMapConfig(Requester request, StreamObserver<ConfigReponse> responseObserver) {
        ConfigReponse.Builder builder = ConfigReponse.newBuilder();
        builder.setConfiguration("");
        builder.setId(hashString("CM_" + request.getIp()));
        if(!CrushMapMembers.contains(request.getIp())) {
            try {
                if(CrushMapMembers.size() >= 2){
                    for (int i = 0; i < 3; ++i){
                        try {
                            addRemoveServer(CrushMapMembers.get(0), builder.getId(),
                                    CLIENT.RequestType.add_server, ClientCrushMapPort);
                            break;
                        } catch (Exception e) {
                            if(i + 1 == 3) {
                                throw e;
                            }
                            Thread.sleep(1000);
                        }
                    }
                }
                CrushMapMembers.add(request.getIp());
            } catch (Throwable throwable) {
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                return;
            }
        }
        builder.setMemberCount(CrushMapMembers.size());
        builder.setConfiguration(generateCrushMapConfig(request.getIp()));
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void leaveFileSystemConfig(Requester request, StreamObserver<ConfigReponse> responseObserver) {
        String id = hashString("FS_" + request.getIp());

        if(FileSystemMembers.contains(request.getIp())) {
            FileSystemMembers.remove(request.getIp());
            if(FileSystemMembers.size() >= 2) {
                try {
                    addRemoveServer(FileSystemMembers.get(0), id,
                            CLIENT.RequestType.remove_server, ClientFileSystemPort);
                } catch (Throwable ignored) {
                }
            }
        }

        responseObserver.onNext(ConfigReponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void leaveCrushMapConfig(Requester request, StreamObserver<ConfigReponse> responseObserver) {
        String id = hashString("CM_" + request.getIp());

        if(CrushMapMembers.contains(request.getIp())) {
            CrushMapMembers.remove(request.getIp());
            if(CrushMapMembers.size() >= 2) {
                try {
                    addRemoveServer(CrushMapMembers.get(0), id,
                            CLIENT.RequestType.remove_server, ClientCrushMapPort);
                } catch (Throwable ignored) {
                }
            }
        }

        responseObserver.onNext(ConfigReponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    private static final String GOOGLE_CLOUD = "<config xmlns=\"urn:org:jgroups\"\n" +
            "        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "        xsi:schemaLocation=\"urn:org:jgroups http://www.jgroups.org/schema/jgroups.xsd\">\n" +
            "    <TCP bind_port=\"${RAFT_PORT}\"/>\n" +
            "   <GOOGLE_PING\n" +
            "                location=\"jgroups-raft\"\n" +
            "                access_key=\"GOOGXRKOX2T46TQABPO5ZC23\"\n" +
            "                secret_access_key=\"GCz0SOVWwxfpiIKJsoBInMemeqLENWWghXEhka84\"/>\n" +
            "    <MERGE3 />\n" +
            "    <FD_SOCK/>\n" +
            "    <FD_ALL/>\n" +
            "    <VERIFY_SUSPECT timeout=\"1500\"  />\n" +
            "    <pbcast.NAKACK2 xmit_interval=\"500\"/>\n" +
            "    <UNICAST3 xmit_interval=\"500\"/>\n" +
            "    <pbcast.STABLE desired_avg_gossip=\"50000\"\n" +
            "                   max_bytes=\"4M\"/>\n" +
            "    <raft.NO_DUPES/>\n" +
            "    <pbcast.GMS print_local_addr=\"true\" join_timeout=\"2000\"/>\n" +
            "    <UFC max_credits=\"2M\" min_threshold=\"0.4\"/>\n" +
            "    <MFC max_credits=\"2M\" min_threshold=\"0.4\"/>\n" +
            "    <FRAG2 frag_size=\"60K\"  />\n" +
            "    <raft.ELECTION election_min_interval=\"100\" election_max_interval=\"500\"/>\n" +
            "    <raft.RAFT members=\"${MEMBERS}\" raft_id=\"${RAFT_ID}\"/>\n" +
            "    <raft.REDIRECT/>\n" +
            "    <raft.CLIENT bind_addr=\"0.0.0.0\" port=\"${CLIENT_PORT}\" />\n" +
            "</config>";

    private static final String WITH_MULTICAST   ="<config xmlns=\"urn:org:jgroups\"\n"+
            "        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"+
            "        xsi:schemaLocation=\"urn:org:jgroups http://www.jgroups.org/schema/jgroups.xsd\">\n"+
            "    <UDP\n"+
            "         mcast_addr=\"228.5.5.6\"\n"+
            "         mcast_port=\"${jgroups.udp.mcast_port:${RAFT_PORT}}\"/>\n"+
            "    <PING />\n"+
            "    <MERGE3 />\n"+
            "    <FD_SOCK/>\n"+
            "    <FD_ALL/>\n"+
            "    <VERIFY_SUSPECT timeout=\"1500\"  />\n"+
            "    <pbcast.NAKACK2 xmit_interval=\"500\"/>\n"+
            "    <UNICAST3 xmit_interval=\"500\"/>\n"+
            "    <pbcast.STABLE desired_avg_gossip=\"50000\"\n"+
            "                   max_bytes=\"4M\"/>\n"+
            "    <raft.NO_DUPES/>\n"+
            "    <pbcast.GMS print_local_addr=\"true\" join_timeout=\"2000\"/>\n"+
            "    <UFC max_credits=\"2M\" min_threshold=\"0.4\"/>\n"+
            "    <MFC max_credits=\"2M\" min_threshold=\"0.4\"/>\n"+
            "    <FRAG2 frag_size=\"60K\"  />\n"+
            "    <raft.ELECTION election_min_interval=\"100\" election_max_interval=\"500\"/>\n"+
            "    <raft.RAFT members=\"${MEMBERS}\" raft_id=\"${RAFT_ID}\"/>\n"+
            "    <raft.REDIRECT/>\n"+
            "    <raft.CLIENT bind_addr=\"0.0.0.0\" port=\"${CLIENT_PORT}\" />\n"+
            "</config>";

}
