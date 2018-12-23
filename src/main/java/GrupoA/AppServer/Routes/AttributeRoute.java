package GrupoA.AppServer.Routes;

import GrupoA.AppServer.ApplicationServer;
import GrupoA.AppServer.Models.AttributeUpdateRequest;
import GrupoA.AppServer.Models.NodeAttributes;
import GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/attribute")
public class AttributeRoute {

    /**
     * returns the attributes of a path
     * @param path the queried path
     * @return the attributes
     */
    @GET
    @Path("{strPath: .*}")
    @Produces(MediaType.APPLICATION_JSON)
    public NodeAttributes getAttr(@PathParam("strPath") String path) {
        try {
            path = "/" + path;
            System.out.println("path: " + path);
            NodeAttributes attr = new NodeAttributes();


            iNodeAttributes nattributes = ApplicationServer.FileSystemClient.GetAttributes(path);
            if(nattributes.getINodeNumber() == -1)
                throw new NotFoundException();
            attr._FileType = nattributes.getFileType();
            attr.Name = nattributes.getName();
            attr.UserId = nattributes.getOwnerId();
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

    @POST
    @Path("{strPath: .*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean UpdateAttribute(AttributeUpdateRequest updateRequest){
        return false;
    }
}
