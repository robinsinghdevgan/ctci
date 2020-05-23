#include <bits/stdc++.h>
using namespace std;

void peaksAndValley(vector<int> &input)
{
    sort(input.begin(), input.end());
    int n = input.size();

    for (int i = 1; i < n; i += 2)
        swap(input[i - 1], input[i]);
}

void valleyAndPeaks(vector<int> &input)
{
    sort(input.rbegin(), input.rend());
    int n = input.size();

    for (int i = 1; i < n; i += 2)
        swap(input[i - 1], input[i]);
}

//from ctci github question C
void peaksAndValley2(vector<int> &array)
{
    for (int i = 1; i < array.size(); i += 2)
    {
        if (array[i - 1] < array[i])
        {
            swap(array[i - 1], array[i]);
        }
        if (i + 1 < array.size() && array[i + 1] < array[i])
        {
            swap(array[i + 1], array[i]);
        }
    }
}

int main()
{
    vector<int> input = {5, 3, 1, 2, 3};
    peaksAndValley(input);
    for (auto i : input)
        cout << i << " ";
    cout << "\n";
    input = {5, 3, 1, 2, 3};
    valleyAndPeaks(input);
    for (auto i : input)
        cout << i << " ";
    cout << "\n";
    input = {5, 3, 1, 2, 3};
    peaksAndValley2(input);
    for (auto i : input)
        cout << i << " ";
    cout << "\n";
    return 0;
}