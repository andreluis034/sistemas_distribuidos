package GrupoA.AppServer.Models;

import GrupoA.AppServer.ApplicationServer;

public abstract class INode {
    public Attributes attributes = new Attributes();

    public static class Attributes {
        public String Name;

        public Byte UserPermissions;
        public Byte GroupPermissions;
        public Byte OthersPermissions;
        public Integer OwnerId;
        public Integer GroupId;
        public Long INodeNumber;
        public Long Size;
        public Long ParentINodeNumber;
        public int getBlockSize() {
            return ApplicationServer.maxBlockSize;
        }
    }

}
