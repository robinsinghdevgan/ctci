#include <bits/stdc++.h>
using namespace std;

//allBoardSizes: Built With K_Planks, Planks: big, small
set<int> allBoardSizes(int k, int shortPlankLen, int longPlankLen)
{
    set<int> sizes;
    int shortPlanks, longPlanks = k;
    for (shortPlanks = 0; shortPlanks <= k; ++shortPlanks, --longPlanks)
    {
        int combination = shortPlankLen * shortPlanks + longPlankLen * longPlanks;
        sizes.insert(combination);
    }
    return sizes;
}

int main()
{
    set<int> ans1 = allBoardSizes(50, 5, 10);
    set<int> ans2 = allBoardSizes(50, 1, 10);
    set<int> ans3 = allBoardSizes(50, 2, 5);
    set<int> ans4 = allBoardSizes(60, 2, 3);

    vector<set<int>> test_Results = {ans1, ans2, ans3, ans4};
    for (set<int> s : test_Results)
    {
        cout << "{";
        for (int i : s)
            cout << i << ",";
        cout << "}\n\n";
    }
}
