package GrupoA.StorageController;

import GrupoA.StorageController.RaftServices.FileSystemService;
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
                                + ThreadLocalRandom.current().nextInt(0, (int) Math.pow(2,16)));
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

    private static void startRaft(String raftId) throws Exception{

        FileSystemService service = FileSystemService.getInstance("./raft.xml", raftId);
        loop(service);

    }

   /* public static void atomix_func(String[] args) throws  Exception {
        String memberId = args.length == 0 ?
                "member" + ThreadLocalRandom.current().nextInt(0, (int) Math.pow(2,16))
                : args[0];

        AtomixBuilder builder = Atomix.builder().withMemberId(memberId);
        List<String> addresses = getLocalAddresses(); //Returns a list of all non-loopback addresses
        for (String address : addresses) {
            System.out.println(address);
            builder = builder.withAddress(address + ":5679");
        }

        builder = builder.withClusterId("GroupA")
                .withMulticastEnabled()
                .withMembershipProvider(new MulticastDiscoveryProvider())
                .withManagementGroup(RaftPartitionGroup.builder("system")
                        .withNumPartitions(1)
                        .withDataDirectory(new File(memberId))
                        .withMembers("member1", "member2", "member3")
                        .build())
                .withPartitionGroups(
                        PrimaryBackupPartitionGroup.builder("data")
                                .withNumPartitions(32)
                                .build());//

        //Atomix at = new Atomix("atomix.conf");
        // at
        System.out.println("14:57");
        System.out.println(memberId);


        Atomix at = builder.build();//
        at.start().join();
        System.out.println("Created cluster");
        DistributedFSTree fsTree = at.getPrimitive("FSTree", DistributedFSTreeType.instance());

     /*   DistributedFSTree fsTree = at.primitiveBuilder("FSTree", DistributedFSTreeType.instance())
                .withProtocol(MultiPrimaryProtocol.builder()
                        .withReplication(Replication.ASYNCHRONOUS)
                        .withBackups(2)
                        .build())
                .build();
        //System.out.println("Calling LS");
        //fsTree.ls("Hello");

        //DistributedFSTree fsTree= at.getPrimitive("FSTree", DistributedFSTreeType.instance());
        System.out.println("Got primitive tree");

        if(memberId.equals("member1")) {
            // DistributedFSTree fsTree = atomix.getPrimitive("FSTree", DistributedFSTreeType.instance());

            Boolean result  = fsTree.mkDir("/etc");
            System.out.println(result);

            List<String> dirs = fsTree.ls("/");
            for (String dir :
                    dirs) {
                System.out.println(dir);
            }
        }

    }*/
    public static void main(String[] args) throws Exception {
        startRaft(args[0]);
    }

    public static void foo(){
       // AtomixClient
    }
}
