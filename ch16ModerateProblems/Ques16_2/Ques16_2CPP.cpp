#include <bits/stdc++.h>
#include <functional>
using namespace std;

void countWordFrequency(string file_name, set<std::pair<size_t, std::string>> &s)
{
    map<string, size_t> mapOfWordCount;
    ifstream ifile;
    ifile.open(file_name);
    if (ifile)
    {
        cout << "file exists\n";
    }
    else
    {
        cout << "file doesn't exist\n";
    }
    while (!ifile.eof())
    {
        string word;
        ifile >> word;
        //wcout << word << " ";
        ++mapOfWordCount[word];
    }
    ifile.close();

    for (auto const &kv : mapOfWordCount)
        s.emplace(kv.second, kv.first); // Flip the pairs.
}

void createWordFrequencyFile(string book_name)
{
    set<std::pair<size_t, std::string>> mapOfWordCount;
    countWordFrequency(book_name, mapOfWordCount);
    ofstream ofile;
    ofile.open("cpp_WordFrequencyCountof_" + book_name);
    ofile << "Word    :   Frequency\n";
    for (auto const &p : mapOfWordCount)
    {
        string line = (p.second + " : " + to_string(p.first) + "\n");
        ofile << line;
    }
    ofile.close();
}

int main()
{
    string book1 = "JapjiSahib.txt";
    string book2 = "SGGS ji Maharaj.txt";
    createWordFrequencyFile(book1);
    createWordFrequencyFile(book2);
    return 0;
}