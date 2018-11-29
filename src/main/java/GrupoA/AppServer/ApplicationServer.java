package GrupoA.AppServer;

import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.OSD.OSDService.ObjectData;
import io.grpc.StatusRuntimeException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ApplicationServer {
    public static void main(String[] args) throws Exception {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        Path path = Paths.get(s, "cenas.dat");
        byte[] fileContents =  Files.readAllBytes(path);
        //long hash = GrupoA.Utility.Jenkins.hash32(fileContents);
        OSDClient client = new OSDClient("localhost", 50051);
 //TODO Send to client
        try {
            client.putObject(fileContents, path.toString(), 1);
            ObjectData od = client.getObject(path.toString(), 1);
            System.out.println(Arrays.equals(od.getObjectData().toByteArray(), fileContents));
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        } finally {
            client.shutdown();
        }
    }
}
