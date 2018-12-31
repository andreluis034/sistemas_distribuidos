package GrupoA.AppServer.Routes;

import GrupoA.AppServer.ApplicationServer;
import GrupoA.AppServer.Models.*;
import GrupoA.OSD.OSDClient.OSDClient;
import GrupoA.StorageController.Crush.CrushMap;
import GrupoA.StorageController.gRPCService.FileSystem.CrushMapResponse;
import GrupoA.StorageController.gRPCService.FileSystem.LockResponse;
import GrupoA.StorageController.gRPCService.FileSystem.RedundancyProto;
import GrupoA.StorageController.gRPCService.FileSystem.iNodeAttributes;
import GrupoA.Utility.Jenkins;
import com.google.protobuf.ByteString;
import com.xiaomi.infra.ec.ErasureCodec;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static GrupoA.AppServer.ApplicationServer.subBlockSize;

@Path("/file")
public class FileRoute {

    @Context
    private HttpServletRequest servletRequest;

    /**
     * Creates a file
     *
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
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<WriteBlockData> getWriteBlockDatas(String path, long offset, long size, RedundancyProto red) {
        List<WriteBlockData> wbds = new LinkedList<>();
        List<Integer> superBlocks = getSuperBlocks(offset, size);
        long currentGlobalOffset = offset;
        long remainingSize = size;
        for (Integer superblock : superBlocks) {
            List<Integer> smallerBlocks = getSmallerBlocks(superblock, offset, size);
            for (Integer miniBlock : smallerBlocks) {
                WriteBlockData wbd = new WriteBlockData(path, superblock, miniBlock, red);
                wbd.startRelativeOffset = (int) (currentGlobalOffset - wbd.getGlobalOffset());
                long maxAllowedToWriteToBlock = wbd.getActualSize();
                int toRead = (int) Math.min(maxAllowedToWriteToBlock, remainingSize);
                wbd.endRelativeOffset = (wbd.startRelativeOffset + toRead);
                currentGlobalOffset += toRead;
                remainingSize -= toRead;
                wbds.add(wbd);
            }
        }

        return wbds;
    }

    private HashMap<Long, CrushMapResponse> maps = new HashMap<>();

    private LockResponse getLock(String path, long id, long version) {
        LockResponse gotLock = ApplicationServer.FileSystemClient
                .SetLock(path, id, !maps.containsKey(version) ? -1
                        : maps.get(version).getVersion());

        while (!maps.containsKey(gotLock.getNecessaryVersion())) {
            CrushMapResponse cm = ApplicationServer.FileSystemClient.GetLatestCrushMap();
            maps.put(cm.getVersion(), cm);
            gotLock = ApplicationServer.FileSystemClient
                    .SetLock(path, id, !maps.containsKey(version) ? -1
                            : maps.get(version).getVersion());
        }
        return gotLock;
    }

    /**
     * Returns the contents of a file
     *
     * @param rr the contents requested
     * @return the contents of the file
     */
    @POST
    @Path("/read")
    @Produces(MediaType.APPLICATION_JSON)
    public ReadFileResponse readFile(ReadRequest rr) {//TODO lock this?
        ReadFileResponse resp = new ReadFileResponse();
        long id = Jenkins.hash64(servletRequest.getRemoteHost().getBytes());
        resp.path = rr.path;
        try {
            iNodeAttributes nattributes = ApplicationServer.FileSystemClient.GetAttributes(rr.path);
            if (nattributes.getINodeNumber() == -1) {
                resp.Status = -2;/* No such file or directory */
                return resp;
            }
            LockResponse gotLock = getLock(rr.path, id, nattributes.getCrushMapVersion());

            if (!gotLock.getResult()) {
                resp.Status = -16; /* Device or resource busy */
                return resp;
            }
            CrushMapResponse crushmap = maps.get(nattributes.getCrushMapVersion());

            int bytesToRead = (int) Math.min(rr.size, nattributes.getSize() - rr.offset);
            List<WriteBlockData> blocksToRead = getWriteBlockDatas(rr.path, rr.offset, bytesToRead, nattributes.getRedundancy());

            resp.Data = new byte[bytesToRead];
            int outputOffset = 0;

            for (WriteBlockData btr : blocksToRead) {
                int status = btr.read(crushmap);
                if (status < 0) {
                    resp.Status = status;
                    return resp;
                }
                System.arraycopy(btr.Data, btr.startRelativeOffset, resp.Data, outputOffset, btr.getActualSize());
                outputOffset += btr.getActualSize();
            }
            resp.Status = outputOffset;
            ApplicationServer.FileSystemClient.ReleaseLock(rr.path, id);
            return resp;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * writes to a file
     *
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
            if (nAttributes.getINodeNumber() == -1)
                return -2;      /* No such file or directory */
            long id = Jenkins.hash64(servletRequest.getRemoteHost().getBytes());
            LockResponse gotLock = getLock(wr.path, id, nAttributes.getCrushMapVersion());


            if (!gotLock.getResult())
                return -16; /* Device or resource busy */
            Boolean finalSizeUpdated;
            List<WriteBlockData> blocksToWrite = new LinkedList<>();

            long newSize = Math.max(handleFillingPrevious(nAttributes, wr, blocksToWrite),
                    handleWritingAtOffset(nAttributes, wr, blocksToWrite));
            finalSizeUpdated = newSize != nAttributes.getSize();

            CrushMapResponse crushmap = maps.get(nAttributes.getCrushMapVersion());
            //TODO calculate jerasure
            int written = 0;
            for (WriteBlockData wbd : blocksToWrite) {
                CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto
                        osd;
                long hashToUse = wbd.getHashForPG();
                int selectedPG = (int) Math.abs(hashToUse % crushmap.getPGsCount());
                CrushMapResponse.PlacementGroupProto
                        PG = crushmap.getPGs(selectedPG);
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
                client.awaitTermination();
            }

            if (finalSizeUpdated) {
                ApplicationServer.FileSystemClient.UpdateAttribute(wr.path, newSize, AttributeUpdateRequest.UpdateType.CHANGE_SIZE);
            }
            ApplicationServer.FileSystemClient.ReleaseLock(wr.path, id);
            return written;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -5; //IO error
    }

    @PUT
    @Path("/truncate")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer truncate(TruncateRequest tr) {
        long id = Jenkins.hash64(servletRequest.getRemoteHost().getBytes());
        iNodeAttributes iNodeAttr = ApplicationServer.FileSystemClient.GetAttributes(tr.path);

        if(iNodeAttr.getINodeNumber() == -1)
            return -2; /* No such file or directory */

        if(tr.offset == iNodeAttr.getSize())
            return 0;

        LockResponse gotLock = this.getLock(tr.path, id, iNodeAttr.getCrushMapVersion());

        if (!gotLock.getResult())
            return -16; // Device or resource busy
        CrushMapResponse crushmap = maps.get(iNodeAttr.getCrushMapVersion());

        long fileSize = iNodeAttr.getSize();

        List<WriteBlockData> BlocksToTruncate = getWriteBlockDatas(tr.path, tr.offset, fileSize, iNodeAttr.getRedundancy());
        for (WriteBlockData wbd : BlocksToTruncate) {
            fileSize += wbd.truncate(crushmap);
        }
        if(fileSize != iNodeAttr.getSize()) {
            ApplicationServer.FileSystemClient.UpdateAttribute(tr.path, fileSize, AttributeUpdateRequest.UpdateType.CHANGE_SIZE);
        }
        ApplicationServer.FileSystemClient.ReleaseLock(tr.path, id);

        return 0;
    }

    @Deprecated
    private void deleteBlock(iNodeAttributes iNodeAttr, List<BlockData> blocksToDelete) throws InterruptedException {
        for (BlockData bd : blocksToDelete) {
            // Get the OSD in which each smallerBlock is placed
            long bdHash = bd.getHashForPG();
            CrushMapResponse crushmap = maps.get(iNodeAttr.getCrushMapVersion());
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd;
            CrushMapResponse.PlacementGroupProto pg = crushmap
                    .getPGs((int)(bdHash % crushmap.getPGsCount()));

            if (iNodeAttr.getRedundancy().equals(RedundancyProto.Replication)) {
                osd = pg.getOSDs(0);    //TODO: check if this is the leader
            } else {
                // For JErasure
                String hash_str = Long.toHexString(bdHash) + "_" + pg.getPGNumber();
                bdHash = Jenkins.hash64(hash_str.getBytes());

                osd = pg.getOSDs((int) (bdHash % pg.getOSDsCount()));
            }

            // Delete the smaller block
            String hostname = osd.getAddress().split(":")[0];
            Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);

            OSDClient osdClient = new OSDClient(hostname, port);

            osdClient.deleteObject(bdHash, iNodeAttr.getRedundancy().equals(RedundancyProto.Replication));
            osdClient.shutdown();           // Maybe inefficient
        }
    }

