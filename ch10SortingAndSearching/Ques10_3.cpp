#include <bits/stdc++.h>
using namespace std;

int search(vector<int> a, int left, int right, int x)
{
    int mid = left + ((right - left) / 2);

    if (x == a[mid])
    { // Found element
        return mid;
    }
    if (right < left)
    {
        return -1;
    }

    /* While there may be an inflection point due to the rotation, either the left or 
		 * right half must be normally ordered.  We can look at the normally ordered half
		 * to make a determination as to which half we should search. 
		 */
    if (a[left] < a[mid])
    { // Left is normally ordered.
        if (x >= a[left] && x < a[mid])
        {
            return search(a, left, mid - 1, x);
        }
        else
        {
            return search(a, mid + 1, right, x);
        }
    }
    else if (a[mid] < a[right])
    { // Right is normally ordered.
        if (x > a[mid] && x <= a[right])
        {
            return search(a, mid + 1, right, x);
        }
        else
        {
            return search(a, left, mid - 1, x);
        }
    }
    else if (a[left] == a[mid])
    { // Left is either all repeats OR loops around (with the right half being all dups)
        if (a[mid] != a[right])
        { // If right half is different, search there
            return search(a, mid + 1, right, x);
        }
        else
        { // Else, we have to search both halves
            int result = search(a, left, mid - 1, x);
            if (result == -1)
            {
                return search(a, mid + 1, right, x);
            }
            else
            {
                return result;
            }
        }
    }
    return -1;
}

int search(vector<int> a, int x)
{
    return search(a, 0, a.size() - 1, x);
}


int bsearch(vector<int> &nums, int l, int h, int target)
{
    if (l > h)
        return -1;
    int mid = l + (h - l) / 2;

    if (nums[mid] == target)
        return mid;

    int lindex = bsearch(nums, l, mid - 1, target);
    int rindex = bsearch(nums, mid + 1, h, target);

    return lindex == -1 ? rindex : lindex;
}

int search2(vector<int> nums, int target)
{
    if (nums.size() < 1)
        return -1;

    int l = 0;
    int h = nums.size() - 1;

    return bsearch(nums, 0, nums.size() - 1, target);
}

/*You can do 2 binary searches: first to find the index i such that arr[i] > arr[i+1].

Apparently, (arr\[1], arr[2], ..., arr[i]) and (arr[i+1], arr[i+2], ..., arr[n]) are both sorted arrays.

Then if arr[1] <= x <= arr[i], you do binary search at the first array, else at the second.

The complexity O(logN)*/

int testrec()
{
    vector<int> a = {2, 3, 1, 2, 2, 2, 2, 2, 2, 2};
    cout << search(a, 2) << endl;
    cout << search(a, 3) << endl;
    cout << search(a, 4) << endl;
    cout << search(a, 1) << endl;
    cout << search(a, 8) << endl;
    return 0;
}

int test2()
{
    cout << "TEST 2\n";
    const vector<int> a = {2, 3, 1, 2, 2, 2, 2, 2, 2, 2};
    cout << search2(a, 2) << endl;
    cout << search2(a, 3) << endl;
    cout << search2(a, 4) << endl;
    cout << search2(a, 1) << endl;
    cout << search2(a, 8) << endl;
    return 0;
}

int main()
{
    assert(testrec() == 0);
    assert(test2() == 0);
    return 0;
}