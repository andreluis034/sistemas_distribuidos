package GrupoA.AppServer.Models;

public class AttributeUpdateRequest {
    public enum UpdateType {
        CHMOD,
        UPDATEACCESSTIME,
    }
    public UpdateType Type;
    public Long Value;
}
