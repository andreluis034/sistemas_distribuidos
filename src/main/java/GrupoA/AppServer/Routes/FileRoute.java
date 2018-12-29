package GrupoA.AppServer.Routes;

import GrupoA.AppServer.ApplicationServer;
import GrupoA.AppServer.Models.*;
import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse;
import GrupoA.StorageController.gRPCService.FileSystem.LockResponse;
import GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto;
import GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes;
import GrupoA.Utility.Jenkins;
import com.google.protobuf.ByteString;
import sun.applet.AppletListener;

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

    private List<WriteBlockData> getWriteBlockDatas(String path, long offset, long size, RedundancyProto red) {
        List<WriteBlockData> wbds = new LinkedList<>();
        List<Integer> superBlocks = getSuperBlocks(0, offset);
        long currentGlobalOffset = offset;
        long remainingSize = size;
        for (Integer superblock : superBlocks) {
            List<Integer> smallerBlocks = getSmallerBlocks(superblock,  offset, offset+size);
            for (Integer miniBlock : smallerBlocks) {
                WriteBlockData wbd = new WriteBlockData(path, superblock, miniBlock, red);
                wbd.startRelativeOffset = (int)(currentGlobalOffset - wbd.getGlobalOffset());
                long maxAllowedToWriteToBlock = wbd.getActualSize();
                int toRead = (int) Math.min(maxAllowedToWriteToBlock, remainingSize);
                wbd.endRelativeOffset = (wbd.startRelativeOffset + toRead);
                currentGlobalOffset += toRead;
                remainingSize -= toRead;
            }
        }

        return wbds;
    }

    /**
     * Returns the contents of a file
     * @param rr the contents requested
     * @return the contents of the file
     */
    @POST
    @Path("/read")
    @Produces(MediaType.APPLICATION_JSON)
    public ReadFileResponse readFile(ReadRequest rr) {
        ReadFileResponse resp = new ReadFileResponse();
        resp.path = rr.path;
        try {
            iNodeAttributes nattributes = ApplicationServer.FileSystemClient.GetAttributes(rr.path);
            if(nattributes.getINodeNumber() == -1){
                resp.Status = -2;/* No such file or directory */
                return resp;
            }
            long id = Jenkins.hash64(servletRequest.getRemoteHost().getBytes());
            LockResponse gotLock = ApplicationServer.FileSystemClient
                    .SetWriteLock(rr.path, id, crushmap == null ? -1 : crushmap.getVersion()); //TODO
            while(gotLock.getMapOutdated()) {
                crushmap = ApplicationServer.FileSystemClient.GetLatestCrushMap();
                gotLock = ApplicationServer.FileSystemClient
                        .SetWriteLock(rr.path, id, crushmap == null ? -1 : crushmap.getVersion());
            }
            if(!gotLock.getResult()){
                resp.Status = -16; /* Device or resource busy */
                return resp;
            }
            int bytesToRead = (int) Math.min(rr.size, nattributes.getSize() - rr.offset);
            List<WriteBlockData> blocksToRead = getWriteBlockDatas(rr.path, rr.offset, bytesToRead,nattributes.getRedundancy());

            resp.Data = new byte[bytesToRead];
            int outputOffset = 0;

            for (WriteBlockData btr : blocksToRead) {
                int status = btr.readFromOsd(crushmap);
                if(status <0 ){
                    resp.Status = status;
                    return resp;
                }
                System.arraycopy(btr.Data, btr.startRelativeOffset,resp.Data,outputOffset,btr.getActualSize());
                outputOffset += btr.getActualSize();
            }

            return resp;

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
            System.out.println("Writing file " + wr.path);
            iNodeAttributes nAttributes = ApplicationServer.FileSystemClient.GetAttributes(wr.path);
            if(nAttributes.getINodeNumber() == -1)
                return -2;      /* No such file or directory */
            long id = Jenkins.hash64(servletRequest.getRemoteHost().getBytes());
            LockResponse gotLock = ApplicationServer.FileSystemClient
                    .SetWriteLock(wr.path, id, crushmap == null ? -1 : crushmap.getVersion()); //TODO
            while(gotLock.getMapOutdated()) {
                crushmap = ApplicationServer.FileSystemClient.GetLatestCrushMap();
                gotLock = ApplicationServer.FileSystemClient
                        .SetWriteLock(wr.path, id, crushmap == null ? -1 : crushmap.getVersion());
            }

            if(!gotLock.getResult())
                return -16; /* Device or resource busy */
            Boolean finalSizeUpdated;
            List<WriteBlockData> blocksToWrite = new LinkedList<>();

            long newSize = Math.max(handleFillingPrevious(nAttributes, wr, blocksToWrite),
                    handleWritingAtOffset(nAttributes, wr, blocksToWrite));
            finalSizeUpdated = newSize != nAttributes.getSize();


            //TODO calculate jerasure
            int written = 0;
            for (WriteBlockData wbd : blocksToWrite) {
                CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto
                        osd;
                long hashToUse = wbd.getHashForPG();
                CrushMapResponse.PlacementGroupProto
                        PG = crushmap.getPGs((int) (wbd.getHashForPG() % crushmap.getPGsCount()));
                if (nAttributes.getRedundancy().equals(RedundancyProto.Replication)) {
                    osd = PG.getOSDs(0); //TODO actually check this is the leader??
                } else { //FOR JERASURE
                    String hash_str = Long.toHexString(wbd.getHashForPG()) + "_" + PG.getPGNumber();
                    hashToUse = Jenkins.hash64(hash_str.getBytes());
                    osd = PG.getOSDs((int) (hashToUse % PG.getOSDsCount()));
                }
                String hostname = osd.getAddress().split(":")[0];
                Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);
                OSDClient client = new OSDClient(hostname, port);
                written += wbd.getActualSize();
                client.WriteMiniObject(hashToUse, wbd);
                client.shutdown();
            }

            if(finalSizeUpdated){
                ApplicationServer.FileSystemClient.UpdateAttribute(wr.path, newSize, AttributeUpdateRequest.UpdateType.CHANGE_SIZE );
            }
            //ApplicationServer.FileSystemClient.ReleaseWriteLock(wr.path, id);//TODO
            return written;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -5; //IO error
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer truncate(TruncateRequest tr) {
        long id = Jenkins.hash64(servletRequest.getRemoteHost().getBytes());
        LockResponse gotLock = ApplicationServer.FileSystemClient
                .SetWriteLock(tr.path, id, crushmap == null ? -1 : crushmap.getVersion()); //TODO
        while(gotLock.getMapOutdated()) {
            crushmap = ApplicationServer.FileSystemClient.GetLatestCrushMap();
            gotLock = ApplicationServer.FileSystemClient
                    .SetWriteLock(tr.path, id, crushmap == null ? -1 : crushmap.getVersion());
        }

        if(!gotLock.getResult())
            return -16; /* Device or resource busy */

        iNodeAttributes iNodeAttr = ApplicationServer.FileSystemClient.GetAttributes(tr.path);
        long fileSize = iNodeAttr.getSize();

        // Get the superBlocks and the smallerBlocks, to compute the blocks' hashes
        List<BlockData> blocks = new LinkedList<>();
        List<Integer> superBlocks = getSuperBlocks(0, fileSize);
        long relativeOffset = tr.offset;
        int subBlockToEdit = 0, blockToEdit = 0;
        for (Integer superBlock : superBlocks) {
            List<Integer> smallerBlocks = getSmallerBlocks(superBlock,0, fileSize);
            for (Integer miniBlock : smallerBlocks) {
                long temp = relativeOffset - ApplicationServer.subBlockSize;
                if (temp < 0) {
                    break;
                } else {
                    relativeOffset = temp;
                    subBlockToEdit = miniBlock;
                }
            }

            if(relativeOffset - ApplicationServer.subBlockSize < 0)
                break;
            blockToEdit = superBlock;
        }

        // Get block 'subBlockToEdit' and truncate until offset 'relativeOffset'
        


        // Delete all blocks with "id" higher than subBlockToEdit
    }

    @DELETE
    @Path("{strPath: .*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer deleteFile(@PathParam("strPath") String path) {
        try {
            int response = ApplicationServer.FileSystemClient.RemoveFile(path);
            if (response != 0)
                return response;
            else /* REMOVE BLOCKS FROM OSDs */ {
                iNodeAttributes iNodeAttr = ApplicationServer.FileSystemClient.GetAttributes(path);
                long fileSize = iNodeAttr.getSize();

                // Get the superBlocks and the smallerBlocks, to compute the blocks' hashes
                List<Long> blockHashes = new LinkedList<>();
                List<Integer> superBlocks = getSuperBlocks(0, fileSize);
                for (Integer superBlock : superBlocks) {
                    List<Integer> smallerBlocks = getSmallerBlocks(superBlock,0, fileSize);
                    for (Integer miniBlock : smallerBlocks) {
                        blockHashes.add(
                                new BlockData(path, superBlock, miniBlock, iNodeAttr.getRedundancy())
                                        .getHashForPG());
                    }
                }

                // Delete the blocks from the OSDs
                for (long hash : blockHashes) {
                    // Get the OSD in which each smallerBlock is placed
                    CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd;
                    CrushMapResponse.PlacementGroupProto pg = crushmap
                            .getPGs((int)(hash % crushmap.getPGsCount()));

                    if (iNodeAttr.getRedundancy().equals(RedundancyProto.Replication)) {
                        osd = pg.getOSDs(0);    //TODO: check if this is the leader
                    } else {
                        // For JErasure
                        String hash_str = Long.toHexString(hash) + "_" + pg.getPGNumber();
                        hash = Jenkins.hash64(hash_str.getBytes());

                        osd = pg.getOSDs((int) (hash % pg.getOSDsCount()));
                    }

                    // Delete the smaller block
                    String hostname = osd.getAddress().split(":")[0];
                    Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);

                    OSDClient osdClient = new OSDClient(hostname, port);

                    osdClient.deleteObject(hash);
                    osdClient.shutdown();           // Maybe inefficient
                }

                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -5;  // I/O error
    }

    public class BlockData {
        String path;
        int superBlock;
        int subBlock;
        public RedundancyProto red;
        public byte[] data = new byte[ApplicationServer.subBlockSize];

        BlockData(String path, int superBlock, int subBlock, RedundancyProto red) {
            this.path = path;
            this.superBlock = superBlock;
            this.subBlock = subBlock;
            this.red = red;
        }

        String getPathForPG() {
            return this.path + "_" + superBlock + "_" + subBlock + "_" + red.toString();
        }

        long getHashForPG() {
            return Jenkins.hash64(this.getPathForPG().getBytes());
        }
    }

    private List<Integer> getSuperBlocks(long offset, long size) {
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i * ApplicationServer.maxBlockSize < offset + size; ++i) {
            if((i+1) * ApplicationServer.maxBlockSize < offset)
                continue;
            out.add(i);
        }
        return out;
    }

    private List<Integer> getSmallerBlocks(int superBlock, long offset, long size) {
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
            return this.path + "_" + superBlock + "_" + subBlock + "_" + red.toString();
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

        private long getFinalHash(CrushMapResponse map) {
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd;
            long hashToUse = this.getHashForPG();
            CrushMapResponse.PlacementGroupProto
                    PG = map.getPGs((int) (hashToUse % map.getPGsCount()));
            if (red.equals(RedundancyProto.ForwardErrorCorrection)) {
                String hash_str = Long.toHexString(hashToUse) + "_" + PG.getPGNumber();
                hashToUse = Jenkins.hash64(hash_str.getBytes());
            }

            return  hashToUse;
        }

        private CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto getOSD(CrushMapResponse map) {
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd;
            long hashToUse = this.getHashForPG();
            CrushMapResponse.PlacementGroupProto
                    PG = map.getPGs((int) (hashToUse % map.getPGsCount()));
            if (red.equals(RedundancyProto.Replication)) {
                osd = PG.getOSDs(0); //TODO actually check this is the leader??
            } else { //FOR JERASURE
                String hash_str = Long.toHexString(hashToUse) + "_" + PG.getPGNumber();
                hashToUse = Jenkins.hash64(hash_str.getBytes());
                osd = PG.getOSDs((int) (hashToUse % PG.getOSDsCount()));
            }
            return osd;
        }

        public int readFromOsd(CrushMapResponse map) { // TODO implement erasure
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd = this.getOSD(map);
            String hostname = osd.getAddress().split(":")[0];
            Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);
            OSDClient client = new OSDClient(hostname, port);
            try{
                ByteString readData = client.ReadMiniObject(
                        this.getFinalHash(map), this.startRelativeOffset, this.getActualSize());

                readData.copyTo(this.Data, this.startRelativeOffset);
            } catch (Exception e) {
                return -5;// IO Error
            }
            return this.getActualSize();
        }

        public byte[]getActualData() {
            byte[] output = new byte[this.getActualSize()];
            System.arraycopy(this.Data, (int)this.startRelativeOffset, System.out, 0, this.getActualSize());
            return output;
        }
    }

    private long handleFillingPrevious(iNodeAttributes attr, WriteRequest wr, List<WriteBlockData> wbds) {
        if(attr.getSize() >= wr.offset)
            return attr.getSize();
        if(wr.data.length == 0)
            return attr.getSize();

        List<Integer> superBlocks = getSuperBlocks(0, wr.offset);
        long relativeOffset = 0;
        for (Integer superblock : superBlocks) {
            List<Integer> smallerBlocks = getSmallerBlocks(superblock,0, wr.offset);
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
                wbd.startRelativeOffset = (int)(currentGlobalOffset - wbd.getGlobalOffset());
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
