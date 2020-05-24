#include <bits/stdc++.h>
using namespace std;

template<class T>
void numswap(T &a, T &b)
{
    //won't work with floating data types
    a ^= b;
    b ^= a;
    a ^= b;
}
template<class T>
void numswap2(T &a, T &b)
{
    a = a-b;
    b += a;
    a = b-a;
}
int main()
{
    int a = 5;
    int b = 6;
    numswap(a, b);
    cout << a <<  " " << b << endl;

    float ad = 5.5, bd = 6.6;
    numswap2(ad, bd);
    cout << ad <<  " " << bd << endl;
    return 0;
}