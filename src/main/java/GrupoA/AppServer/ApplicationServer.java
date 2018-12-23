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
                        GrupoA.AppServer.Routes.DirRoute.class.getCanonicalName());
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

        FileSystemClient = new FileSystemClient("172.20.100.1", FileSystemServer.DEFAULT_PORT);
        startJetty(9595);
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        Path path = Paths.get(s, "cenas.dat");
        byte[] fileContents =  Files.readAllBytes(path);
        //long hash = GrupoA.Utility.Jenkins.hash32(fileContents);
        OSDClient client = new OSDClient("localhost", 50051);
 //TODO Send to client
        try {
            List<byte[]> parts = FileObjectManager.SplitByteArray(fileContents);
            int i = 0;
            for(byte[] part : parts) {
                client.putObject(part, path.toString(), i);
                i++;
            }
            ObjectData od;
            int fileSize = fileContents.length;//TODO guardar tamanho do ficheiro no controller
            parts.clear();

            for(int j = 0; j < parts.size(); ++j) {
                parts.add(client.getObject(path.toString(), j).getObjectData().toByteArray());
            }
            System.out.println(Arrays.equals(FileObjectManager.JoinByteArrays(parts, fileSize), fileContents));
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        } finally {
            client.shutdown();
        }
    }
}