    @DELETE
    @Path("{strPath: .*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer deleteFile(@PathParam("strPath") String path) {
        long id = Jenkins.hash64(servletRequest.getRemoteHost().getBytes());
        path = "/" + path;

        try {


            iNodeAttributes iNodeAttr = ApplicationServer.FileSystemClient.GetAttributes(path);
            if(iNodeAttr.getINodeNumber() == -1)
                return -2; /* No such file or directory */

            LockResponse gotLock = getLock(path, id, iNodeAttr.getCrushMapVersion());

            if (!gotLock.getResult()) {
                return -16; /* Device or resource busy */
            }

            int response = ApplicationServer.FileSystemClient.RemoveFile(path);
            if(response != 0) {
                ApplicationServer.FileSystemClient.ReleaseLock(path, id);
                return response;
            }

            CrushMapResponse crushmap = maps.get(iNodeAttr.getCrushMapVersion());

            long fileSize = iNodeAttr.getSize();

            List<WriteBlockData> wbds = getWriteBlockDatas(path,0, fileSize, iNodeAttr.getRedundancy());

            for (WriteBlockData wbd : wbds) {
                wbd.delete(crushmap);
            }

            //ApplicationServer.FileSystemClient.ReleaseLock(path, id); //Removing the file already releases the lock

            return 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -5;  // I/O error
    }

    private class BlockData {
        String path;
        int superBlock;
        int subBlock;
        public RedundancyProto red;
        public byte[] data = new byte[subBlockSize];

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

        CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto getOSD(CrushMapResponse map) {
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd;
            long hashToUse = this.getHashForPG();

            CrushMapResponse.PlacementGroupProto
                    PG = map.getPGs((int) Math.abs(hashToUse % map.getPGsCount()));
            if (red.equals(RedundancyProto.Replication)) {
                osd = PG.getOSDs(0); //TODO actually check this is the leader??
            } else { //FOR JERASURE
                String hash_str = Long.toHexString(hashToUse) + "_" + PG.getPGNumber();
                hashToUse = Jenkins.hash64(hash_str.getBytes());
                osd = PG.getOSDs((int) (hashToUse % PG.getOSDsCount()));
            }
            return osd;
        }
    }

    private static List<Integer> getSuperBlocks(long offset, long size) {
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i * ApplicationServer.maxBlockSize < offset + size; ++i) {
            if ((i + 1) * ApplicationServer.maxBlockSize < offset)
                continue;
            out.add(i);
        }
        return out;
    }

