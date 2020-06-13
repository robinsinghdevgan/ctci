#include <bits/stdc++.h>
using namespace std;

int *getTarget(const vector<int> &A, const vector<int> &B)
{
    int sumA = accumulate(std::begin(A), std::end(A), 0);
    int sumB = accumulate(std::begin(B), std::end(B), 0);

    if ((sumA - sumB) % 2 != 0)
        return nullptr;
    return new int((sumA - sumB) / 2);
}

pair<int, int> sumSwap(vector<int> A, vector<int> B)
{
    sort(std::begin(A), std::end(A));
    sort(std::begin(B), std::end(B));

    int *result = getTarget(A, B);
    if (result == nullptr)
        return {};
    int target = *result;
    //cout << target;

    int a = 0;
    int b = 0;
    while (a < A.size() and b < B.size())
    {
        int diff = A[a] - B[b];

        if (diff == target)
        {
            return make_pair(A[a], B[b]);
        }
        else if (diff < target)
            ++a;
        else
            ++b;
    }
    return {};
}

int main(int argc, char const *argv[])
{
    vector<int> A{4, 1, 2, 1, 1, 2}, B{3, 6, 3, 3};
    auto p = sumSwap(A, B);
    cout << p.first << " " << p.second;
    return 0;
}
