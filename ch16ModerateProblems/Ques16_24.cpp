#include <bits/stdc++.h>
using namespace std;

using Pair = pair<int, int>; 
void print(vector<Pair> &arr)
{
    sort(arr.begin(), arr.end(), [](Pair &A, Pair &B){
        if(A.first > A.second)
            swap(A.first, A.second);
        if(B.first > B.second)
            swap(B.first, B.second);
        return A.first < B.first;
    });
    for (Pair p : arr)
        cout << "{" << p.first << ", " << p.second << "}, ";
    cout << endl;
}

void pairsWithSumBF(const vector<int> &arr, int sum)
{
    unsigned n = arr.size();
    vector<Pair> res;
    for (unsigned i = 0; i < n - 1; ++i)
    {
        for (unsigned j = i+1; j < n; ++j)
        {
            if (arr[i] + arr[j] == sum)
                res.emplace_back(make_pair(arr[i], arr[j]));
        }
    }
    print(res);
}

void pairsWithSumHM(const vector<int> &arr, int sum)
{
    unsigned n = arr.size();
    vector<Pair> res;
    std::unordered_map<int, int> hm;

    // do for each element
    for (int i = 0; i < n; i++)
    {
        // check if pair (arr[i], sum-arr[i]) exists

        // if difference is seen before, print the pair
        if (hm.find(sum - arr[i]) != hm.end())
        {
            res.emplace_back(make_pair(hm[sum - arr[i]], arr[i]));
        }
        // store index of current element in the hm
        hm[arr[i]] = arr[i];
    }
    print(res);
}

void pairsWithSumSorting(vector<int> &arr, int sum)
{
    unsigned n = arr.size();
    vector<Pair> res;
    // sort the array in ascending order
    std::sort(arr.begin(), arr.end());

    // maintain two indices pointing to end-points of the array
    int low = 0;
    int high = n - 1;

    // reduce search space arr[low..high] at each iteration of the loop

    // loop till low is less than high
    while (low < high)
    {
        // sum found
        if (arr[low] + arr[high] == sum)
        {
            res.emplace_back(make_pair(arr[low], arr[high]));
        }

        // increment low index if total is less than the desired sum
        // decrement high index is total is more than the sum
        (arr[low] + arr[high] < sum) ? low++ : high--;
    }
    print(res);
}

static mt19937 mte(chrono::steady_clock::now().time_since_epoch().count());

vector<int> generateRandomVector(int size, int range_start, int range_end)
{
    uniform_int_distribution<int> uit(range_start, range_end);
    vector<int> result(size);
    for (int i = 0; i < size; ++i)
        result[i] = uit(mte);
    return result;
}

int callAllMethods(vector<int> &A, int sum)
{
    cout << "\t...........BF.................\n";
    pairsWithSumBF(A, sum);
    cout << "\t...........HM.................\n";
    pairsWithSumHM(A, sum);
    cout << "\t...........SORT.................\n";
    pairsWithSumSorting(A, sum);
    return 0;
}

int t1()
{
    cout << "\n.......................t1.................\n";
    vector<int> A = {9, 3, 6, 5, 7, 7, -1, 13, 14, -2, 12, 0};
    int sum = 12;
    callAllMethods(A, sum);
    return 0;
}

int t2()
{
    cout << "\n.......................t2.................\n";
    vector<int> A = {8, 7, 2, 5, 3, 1};
    int sum = 10;
    callAllMethods(A, sum);
    return 0;
}

int t3()
{
    cout << "\n.......................t3.................\n";
    vector<int> A = generateRandomVector(20, 1, 12);
    int sum = 4;
    callAllMethods(A, sum);
    return 0;
}

int main()
{
    assert((t1() or t2() or t3()) == 0);
    return 0;
}
