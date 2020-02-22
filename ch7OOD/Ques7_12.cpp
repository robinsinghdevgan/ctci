#include <list>
#include <vector>
#include <string>
#include <iostream>
using namespace std;

class MyHashTable {
private:
    vector<list<string>> ht;
    unsigned sz = 0;   

public:
    MyHashTable(int size) {
        sz = size;
        ht = vector<list<string>>(size);
    }

    unsigned hash(string s) {
        int charSum = 0;
        for (auto c : s)
            charSum += c;
        cout << "charSum: " << charSum << endl;
        return (s.length() + charSum) % sz;
    }

    unsigned size() {
        return sz;
    }

    bool containsKey(string key) {
        if (ht[hash(key)].size() > 0)
            return true;
    
        return false;
    }

    void print() {
        int i = 0;
        for (auto l : ht) {
            cout << i << ")";
            for(auto s : l) {
                cout<<(s + " -> ");
            }
            cout<<endl;
            ++i;
        }
    }

    void put(string key) {
        ht[hash(key)].push_back(key);
    }
};

int main(void) {
    MyHashTable mht(10);
    string sa[] = {"a","ab","abc", "abcd", "abcde", "abcdef"};
    for (string s : sa) {
        mht.put(s);
    }
    mht.print();
}