package GrupoA.FuseSupport;

import GrupoA.AppServer.Models.Directory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

    private String restBaseUri = "";
    private Client client = ClientBuilder.newClient();
    public RestClient(String address) {
        this.restBaseUri = "http:// " + address + "/";
    }


    public Response createDirectory(Directory directory) {
        return client.target( restBaseUri + "/dir")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(directory, MediaType.APPLICATION_JSON));
    }
}
