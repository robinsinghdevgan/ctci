#include <bits/stdc++.h>
using namespace std;

unsigned flip_one_bit_to_get_max_consecutive_ones(unsigned a)
{
    if(~a==0) return std::numeric_limits<unsigned>::digits; //all 1s (32 bits)

    unsigned currlen = 0, prevlen=0, maxlen=1;

    while(a){
        if(a&1==1) ++currlen;
        else{
            prevlen = (a&2)==0 ? 0 : currlen;
            currlen=0;
        }
        maxlen = std::max(prevlen+currlen+1, maxlen);
        //cout<<bitset<32>(a)<<endl;
        a >>=1 ;
    }
    return maxlen;
}

int main()
{
    unsigned m = std::numeric_limits<unsigned>::max();
    
    unsigned n = m - ((unsigned)1<<31);
    //cout<<n << " " << ~n<<endl;
    cout<<bitset<32>(n)<< " " << flip_one_bit_to_get_max_consecutive_ones(n)<<endl;
    
    n = m - ((unsigned)1);
    cout<<bitset<32>(n)<< " " << flip_one_bit_to_get_max_consecutive_ones(n)<<endl;

    n = m - ((unsigned)1<<1);
    cout<<bitset<32>(n)<< " " << flip_one_bit_to_get_max_consecutive_ones(n)<<endl;

    n = m - ((unsigned)1<<30);
    cout<<bitset<32>(n)<< " " << flip_one_bit_to_get_max_consecutive_ones(n)<<endl;

    /**/
    n = m - ((unsigned)1<<30);
    //cout<<n << " " << ~n<<endl;
    cout<<bitset<32>(n)<< " " << flip_one_bit_to_get_max_consecutive_ones(n)<<endl;
    
    n = m - (2);
    cout<<bitset<32>(n)<< " " << flip_one_bit_to_get_max_consecutive_ones(n)<<endl;

    n = m - ((unsigned)1<<2);
    cout<<bitset<32>(n)<< " " << flip_one_bit_to_get_max_consecutive_ones(n)<<endl;

    n = m - ((unsigned)1<<29);
    cout<<bitset<32>(n)<< " " << flip_one_bit_to_get_max_consecutive_ones(n)<<endl;
    return 0;
}