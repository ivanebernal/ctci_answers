class SortedMatrixSearch {
    public static void main(String[] args) {
        int[][] mat = new int[][] {
            new int[] {15, 20, 70, 85},
            new int[] {20, 35, 80, 95},
            new int[] {30, 55, 95, 105},
            new int[] {40, 80, 100, 120},
        };
        int n = 95;
        int[] position = findInMatrix(n, mat);
        if(position[0] == -1) System.out.println("The element " + n + " is not in the matrix.");
        System.out.println("The element " + n + " is at M=" + position[0] + " N=" + position[1]);
    }

    private static int[] findInMatrix(int n, int[][] mat) {
        return findInMatrix(n, mat, 0, mat[0].length);
    }
    
    private static int[] findInMatrix(int n, int[][] mat, int start, int end) {
        if(start > end) return new int[]{-1, -1};
        int mid = (start + end) / 2;
        int[] row = mat[mid];
        int first = row[0];
        int last = row[row.length - 1];
        if(n >= first && n <= last) {
            int[] result = new int[2];
            result[0] = mid;
            result[1] = findInRow(n, row, 0, row.length - 1);
            if(result[1] == -1) return new int[]{-1, -1};
            return result;
        } else if (n < first) {
            return findInMatrix(n, mat, start, mid - 1);
        } else {
            return findInMatrix(n, mat, mid + 1, end);
        }
    }

    private static int findInRow(int n, int[] row, int start, int end) {
        if(start > end) return -1;
        int mid = (start + end) / 2;
        int middle = row[mid];
        if(middle == n) return mid;
        if(n < middle) {
            return findInRow(n, row, start, mid - 1);
        } else {
            return findInRow(n, row, mid + 1, end);
        }
    }
}