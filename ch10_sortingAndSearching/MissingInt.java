import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

class MissingInt {
    public static void main(String[] args) {
        String filePath = "/Users/ivanebernal/development/ctci/ch10_sortingAndSearching/missingint.txt";
        writeFile(filePath);
        try {
            findMissingIntLessMemory(filePath);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }

    //Follow up: FROM BOOK

    private static void findMissingIntLessMemory(String path) throws FileNotFoundException, IOException {
        int rangeSize = (1 << 7);
        int[] blocks = getCountPerBlock(path, rangeSize);

        int blockIndex = findBlockWithMissingInt(blocks, rangeSize);
        if(blockIndex < 0) {
            System.out.println(-1);
            return; 
        }
        byte[] bitVector = getBitVectorForRange(path, blockIndex, rangeSize);
        int offset = findZero(bitVector);

        if(offset < 0) {
            System.out.println(-1);
            return; 
        }

        System.out.println(blockIndex * rangeSize + offset);
    }

    private static int[] getCountPerBlock(String path, int rangeSize) throws FileNotFoundException, IOException {
        int arrSize = ((int) Math.pow(2, 21)) / rangeSize;
        int[] blocks = new int[arrSize];
        DataInputStream dis = new DataInputStream(new FileInputStream(path));
        while(dis.available() != 0) {
            int nextInt = dis.readInt();
            blocks[nextInt/rangeSize]++;
        }
        dis.close();
        return blocks;
    }

    private static int findBlockWithMissingInt(int[] blocks, int rangeSize) {
        for(int i = 0; i < blocks.length; i++) {
            if(blocks[i] < rangeSize) return i;
        }
        return -1;
    }

    private static byte[] getBitVectorForRange(String path, int index, int rangeSize) throws FileNotFoundException, IOException {
        int start = index * rangeSize;
        int end = start + rangeSize;
        byte[] vector = new byte[rangeSize/Byte.SIZE];
        DataInputStream dis = new DataInputStream(new FileInputStream(path));
        while(dis.available() != 0) {
            int nextInt = dis.readInt();
            if(nextInt >= start && nextInt < end) {
                int offset = nextInt - start;
                vector[offset/Byte.SIZE] |= 1 << offset % Byte.SIZE;
            }
        }
        dis.close();
        return vector;
    }

    private static int findZero(byte[] bitVector) {
        for(int i = 0; i < bitVector.length; i++) {
            if(bitVector[i] != ~0) {
                int offset = findZero(bitVector[i]);
                return i * Byte.SIZE + offset;
            }
        }
        return -1;
    }

    private static int findZero(byte b) {
        for(int i = 0; i < Byte.SIZE; i++) {
            if((b & 1 << i) == 0) return i;
        }
        return -1;
    }

    // Main question
    private static byte[] bVector = new byte[Integer.MAX_VALUE / 4];

    private static void findMissingInt(String path) throws FileNotFoundException, IOException {
        initializeByteVector();
        DataInputStream dis = new DataInputStream(new FileInputStream(path));
        
        while(dis.available() != 0) {
            int nextInt = dis.readInt();
            insertToVector(nextInt);
        }
        dis.close();
        printMissing();
    }

    private static void insertToVector(int n) {
        int index = n / 8;
        int bit = n % 8;
        bVector[index] |= 1 << bit;
    }

    private static void printMissing() {
        for(int i = 0; i < bVector.length; i++) {
            for(int j = 0; j < 8; j++) {
                if((bVector[i] & 1 << j) == 0) {
                    int result = i*8 + j;
                    System.out.println("Missing: " + result);
                    return;
                }
            }
        }
    }

    private static void initializeByteVector() {
        for(int i = 0; i < bVector.length; i++) {
            bVector[i] = 0b00000000;
        }
    }

    private static void writeFile(String path) {
        Random random = new Random();
        //TODO: it's supposed to be 2 ^ 31 but takes too long to create and file size is 8.59 GB
        int max = (int) Math.pow(2, 21);
        int exclude = random.nextInt(max);
        System.out.println("Excluded value: " + exclude);
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(path));
            for(int i = 0; i < max; i++) {
                if(i != exclude) {
                    dos.writeInt(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

}