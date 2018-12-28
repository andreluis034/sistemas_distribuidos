package GrupoA.AppServer.Models;

import GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto;

public class CreateRequest {
    public enum CreateRequestType { FILE, DIR }

    public CreateRequestType type;
    public String Path;
    public long mode;
    public long uid;
    public long gid;
    public long permission;
    public long creationTime;
    public RedundancyProto redundancyProto = RedundancyProto.None;
}
