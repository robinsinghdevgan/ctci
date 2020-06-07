#include <bits/stdc++.h>
using namespace std;

int smallestDifference(vector<int> A, vector<int> B)
{
    sort(A.begin(), A.end());
    sort(B.begin(), B.end()); //ascending order
    int a = 0, b = 0; 
  
    // Initialize result as max value 
    int min_difference = std::numeric_limits<int>::max(); 
  
    // Scan Both Arrays upto  
    // sizeof of the Arrays 
    while (a < A.size() and b < B.size()) 
    { 
        int difference = abs(A[a] - B[b]);
        if (difference < min_difference) 
            min_difference = difference; 
  
        // Move Smaller Value 
        if (A[a] < B[b]) 
            a++; 
  
        else
            b++; 
    } 
    return min_difference;  
}

int main(int argc, char const *argv[])
{
    vector<int> A = {1,3,15,11,2}, B = {23,127,265,19,8};
    cout << smallestDifference(A, B);
    return 0;
}