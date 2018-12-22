package GrupoA.AppServer.Routes;

import GrupoA.AppServer.Models.INode;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/inode")
public class INodeRoute {

    /**
     * returns a given path be it a folder or a directory
     * @param path the path to write this to
     * @return
     */
    @GET
    @Path("{inode: [0-9]+/attr}")
    @Produces(MediaType.APPLICATION_JSON)
    public INode.Attributes getAttr(@PathParam("inode") long path) {
        INode.Attributes attr = new INode.Attributes();



        return attr;

    }
}
