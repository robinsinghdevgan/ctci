#include <bits/stdc++.h>
using namespace std;

//pattern matching

bool isEqual(string s1, int offset1, int offset2, int size)
{
    //with a window of passed size, compare left and right end of window, then slide the windows by 1
    for (int i = 0; i < size; ++i)
        if (s1[offset1 + i] != s1[offset2 + i])
            return false;
    return true;
}

bool matches(string pattern, string value, int mainSize, int altSize, int firstAlt)
{
    int stringIndex = mainSize;

    for (int i = 1; i < pattern.size(); ++i)
    {
        int size = pattern[i] == pattern[0] ? mainSize : altSize;
        int offset = pattern[i] == pattern[0] ? 0 : firstAlt;
        if (!isEqual(value, offset, stringIndex, size))
            return false;
        stringIndex += size;
    }
    return true;
}

bool doesMatch(string pattern, string value)
{
    if (pattern.size() == 0)
        return value.size() == 0;

    char mainChar = pattern[0];
    char altChar = mainChar == 'a' ? 'b' : 'a'; //pattern contains 'a' and 'b' only
    int valueSize = value.size();

    int countOfMainChar = std::count(pattern.begin(), pattern.end(), mainChar); //count of main char
    int countOfAltChar = pattern.size() - countOfMainChar; //count of alt char
    int firstAlt = pattern.find(altChar); //get first occurence position of altChar
    int maxMainSize = valueSize / countOfMainChar; //size of string of pattern 'main'

    for (int mainSize = 0; mainSize <= maxMainSize; ++mainSize) // loop till size of pattern 'main'
    {
        int remainingLength = valueSize - mainSize * countOfMainChar; // you are done with how many main patterns
        if (countOfAltChar == 0 || remainingLength % countOfAltChar == 0)
        { // if there's no alt chars OR remainingLength can be exactly divided by number of alt chars
            int altIndex = firstAlt * mainSize; //get alt Index, (0 to mainSize)
            int altSize = countOfAltChar == 0 ? 0 : remainingLength / countOfAltChar;
            if (matches(pattern, value, mainSize, altSize, altIndex))
                return true;
        }
    }
    return false;
}

int main()
{
    string value = "catgocatgocatgocat";
    string pattern = "abababa"; //a is cat, b is go
    cout << doesMatch(pattern, value) << endl;

    value = "catgocatgocatgoCAT";
    pattern = "abababa"; //a is cat, b is go
    cout << doesMatch(pattern, value) << endl;

    value = "appleBALLcatdoll";
    pattern = "abcd"; //abcd is not supported though...
    cout << doesMatch(pattern, value) << endl;

    value = "appleBALLcatdollNOT";
    pattern = "abcda"; //abcd is not supported though...
    cout << doesMatch(pattern, value) << endl;

    value = "catcatcatcat";
    pattern = "aaaa"; //a is cat, b is go
    cout << doesMatch(pattern, value) << endl;

    return 0;
}