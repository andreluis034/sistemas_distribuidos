package GrupoA.StorageController.FileSystem;

class AttemptToAddDuplicateNodeException extends RuntimeException {
    public AttemptToAddDuplicateNodeException(FSTree.Node attemptedNode, FSTree.Node existingNode) {
        super(String.format("Attempt to add node %s of type %s while %s of type %s already exists",
                attemptedNode.nodeName, attemptedNode.getNodeType(),
                existingNode.nodeName, attemptedNode.getNodeType()));
    }

}