    private static List<Integer> getSmallerBlocks(int superBlock, long offset, long size) {
        List<Integer> out = new ArrayList<>();
        int start = superBlock * ApplicationServer.maxBlockSize;
        for (int i = 0; i < ApplicationServer.DivisionFactor; ++i) {
            if ((start + (i + 1) * subBlockSize) < offset)
                continue;
            if (((start + i * subBlockSize) >= offset + size))
                continue;
            out.add(i);
        }

        return out;
    }

    public static class WriteBlockData {

        String path;
        int superBlock;
        int subBlock;
        public RedundancyProto red;
        public int startRelativeOffset = 0;
        public int endRelativeOffset = subBlockSize;
        public byte[] Data = new byte[subBlockSize];

        private final ErasureCodec codec = new ErasureCodec.Builder(ErasureCodec.Algorithm.Reed_Solomon)
                .dataBlockNum(4)
                .codingBlockNum(2)
                .wordSize(8)
                .build();

        public WriteBlockData(String path, int superblock, int subblock, RedundancyProto red) {
            this.path = path;
            this.superBlock = superblock;
            this.subBlock = subblock;
            this.red = red;
        }

        String getPathForPG() {
            return this.path + "_" + superBlock + "_" + subBlock + "_" + red.toString();
        }

        String getPathForPG(int subBlock) {
            return this.path + "_" + this.superBlock + "_" + subBlock + "_" + this.red.toString();
        }

