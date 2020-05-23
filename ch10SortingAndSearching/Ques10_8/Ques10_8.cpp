#include <bits/stdc++.h>
using namespace std;
#define FOUR_MILLION 4000000u

void generate_file(string file_name, size_t start_num, size_t end_num, unordered_set<long long> nums_to_duplicate)
{
    fstream file;

    // opening file "Gfg.txt"
    // in out(write) mode
    // ios::out Open for output operations.
    file.open(file_name, ios::out);

    // If no file is created, then
    // show the error message.
    if (!file)
    {
        cout << "Error in creating file!!!\n";
        return;
    }

    cout << "File created successfully.\n";

    if (file.is_open())
    {
        for (size_t i = start_num; i <= end_num; ++i)
        {
            if (nums_to_duplicate.find(i) != nums_to_duplicate.end())
            {
                int count = rand() % 3 + 2;
                while (count--)
                    file << i << "\n";
            }
            else
            {
                file << i << "\n";
            }
        }
        file.close();
    }
}

vector<long long> all_duplicates(string file_name)
{
    set<long long> temp_set;
    const unsigned bs_size = 1024;
    std::bitset<bs_size + 1u> memory = std::bitset<bs_size + 1u>().reset();
    ifstream file;
    file.open(file_name, ios::in);
    if (file.is_open())
    {
        unsigned bs_index = 0;
        size_t iterations = 1;
        size_t integer;
        file >> integer;
        size_t first_integer_of_batch = integer;
        while (!file.eof())
        {
            unsigned index = integer - first_integer_of_batch;
            if (memory[index] == 1)
                temp_set.insert(first_integer_of_batch + index);
            else
                memory.set(index);
            //cout << integer % bs_size << " ";
            ++bs_index;
            file >> integer;
            if (bs_index >= bs_size)
            {
                memory.reset();
                bs_index = 0;
                ++iterations;
                first_integer_of_batch = integer;
            }
        }
        file.close();
    }
    vector<long long> result (temp_set.begin(), temp_set.end());
    return result; //no missing integer
}

int main()
{
    //unordered_set<long long> nums_to_duplicate = {7777,8888,9999,1010101};
    //generate_file("large_file.txt", 6666, FOUR_MILLION + 6666, nums_to_duplicate);

    vector<long long> v = all_duplicates("large_file.txt");
    for (auto i : v)
        cout << i << " ";
    return 0;
}