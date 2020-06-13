#include <bits/stdc++.h>
using namespace std;

using Grid = vector<vector<bool>>;

enum Orientation
{
    north,
    east,
    south,
    west
};

class Position
{

public:
    int x, y;
    Position() {}
    Position(int nx, int ny)
    {
        x = nx;
        y = ny;
    }
};

class Ant
{

    Orientation o;
    Position pos;

    void turn90ClockWise()
    {
        if (o == north)
            o = east;
        else if (o == east)
            o = south;
        else if (o == south)
            o = west;
        else
            o = north;
    }
    void turn90CounterClockWise()
    {
        if (o == north)
            o = west;
        else if (o == west)
            o = south;
        else if (o == south)
            o = east;
        else
            o = north;
    }
    void moveOneAhead(Grid &g)
    {
        cout << "At Position: " << pos.x << "," << pos.y << " ";
        cout << " and Facing: " << o << endl;
        int x = pos.x;
        int y = pos.y;
        if (o == north)
            ++y;
        else if (o == east)
            ++x;
        else if (o == south)
            --y;
        else
            --x;
        if (x >= g.size() or y >= g[0].size() or x < 0 or y < 0)
        {
            cout << "Ant is at the border, so its changing direction now and moving...\n";
            turn90ClockWise();
            moveOneAhead(g);
        }
        else
        {
            pos.x = x;
            pos.y = y;
        }
    }
    void atWhiteSquare(Grid &g)
    {
        turn90ClockWise();
        moveOneAhead(g);
    }

    void atBlackSquare(Grid &g)
    {
        turn90CounterClockWise();
        moveOneAhead(g);
    }

public:
    Ant() {}
    Ant(int x, int y, Orientation orientation)
    {
        pos = Position(x, y);
        o = orientation;
    }

    void move(Grid &g)
    {
        int &x = pos.x;
        int &y = pos.y;
        if (g[x][y] == 0)
        {
            atBlackSquare(g);
            g[x][y] = 1;
        }
        else
        {
            atWhiteSquare(g);
            g[x][y] = 0;
        }
    }
    Position getPosition()
    {
        return pos;
    }
};
class Board
{
    friend class Ant;
    Grid grid;

public:
    Board(Grid g)
    {
        grid = g;
    }
    void printGrid()
    {
        for (const auto &v : grid)
        {
            for (const auto &e : v)
                cout << e << " ";
            cout << endl;
        }
    }
    void printKMoves(int k)
    {
        Ant a(5, 5, south);
        for (int i = 1; i <= k; ++i)
        {
            cout << "Move: " << i << ") ";
            a.move(grid);
        }
        printGrid();
    }
};

void generateGrid(Grid &g)
{
    srand(time(0));
    int n = 10;
    for (int i = 0; i < n; ++i)
    {
        vector<bool> v;
        for (int j = 0; j < n; ++j)
            v.emplace_back(rand() % 2);
        g.emplace_back(v);
    }
}

int main(int argc, char const *argv[])
{
    Grid grid;
    generateGrid(grid);
    Board board(grid);
    board.printGrid();
    cout << "....................\n";
    board.printKMoves(30);
    return 0;
}
