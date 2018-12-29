package GrupoA.AppServer.Routes;

import GrupoA.AppServer.ApplicationServer;
import GrupoA.AppServer.Models.AttributeUpdateRequest;
import GrupoA.AppServer.Models.CreateRequest;
import GrupoA.AppServer.Models.WriteRequest;
import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse;
import GrupoA.StorageController.gRPCService.FileSystem.LockResponse;
import GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto;
import GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes;
import GrupoA.Utility.Jenkins;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Path("/file")
public class FileRoute {

    @Context
    private HttpServletRequest servletRequest;
    /**
     * Creates a file
     * @param cfr The request to create a file
     * @return true is the file was created
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean createFile(CreateRequest cfr) {

        try {
            System.out.println("Creating file " + cfr.Path);
            return ApplicationServer.FileSystemClient.CreateFile(cfr.Path, cfr.mode, cfr.uid, cfr.gid, cfr.permission,
                    cfr.creationTime, cfr.redundancyProto);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * writes to a file
     * @param wr The request to write the file
     * @return Linux System Error
     */
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer writeFile(WriteRequest wr) { //TODO support jerasure
        try {
            System.out.println("Writting file " + wr.path);
            long id = Jenkins.hash64(servletRequest.getRemoteHost().getBytes());
            LockResponse gotLock = ApplicationServer.FileSystemClient
                    .SetWriteLock(wr.path, id, crushmap == null ? -1 : crushmap.getVersion()); //TODO
            while(gotLock.getMapOutdated()) {
                crushmap = ApplicationServer.FileSystemClient.GetLatestCrushMap();
                ApplicationServer.FileSystemClient
                        .SetWriteLock(wr.path, id, crushmap == null ? -1 : crushmap.getVersion());
            }
            if(!gotLock.getResult())
                return -16; /* Device or resource busy */
            Boolean finalSizeUpdated = false;
            List<WriteBlockData> blocksToWrite = new LinkedList<>();

            iNodeAttributes nattributes = ApplicationServer.FileSystemClient.GetAttributes(wr.path);
            long newSize = Math.max(handleFillingPrevious(nattributes, wr, blocksToWrite),
                    handleWritingAtOffset(nattributes, wr, blocksToWrite));
            finalSizeUpdated = newSize != nattributes.getSize();


            //TODO calculate jerasure

            for (WriteBlockData wbd : blocksToWrite) {
                CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto
                        osd;
                long hashToUse = wbd.getHashForPG();
                CrushMapResponse.PlacementGroupProto
                        PG = crushmap.getPGs((int) (wbd.getHashForPG() % crushmap.getPGsCount()));
                if (nattributes.getRedundancy().equals(RedundancyProto.Replication)) {
                    osd = PG.getOSDs(0); //TODO actually check this is the leader??
                } else { //FOR JERASURE
                    String hash_str = Long.toHexString(wbd.getHashForPG()) + "_" + PG.getPGNumber();
                    hashToUse = Jenkins.hash64(hash_str.getBytes());
                    osd = PG.getOSDs((int) (hashToUse % PG.getOSDsCount()));
                }
                String hostname = osd.getAddress().split(";")[0];
                Integer port = Integer.parseInt(osd.getAddress().split(";")[1]);
                OSDClient client = new OSDClient(hostname, port);
                client.WriteMiniObject(hashToUse, wbd);
                client.shutdown();

            }


            if(finalSizeUpdated){
                ApplicationServer.FileSystemClient.UpdateAttribute(wr.path, newSize, AttributeUpdateRequest.UpdateType.CHANGE_SIZE );
            }
            ApplicationServer.FileSystemClient.ReleaseWriteLock(wr.path, id);//TODO
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -5; //IO error
    }

    private List<Integer> getSuperBlocks(long offset, int size) {
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i * ApplicationServer.maxBlockSize < offset + size; ++i) {
            if((i+1) * ApplicationServer.maxBlockSize < offset)
                continue;
            out.add(i);
        }
        return out;
    }

