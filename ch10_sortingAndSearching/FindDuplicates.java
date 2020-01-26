import java.util.Random;

class FindDuplicates {
    public static void main(String[] args) {
        findDuplicates(generateIntArr());
    }

    private static void findDuplicates(int[] arr) {
        BitVector vector = new BitVector(32000);
        for(int n : arr) {
            if(vector.isSet(n - 1)) {
                System.out.println(n);
                continue;
            }
            vector.set(n - 1);
        }

    }

    private static int[] generateIntArr() {
        Random rand = new Random();
        int[] result = new int[32000];
        for(int i = 0; i < result.length; i++) {
            int n = rand.nextInt(32000 - 1);
            result[i] = n + 1;
        }
        return result;
    }
}

class BitVector {
    byte[] vector;

    public BitVector(int size) {
        vector = new byte[size / Byte.SIZE];
    }

    public void set(int position) {
        int index = position / Byte.SIZE;
        int bit = position % Byte.SIZE;
        vector[index] |= 1 << bit;
    }

    public boolean isSet(int position) {
        int index = position / Byte.SIZE;
        int bit = position % Byte.SIZE;
        return (vector[index] & 1 << bit) == 1;
    }
}