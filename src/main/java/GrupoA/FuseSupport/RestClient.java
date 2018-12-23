package GrupoA.FuseSupport;

import GrupoA.AppServer.Models.*;
import GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

    private String restBaseUri = "";
    private Client client;
    public RestClient(String address) {
        ClientConfig config = new ClientConfig(NodeAttributes.class);
        config.register(JacksonJsonProvider.class);
        client = ClientBuilder.newClient(config);
        this.restBaseUri = "http://" + address + "/";
    }




    public NodeAttributes getAttribute(String path) {
        try {
            NodeAttributes attr  = client.target(restBaseUri).path("attribute").path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .get(NodeAttributes.class);
            return attr;

        } catch (NotFoundException ex) {
            return null;
        }
    }

    public NodeAttributes getAttribute(long iNode) {
        try {
            System.out.println(restBaseUri + "inode/" + iNode + "/attr");
            NodeAttributes attr  = client.target(restBaseUri).path("inode").path(Long.toString(iNode)).path("attr")
            .request(MediaType.APPLICATION_JSON)
            .get(NodeAttributes.class);
            return attr;

        } catch (NotFoundException ex) {
            return null;
        }
    }

    public boolean updateAttribute(String path, AttributeUpdateRequest.UpdateType type, long value) {

        AttributeUpdateRequest aur = new AttributeUpdateRequest();
        aur.Value = value;
        aur.Type = type;
        Boolean bool = client.target(restBaseUri).path("attribute").path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(aur, MediaType.APPLICATION_JSON), Boolean.class);
        return bool;
    }

    public DirectoryContents readDir(String path) {
        return client.target(restBaseUri).path("directory").path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(DirectoryContents.class);
    }

    public Response createDirectory(Directory directory) {
        return client.target( restBaseUri + "/dir")
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(directory, MediaType.APPLICATION_JSON));
    }

    public Boolean createFile(String path, long mode, long uid, long gid){
        CreateFileRequest cfr = new CreateFileRequest();
        cfr.Path = path;
        cfr.mode = mode;
        cfr.uid = uid;
        cfr.gid = gid;
        return client.target(restBaseUri).path("file")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(cfr, MediaType.APPLICATION_JSON), Boolean.class);
    }
}
