package GrupoA.AppServer.Models;

public class AttributeUpdateRequest {
    public enum UpdateType {
        CHMOD,
    }
    public UpdateType Type;
    public Long Value;
}
