#include <iostream>
#include <bitset>
using namespace std;


int swapOddEvenBits(unsigned int a)
{
   return ((a & 0xaaaaaaaa) >> 1) | ((a & 0x55555555) << 1);
}

int main()
{
    int a = 0xAAAA;
    cout << "Number is " << a << endl;
    cout << bitset<32>(a) << endl;
    
    cout << "0xaaaaaaaa :\n" << bitset<32>(0xaaaaaaaa)<< endl;
    cout << "0x55555555:\n" << bitset<32>(0x55555555)<< endl;
    
    cout << "a & 0xaaaaaaaa : \n" << bitset<32>(a & 0xaaaaaaaa) << endl;
    cout << "a & 0x55555555 : \n" << bitset<32>(a & 0x55555555) << endl;
    
    cout << "(a & 0xaaaaaaaa) >> 1 : \n" << bitset<32>((a & 0xaaaaaaaa) >> 1)<< endl;
    cout << "(a & 0x55555555) << 1 : \n" << bitset<32>((a & 0x55555555) << 1)<< endl;

    int ans = swapOddEvenBits(a);
    cout << ans << "\n" << bitset<32>(ans) << endl;

    return 0;
}