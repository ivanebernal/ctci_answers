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
            findMissingInt(filePath);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }

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