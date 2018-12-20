package GrupoA.StorageController.AtomixService;

import io.atomix.primitive.event.Event;

public interface DistributedFSTreeClient {
   @Event
   void failed();
}
