#include <bits/stdc++.h>
using namespace std;

int rand5()
{
    return rand() % 5; //0 - 4
}

//given rand5 implement rand7
int rand7() //0-6
{
    while (true)
    {
        int evens_btw_0_9 = rand5() * 2;
        int btw_0_4 = rand5();
        if (btw_0_4 not_eq 4) // avoid 4, 4%2 equals 2
        {
            int zero_or_one = btw_0_4 % 2; //0 or 1
            int num = zero_or_one + evens_btw_0_9;
            if (num < 7)
                return num;
        }
    }
}

int main()
{
    srand(time(0));
    for(int i=0; i<200; ++i)
        cout << rand7() << ", ";
    return 0;
}