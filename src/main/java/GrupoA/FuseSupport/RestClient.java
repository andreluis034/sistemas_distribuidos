package GrupoA.FuseSupport;

import GrupoA.AppServer.Models.Directory;
import GrupoA.AppServer.Models.NodeAttributes;
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
            System.out.println(restBaseUri + "attribute/" + path );
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

    public Response createDirectory(Directory directory) {
        return client.target( restBaseUri + "/dir")
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(directory, MediaType.APPLICATION_JSON));
    }
}
