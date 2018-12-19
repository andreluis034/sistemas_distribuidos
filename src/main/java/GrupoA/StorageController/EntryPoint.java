package GrupoA.StorageController;

import GrupoA.StorageController.AtomixPrimitive.*;
import io.atomix.cluster.Node;
import io.atomix.cluster.discovery.BootstrapDiscoveryProvider;
import io.atomix.cluster.discovery.MulticastDiscoveryProvider;
import io.atomix.core.Atomix;
import io.atomix.core.AtomixBuilder;
import io.atomix.core.profile.Profile;
import io.atomix.primitive.Replication;
import io.atomix.protocols.backup.MultiPrimaryProtocol;
import io.atomix.protocols.backup.partition.PrimaryBackupPartitionGroup;
import io.atomix.protocols.raft.MultiRaftProtocol;
import io.atomix.protocols.raft.ReadConsistency;
import io.atomix.protocols.raft.partition.RaftPartitionGroup;
import io.atomix.utils.net.Address;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

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

    public static void main(String[] args) throws Exception {
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
                                .build());//*/

        //Atomix at = new Atomix("atomix.conf");
       // at
        System.out.println("14:57");
        System.out.println(memberId);


        Atomix at = builder.build();//*/
        at.start().join();
        System.out.println("Created cluster");
        DistributedFSTree fsTree = at.primitiveBuilder("FSTree", DistributedFSTreeType.instance())
                .withProtocol(MultiPrimaryProtocol.builder()
                        .withReplication(Replication.ASYNCHRONOUS)
                        .withBackups(2)
                        .build())
                .build();
        System.out.println("Calling LS");
        fsTree.ls("Hello");

        //DistributedFSTree fsTree= at.getPrimitive("FSTree", DistributedFSTreeType.instance());
        System.out.println("Got primitive tree");

    }

    public static void foo(){
       // AtomixClient
    }
}
