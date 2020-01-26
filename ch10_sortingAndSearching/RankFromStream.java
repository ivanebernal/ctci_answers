import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.stream.FileImageInputStream;

class RankFromStream {

    private static RankNode root = null;

    public static void main(String[] args) {
        String path = "/Users/ivanebernal/development/ctci/ch10_sortingAndSearching/rankfromstream.txt";
        generateStream(path);
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(path));
            int i = 0;
            while(dis.available() != 0) {
                int value = dis.readInt();
                track(value);
                if(i % 17 == 0) System.out.println("The rank of " + value + " is " + getRankOf(value));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //BOOK:
    private static void track(int x) {
        if(root == null) {
            root = new RankNode(x);
        } else {
            root.insert(x);
        }
    }

    private static int getRankOf(int x) {
        return root.getRank(x);
    }

    private static void generateStream(String path) {
        Random random = new Random();
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(path));
            for(int i = 0; i < 1000; i++) {
                dos.writeInt(random.nextInt(1000));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class RankNode {
    int data;
    int leftCount = 0;
    RankNode left, right;

    public RankNode(int d) {
        this.data = d;
    }

    public void insert(int x) {
        if(x <= data) {
            if(left != null) left.insert(x);
            else left = new RankNode(x);
            leftCount++;
        } else {
            if(right != null) right.insert(x);
            else right = new RankNode(x);
        }
    }

    public int getRank(int x) {
        if(x == data) {
            return leftCount;
        } else if (x < data) {
            if(left == null) return -1;
            return left.getRank(x);
        } else {
            if(right == null) return -1;
            return 1 + leftCount + right.getRank(x);
        }
    }
}