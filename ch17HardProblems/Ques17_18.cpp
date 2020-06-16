#include <bits/stdc++.h>
using namespace std;
using vi = vector<int>;
using pii = pair<int, int>;

pii paishortestSupersequence(vi arr, vi set)
{
    unordered_map<int, int> hm;
    for (int i = 0; i < arr.size(); ++i) //O(N): time and space
        hm[arr[i]] = i;
    int min = numeric_limits<int>::max();
    int max = numeric_limits<int>::min();
    for (const int &i : set) //O(K)
    {
        if (hm.find(i) != hm.end())
        {
            int index = hm[i];
            min = std::min(min, index);
            max = std::max(max, index);
        }
    }
    cout << min << " " << max << endl;
    return make_pair(min, max);
}

int main()
{
    vi array = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
    vi set = {1, 5, 9};
    paishortestSupersequence(array, set);
    return 0;
}