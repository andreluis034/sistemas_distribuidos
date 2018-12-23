package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;
import GrupoA.StorageController.gRPCService.FileSystem.BooleanMessage;
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

}
