#include <bits/stdc++.h>
using namespace std;

class Listy
{
    vector<int> array;

public:
    Listy(vector<int> arr)
    {
        array = arr;
    }

    int elementAt(int index)
    {
        if (index >= array.size())
        {
            return -1;
        }
        return array[index];
    }
};

int binarySearch(Listy list, int value, int low, int high)
{
    int mid;

    while (low <= high)
    {
        mid = low + (high-low) / 2;
        int middle = list.elementAt(mid);
        if (middle > value || middle == -1)
        {
            high = mid - 1;
        }
        else if (middle < value)
        {
            low = mid + 1;
        }
        else
        {
            return mid;
        }
    }
    return -1;
}

int search(Listy list, int value)
{
    int index = 1;
    while (list.elementAt(index) != -1 && list.elementAt(index) < value)
    {
        index *= 2; //find upper limit just greater than value
    }
    return binarySearch(list, value, index / 2, index);
}

int main()
{
    vector<int> array = {1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18};
    Listy list(array);
    for (int a : array)
    {
        cout << search(list, a) << endl;
    }
    cout << search(list, 15) << endl;
    return 0;
}