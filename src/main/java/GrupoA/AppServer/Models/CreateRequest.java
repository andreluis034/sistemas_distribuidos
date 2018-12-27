package GrupoA.AppServer.Models;

public class CreateRequest {
    public enum CreateRequestType { FILE, DIR }

    public CreateRequestType type;
    public String Path;
    public long mode;
    public long uid;
    public long gid;
}
