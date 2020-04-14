#include <bits/stdc++.h>
using namespace std;

string printBinary(double n)
{
    //if(n>=1||n<=0)  return "";

    string binary = ".";

    while (n>0){
        if(binary.size()>=32) break;
        double r = n*2;
        cout<<r<<"  ";
        if(r>=1){
            binary += '1';
            n = r-1;
        }
        else{
            binary += '0';
            n=r;
        }
    }
    return binary;
}

int main()
{
    double n = 0.625;
    cout << printBinary(n);
    return 0;
}