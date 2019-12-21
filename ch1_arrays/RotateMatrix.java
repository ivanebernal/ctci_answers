class RotateMatrix {
    public static void main(String[ ] args) {
        int[][] mat = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
        };
        int[][] mat2 = {{1, 2},{3, 4}};
        rotate(mat2);
        printMatrix(mat2);
    }
    
    static void rotate(int[][] mat) {
        int last;
        int temp;
        int end = mat.length;
        int start = 0;
        while(end - start >= 2) {
            for(int i = start; i < end - 1; i++) {
                temp = mat[start][ i];
                mat[start][i] = mat[i][end - 1];
                mat[i][end - 1] = mat[end-1][end - 1 - i + start];
                mat[end-1][end - 1 - i + start] = mat[end - 1 - i + start][start];
                mat[end - 1 - i + start][start] = temp;
            }
            end--;
            start++;
        }
    }
    
    static void printMatrix(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
