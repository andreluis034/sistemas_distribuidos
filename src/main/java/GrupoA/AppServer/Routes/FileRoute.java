package GrupoA.AppServer.Routes;

import GrupoA.AppServer.ApplicationServer;
import GrupoA.AppServer.Models.CreateRequest;
import GrupoA.AppServer.Models.WriteRequest;
import GrupoA.Utility.Jenkins;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/file")
public class FileRoute {

    @Context
    private HttpServletRequest servletRequest;
    /**
     * Creates a file
     * @param cfr The request to create a file
     * @return true is the file was created
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean createFile(CreateRequest cfr) {

        try {
            System.out.println("Creating file " + cfr.Path);
            return ApplicationServer.FileSystemClient.CreateFile(cfr.Path, cfr.mode, cfr.uid, cfr.gid, cfr.permission);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * writes to a file
     * @param wr The request to write the file
     * @return Linux System Error
     */
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer writeFile(WriteRequest wr) {
        try {
            System.out.println("Writting file " + wr.path);
            long id = Jenkins.hash64(servletRequest.getRemoteHost().getBytes());
            boolean gotLock = ApplicationServer.FileSystemClient.SetWriteLock(wr.path, id); //TODO
            if(!gotLock)
                return -16; /* Device or resource busy */


            //TODO write

            //TODO update data
            ApplicationServer.FileSystemClient.ReleaseWriteLock(wr.path, id);//TODO
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -5; //IO error
    }

}
