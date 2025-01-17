package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;
import GrupoA.StorageController.RaftServices.CrushMap.CrushMapService;
import GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage;
import GrupoA.StorageController.gRPCService.FileSystem.ProtoAttributeUpdateRequestType;
import GrupoA.StorageController.gRPCService.FileSystem.UpdateAttribute;

public class UpdateAttributeCommand extends FileSystemCommand<Boolean> {

    private String path = "";
    private UpdateAttribute update;
    public UpdateAttributeCommand(String Path, UpdateAttribute type) {
        this.path = Path;
        this.update = type;

    }

    @Override
    public Boolean execute(FSTree context) {
        FSTree.Node node = context.getNode(this.path);
        if(node == null)
            return false;
        switch (update.getType()) {
            case CHMOD:
                chmod(node);
                break;
            case CHUID:
                chuid(node);
                break;
            case CHGID:
                chgid(node);
                break;
            case UPDATEACCESSTIME:
                node.accessTime = this.update.getValue() * 1000;
                break;
            case CHANGE_SIZE:
                if(node.getNodeType() != FSTree.NodeType.FileNode)
                    return false;
                ((FSTree.FileNode)node).fileSize = this.update.getValue();
                if(this.update.getValue() == 0) {
                    ((FSTree.FileNode)node).setCrushMapVersion(CrushMapService.getInstance().getLatestMap().getVersion());
                }
                break;
            case UNRECOGNIZED:
                return false;
            default:
                return false;
        }
        return true;
    }

    private void chmod(FSTree.Node node) {
        long perms = update.getValue() & 0x1FF;
        node.UserPermissions = (byte)((perms & (0x7 << 6)) >> 6);
        node.GroupPermissions = (byte)((perms & (0x7 <<3)) >> 3);
        node.OthersPermissions = (byte)((perms & 0x007) >> 0);
    }

    private void chuid(FSTree.Node node) {
        node.UserId = update.getValue();
    }

    private void chgid(FSTree.Node node) {
        node.GroupId = update.getValue();
    }

}
