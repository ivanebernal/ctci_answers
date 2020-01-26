import java.util.Random;

class PeaksAndValleys {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(20);
        }
        printArr(arr);
        sortPeaksAndValleys(arr);
        printArr(arr);
    }

    private static void sortPeaksAndValleys(int[] arr) {
        for(int i = 0; i < arr.length - 3; i++) {
            int current = arr[i];
            int next = arr[i + 1];
            int last = arr[i + 2];
            if(current > next && next > last) {
                arr[i + 1] = last;
                arr[i + 2] = next;
            } else if(current < next && next < last){
                arr[i + 1] = last;
                arr[i + 2] = next;
            }
        }
    }

    private static void printArr(int[] arr) {
        for(int n : arr) {
            System.out.print(n + ", ");
        }
        System.out.println();
    }
}