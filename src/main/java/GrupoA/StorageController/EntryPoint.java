package GrupoA.StorageController;

import GrupoA.ConfigurationHandler.ConfigHandlerService.ConfigReponse;
import GrupoA.ConfigurationHandler.ConfigurationHandlerClient;
import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;
import GrupoA.StorageController.RaftServices.FileSystem.FileSystemService;
import GrupoA.StorageController.gRPCService.FileSystemServer;
import GrupoA.StorageController.gRPCService.GRPCServer;
import GrupoA.StorageController.gRPCService.OSDListenerServiceImpl;
import org.jgroups.util.Util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EntryPoint {
    private static String getLocalAddress() throws SocketException {
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
                    return currentAddress.toString().replace("/", "");
                }
            }
        }
        return "";
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
                                0, 1000, 1000, 0x1FD, 0);
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

    private static void startRaft(String fileSystemID, String crushMapID) throws Exception{
        FileSystemService service = FileSystemService.getInstance("./fileSystem.xml", fileSystemID);
        CrushMapService crushMapService = CrushMapService.getInstance("./crushmap.xml", crushMapID);
        loop(service);
    }

    public static void write(String filename, String data) throws IOException {
        File file = new File(filename);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(data);
        fileWriter.close();
    }

    public static void main(String[] args) throws Exception {
        String ConfigManagerIP = args[0];
        String MyIP = getLocalAddress();
        ConfigurationHandlerClient client = new ConfigurationHandlerClient(ConfigManagerIP);
        ConfigReponse fsconfig = client.joinFileSystemConfig(MyIP);
        ConfigReponse cmconfig = client.joinCrushMapConfig(MyIP);
        if(fsconfig.getConfiguration().equals("") || fsconfig.getConfiguration().equals("")){
            System.out.println("Could not get configuration from Config Manager");
            return;
        }
        write("fileSystem.xml", fsconfig.getConfiguration());
        write("crushmap.xml", cmconfig.getConfiguration());
        startRaft(fsconfig.getId(), cmconfig.getId());
    }

    public static void foo(){
       // AtomixClient
    }
}
