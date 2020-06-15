//https://sites.google.com/site/spaceofjameschen/home/array/find-the-majority-number----google

/*
 ============================================================================
 Author         : James Chen
 Email          : a.james.chen@gmail.com
 Description    : Find the majority number in a given sequence of numbers
 Created Date   : 16-06-2013 
 Last Modified  : 17-06-2013
 ============================================================================
 */

#include <iostream>
#include <iomanip>
#include <iterator>
#include <algorithm>
using namespace std;


bool FindMajorityNumber(int* arr, int len, int& majorNum)
{
    int* candidate = new int[len];
    int candidateLen(0);
    int* pCandidate(candidate);
    bool retVal(false);
    for(int i = 0; i < len + 2; i += 3){
        int a = arr[i];
        int b = arr[(i + 1) % len];
        int c = arr[(i + 2) % len];
        if(a == b || a == c){
            *pCandidate = a;
            pCandidate ++;
        }
        else if(b == c){
            *pCandidate = b;
            pCandidate ++;
        }
    }

    candidateLen = pCandidate - candidate;

    for(int i = 0; i < candidateLen; ++i){
        int cnt(0);
        for(int j = 0; j < len; j++){
            if(candidate[i] == arr[j]){
                cnt ++;
            }

            if(cnt > len / 2){
                majorNum = candidate[i];
                retVal = true;
                goto EXIT;                 
            }
        }
    }

EXIT:  
    delete[] candidate;
    return retVal;
}

int main(int argc, char* argv[])
{
    int testCase[20] = {1, 1, 2, 3, 4, 1, 6, 1, 7, 1, 1};
    int len = 11; //sizeof(testCase)/sizeof(testCase[0]);
    int majorNum;

    cout << "Test case 0" << endl;
    for(int j = 0; j < len; j++){

        cout << setw(4) << testCase[j];
    }
    cout << endl;

    bool found = FindMajorityNumber(testCase, len, majorNum);

    if(found){
        cout << "The major number is " << majorNum << endl;
    }
    else{
        cout << "There is no major number!" << endl;
    }

    cout << " --------------------------------- " << endl;
    len = 20;
    for(int i = 1; i < len; ++i){
        cout << "Test case " << i << endl;
        for(int j = 0; j < i; j++){
            if(i < 9){
                testCase[j] = rand() % i;
            }
            else{
                testCase[j] = rand() % (i - i / 2 - i / 4 - i / 8);
            }
            cout << setw(4) << testCase[j];
        }
        cout << endl;

        found = FindMajorityNumber(testCase, i, majorNum);

        if(found){
            cout << "The major number is " << majorNum << endl;
        }
        else{
            cout << "There is no major number!" << endl;
        }

        cout << " --------------------------------- " << endl;
    }

    return 0;
}