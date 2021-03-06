
public class Ques17_23 {
    public static Subsquare findSquareWithSize(SquareCell[][] processed, int square_size) {
		// On an edge of length N, there are (N - sz + 1) squares of length sz.
		int count = processed.length - square_size + 1; 
		
		// Iterate through all squares with side length square_size.
		for (int row = 0; row < count; row++) {
			for (int col = 0; col < count; col++) {
				if (isSquare(processed, row, col, square_size)) {
					return new Subsquare(row, col, square_size);
				}
			}
		}
		return null;
	}
	
	public static Subsquare findSquare(int[][] matrix){
		assert(matrix.length > 0);
		for (int row = 0; row < matrix.length; row++){
			assert(matrix[row].length == matrix.length);
		}
		
		SquareCell[][] processed = processSquare(matrix);
		
		int N = matrix.length;
		
		for (int i = N; i >= 1; i--) {
			Subsquare square = findSquareWithSize(processed, i);
			if (square != null) {
				return square;
			}
		}
		return null;
	}	

	private static boolean isSquare(SquareCell[][] matrix, int row, int col, int size) {
		SquareCell topLeft = matrix[row][col];
		SquareCell topRight = matrix[row][col + size - 1];
		SquareCell bottomRight = matrix[row + size - 1][col];
		if (topLeft.zerosRight < size) { // Check top edge
			return false;
		}
		if (topLeft.zerosBelow < size) { // Check left edge
			return false;
		}
		if (topRight.zerosBelow < size) { // Check right edge
			return false;
		}
		if (bottomRight.zerosRight < size) { // Check bottom edge
			return false;
		}
		return true;
	}
	
	public static SquareCell[][] processSquare(int[][] matrix) {
		SquareCell[][] processed = new SquareCell[matrix.length][matrix.length];
		
		for (int r = matrix.length - 1; r >= 0; r--) {
			for (int c = matrix.length - 1; c >= 0; c--) {
				int rightZeros = 0;
				int belowZeros = 0;
				if (matrix[r][c] == 0) { // only need to process if it's a black cell
					rightZeros++;
					belowZeros++;
					if (c + 1 < matrix.length) { // next column over is on same row
						SquareCell previous = processed[r][c + 1];
						rightZeros += previous.zerosRight;
					}
					if (r + 1 < matrix.length) {
						SquareCell previous = processed[r + 1][c];
						belowZeros += previous.zerosBelow;
					}
				}
				processed[r][c] = new SquareCell(rightZeros, belowZeros);
			}
		}	
		return processed;
	}
	
	public static void main(String[] args) {
		int[][] matrix = randomMatrix(7, 7, 0, 1);
		printMatrix(matrix);
		Subsquare square = findSquare(matrix);
		square.print();
    }

    
    public static int[][] randomMatrix(int M, int N, int min, int max) {
		int[][] matrix = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = randomIntInRange(min, max);
			}
		}
		return matrix;
    }
    
    public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
    }
    
    public static int randomInt(int n) {
		return (int) (Math.random() * n);
    }
    
    public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] < 10 && matrix[i][j] > -10) {
					System.out.print(" ");
				}
				if (matrix[i][j] < 100 && matrix[i][j] > -100) {
					System.out.print(" ");
				}
				if (matrix[i][j] >= 0) {
					System.out.print(" ");
				}
				System.out.print(" " + matrix[i][j]);
			}
			System.out.println();
		}
	}

}

class Subsquare {
	public int row, column, size;
	public Subsquare(int r, int c, int sz) {
		row = r;
		column = c;
		size = sz;
	}
	
	public void print() {
		System.out.println("(" + row + ", " + column + ", " + size + ")");
	}
}

class SquareCell {
	public int zerosRight = 0;
	public int zerosBelow = 0;
	public SquareCell(int right, int below) {
		zerosRight = right;
		zerosBelow = below;
	}
	
	public void setZerosRight(int right) {
		zerosRight = right;
	}
	
	public void setZerosBelow(int below) {
		zerosBelow = below;
	}
}