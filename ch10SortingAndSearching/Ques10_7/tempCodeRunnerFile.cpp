#include <bits/stdc++.h>
using namespace std;
#define FOUR_MILLION 4000000u

//using bitset
size_t missing_int(string file_name)
{
    bitset<FOUR_MILLION> bs(0);
    ifstream file;
    file.open(file_name, ios::in);
    size_t starting_integer;
    if (file.is_open())
    {
        size_t integer;
        file >> integer;
        starting_integer = integer;
        while (!file.eof())
        {
            bs.set(integer % FOUR_MILLION);
            file >> integer;
        }
        file.close();
    }
    for (size_t bit_index = 0; bit_index < FOUR_MILLION; ++bit_index)
    {
        if (bs[bit_index] == 0)
        {
            if (starting_integer < 2)
                return bit_index;
            return starting_integer + bit_index;
        }
    }
    return 0; //no missing integer
}

//when memory crunch is there, handling 1024 numbers at a time
size_t missing_int_less_memory(string file_name)
{
    const unsigned bs_size = 1024;
    std::bitset<bs_size> memory = std::bitset<bs_size>().set();
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
            memory.reset(integer % bs_size);
            //cout << integer % bs_size << " ";
            ++bs_index;
            file >> integer;
            if (bs_index >= bs_size)
            {
                //cout << integer << " " << bs_index << " " << iterations << endl;
                unsigned one_found_at_index = memory._Find_first();
                if (one_found_at_index != memory.size())
                    return (first_integer_of_batch + one_found_at_index) - 1;
                memory.set();
                bs_index = 0;
                ++iterations;
                first_integer_of_batch = integer;
            }
        }
        file.close();
    }
    return 0; //no missing integer
}

void generate_file(string file_name, size_t start_num, size_t end_num, size_t num_to_skip)
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
            if (i != num_to_skip)
                file << i << "\n";
        }
        file.close();
    }
}

int main()
{
    //generate_file("large_file1.txt", 0u, FOUR_MILLION, 9999);
    //generate_file("large_file1.txt", 1u, FOUR_MILLION + 1u, 9999);
    //generate_file("large_file2.txt", 2u, FOUR_MILLION + 2u, 1024);
    //generate_file("large_file3.txt", 9999, FOUR_MILLION + 9999, 655536);

    //cout << missing_int("large_file.txt") << endl;
    cout << missing_int_less_memory("large_file.txt") << endl;

    cout << missing_int("large_file1.txt") << endl;
    cout << missing_int_less_memory("large_file1.txt") << endl;

    cout << missing_int("large_file2.txt") << endl;
    cout << missing_int_less_memory("large_file2.txt") << endl;

    cout << missing_int("large_file3.txt") << endl;
    cout << missing_int_less_memory("large_file3.txt") << endl;

    return 0;
}