package GrupoA.AppServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileObjectManager {
    private final static int maxBlockSize = 3670016; //2^21 + 2^20 + 2^19 Bytes (to avoid GRPC overhead)

    public static List<byte[]> SplitByteArray(byte[] data) {
        List<byte[]> parts = new ArrayList<>((int) Math.ceil(((float)data.length)/maxBlockSize));

        for (int current_size = 0; current_size < data.length; current_size += maxBlockSize)
            parts.add(Arrays.copyOfRange(data, current_size, Math.min(current_size + maxBlockSize, data.length)));

        return parts;
    }

    public static byte[] JoinByteArrays(List<byte[]> parts, int fileSize) {
        byte[] data = new byte[fileSize];
        int i = 0;
        for(byte[] part : parts) {
            System.arraycopy(part, 0, data, Math.min(maxBlockSize*i, fileSize), part.length);
            i++;
        }
        return data;
    }
}
