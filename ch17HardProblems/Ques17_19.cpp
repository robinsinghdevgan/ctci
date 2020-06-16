#include <bits/stdc++.h>
using namespace std;
  
// Function to find two missing numbers in range https://www.geeksforgeeks.org/find-two-missing-numbers-set-2-xor-based-solution/
// [1, n]. This function assumes that size of array 
// is n-2 and all array elements are distinct 
void findTwoMissingNumbers(vector<int> arr, int n) 
{ 
    /* Get the XOR of all elements in arr[] and 
       {1, 2 .. n} */
    int XOR = arr[0]; 
    for (int i = 1; i < arr.size(); i++) 
        XOR ^= arr[i]; 
    for (int i = 1; i <= n; i++) 
        XOR ^= i; 
  
    // Now XOR has XOR of two missing elements. Any set 
    // bit in it must be set in one missing and unset in 
    // other missing number 
  
    // Get a set bit of XOR (We get the rightmost set bit) 
    int set_bit_no = XOR & ~(XOR-1); 
  
    // Now divide elements in two sets by comparing rightmost 
    // set bit of XOR with bit at same position in each element. 
    int x = 0, y = 0; // Initialize missing numbers 
    for (int i = 0; i < arr.size(); i++) 
    { 
        if (arr[i] & set_bit_no) 
            x = x ^ arr[i]; /*XOR of first set in arr[] */
        else
            y = y ^ arr[i]; /*XOR of second set in arr[] */
    } 
    for (int i = 1; i <= n; i++) 
    { 
        if (i & set_bit_no) 
            x = x ^ i; /* XOR of first set in arr[] and 
                         {1, 2, ...n }*/
        else
            y = y ^ i; /* XOR of second set in arr[] and 
                         {1, 2, ...n } */
    } 
  
    printf("Two Missing Numbers are\n %d %d\n", x, y); 
} 
  
// Driver program to test above function 
int main() 
{ 
    vector<int> arr = {1, 3, 5, 6}; 
  
    // Range of numbers is 2 plus size of array 
    int n = 2 + arr.size(); 
  
    findTwoMissingNumbers(arr, n); 


    vector<int> arr2;
    for(int i=1; i<10000; ++i)
        if(i != 5555 and i != 4444)
            arr2.emplace_back(i);
    
    n = 2 + arr2.size(); 
  
    findTwoMissingNumbers(arr2, n); 
  
    return 0; 
} 