package GrupoA.StorageController.AtomixService;

import io.atomix.primitive.operation.Command;
import io.atomix.primitive.operation.Query;

public interface DistributedFSTreeService {

    /*
    @Command - Indicates the method modifies the state of the primitive
    @Query - Indicates the method queries but does not modify the state of the primitive
     */

    @Command
    void mkDir();

    @Command
    void mkFile();

    @Command
    void rmDir();

    @Command
    void rmFile();

    @Query
    void ls();
}
