package GrupoA.AppServer.Routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Cephish {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHelloWorld() {
        return "HelloWorld";
    }
}
