package GrupoA.AppServer.Models;

public class AttributeUpdateRequest {
    public enum AttributeUpdateRequestType {
        CHMOD,
    }
    public AttributeUpdateRequestType Type;
    public Long Value;
}
