package GrupoA.AppServer.Routes;

import GrupoA.AppServer.ApplicationServer;
import GrupoA.AppServer.Models.CreateFileRequest;
import GrupoA.AppServer.Models.DirectoryContents;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/file")
public class FileRoute {
    /**
     * returns the attributes of a path
     * @param cfr The request to create a file
     * @return the attributes
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean readDir(CreateFileRequest cfr) {

        try {
            System.out.println("Creating file " + cfr.Path);
            return ApplicationServer.FileSystemClient.Create(cfr.Path, cfr.mode, cfr.uid, cfr.gid);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
