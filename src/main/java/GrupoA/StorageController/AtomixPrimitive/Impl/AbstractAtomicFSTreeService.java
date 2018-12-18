package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AtomicFSTreeType;
import GrupoA.StorageController.FSTree;
import io.atomix.primitive.PrimitiveType;
import io.atomix.primitive.service.AbstractPrimitiveService;
import io.atomix.primitive.service.BackupInput;
import io.atomix.primitive.service.BackupOutput;
import io.atomix.primitive.session.SessionId;
import io.atomix.utils.serializer.Namespace;
import io.atomix.utils.serializer.Serializer;

import java.util.SortedSet;

/**
    TODO: Implement the methods here
 */
public class AbstractAtomicFSTreeService
        extends AbstractPrimitiveService<AtomicFSTreeClient>
        implements AtomicFSTreeService {

    private static final Serializer SERIALIZER = Serializer.using(Namespace.builder()
            .register(AtomicFSTreeType.instance().namespace())
            .register(FSTree.class)
            .register(SessionId.class)
            .build());

    /**
     * TODO: Check if it is
     *      private FSTree tree;
     *
     *      OR
     *
     *      private FSTree tree = new FSTree();
     */

    private FSTree tree = new FSTree();


    AbstractAtomicFSTreeService(PrimitiveType primitiveType) {
        super(primitiveType, AtomicFSTreeClient.class);
    }

    @Override
    public Serializer serializer() {
        return SERIALIZER;
    }

    @Override
    public void backup(BackupOutput output) {
        output.writeObject(tree);
    }

    @Override
    public void restore(BackupInput input) {
        tree = input.readObject();
    }

    /*
    @Override
    public void onClose(Session session) {
        //release(session.sessionID) || releaseSession(session)
    }

    @Override
    public void onExpire(Session session) {
        //release(session.sessionID) || releaseSession(session)
    }

    private void release(SessionId sessionId) {

    }
    */

    @Override
    public boolean mkDir(String path) {
        // Can't create the root
        if (path.compareTo("/") == 0) {
            return false;
        }

        int flag;
        String[] parts = path.split("/");

        FSTree.DirNode currentNode = tree.getRoot();
        SortedSet<FSTree.Node> set = currentNode.getChildren();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            flag = 0;

            for (FSTree.Node node : set) {
                if (part.compareTo(node.getNodeName()) == 0) {
                    if (node.getNodeType() != FSTree.NodeType.DirNode)
                        return false;

                    currentNode = (FSTree.DirNode)node;
                    set = currentNode.getChildren();
                    flag = 1;

                    break;
                }
            }

            // It only enters this 'if' if a part of the path doesn't exist;
            // or it was able to reach the final part of the path,
            // which means it is ready to create the directory.
            if (flag == 0) {

                // Everything was OK
                if (i == parts.length - 1) {
                    FSTree.DirNode newNode = new FSTree.DirNode(part);
                    set.add(newNode);
                    currentNode.setChildren(set);

                    return true;
                }

                // Couldn't create the directory, as there is
                // something wrong with the provided path.
                break;
            }
        }

        return false;
    }

    @Override
    public boolean rmDir(String path) {
        // Can't remove the root
        if (path.compareTo("/") == 0) {
            return false;
        }

        int flag;
        String[] parts = path.split("/");

        FSTree.DirNode currentNode = tree.getRoot();
        SortedSet<FSTree.Node> set = currentNode.getChildren();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            flag = 0;

            for (FSTree.Node node : set) {
                if (part.compareTo(node.getNodeName()) == 0) {
                    if (node.getNodeType() != FSTree.NodeType.DirNode)
                        return false;

                    if (i == parts.length - 1) {
                        set.remove(node);

                        return true;
                    }

                    currentNode = (FSTree.DirNode)node;
                    set = currentNode.getChildren();
                    flag = 1;

                    break;
                }
            }

            // It only enters this 'if' if either a part of
            // the path is a file or it doesn't exist; or it
            // was able to reach the final part of the path,
            // which means it is ready to create the directory
            if (flag == 0) {
                break;
            }
        }

        return false;
    }

    @Override
    public void mkFile(String path) {

    }

    @Override
    public void rmFile(String path) {

    }

    @Override
    public void ls(String path) {

    }
}
