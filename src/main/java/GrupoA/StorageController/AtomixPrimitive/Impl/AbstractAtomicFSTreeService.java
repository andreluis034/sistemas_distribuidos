package GrupoA.StorageController.AtomixPrimitive.Impl;

import GrupoA.StorageController.AtomixPrimitive.AtomicFSTreeType;
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
            //.register(LockHolder.class)
            .register(SessionId.class)
            .build());

    AbstractAtomicFSTreeService(PrimitiveType primitiveType) {
        super(primitiveType, AtomicFSTreeClient.class);
    }

    @Override
    public void backup(BackupOutput backupOutput) {

    }

    @Override
    public void restore(BackupInput backupInput) {

    }
}
