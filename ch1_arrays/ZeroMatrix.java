import java.util.List;
import java.util.ArrayList;

class ZeroMatrix {;
    public static void main(String[ ] args) {
        int[][] mat = {
            {1, 2, 3, 4, 0},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 0, 18, 19, 20},
            {21, 22, 23, 24, 25}
        };
        int[][] mat2 = {{1, 2},{3, 4}};
        zeroMatrix_o1space(mat);
        printMatrix(mat);
    }
    
    static void zeroMatrix_o1space(int[][] mat) {
        boolean rowHasZeros = false;
        boolean colHasZeros = false;
        
        for(int i = 0; i < mat.length; i++) {
            if(mat[i][0] == 0) colHasZeros = true;
        }
        
        for(int j = 0; j < mat[0].length; j++) {
            if(mat[0][j] == 0) rowHasZeros = true;
        }
        
        for(int i = 1; i < mat.length; i++) {
            for(int j = 1; j < mat[i].length; j++) {
                if(mat[i][j] == 0) {
                    mat[i][0] = 0;
                    mat[0][j] = 0;
                }
            }
        }
        
        for(int i = 0; i < mat.length; i++) {
            if(mat[i][0] == 0) nullifyRow(mat, i);
        }
        
        for(int j = 0; j < mat[0].length; j++) {
            if(mat[0][j] == 0) nullifyCol(mat, j);
        }
        
        if(rowHasZeros) nullifyRow(mat, 0);
        if(colHasZeros) nullifyCol(mat, 0);
    }
    
    static void nullifyCol(int[][] mat, int col) {
        for(int j = 0; j < mat.length; j++) {
            mat[j][col] = 0;
        }
    }
    
    static void nullifyRow(int[][] mat, int row) {
        for(int i = 0; i < mat[row].length; i++) {
            mat[row][i] = 0;
        }
    }
    
    static void zeroMatrix(int[][] mat) {
        List<Integer> zRows = new ArrayList();
        List<Integer> zCols = new ArrayList();
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++) {
                if(mat[i][j] == 0) {
                    zRows.add(i);
                    zCols.add(j);
                }
            }
        }
        zeroRowsAndCols(mat, zRows, zCols);
    }
    
    static void zeroRowsAndCols(int[][] mat, List<Integer> rows, List<Integer> cols) {
        for(int row : rows) {
            for(int j = 0; j < mat[row].length; j++) {
                mat[row][j] = 0;
            }
        }
        for(int col : cols) {
            for(int i = 0; i < mat.length; i++) {
                mat[i][col] = 0;
            }
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
