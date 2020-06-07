#include <bits/stdc++.h>
using namespace std;

int negate_int(int a)
{
    int neg_a = 0;
    int newSign = a < 0 ? 1 : -1;
    while (a)
    {
        neg_a += newSign;
        a += newSign;
    }
    return neg_a;
}

int subtract(int a, int b)
{
    return a + negate_int(b);
}

int multiply(int a, int b)
{
    //case 1: either of them is zero
    if (a == 0 or b == 0)
        return 0;
    //case 2: both are positive
    int ans = 0;
    int multiplier = a < b ? a : b;
    bool negative = false;
    if (multiplier < 0)
        negative = true;
    int times = abs(multiplier);
    int multiplicand = a < b ? b : a;
    while (times--)
    {
        ans += multiplicand;
    }
    return negative ? -ans : ans;
}

double divide(int a, int b)
{
    if (b == 0)
        throw(b);
    int quotient = 0, dividend;
    int divisor = negate_int(abs(b));
    bool differentSign = ((a<0 && b>0) || (a>0 && b<0));
    
    for (dividend = abs(a); dividend >= abs(divisor); dividend += divisor)
        quotient++;

    if (differentSign)
        quotient = negate_int(quotient);
    return quotient;
}

int main(int argc, char const *argv[])
{
    cout << negate_int(5) << endl;

    cout << "SUBTRACT\n";
    cout << subtract(6, 5) << endl;
    cout << subtract(6, -5) << endl;
    cout << subtract(-6, 5) << endl;
    cout << subtract(-6, -5) << endl;
    cout << subtract(5, -6) << endl;

    cout << "MULTIPLY\n";
    cout << multiply(5, 6) << endl;
    cout << multiply(-5, 6) << endl;
    cout << multiply(5, -6) << endl;
    cout << multiply(-5, -6) << endl;

    cout << "DIVIDE\n";
    cout << divide(30, 6) << endl;
    cout << divide(30, 5) << endl;
    cout << divide(-30, 6) << endl;
    cout << divide(-30, 5) << endl;
    cout << divide(30, -6) << endl;
    cout << divide(30, -5) << endl;
    cout << divide(-30, -6) << endl;
    cout << divide(-30, -5) << endl;

    return 0;
}
