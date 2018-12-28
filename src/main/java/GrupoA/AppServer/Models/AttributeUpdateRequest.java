package GrupoA.AppServer.Models;

public class AttributeUpdateRequest {
    public enum UpdateType {
        CHMOD,
        CHUID,
        CHGID,
        UPDATEACCESSTIME,
        CHANGE_SIZE
    }
    public UpdateType Type;
    public Long Value;
}
