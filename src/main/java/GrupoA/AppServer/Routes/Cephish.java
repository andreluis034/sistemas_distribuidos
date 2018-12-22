package GrupoA.AppServer.Routes;

import GrupoA.AppServer.Models.File;
import GrupoA.AppServer.Models.INode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Cephish {



    /**
     * returns a given path be it a folder or a directory
     * @param path the path to write this to
     * @return
     *//*
    @GET
    @Path("{path: [a-zA-Z0-9_/]+}")
    @Produces(MediaType.APPLICATION_JSON)
/*    public INode getPath(@PathParam("path") String path) {
        File file = new File();
        file.Path = path;
        file.Data = new byte[] {2,2,2};
        return file;

    }*/


    /**
     * Creates a given path be it a folder or a directory
     * @param path the path to write this to
     * @param isDirectory
     * @return
     */
    @POST
    @Path("{path: [a-zA-Z0-9_/]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPath(File file, @PathParam("path") String path, @QueryParam("IsDir") Boolean isDirectory) {
        return Response.status(Response.Status.CREATED.getStatusCode()).build();

    }
}
