package GrupoA.StorageController.Crush;

import GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ObjectStorageDaemon implements Serializable {
    private PlacementGroup belongingPG; //TODO you need to set this when you create the PGs
    public boolean isLeader = false;
    private String Address = "";

    public ObjectStorageDaemon(ObjectStorageDaemon osd) {
        this.belongingPG = osd.belongingPG;
        this.Address = osd.Address;
    }

    public ObjectStorageDaemon(String Address) {
        this.Address = Address;
        this.belongingPG = new PlacementGroup(-1, new LinkedList<>());
    }

    public PlacementGroup getBelongingPG() {
        return this.belongingPG;
    }

    void setBelongingPG(PlacementGroup pg) {
        this.belongingPG = pg;
    }

    public String getAddress() {
        return this.Address;
    }

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof ObjectStorageDaemon))return false;

        return ((ObjectStorageDaemon) other).getAddress().equals(this.Address);
    }

    public String getHost() {
        return this.Address.split(":")[0];
    }

    public int getPort() {
        return Integer.parseInt(this.Address.split(":")[1]);
    }

    public CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto toProto() {
        CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto.Builder
                builder = CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto.newBuilder();
        builder.setIsLeader(this.isLeader);
        builder.setAddress(this.getAddress());
        return builder.build();
    }
}
