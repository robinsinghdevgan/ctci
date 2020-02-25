import java.util.Set;
import java.util.TreeSet;

public class Ques1_8{
    public static void main(String[] args) {
        int[][] A = {
            {0,2,3,0,5},
            {6,7,8,9,10},
            {11,12,13,14,15},
            {16,17,18,19,20},
            {21,22,23,24,25}
        };
        printMatrix(A);
        A = zeroMatrix(A);
        printMatrix(A);
    }

    static int[][] zeroMatrix(int[][] A) {
        Set<Integer> rowWithZeroes = new TreeSet<Integer>();
        Set<Integer> colWithZeroes = new TreeSet<Integer>();
        for(int i = 0, rowCount = A.length; i < rowCount; ++i) {
            for(int j = 0, colCount = A[0].length; j < colCount; ++j) {
                if (A[i][j] == 0) {
                    rowWithZeroes.add(i);
                    colWithZeroes.add(j);
                }
            }
        }
        for (Integer colNum : colWithZeroes) {
            for(int i = 0, rowCount = A.length; i < rowCount; ++i) {
                A[i][colNum] = 0;
            }
        }
        for (Integer rowNum : rowWithZeroes) {
            for(int col = 0, colCount = A[0].length; col < colCount; ++col) {
                A[rowNum][col] = 0;
            }
        }
        return A;
    }

    static void printMatrix(int[][] A){
        int n = A[0].length;
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                System.out.printf("%-4d ", A[i][j]);
            }
            System.out.println();
        }
    }
}