package GrupoA.StorageController;

import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;
import GrupoA.StorageController.RaftServices.FileSystem.FileSystemService;
import GrupoA.StorageController.gRPCService.FileSystemServer;
import GrupoA.StorageController.gRPCService.GRPCServer;
import GrupoA.StorageController.gRPCService.OSDListener.OSDListenerGrpc;
import GrupoA.StorageController.gRPCService.OSDListenerServiceImpl;
import org.jgroups.util.Util;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EntryPoint {
    private static List<String> getLocalAddresses() throws SocketException {
        List<String> addresses = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
            while(inetAddress.hasMoreElements())
            {
                InetAddress currentAddress;
                currentAddress = inetAddress.nextElement();
                if(currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress())
                {
                    //System.out.println(currentAddress.toString().replace("/", ""));
                    addresses.add(currentAddress.toString().replace("/", ""));
                }
            }
        }

        return addresses;
    }

    protected static void loop(FileSystemService service) throws Exception {
        boolean looping=true;
        while(looping) {
            try {
                int key=Util.keyPress("[1] mkDir [2] ls\n");

                switch(key) {
                    case '1':
                        System.out.println("mkDir");
                        boolean val = service.mkDir("/etc"
                                + ThreadLocalRandom.current().nextInt(0, (int) Math.pow(2,16)),
                                0, 1000, 1000, 0x1FD);
                        System.out.printf("%s: %s\n", "ls", val);
                        break;
                    case '2':
                        System.out.println("ls");
                        List<String> nodes = service.ls("/");
                        for (String node :
                                nodes) {
                            System.out.println(node);
                        }
                        break;
                    case 'x':
                        looping=false;
                        break;
                }
            }
            catch(Throwable t) {
                System.err.println(t.toString());
            }
        }
    }

    private static void startOSDListener() throws Exception {
        GRPCServer server = new GRPCServer(OSDListenerServiceImpl.getServer(OSDListenerServiceImpl.DEFAULT_PORT));
        server.start();
    }

    private static void startRaft(String raftId) throws Exception{
        FileSystemService service = FileSystemService.getInstance("./fileSystem.xml", raftId);
        CrushMapService crushMapService = CrushMapService.getInstance("./crushmap.xml", raftId + "0");
        loop(service);
    }

    public static void main(String[] args) throws Exception {
        startOSDListener();
        FileSystemServer fss = new FileSystemServer();
        fss.start();
        startRaft(args[0]);
    }

    public static void foo(){
       // AtomixClient
    }
}
