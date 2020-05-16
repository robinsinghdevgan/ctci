#include <bits/stdc++.h>
using namespace std;


int getNextArith(int n)
{
    int c = n;
    int c0 = __builtin_ctz(n);
    auto trailing_ones = [](int n) -> int {
        //for signed int only
        //~n of n is negative(n) - 1, e.g 3 : -4
        //~n & n+1 is what just ~n would do for unsigned n
        cout << "n : " << n;
        cout <<"\n~n : " << ~n;
        cout <<"\n(n+1) : " << (n+1);
        cout << "\n ~n & (n+1) : " << (~n & (n+1)) << endl;
        cout << bitset<16>(~n & (n+1)) << endl;
        // perform 1's complement
        n = ~n & (n+1);
        return __builtin_ctz(n); //return trailing ones
    };
    int c1 = trailing_ones(n>>c0);

    /* If c is 0, then n is a sequence of 1s followed by a sequence of 0s. This is already the biggest
		 * number with c1 ones. Return error.
		 */
    if (c0 + c1 == 31 || c0 + c1 == 0)
    {
        return -1;
    }

    /* Arithmetically:
		 * 2^c0 = 1 << c0
		 * 2^(c1-1) = 1 << (c0 - 1)
		 * next = n + 2^c0 + 2^(c1-1) - 1;
		 */

    cout << "c0: " << c0 << " c1: " << c1 << endl;
    cout << bitset<16>(n) << endl;
    cout << bitset<16>((1 << c0)) << endl;
    cout << bitset<16>((1 << (c1 - 1))) << endl;
    cout << bitset<16>((1 << c0) + (1 << (c1 - 1))) << endl;
    cout << bitset<16>(n + (1 << c0) + (1 << (c1 - 1)) - 1) << endl;
    return n + (1 << c0) + (1 << (c1 - 1)) - 1;
}

int main()
{
    cout << getNextArith(6);
    return 0;
}
