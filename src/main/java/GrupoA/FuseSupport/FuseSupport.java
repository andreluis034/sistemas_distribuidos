package GrupoA.FuseSupport;

import GrupoA.AppServer.Models.NodeAttributes;

import java.nio.file.Paths;

public class FuseSupport {

    public static String Address = "172.20.100.100:9595";

    public static void test() {
        RestClient restClient = new RestClient(FuseSupport.Address);
        NodeAttributes attr =  restClient.getAttribute("/");
        System.out.println("Got attribute: " + attr);

        //restClient.readDir("/");

        restClient.createFile("/a", 100, 1000, 1000);
    }
    public static void main(String[] args)  {
        test();
        Address = "172.20.100.100:9595";
        CephishFuse stub = new CephishFuse(Address);


        stub.mount(Paths.get(args[0]), true, true);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("Un-mounting");
                stub.umount();
                System.err.println("Done");
            }
        });

    }
}
