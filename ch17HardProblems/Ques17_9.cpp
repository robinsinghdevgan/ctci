
//https://sites.google.com/site/spaceofjameschen/annnocements/findthekthnumbersuchthattheonlyprimefactorsare35and7
/*
============================================================================
Author         : James Chen
Email          : a.james.chen@gmail.com
Description    : Find the kth number such that the only prime factors are 3, 5, and 7
Created Date   : 12-July-2013
Last Modified  :
===========================================================================
*/
#include <iostream>
#include <iomanip>
#include <vector>
#include <algorithm>

using namespace std;

static inline int min3(int a, int b, int c)
{
    return min(min(a, b), c);
}

static int KthFactorOf357(int k)
{
    int count3(0), count5(0), count7(0);

    vector<int> v;
    v.push_back(1);

    while(v.size() <= (unsigned)k + 1)
    {
        int m = min3(v[count3] * 3, v[count5] * 5, v[count7] * 7);

        v.push_back(m);
        if(m == v[count3] * 3) count3 ++;
        if(m == v[count5] * 5) count5 ++;
        if(m == v[count7] * 7) count7 ++;
    }

    return v[k];
}


int main(int argc, char* argv[])
{
    for(int i = 0; i < 15; ++i){
        cout << i << "th ---- ";
        cout << KthFactorOf357(i) << endl;
    }
 return 0;
}