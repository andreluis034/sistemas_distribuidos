package GrupoA.AppServer.Routes;

import GrupoA.AppServer.ApplicationServer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import GrupoA.AppServer.Models.NodeAttributes;
import GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes;

@Path("/inode")
public class INodeRoute {

    @GET
    public String get() {
        return "HelloWorld";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("testes")
    public NodeAttributes dsa() {
        return new NodeAttributes();
    }

    /**
     * returns the attributes of a node
     * @param iNode the attributes of anode
     * @return
     */
    @GET
    @Path("{inode: \\d+}/attr")
    @Produces(MediaType.APPLICATION_JSON)
    public NodeAttributes getAttr(@PathParam("inode") long iNode) {
        return null;
    }
}
