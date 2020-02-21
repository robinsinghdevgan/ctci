public final class Ques1_5 {
    public static int[][] rotateMatrix(int[][] A){
        int n = A[0].length;
        int[][] B = new int[n][n];
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                B[j][(n-1)-i] = A[i][j];
            }
        }
        return B; 
    }

    public static int[][] rotateMatrix2(int[][] A){
        int n = A[0].length;
        
        for(int layer=0; layer<=n/2; ++layer){
            //topLeft = A[layer][layer];
            //topRight = A[layer][(n-1)-layer];
            //bottomLeft = A[(n-1)-layer][layer];
            //bottomRight = A[(n-1)-layer][(n-1)-layer];
            int layerMinIndex = layer;
            int layerMaxIndex = (n-1)-layer;
            //traverse the layer to rotate elements at edges
            for(int i = layerMinIndex; i < layerMaxIndex; ++i){
                //temp = topLeft
                int temp = A[layerMinIndex][layerMinIndex + i];
                //topLeft = bottomLeft
                A[layerMinIndex][layerMinIndex + i] = A[layerMaxIndex - i][layerMinIndex];
                //bottomLeft = bottomRight
                A[layerMaxIndex - i][layerMinIndex] = A[layerMaxIndex][layerMaxIndex - i];
                //bottomRight = topRight
                A[layerMaxIndex][layerMaxIndex - i] = A[layerMinIndex + i][layerMaxIndex];
                //topRight = temp
                A[layerMinIndex + i][layerMaxIndex] = temp; 
            }
        }
        return A; 
    }

    public static void printMatrix(int[][] A){
        int n = A[0].length;
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                System.out.printf("%-4d ", A[i][j]);
            }
            System.out.println();
        }
    }
  public static void main(String[] args) {
      int[][] A = {{1,2,3,4,111},
      {5,6,7,8,222},
      {9,10,11,12,333},
      {13,14,15,16,444},
      {17,18,19,20,555}};
      printMatrix(A);
      int[][] B = rotateMatrix(A);
      System.out.println("\n90 degrees right rotation using new matrix\n");
      printMatrix(B);
      B = rotateMatrix2(A);
      System.out.println("\n90 degrees right rotation using in place\n");
      printMatrix(B);
  }
}