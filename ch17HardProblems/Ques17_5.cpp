#include <bits/stdc++.h>
using namespace std;

static std::vector<int> computeDeltaArray(std::vector<char> &array)
{
    std::vector<int> deltas(array.size());
    int delta = 0;
    for (int i = 0; i < array.size(); i++)
    {
        if (std::isalpha(array[i]))
        {
            delta++;
        }
        else if (std::isdigit(array[i]))
        {
            delta--;
        }
        deltas[i] = delta;
    }
    return deltas;
}

/* Find the matching pair of values in the deltas array with the 
	 * largest difference in indices. */
static std::vector<int> findLongestMatch(std::vector<int> &deltas)
{
    std::unordered_map<int, int> map;
    map.emplace(0, -1);
    std::vector<int> max(2);
    for (int i = 0; i < deltas.size(); i++)
    {
        if (map.find(deltas[i]) == map.end())
        {
            map.emplace(deltas[i], i);
        }
        else
        {
            int match = map[deltas[i]];
            int distance = i - match;
            int longest = max[1] - max[0];
            if (distance > longest)
            {
                max[1] = i;
                max[0] = match;
            }
        }
    }
    return max;
}

static std::vector<char> extract(std::vector<char> &array, int start, int end)
{
    if (start > end)
    {
        return std::vector<char>();
    }
    std::vector<char> subarray(end - start + 1);
    for (int i = start; i <= end; i++)
    {
        subarray[i - start] = array[i];
    }
    return subarray;
}

static std::vector<char> findLongestSubarray(std::vector<char> &array)
{
    /* Compute deltas betw count of numbers and count of letters. */
    std::vector<int> deltas = computeDeltaArray(array);

    /* Find pair in deltas with matching values and largest span. */
    std::vector<int> match = findLongestMatch(deltas);

    /* Return the subarray. Note that it starts one *after* the 
		 * initial occurence of this delta. */
    return extract(array, match[0] + 1, match[1]);
}

static bool isEqual(std::vector<char> &array, int start, int end)
{
    int counter = 0;
    for (int i = start; i < end; i++)
    {
        if (std::isalpha(array[i]))
        {
            counter++;
        }
        else if (std::isdigit(array[i]))
        {
            counter--;
        }
    }
    return counter == 0;
}

int main()
{
    char b = '1';
    char a = 'a';
    std::vector<char> array = {a, b, a, b, a, b, b, b, b, b, a, a, a, a, a, b, a, b, a, b, b, a, a, a, a, a, a, a};
    for (int i = 0; i < array.size(); i++)
    {
        std::cout << array[i] << " ";
    }
    std::cout << std::endl;
    std::vector<char> max = findLongestSubarray(array);
    if (max.empty())
    {
        std::cout << "No equal subarray" << std::endl;
    }
    else
    {
        std::cout << max.size() << std::endl;
        for (int i = 0; i < max.size(); i++)
        {
            std::cout << max[i] << " ";
        }

        std::cout << "\nIs Valid? " << isEqual(max, 0, max.size()) << std::endl;
    }
}
