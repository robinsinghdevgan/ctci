#include <bits/stdc++.h>
using namespace std;

class Rank
{
    multiset<int> ms;

public:
    void insert(int d)
    {
        ms.insert(d);
    }
    int getRankOfNumber(int d)
    {
        auto it = std::upper_bound(ms.begin(), ms.end(), d);
        if (it != ms.end())
            return distance(ms.begin(), it) - 1;
        return -1;
    }
};

int main()
{
    vector<int> stream = {5, 1, 4, 4, 5, 9, 7, 13, 3};
    Rank r;
    for (int i : stream)
        r.insert(i);
    cout << r.getRankOfNumber(1) << endl;
    cout << r.getRankOfNumber(3) << endl;
    cout << r.getRankOfNumber(4) << endl;
    return 0;
}