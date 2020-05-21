#include <bits/stdc++.h>
using namespace std;

int binarySearch(vector<string> strings, string value, int low, int high)
{
    int mid;

    while (low <= high)
    {
        mid = low + (high - low) / 2;
        if (strings[mid].empty())
        {
            int left = mid - 1;
            int right = mid + 1;
            while (true)
            {
                if (left < low && right > high)
                {
                    return -1;
                }
                else if (right <= high && !strings[right].empty())
                {
                    mid = right;
                    break;
                }
                else if (left >= low && !strings[left].empty())
                {
                    mid = left;
                    break;
                }
                right++;
                left--;
            }
        }
        string middle = strings[mid];

        if (middle > value)
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
int search(vector<string> strings, string str)
{
    if (strings.empty() || str.empty())
    {
        return -1;
    }
    return binarySearch(strings, str, 0, strings.size() - 1);
}
int main()
{
    vector<string> stringList = {"apple", "", "", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower"};
    cout << search(stringList, "ac") << endl;

    for (string s : stringList)
        cout << "<" + s + "> " + " appears at location " << search(stringList, s) << endl;

    return 0;
}