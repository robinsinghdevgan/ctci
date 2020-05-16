#include <bits/stdc++.h>
using namespace std;

int bitsSwapRequired(int a, int b)
{
    return __builtin_popcount(a ^ b);
}

int bitsSwapRequired2(int a, int b)
{
    int count = 0;
    for (int x = a ^ b; x != 0; x = x & (x - 1))
        ++count;
    return count;
}

int main()
{
    int a = 129837, b = 239281;
    cout << bitset<32>(a) << endl
         << bitset<32>(b) << endl;
    cout << bitset<32>(a ^ b) << endl;
    cout << bitsSwapRequired(a, b) << endl;
    cout << bitsSwapRequired2(a, b) << endl;
    return 0;
}