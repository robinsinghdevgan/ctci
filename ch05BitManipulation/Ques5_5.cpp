#include <bits/stdc++.h>
using namespace std;


bool test_power_of_two(int n)
{
    cout << "****\ntesting int : " << n;
    cout <<"\nn         : " << bitset<32>(n);
    cout <<"\nn-1       : " << bitset<32>(n-1);
    cout <<"\nn & (n-1) : " << bitset<32>(n & (n-1)) << endl << endl;

    return (n & (n-1)) == 0;
}

int main() 
{
    //positive
    cout << test_power_of_two(2) << endl;
    cout << test_power_of_two(4) << endl;
    cout << test_power_of_two(8) << endl;
    cout << test_power_of_two(16) << endl;
    cout << test_power_of_two(32) << endl;
    
    //negative
    cout << test_power_of_two(3) << endl;
    cout << test_power_of_two(9) << endl;
    cout << test_power_of_two(27) << endl;
    cout << test_power_of_two(81) << endl;
    cout << test_power_of_two(243) << endl;
    
}