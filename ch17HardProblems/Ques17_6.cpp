#include <bits/stdc++.h>
using namespace std;

static int count2sInRangeAtDigit(int number, int d)
{
    int powerOf10 = static_cast<int>(std::pow(10, d));
    int nextPowerOf10 = powerOf10 * 10;
    int right = number % powerOf10;

    int roundDown = number - number % nextPowerOf10;
    int roundUp = roundDown + nextPowerOf10;

    int digit = (number / powerOf10) % 10;
    /*debug prints*/
    cout << "\n________number " << number
         << "\n\td " << d
         << "\n\tpowerOf10 = pow(10, d); \t" << powerOf10
         << "\n\tnextPowerOf10 = powerOf10 * 10; \t" << nextPowerOf10
         << "\n\tright = number % powerOf10; \t" << right
         << "\n\troundDown = number - number % nextPowerOf10; \t" << roundDown
         << "\n\troundUp = roundDown + nextPowerOf10; \t" << roundUp
         << "\n\tdigit = (number / powerOf10) % 10; \t" << digit;
    if (digit < 2)
    { // if the digit in spot digit is
        cout << "\n\t*****return roundDown / 10; \t" << roundDown / 10 << endl;
        return roundDown / 10;
    }
    else if (digit == 2)
    {
        cout << "\n\t*****return roundDown / 10 + right + 1; \t " << roundDown / 10 + right + 1 << endl;
        return roundDown / 10 + right + 1;
    }
    else
    {
        cout << "\n\t*****return roundUp / 10; \t" << roundUp / 10 << endl;
        return roundUp / 10;
    }
}

static int count2sInRange(int number)
{
    int count = 0;
    int len = std::to_string(number).length();
    for (int digit = 0; digit < len; digit++)
    {
        count += count2sInRangeAtDigit(number, digit);
    }
    return count;
}

static int count2sR(int n)
{
    /* Alternate, messier, solution */

    // Example: n = 513

    // Base case
    if (n == 0)
    {
        return 0;
    }

    // Split apart 513 into 5 * 100 + 13.
    // [Power = 100; First = 5; Remainder = 13]
    int power = 1;
    while (10 * power < n)
    {
        power *= 10;
    }
    int first = n / power;
    int remainder = n % power;

    // Counts 2s from first digit
    int nTwosFirst = 0;
    if (first > 2)
    {
        nTwosFirst += power;
    }
    else if (first == 2)
    {
        nTwosFirst += remainder + 1;
    }

    // Count 2s from all other digits
    int nTwosOther = first * count2sR(power - 1) + count2sR(remainder);

    return nTwosFirst + nTwosOther;
}

int main()
{
    for (int i = 140; i < 210; i += 7)
    {
        std::cout << "Between 0 and " << i << ": ";
        int v1 = count2sR(i);
        int v2 = count2sInRange(i);
        std::cout << v1 << " " << v2 << std::endl;
    }
    return 0;
}