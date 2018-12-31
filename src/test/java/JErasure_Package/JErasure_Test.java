package JErasure_Package;

import com.xiaomi.infra.ec.CodecUtils;
import com.xiaomi.infra.ec.ErasureCodec;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class JErasure_Test {

    /**
     * Routine to print an array
     * @param array to print
     */
    private static void printArray(byte[] array) {
        if (array.length == 0)
            System.out.println("{}");
        else {
            System.out.print("{ " + array[0]);
            for (int i = 1; i < array.length - 1; i++) {
                System.out.print(", " + array[i]);
            }
            System.out.println(", " + array[array.length - 1] + " }");
        }
        System.out.println();
    }

    /**
     * Routine to print an array
     * @param array to print
     */
    private static void printArray(int[] array) {
        if (array.length == 0)
            System.out.println("{}");
        else {
            System.out.print("{ " + array[0]);
            for (int i = 1; i < array.length - 1; i++) {
                System.out.print(", " + array[i]);
            }
            System.out.println(", " + array[array.length - 1] + " }");
        }
        System.out.println();
    }

    /**
     * Transforms the given array into an array with size divisible by 4
     *
     * @param toPad Array to pad
     * @return Padded array
     */
    public static byte[] pad(byte[] toPad) {
        int toPadLength = toPad.length;
        int rest = toPadLength % 4;

        if (rest == 0)
            return toPad;
        else {
            int bytesToAdd = 4 - rest;
            byte[] padded = new byte[toPadLength + bytesToAdd];
            int paddedLength = padded.length;

            System.arraycopy(toPad, 0, padded, 0, toPadLength);
            for (int i = 0; i < bytesToAdd; i++) {
                padded[paddedLength - i - 1] = -1;
            }

            return padded;
        }
    }

    /**
     * RANDOM STRING, WITH NO PRE-DEFINED SIZE VERSION
     */
    public static void jErasureOnString(String[] args) {
        Random random = new Random();

        ErasureCodec codec = new ErasureCodec.Builder(ErasureCodec.Algorithm.Reed_Solomon)
                .dataBlockNum(4)
                .codingBlockNum(2)
                .wordSize(8)
                .build();

        byte[] bytes = "Hello world! How do you do?".getBytes();

        byte[] padded = pad(bytes);

        System.out.println("PADDED(!) byte array of string 'Hello world! How do you do?'");
        printArray(padded);

        int cols = padded.length / 4;
        byte[][] byteMatrix = new byte[4][cols];
        byte[][] copiedMatrix = new byte[4][cols];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < cols; j++) {
                byteMatrix[i][j] = padded[(j * 4) + i];
                copiedMatrix[i][j] = padded[(j * 4) + i];
            }
        }

        System.out.println("Original data matrix:");
        CodecUtils.printMatrix(byteMatrix, true);
        byte[][] coding = codec.encode(byteMatrix);
        byte[][] copiedCoding = new byte[coding.length][coding[0].length];
        for (int r = 0; r < coding.length; ++r)
            System.arraycopy(coding[r], 0, copiedCoding[r], 0, coding[r].length);

        System.out.println("Original coding matrix:");
        CodecUtils.printMatrix(coding, true);

        // Erasure m random blocks
        int erasures[] = new int[2];
        int erasured[] = new int[4 + 2]; // k + m

        for (int i = 0; i < 2;) {
            int randomNum = random.nextInt(4 + 2); // k + m
            erasures[i] = randomNum;

            if (erasured[erasures[i]] == 0) {
                erasured[erasures[i]] = 1;

                if (erasures[i] < 4) {
                    Arrays.fill(byteMatrix[erasures[i]], 0, byteMatrix[0].length, (byte)0);
                } else {
                    Arrays.fill(coding[erasures[i] - 4], 0, byteMatrix[0].length, (byte)0);
                }
                ++i;
            }
        }

        System.out.println("Erasures matrix:");
        printArray(erasures);
        System.out.println("Erasured data matrix:");
        CodecUtils.printMatrix(byteMatrix, true);
        System.out.println("Erasured coding matrix:");
        CodecUtils.printMatrix(coding, true);

        codec.decode(erasures, byteMatrix, coding);
        System.out.println("Decoded data matrix:");
        CodecUtils.printMatrix(byteMatrix, true);
        System.out.println("Decoded coding matrix:");
        CodecUtils.printMatrix(coding, true);

        assertArrayEquals(copiedMatrix, byteMatrix);
        assertArrayEquals(copiedCoding, coding);

        int arrayLength = cols * 4;
        byte[] paddedStringBytes = new byte[arrayLength];

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < cols; j++)
                paddedStringBytes[(j * 4) + i] = byteMatrix[i][j];

        int cont = 0;
        for (int i = paddedStringBytes.length - 1; paddedStringBytes[i] == -1; i--)
            cont++;

        byte[] originalStringBytes = new byte[arrayLength - cont];
        System.arraycopy(paddedStringBytes, 0, originalStringBytes, 0, arrayLength - cont);

        System.out.println(new String(originalStringBytes, StandardCharsets.UTF_8));
    }

    /**
     * RANDOM BYTES BLOCK, WITH PRE-DEFINED SIZE VERSION
     *
     * TODO: Find out which subBlocks were lost (max_subBlock_loss = m, which, in this case, is equal to 2)
     * TODO: If subBlock_loss > max_subBlock_loss, can't recover the full block
     */
    public static void main(String[]args) {
        final int blockSize = 3670016; // 3670016 / 4
        Random random = new Random();

        ErasureCodec codec = new ErasureCodec.Builder(ErasureCodec.Algorithm.Reed_Solomon)
                .dataBlockNum(4)
                .codingBlockNum(2)
                .wordSize(8)
                .build();

        byte[] bytes =  new byte[blockSize];
        random.nextBytes(bytes);

        byte[] padded = pad(bytes);

        int cols = padded.length / 4;
        byte[][] byteMatrix = new byte[4][cols];
        byte[][] copiedMatrix = new byte[4][cols];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < cols; j++) {
                byteMatrix[i][j] = padded[(j * 4) + i];
                copiedMatrix[i][j] = padded[(j * 4) + i];
            }
        }

        byte[][] coding = codec.encode(byteMatrix);
        byte[][] copiedCoding = new byte[coding.length][coding[0].length];
        for (int r = 0; r < coding.length; ++r)
            System.arraycopy(coding[r], 0, copiedCoding[r], 0, coding[r].length);

        // Erasure m random blocks
        int erasures[] = new int[2];
        int erasured[] = new int[4 + 2]; // k + m

        for (int i = 0; i < 2;) {
            int randomNum = random.nextInt(4 + 2); // k + m
            erasures[i] = randomNum;

            if (erasured[erasures[i]] == 0) {
                erasured[erasures[i]] = 1;

                if (erasures[i] < 4) {
                    Arrays.fill(byteMatrix[erasures[i]], 0, byteMatrix[0].length, (byte)0);
                } else {
                    Arrays.fill(coding[erasures[i] - 4], 0, byteMatrix[0].length, (byte)0);
                }
                ++i;
            }
        }

        System.out.println("Erasures:");
        printArray(erasures);

        codec.decode(erasures, byteMatrix, coding);
        assertArrayEquals(copiedMatrix, byteMatrix);
        System.out.println("CopiedMatrix == ByteMatrix");
        assertArrayEquals(copiedCoding, coding);
        System.out.println("CopiedCoding == Coding");

        int arrayLength = cols * 4;
        byte[] paddedStringBytes = new byte[arrayLength];

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < cols; j++)
                paddedStringBytes[(j * 4) + i] = byteMatrix[i][j];

        int cont = 0;
        for (int i = paddedStringBytes.length - 1; paddedStringBytes[i] == -1; i--)
            cont++;

        byte[] originalStringBytes = new byte[arrayLength - cont];
        System.arraycopy(paddedStringBytes, 0, originalStringBytes, 0, arrayLength - cont);

        assertArrayEquals(originalStringBytes, bytes);
        System.out.println("OriginalStringBytes == Bytes");
    }
}
