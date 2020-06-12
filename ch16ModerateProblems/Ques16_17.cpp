#include <bits/stdc++.h>
using namespace std;

int largestSumContigousSequence(const vector<int> &arr)
{
    //discuss with interviewer what to return when there are all negative numbers
    //whether to return least negative number, 0 (seq len) or max_int as error
    int maxsum = 0; 
    int sum = 0;
    for (int i = 0; i < arr.size(); ++i)
    {
        sum += arr[i]; //accumulate positive sum
        if (maxsum < sum)
            maxsum = sum;
        else if (sum < 0)
            sum = 0;
    }
    return maxsum;
}

int largestSumContigousSequence2(const vector<int> &a)
{ 
   int max_so_far = a[0]; 
   int curr_max = a[0]; 
  
   for (int i = 1; i < a.size(); i++) 
   { 
        curr_max = max(a[i], curr_max+a[i]); 
        max_so_far = max(max_so_far, curr_max); 
   } 
   return max_so_far; 
} 

int main()
{
    vector<int> arr = {2, -8, 3, -2, 4, -10}; //some positive, some negative
    cout << largestSumContigousSequence(arr) << endl; //should be 5
    cout << largestSumContigousSequence2(arr) << endl; //should be 5

    arr = {-2, -8, -3, -2, -4, -10}; //all negative
    cout << largestSumContigousSequence(arr) << endl; //should be 0
    cout << largestSumContigousSequence2(arr) << endl; //should be -2

    arr = {2, 8, 3, 2, 4, 10}; // all positive
    cout << largestSumContigousSequence(arr) << endl; //should be 29
    cout << largestSumContigousSequence2(arr) << endl; //should be 29

    arr = {-2, -8, -3, 2, 4, 10}; //some positive, some negative
    cout << largestSumContigousSequence(arr) << endl; //should be 16
    cout << largestSumContigousSequence2(arr) << endl; //should be 16
    return 0;
}