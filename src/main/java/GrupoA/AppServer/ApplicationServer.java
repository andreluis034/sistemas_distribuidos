package GrupoA.AppServer;

import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.OSD.OSDService.ObjectData;
import io.grpc.StatusRuntimeException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ApplicationServer {
    private final static int maxBlockSize = 3670016; //2^21 + 2^20 + 2^19 Bytes (to avoid GRPC overhead)

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
            int part = 0;
            for (int current_size = 0; current_size < fileContents.length; current_size += maxBlockSize) {
                client.putObject(
                        Arrays.copyOfRange(fileContents, current_size,
                                Math.min(current_size + maxBlockSize, fileContents.length)),
                        path.toString(),
                        part
                );

                part++;
            }

            ObjectData od;
            int fileSize = fileContents.length;
            byte[] response = new byte[fileSize], odArray; //TODO guardar tamanho do ficheiro no controller
            for (int p = 0; p < part; p++) {
                od = client.getObject(path.toString(), p);
                odArray = od.getObjectData().toByteArray();
                System.arraycopy(odArray, 0, response, Math.min(maxBlockSize * p, fileSize), odArray.length );
            }
            System.out.println(Arrays.equals(response, fileContents));
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        } finally {
            client.shutdown();
        }
    }
}
