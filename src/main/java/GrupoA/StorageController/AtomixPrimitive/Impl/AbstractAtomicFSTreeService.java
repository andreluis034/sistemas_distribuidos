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

    FSTree tree;


    AbstractAtomicFSTreeService(PrimitiveType primitiveType) {
        super(primitiveType, AtomicFSTreeClient.class);
    }

    @Override
    public void backup(BackupOutput output) {
        output.writeObject(tree);
    }

    @Override
    public void restore(BackupInput input) {
        tree = input.readObject();
    }

    @Override
    public void mkDir(String path) {

    }

    @Override
    public void rmDir(String path) {

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
