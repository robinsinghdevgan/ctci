#include <bits/stdc++.h>
using namespace std;

void printVector(const vector<string> &v)
{
    // Optional
    for (auto i : v)
    {
        cout << i << " ";
    }
    cout << "\n\n";
}

void groupAnagrams(vector<string> v)
{
    cout << "Before anagram sorting\n";
    printVector(v);
    sort(v.begin(), v.end(), [](string a, string b){
        if(a.size() > b.size())
            return false;
        else if(a.size() < b.size())
            return true;
        else
        {
            sort(a.begin(), a.end());
            sort(b.begin(), b.end());
            if(a == b)
                return true;
            return false;
        }
        
    });
    cout << "After anagram sorting\n";
    printVector(v);
}

int main()
{
    vector<string> v = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
    groupAnagrams(v);
}