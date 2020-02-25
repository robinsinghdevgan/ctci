#include <iostream>
#include <malloc.h>


int** my2DAlloc(int m, int n) {
    /*
    pointer to pointer method,(n+1) malloc calls
    int** arr = (int**)malloc(m * sizeof(int));
    for (int i = 0; i < n; ++i) {
        arr[i] = (int*)malloc(n * sizeof(int));
    }*/
    int len = sizeof(int *) * m + sizeof(int) * n * m; 
    int** arr = (int **)malloc(len); 
    int *ptr = (int *)(arr + m); 
  
    // for loop to point rows pointer to appropriate location in 2D array 
    for(int i = 0; i < m; i++) 
        arr[i] = (ptr + n * i); 
    return arr;
}

void printArray(int** arr, int m, int n) {
    for (int i = 0; i < m; ++i) {
         for (int j = 0; j < n; ++j) {
             std::cout << arr[i][j] << " ";
         }
         std::cout << "\n";
    }
}

int main() { 
  int **arr = my2DAlloc(5, 6);
  printArray(arr, 5, 6);
  return 0;
}