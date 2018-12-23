package GrupoA.AppServer.Routes;

import GrupoA.AppServer.ApplicationServer;
import GrupoA.AppServer.Models.INode;

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
        try {
            System.out.println("getAttr");
            NodeAttributes attr = new NodeAttributes();


            iNodeAttributes nattributes = ApplicationServer.FileSystemClient.GetAttributes(iNode);
            if(nattributes.getINodeNumber() == -1)
                throw new NotFoundException();

            attr.Name = nattributes.getName();
            attr.OwnerId = nattributes.getOwnerId();
            attr.INodeNumber = nattributes.getINodeNumber();
            attr.UserPermissions = nattributes.getUserPermissions();
            attr.GroupPermissions = nattributes.getGroupPermissions();
            attr.OthersPermissions = nattributes.getOtherPermissions();
            attr.Size = nattributes.getSize();
            attr.ParentINodeNumber = nattributes.getParentINodeNumber();
            attr.GroupId = nattributes.getGroupId();

            return attr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
}
