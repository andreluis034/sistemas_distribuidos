package GrupoA.StorageController.RaftServices.FileSystem.Commands;

import GrupoA.StorageController.FileSystem.FSTree;

public class TryReleaseLockCommand extends FileSystemCommand<Boolean> {

    String path;
    long id;
    boolean forceRelease;

    public TryReleaseLockCommand(String path, long id, boolean forceRelease) {
        this.path = path;
        this.id = id;
        this.forceRelease = forceRelease;
    }
    @Override
    public Boolean execute(FSTree context) {
        FSTree.Node node = context.getNode(this.path);
        if(node.isLocked()) {
            if(forceRelease || node.LockedByID == this.id) {
                node.LockedAt = 0;
                return true;
            }
            return false;
        }
        return true;

    }
}