        public long getHashForPG() {
            return Jenkins.hash64(this.getPathForPG().getBytes());
        }

        private long getHashForPG(int subBlock) {
            return Jenkins.hash64(this.getPathForPG(subBlock).getBytes());
        }

        long getGlobalOffset() {
            return ApplicationServer.maxBlockSize * superBlock + subBlock * subBlockSize;
        }

        int getActualSize() {
            return this.endRelativeOffset - this.startRelativeOffset;
        }

        boolean isComplete() {
            return subBlockSize == this.getActualSize();
        }

        private long getFinalHash(CrushMapResponse map) {
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd;
            long hashToUse = this.getHashForPG();
            int selectedPG = (int) Math.abs(hashToUse % map.getPGsCount());
            CrushMapResponse.PlacementGroupProto
                    PG = map.getPGs(selectedPG);
            if (red.equals(RedundancyProto.ForwardErrorCorrection)) {
                String hash_str = Long.toHexString(hashToUse) + "_" + PG.getPGNumber();
                hashToUse = Jenkins.hash64(hash_str.getBytes());
            }

            return hashToUse;
        }

        private long getFinalHash(CrushMapResponse map, int subBlock) {

            long hashToUse = this.getHashForPG(subBlock);
            int selectedPG = (int) Math.abs(hashToUse % map.getPGsCount());
            CrushMapResponse.PlacementGroupProto
                    PG = map.getPGs(selectedPG);
            if (red.equals(RedundancyProto.ForwardErrorCorrection)) {
                String hash_str = Long.toHexString(hashToUse) + "_" + PG.getPGNumber();
                hashToUse = Jenkins.hash64(hash_str.getBytes());
            }

            return hashToUse;
        }

        private List<CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto> getOSDReplication(CrushMapResponse map) {
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd;
            long hashToUse = this.getHashForPG();
            CrushMapResponse.PlacementGroupProto
                    PG = map.getPGs((int) Math.abs(hashToUse % map.getPGsCount()));

            return PG.getOSDsList();
        }

        public CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto getOSD(CrushMapResponse map) {
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd;
            long hashToUse = this.getHashForPG();
            CrushMapResponse.PlacementGroupProto
                    PG = map.getPGs((int) Math.abs(hashToUse % map.getPGsCount()));
            if (red.equals(RedundancyProto.Replication)) {
                osd = PG.getOSDs(0); //TODO actually check this is the leader??
            } else { //FOR JERASURE
                String hash_str = Long.toHexString(hashToUse) + "_" + PG.getPGNumber();
                hashToUse = Jenkins.hash64(hash_str.getBytes());
                osd = PG.getOSDs((int) Math.abs(hashToUse % PG.getOSDsCount()));
            }
            return osd;
        }

