#include <bits/stdc++.h>
using namespace std;

enum ball
{
    B,
    G,
    R,
    Y,
    E
}; //E: an empty slot

using Slots = ball[4];

void master_mind(Slots guess, Slots solution)
{
    int hits = 0, pseudo_hits = 0;
    unordered_set<ball> seen;
    for (int i = 0; i < 4; ++i)
    {
        if (guess[i] == solution[i])
            ++hits;
        else if (seen.find(guess[i]) != seen.end())
            ++pseudo_hits;
        seen.insert(solution[i]);
    }
    cout << "hits: " << hits << " and pseudo_hits: " << pseudo_hits << endl;
}

int test1()
{
    Slots guess = {G, G, R, R}, soluton = {R, G, B, Y};
    master_mind(guess, soluton);
    return 0;
}

int test2()
{
    Slots guess = {G, G, G, G}, soluton = {R, G, B, Y};
    master_mind(guess, soluton);
    return 0;
}

int test3()
{
    Slots guess = {R, G, B, Y}, soluton = {R, G, B, Y};
    master_mind(guess, soluton);
    return 0;
}

int test4()
{
    Slots guess = {E, E, E, E}, soluton = {R, G, B, Y};
    master_mind(guess, soluton);
    return 0;
}

int main()
{
    assert(test1() == 0);
    assert(test2() == 0);
    assert(test3() == 0);
    assert(test4() == 0);
    return 0;
}