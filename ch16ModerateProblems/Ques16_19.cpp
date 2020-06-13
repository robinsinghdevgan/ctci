#include <bits/stdc++.h>
using namespace std;

int pondSize(vector<vector<int>> &map, int row, int col)
{
    if (row >= map.size() or col >= map[0].size() or row < 0 or col < 0 or map[row][col] != 0)
        return 0;
    map[row][col] = -1; //mark it as visited
    int size = 1;
    for (int delta_row = -1; delta_row <= 1; ++delta_row)
    {
        for (int delta_col = -1; delta_col <= 1; ++delta_col)
        {
            size += pondSize(map, row + delta_row, col + delta_col);
        }
    }
    return size;
}

vector<int> allPondSizes(vector<vector<int>> &map)
{
    vector<int> result;
    for (int row = 0; row < map.size(); ++row)
    {
        for (int col = 0; col < map[row].size(); ++col)
        {
            if (map[row][col] == 0) //if this is pond, calculate its size
            {
                int size = pondSize(map, row, col);
                result.emplace_back(size);
            }
        }
    }
    return result;
}

int main(int argc, char const *argv[])
{
    vector<vector<int>> map = {
        {0, 2, 1, 0},
        {0, 1, 0, 1},
        {1, 1, 0, 1},
        {0, 1, 0, 1}};
    vector<int> ans = allPondSizes(map);
    for (int i : ans)
        cout << i << " ";
    return 0;
}
