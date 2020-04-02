public class Ques8_2 {
    public static void main(String[] args) {
        final boolean[][] maze1 = {
            {true,true,true,true,false},
            {true,false,true,true,true},
            {true,true,true,true,true},
            {true,false,false,true,true},
            {false,true,true,false,true}
        };
        Solution.findPath(maze1);

        final boolean[][] maze2 = {
            {true,false,true,true,false},
            {true,false,true,true,true},
            {true,true,true,true,true},
            {true,false,false,true,false},
            {false,true,false,true,true}
        };
        Solution.findPath(maze2);
    }
}

class Solution {
    private static boolean[][] visited;
    private static boolean[][] path;
    private static int numOfRows, numOfColumns;

    protected static void findPath(final boolean[][] maze) {
        if (maze == null)
            return;
        numOfRows = maze.length;
        numOfColumns = maze[0].length;
        visited = new boolean[numOfRows][numOfColumns];
        path = new boolean[numOfRows][numOfColumns];

        findPathHelper(maze,0,0);
        printMatrix(path);
    }

    private static void printMatrix(boolean[][] mx) {
        for (int i=0, size = mx.length; i<size;++i){
            for(int j=0,colSize = mx[0].length; j<colSize; ++j) {
                System.out.printf("%2d ", mx[i][j] ? 1 : 0);
            }
            System.out.println();
        }
        System.out.println();

        //Print all path coordinates
        for (int i=0, size = mx.length; i<size;++i){
            for(int j=0,colSize = mx[0].length; j<colSize; ++j) {
                if(mx[i][j])
                    System.out.printf("(%2d,%2d) -> ", i, j);
            }
        }
        System.out.println();
    }

    private static boolean findPathHelper(final boolean[][] maze, int i, int j) {
        if (i >= numOfRows || j >= numOfColumns)
            return false;
        if (visited[i][j])
            return false;
        if ((i == numOfRows-1) && (j == numOfColumns-1)){
            path[i][j] = true;
            return true;
        }
        if (!maze[i][j])
            return false;
        visited[i][j] = true;
        path[i][j] = findPathHelper(maze, i, j+1) ? true : findPathHelper(maze, i+1, j);
        return path[i][j];
    }
}