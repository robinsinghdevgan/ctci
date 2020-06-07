#include <bits/stdc++.h>
using namespace std;

int trailingZereos(int n)
{
    return (n==0) ? 0 : n/5 + trailingZereos(n/5);
}

int iterativeTrailingZereos(int n)
{
    int count = 0;
    while(n>0)
    {
        int temp = n/5;
        count += temp;
        n = temp;
    }
    return count;
}

int main(int argc, char const *argv[])
{
    for(int i=1;i<=100; ++i)
    cout<< i << " : " << trailingZereos(i)<< " " << iterativeTrailingZereos(i) << endl;
    return 0;
}
