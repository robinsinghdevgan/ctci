#include <bits/stdc++.h>
using namespace std;

int add(int a, int b)
{
    while (b)
    {
        int sum = a ^ b;
        int carry = (a & b) << 1;
        a = sum;
        b = carry;
    }
    return a;
}

//sub
int sub(int x, int y)
{
    // Iterate till there
    // is no carry
    while (y != 0)
    {
        // borrow contains common
        // set bits of y and unset
        // bits of x
        int borrow = (~x) & y;

        // Subtraction of bits of x
        // and y where at least one
        // of the bits is not set
        x = x ^ y;

        // Borrow is shifted by one
        // so that subtracting it from
        // x gives the required sum
        y = borrow << 1;
    }
    return x;
}

int main(int argc, char const *argv[])
{
    for (int i = 0, j = 203; i < 100; i += 2, j -= 5)
    {
        cout << "ADD: " << i << " and " << j << " -> " << i + j << " : " << add(i, j) << endl;
        cout << "SUB: " << j << " and " << i << " -> " << j - i << " : " << sub(j, i) << endl;
    }
    return 0;
}
