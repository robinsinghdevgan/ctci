import java.util.*;

class Pair {
    Integer i, j;

    Pair(Integer i, Integer j) {
        this.i = i;
        this.j = j;
    }
}

public class Ques10_9 {
    static Pair searchMatrixHelper(int[][] arr, boolean[][] visited, int key, int i, int j, int m, int n) {
        if ((i < 0 || j < 0) || (i >= n || j >= m))
            return null;
        if (visited[i][j] == true)
            return null;

        visited[i][j] = true;

        if (arr[i][j] == key)
            return new Pair(i, j);

        Pair result = null;
        if (key < arr[i][j]) {
            result = searchMatrixHelper(arr, visited, key, i - 1, j - 1, m, n);
            if (result != null) {
                return result;
            }
            result = searchMatrixHelper(arr, visited, key, i - 1, j, m, n);
            if (result != null) {
                return result;
            }
            result = searchMatrixHelper(arr, visited, key, i - 1, j + 1, m, n);
            if (result != null) {
                return result;
            }
            result = searchMatrixHelper(arr, visited, key, i, j - 1, m, n);
            if (result != null) {
                return result;
            }
        }

        else if (key > arr[i][j]) {
            result = searchMatrixHelper(arr, visited, key, i, j + 1, m, n);
            if (result != null) {
                return result;
            }
            result = searchMatrixHelper(arr, visited, key, i + 1, j - 1, m, n);
            if (result != null) {
                return result;
            }
            result = searchMatrixHelper(arr, visited, key, i + 1, j, m, n);
            if (result != null) {
                return result;
            }
            result = searchMatrixHelper(arr, visited, key, i + 1, j + 1, m, n);
            if (result != null) {
                return result;
            }
        }
        return result;
    }

    static Pair searchMatrix(int[][] arr, int key) {
        int m = arr.length;
        int n = arr[0].length;
        int midRow = m / 2;
        int midCol = n / 2;
        boolean[][] visited = new boolean[m][n];
        // System.out.println(arr[midRow][midCol]);
        Pair res = searchMatrixHelper(arr, visited, key, midRow, midCol, m, n);
        // printMatrixBoolean(visited);
        return res;
    }

    public static Pair searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return null;

        int numOfRows = matrix.length;
        int numOfCols = matrix[0].length;

        int begin = 0, end = numOfRows * numOfCols - 1;

        while (begin <= end) {
            int mid = begin + ((end - begin) / 2);
            int rowIndex = mid / numOfCols;
            int colIndex = mid % numOfCols;
            int mid_value = matrix[rowIndex][colIndex];

            if (mid_value == target) {
                return new Pair(rowIndex, colIndex);

            } else if (mid_value < target) {
                // Should move a bit further, otherwise dead loop.
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return null;
    }

    public static void testBruteForce(int[][] A, ArrayList<Integer> keys) {

        ArrayList<Pair> res = new ArrayList<Pair>();
        long startTime = System.nanoTime();
        for (Integer i : keys) {
            res.add(searchMatrix(A, i));
        }

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds  : " + timeElapsed);

        System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
        for (Pair pair : res) {
            if (pair != null)
                System.out.print("[" + pair.i + "," + pair.j + "] , ");
            else
                System.out.print("null, ");
        }
        System.out.println();
    }

    public static void testFaster(int[][] A, ArrayList<Integer> keys) {

        ArrayList<Pair> res = new ArrayList<Pair>();
        long startTime = System.nanoTime();
        for (Integer i : keys) {
            res.add(searchMatrix2(A, i));
        }

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds  : " + timeElapsed);

        System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
        for (Pair pair : res) {
            if (pair != null)
                System.out.print("[" + pair.i + "," + pair.j + "] , ");
            else
                System.out.print("null, ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] A = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
                { 21, 22, 23, 24, 25 } };

        ArrayList<Integer> keys = new ArrayList<Integer>();
        for (int[] arr : A)
            for (int x : arr)
                keys.add(x);
        int[] nonExistents = { -1, -2, -3, -4, -5, 55, 66, 77, 88 };
        for (int i : nonExistents)
            keys.add(i);

        printMatrix(A);

        // printMatrix(A);

        testBruteForce(A, keys);
        testFaster(A, keys);
    }

    static void printMatrix(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.printf("%-4d ", arr[i][j]);
            }
            System.out.println();
        }
    }

    static void printMatrixBoolean(boolean[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}