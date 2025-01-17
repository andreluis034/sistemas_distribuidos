package GrupoA.AppServer;

import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.OSD.OSDService.ObjectData;
import GrupoA.StorageController.gRPCService.FileSystemClient;
import GrupoA.StorageController.gRPCService.FileSystemServer;
import io.grpc.StatusRuntimeException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class ApplicationServer {
    public final static int maxBlockSize = 3670016; //2^21 + 2^20 + 2^19 Bytes (to avoid GRPC overhead)
    public final static int DivisionFactor = 4;
    public final static int subBlockSize = maxBlockSize / DivisionFactor;

    public static FileSystemClient FileSystemClient = null;

    private static void startJetty(int port) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(port);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                GrupoA.AppServer.Routes.INodeRoute.class.getCanonicalName() + ", " +
                        GrupoA.AppServer.Routes.AttributeRoute.class.getCanonicalName() + ", " +
                        GrupoA.AppServer.Routes.DirRoute.class.getCanonicalName() + ", " +
                        GrupoA.AppServer.Routes.FileRoute.class.getCanonicalName()+ ", " +
                        GrupoA.AppServer.Routes.HealthCheck.class.getCanonicalName());
        try {
            jettyServer.start();
            jettyServer.join();

        } catch(Exception e) {
            throw e;
        } finally{
            jettyServer.destroy();
        }
    }

    public static void main(String[] args) throws Exception {

        if(args.length < 2 || !args[0].equals("-cip")) {
            System.out.println("Usage: -cip <clusterip>");
            return;
        }
        FileSystemClient = new FileSystemClient(args[1], FileSystemServer.DEFAULT_PORT);
        startJetty(9595);
    }
}
