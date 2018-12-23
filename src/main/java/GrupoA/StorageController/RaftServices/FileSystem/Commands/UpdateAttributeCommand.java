package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;
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
                break;

            default:
                return false;
        }
        return false;
    }

    private chmod

}
