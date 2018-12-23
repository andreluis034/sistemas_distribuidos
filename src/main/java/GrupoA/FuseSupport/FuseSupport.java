package GrupoA.FuseSupport;

import GrupoA.OSD.OSDServer.OSDServer;
import fuse.Fuse;
import fuse.SWIGTYPE_p_fuse_chan;
import fuse.SWIGTYPE_p_fuse_session;
import fuse.Session;
import jlowfuse.JLowFuse;
import jlowfuse.JLowFuseArgs;

import java.io.IOException;
import java.util.Random;

public class FuseSupport {

    public static String Address = "172.20.100.100:9595";

    public static void test() {
        RestClient restClient = new RestClient(FuseSupport.Address);
        restClient.getAttribute(1);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        test();
        Address = "172.20.100.100:9595";
        Random random = new Random();
        int randomNumber = random.nextInt((int)Math.pow(2, 31) );
        String[] fuseArgs = { "-o foo,subtype=cephfs" + randomNumber, "-d" };
        String mountpoint = args[0];
        System.out.println("Parsing args");
        JLowFuseArgs jLowFuseArgs = JLowFuseArgs.parseCommandline(fuseArgs);

        System.out.println("Fuse mount");
        SWIGTYPE_p_fuse_chan chan = Fuse.mount(mountpoint, jLowFuseArgs);
        System.out.println("create session");

        SWIGTYPE_p_fuse_session sess = JLowFuse.lowlevelNew(jLowFuseArgs,
                new CephishStorageOps());
        System.out.println("Chan add");
        Session.addChan(sess, chan);
        System.out.println("Loop single");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Session.loopMulti(sess);
                System.out.println("Remove chan");
                Session.removeChan(chan);
                System.out.println("Destroy");
                Session.destroy(sess);
                System.out.println("Exit");
                Session.exit(sess);
                System.out.println("unmount");
                Fuse.unmount(mountpoint, chan);
            }
        });
        Session.loopSingle(sess);


    }
}
