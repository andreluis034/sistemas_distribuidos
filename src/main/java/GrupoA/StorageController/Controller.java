package GrupoA.StorageController;

import io.atomix.core.Atomix;
import io.atomix.core.map.AtomicMap;
import io.atomix.primitive.Replication;
import io.atomix.protocols.backup.MultiPrimaryProtocol;
import io.atomix.protocols.raft.MultiRaftProtocol;
import io.atomix.protocols.raft.ReadConsistency;

public class Controller {

    private AtomicMap<String, String> map;

    private Controller() {
        /*
        //Create new AtomixPrimitive
        MyPrimitive myPrimitive = atomix.primitiveBuilder("my-primitive", MyPrimitive.instance())
                .withProtocol(MultiPrimaryProtocol.builder()
                .withReplication(Replication.ASYNCHRONOUS)
                .withBackups(2)
                .build())
                .build();
        */

        //Build atomix. Check https://atomix.io/docs/latest/getting-started/
        Atomix atomix = Atomix.builder()
                .build();

        MultiRaftProtocol protocol = MultiRaftProtocol.builder()
                .withReadConsistency(ReadConsistency.LINEARIZABLE)
                .build();

        map = atomix.<String, String>atomicMapBuilder("my-map")
                .withProtocol(protocol)
                .build();
    }

    // FSTree fst = new FSTree();

    /*
    When the controller receives a PUT request from the server,
    it inserts the hash into the AtomicMap / create new AtomicValue,
    and sends the OSDs, alongside the value's version(?), to the server,
    for it to create the entries in the OSDs

    When the controller receives a GET request from the server,
    send the OSDs to the server
     */
}
