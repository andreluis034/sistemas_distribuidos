package GrupoA.AppServer.Models;

public class TruncateRequest {
    public String path;
    public long offset;

    public TruncateRequest() {

    }

    public TruncateRequest(String path, long offset) {
        this.path = path;
        this.offset = offset;
    }
}
