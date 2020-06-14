#include <bits/stdc++.h>
using namespace std;

static mt19937 mte(chrono::steady_clock::now().time_since_epoch().count());

int my_random_int_gen(unsigned range_start, unsigned range_end)
{
    uniform_int_distribution<unsigned> uit(range_start, range_end);
    int res = uit(mte);
    cout << "Range(" << range_start << "," << range_end << ") : " << res << endl;
    return res;
}

vector<int> random_m_elements_from_array(vector<int> original, unsigned m)
{
    unsigned n = original.size();
    if (m > n)
        return {};
    vector<int> subset(m);

    /* Fill in subset array with first part of original array */
    for (unsigned i = 0; i < m; i++)
    {
        subset[i] = original[i];
    }

    /* Go through rest of original array. */
    for (unsigned i = m; i < n; i++)
    {
        int k = my_random_int_gen(0, i); // Generate random between 0 and i (inclusive)
        if (k < m)
        {
            subset[k] = original[i];
        }
    }

    return subset;
}

int main(int argc, char const *argv[])
{
    vector<int> A = {28, 46, 37, 29, 47, 11, 13, 26, 41, 6, 27, 32, 16, 10, 18, 24, 48, 36, 45, 2, 23, 21, 14, 0, 7, 12, 3, 15, 38, 33, 51, 19, 39, 31, 17, 20, 35, 44, 22, 8, 43, 25, 9, 40, 30, 1, 34, 50, 5, 52, 42, 4, 49};
    vector<int> m_random_ele_subset = random_m_elements_from_array(A, 25);
    for (const int &i : m_random_ele_subset)
        cout << i << " ";
    cout << endl;
    return 0;
}