    private List<Integer> getSmallerBlocks(int superBlock, long offset, int size) {
        List<Integer> out = new ArrayList<>();
        int start = superBlock * ApplicationServer.maxBlockSize;
        for (int i = 0; i < ApplicationServer.DivisionFactor; ++i ){
            if((start + (i+1)*ApplicationServer.subBlockSize) < offset)
                continue;
            if(((start + i*ApplicationServer.subBlockSize) >= offset+size))
                continue;
            out.add(i);
        }

        return out;
    }

    public class WriteBlockData {

        String path;
        int superBlock;
        int subBlock;
        public RedundancyProto red;
        public int startRelativeOffset = 0;
        public int endRelativeOffset = ApplicationServer.subBlockSize;
        public byte[] Data = new byte[ApplicationServer.subBlockSize];

        WriteBlockData(String path, int superblock, int subblock, RedundancyProto red){
            this.path = path;
            this.superBlock = superblock;
            this.subBlock = subblock;
            this.red = red;
        }

        String getPathForPG() {
            return this.path + "_" + superBlock + "_" + subBlock + "_" +red.toString();
        }

        long getHashForPG() {
            return Jenkins.hash64(this.getPathForPG().getBytes());
        }

        long getGlobalOffset() {
            return ApplicationServer.maxBlockSize * superBlock + subBlock * ApplicationServer.subBlockSize;
        }

        int getActualSize() {
            return this.endRelativeOffset - this.startRelativeOffset;
        }

        boolean isComplete() {
            return ApplicationServer.subBlockSize == this.getActualSize();
        }
    }

    private long handleFillingPrevious(iNodeAttributes attr, WriteRequest wr, List<WriteBlockData> wbds) {
        if(attr.getSize() >= wr.offset)
            return attr.getSize();
        if(wr.data.length == 0)
            return attr.getSize();

        List<Integer> superBlocks = getSuperBlocks(0, (int)wr.offset);
        long relativeOffset = 0;
        for (Integer superblock : superBlocks) {
            List<Integer> smallerBlocks = getSmallerBlocks(superblock,0, (int)wr.offset);
            for (Integer miniBlock : smallerBlocks) {
                WriteBlockData wbd = new WriteBlockData(wr.path, superblock, miniBlock, attr.getRedundancy());
                int blockSize = Math.min(wbd.Data.length,  (int)(wr.offset - relativeOffset));
                wbd.endRelativeOffset = blockSize;
                relativeOffset +=blockSize;
                wbds.add(wbd);
            }
        }

        return wr.offset;
    }

    private long handleWritingAtOffset(iNodeAttributes attr, WriteRequest wr, List<WriteBlockData> wbds) {
        List<Integer> superBlocks = getSuperBlocks(wr.offset, wr.data.length);
        long currentGlobalOffset = wr.offset;
        long relativeOffset = 0;
        long remainingData = wr.data.length;
        for (Integer superblock : superBlocks) {
            List<Integer> smallerBlocks = getSmallerBlocks(superblock, wr.offset, wr.data.length);
            for (Integer miniBlock : smallerBlocks) {
                WriteBlockData wbd = new WriteBlockData(wr.path, superblock, miniBlock, attr.getRedundancy());
                wbd.startRelativeOffset = (int)(wbd.getGlobalOffset() - currentGlobalOffset);
                long maxAllowedToWriteToBlock = wbd.getActualSize();
                int toWrite = (int) Math.min(maxAllowedToWriteToBlock, remainingData);
                wbd.endRelativeOffset = (int) (wbd.startRelativeOffset + toWrite);
                System.arraycopy(wr.data, (int)relativeOffset, wbd.Data, wbd.startRelativeOffset, toWrite);

                relativeOffset += toWrite;
                remainingData -= toWrite;
                wbds.add(wbd);
            }
        }
        return Math.max(wr.offset + wr.data.length, attr.getSize());
    }





    private static CrushMapResponse crushmap = null;
}
