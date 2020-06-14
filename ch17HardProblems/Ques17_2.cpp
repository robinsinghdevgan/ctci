#include <bits/stdc++.h>
using namespace std;

static mt19937 mte(chrono::steady_clock::now().time_since_epoch().count());

int rand_card(int range_start, int range_end)
{
    uniform_int_distribution<int> uit(range_start, range_end);
    return uit(mte);
}
void shuffle_cards(vector<int> &cards)
{
    int n = cards.size();
    for(int i=0; i<n; ++i)
    {
        int random_Card_from_first_i_cards = rand_card(0,i);
        swap(cards[i], cards[random_Card_from_first_i_cards]);
    }
}

int main(int argc, char const *argv[])
{
    auto gen_cards = []() -> vector<int>{
        vector<int> res;
        for (int i = 0; i < 53; ++i)
            res.emplace_back(i);
        return res;
    };
    
    vector<int> cards = gen_cards();

    auto print_cards = [&](){
        for(int i=0;i<53;++i)
            cout << cards[i] << " ";
        cout << endl;
    };

    shuffle_cards(cards);
    print_cards();
    return 0;
}
