package GrupoA.AppServer.Routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hc")
public class HealthCheck {

    @GET
    public String getResult() {
        return "OK";
    }
}
