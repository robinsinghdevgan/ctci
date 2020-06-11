#include <bits/stdc++.h>
using namespace std;

static int findRightSequenceStart(std::vector<int> &array)
{
    int max = std::numeric_limits<int>::min();
    int lastNo = 0;
    for (int i = 0; i < array.size(); i++)
    {
        if (max > array[i])
        {
            lastNo = i;
        }
        max = std::max(array[i], max);
    }
    return lastNo;
}

static int findLeftSequenceEnd(std::vector<int> &array)
{
    int min = std::numeric_limits<int>::max(); //min is int_max
    int lastNo = 0;
    for (int i = array.size() - 1; i >= 0; i--) // go right to left
    {
        if (min < array[i]) //compare with min
        {
            lastNo = i;
        }
        min = std::min(array[i], min); //update min
    }
    return lastNo;
}

void findUnsortedSequence(std::vector<int> &array)
{
    int leftSequenceEnd = findRightSequenceStart(array);
    int rightSequenceEnd = findLeftSequenceEnd(array);
    cout << leftSequenceEnd << " " << rightSequenceEnd << endl;
}
int main()
{
    std::vector<int> arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};

    findUnsortedSequence(arr);

    arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    findUnsortedSequence(arr);

    arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    findUnsortedSequence(arr);

    return 0;
}
//reference: CTCI official Java repository on github