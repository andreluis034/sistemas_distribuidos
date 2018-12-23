package GrupoA.AppServer.Routes;

import GrupoA.AppServer.ApplicationServer;
import GrupoA.AppServer.Models.DirectoryContents;
import GrupoA.AppServer.Models.NodeAttributes;
import GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/directory")
public class DirRoute {
    /**
     * returns the attributes of a path
     * @param path the queried path
     * @return the attributes
     */
    @GET
    @Path("{strPath: .*}")
    @Produces(MediaType.APPLICATION_JSON)
    public DirectoryContents readDir(@PathParam("strPath") String path) {
        path = "/" + path;

        try {
            return ApplicationServer.FileSystemClient.ReadDir(path);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