        public int writeToOSD(CrushMapResponse map, CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd) {
            String hostname = osd.getAddress().split(":")[0];
            Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);
            OSDClient client = new OSDClient(hostname, port);
            try {
                client.WriteMiniObject(
                        this.getFinalHash(map), this);
                client.shutdown();
                client.awaitTermination();
            } catch (Exception e) {
                return -5;// IO Error
            }
            return this.getActualSize();
        }

        public int write(CrushMapResponse map) {
            return writeToOSD(map, this.getOSD(map));
        }

        private int readWithReplication(CrushMapResponse map) {
            List<CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto> osds = this.getOSDReplication(map); //
            for(CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd : osds) {
                String hostname = osd.getAddress().split(":")[0];
                Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);
                OSDClient client = new OSDClient(hostname, port);
                try {
                    //System.out.printf("ReadMiniObject(%s, %d, %d)\n", Long.toHexString(this.getFinalHash(map)), this.startRelativeOffset, this.getActualSize());
                    ByteString readData = client.ReadMiniObject(
                            this.getFinalHash(map), this.startRelativeOffset, this.getActualSize());
                    client.shutdown();
                    client.awaitTermination();
                    readData.copyTo(this.Data, this.startRelativeOffset);
                } catch (Exception e) {
                    continue;
                }
                return this.getActualSize();
            }
            return -5;//IO Error
        }

        private byte[][] readJErasureMatrix(
                CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd,
                CrushMapResponse map,
                List<Integer> failed) throws Exception {
            byte[][] matrix = new byte[4][subBlockSize];
            for (int i = 0; i < 4 /* NUMBER OF FILE SUBBLOCKS SAVED WITH JERASURE */; i++) {
                if (!failed.contains(i)) {
                    try {
                        String hostname = osd.getAddress().split(":")[0];
                        Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);

                        OSDClient client = new OSDClient(hostname, port);
                        long otherSubBlockHash = this.getFinalHash(map, i);
                        ByteString readData = client.ReadMiniObject(
                                otherSubBlockHash, 0, this.getActualSize());
                        client.shutdown();
                        client.awaitTermination();
                        readData.copyTo(matrix[i], 0);
                    } catch (Exception e) {
                        failed.add(i);
                        throw e;
                    }
                }
            }

            return matrix;
        }

        private byte[][] readJErasureCoding(
                CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd,
                CrushMapResponse map,
                List<Integer> failed) throws Exception {

            byte[][] coding = new byte[2][subBlockSize];
            for (int i = 4; i < 5 /* NUMBER OF FILE SUBBLOCKS SAVED WITH JERASURE */; i++) {
                if (!failed.contains(i)) {
                    try {
                        String hostname = osd.getAddress().split(":")[0];
                        Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);

                        OSDClient client = new OSDClient(hostname, port);
                        long otherSubBlockHash = this.getFinalHash(map, i);
                        ByteString readData = client.ReadMiniObject(
                                otherSubBlockHash, 0, this.getActualSize());
                        client.shutdown();
                        client.awaitTermination();
                        readData.copyTo(coding[i-4], 0);
                    } catch (Exception e) {
                        failed.add(i);
                        throw e;
                    }
                }
            }

            return coding;
        }

        private void getData(byte[][] matrix, byte[][] coding, List<Integer> failed) {
            int[] erasures = new int[2];
            int cont = 0;
            for (Integer i : failed) {
                erasures[cont] = i;
                cont++;
            }

            codec.decode(erasures, matrix, coding);
            if (this.subBlock < 4)
                this.Data = matrix[this.subBlock];
            else
                this.Data = coding[this.subBlock];
        }

        private int readWithJErasure(CrushMapResponse map) {
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd = this.getOSD(map);
            List<Integer> failed = new LinkedList<>();

            try {
                String hostname = osd.getAddress().split(":")[0];
                Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);

                OSDClient client = new OSDClient(hostname, port);

                ByteString readData = client.ReadMiniObject(
                        this.getFinalHash(map), this.startRelativeOffset, this.getActualSize());
                client.shutdown();
                client.awaitTermination();
                readData.copyTo(this.Data, this.startRelativeOffset);
            } catch (Exception e) {
                failed.add(this.subBlock);      //This subBlock failed to be read

                try {
                    byte[][] matrix = readJErasureMatrix(osd, map, failed);
                    byte[][] coding = readJErasureCoding(osd, map, failed);

                    getData(matrix, coding, failed);
                } catch (Exception ignored) {
                    try {
                        byte[][] matrix = readJErasureMatrix(osd, map, failed);
                        byte[][] coding = readJErasureCoding(osd, map, failed);

                        getData(matrix, coding, failed);
                    } catch (Exception ignored2) {
                        // JErasure is done for
                        System.out.println("Couldn't retrieve enough blocks to perform error correction");
                    }
                }
            }

            return this.getActualSize();
        }

        public int read(CrushMapResponse map) {
            if(this.red.equals(RedundancyProto.Replication)) {
                return this.readWithReplication(map);
            } else if (this.red.equals(RedundancyProto.ForwardErrorCorrection)) {
                return this.readWithJErasure(map);
            }
            return -5; //TODO JErasure
        }

        public long truncate(CrushMapResponse map) {
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd = this.getOSD(map);
            String hostname = osd.getAddress().split(":")[0];
            Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);
            OSDClient client = new OSDClient(hostname, port);
            long result = client.truncate(this.getFinalHash(map), startRelativeOffset, this.red.equals(RedundancyProto.Replication));
            try {
                client.shutdown();
                client.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }

        public void delete(CrushMapResponse map) {
            CrushMapResponse.PlacementGroupProto.ObjectStorageDaemonProto osd = this.getOSD(map);
            String hostname = osd.getAddress().split(":")[0];
            Integer port = Integer.parseInt(osd.getAddress().split(":")[1]);
            OSDClient client = new OSDClient(hostname, port);
            try {
                client.deleteObject(this.getFinalHash(map),this.red.equals(RedundancyProto.Replication));
                client.shutdown();
                client.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public byte[] getActualData() {
            byte[] output = new byte[this.getActualSize()];
            System.arraycopy(this.Data, (int) this.startRelativeOffset, output, 0, this.getActualSize());
            return output;
        }
    }

    private long handleFillingPrevious(iNodeAttributes attr, WriteRequest wr, List<WriteBlockData> wbds) {
        if (attr.getSize() >= wr.offset)
            return attr.getSize();
        if (wr.data.length == 0)
            return attr.getSize();

        List<Integer> superBlocks = getSuperBlocks(0, wr.offset);
        long relativeOffset = 0;
        for (Integer superblock : superBlocks) {
            List<Integer> smallerBlocks = getSmallerBlocks(superblock, 0, wr.offset);
            for (Integer miniBlock : smallerBlocks) {
                WriteBlockData wbd = new WriteBlockData(wr.path, superblock, miniBlock, attr.getRedundancy());
                int blockSize = Math.min(wbd.Data.length, (int) (wr.offset - relativeOffset));
                wbd.endRelativeOffset = blockSize;
                relativeOffset += blockSize;
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
                wbd.startRelativeOffset = (int) (currentGlobalOffset - wbd.getGlobalOffset());
                long maxAllowedToWriteToBlock = wbd.getActualSize();
                int toWrite = (int) Math.min(maxAllowedToWriteToBlock, remainingData);
                wbd.endRelativeOffset = (int) (wbd.startRelativeOffset + toWrite);
                System.arraycopy(wr.data, (int) relativeOffset, wbd.Data, wbd.startRelativeOffset, toWrite);

                currentGlobalOffset += toWrite;
                relativeOffset += toWrite;
                remainingData -= toWrite;
                wbds.add(wbd);
            }
        }
        return Math.max(wr.offset + wr.data.length, attr.getSize());
    }

}
