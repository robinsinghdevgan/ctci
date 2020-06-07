#include <bits/stdc++.h>
using namespace std;
//check if someone has won the game of tic-tac-toe
//across horizon, verizon, diagonal

enum boardState : int8_t
{
    B = 0,
    X = 1,
    O = 2
};
typedef vector<vector<boardState>> TicTacToeBoard;

bool checkRows(const TicTacToeBoard &board)
{
    for (const vector<boardState> &row : board)
    {
        unsigned n = row.size();
        boardState f = row[0];
        if (f == B)
            continue; //a blank state found in this row
        unsigned i = 1;
        for (; i < n; ++i)
        {
            if (row[i] != f)
                break;
        }
        if(i == n)
            return true;
    }
    return false;
}

bool checkColumns(const TicTacToeBoard &board)
{
    unsigned n = board[0].size();
    for (unsigned i = 0; i < n; ++i)
    {
        boardState state = board[0][i];
        if (state == B)
            continue; //a blank state, skip this column
        unsigned j=1;
        for (; j < n; ++j)
        {
            if (board[j][i] != state)
                break;
        }
        if (j == n)
            return true; //same board state in a columnn
    }
    return false;
}

bool checkTopLeftToBottomRight(const TicTacToeBoard &board) 
{
    unsigned n = board[0].size();
    boardState state = board[0][0];
    if (state == B)
        return false; //a blank state, this diagonal cannot define the winner
    for (unsigned i = 1; i < n; ++i)
    {
        if(board[i][i] != state)
            return false;
    }
    return true;
}
bool checkBottomLeftToTopRight(const TicTacToeBoard &board) 
{   unsigned n = board[0].size();
    boardState state = board[n-1][0];
    if (state == B)
        return false; //a blank state, this diagonal cannot define the winner
    for (unsigned i = n-2; i != -1; --i) //i != -1 because i is unsigned
    {
        if(board[i][n - 1 - i] != state)
            return false;
    }
    return true;
}
bool checkDiagonals(const TicTacToeBoard &board)
{
    if (checkTopLeftToBottomRight(board))
        return true;
    cout << "checkTopLeftToBottomRight returned false\n";
    return checkBottomLeftToTopRight(board);
}

bool hasWon(const TicTacToeBoard &board)
{
    if (board.size() != board[0].size())
    {
        cout << "Not an NxN board, exiting.\n";
        assert(false);
    }
    if (checkRows(board))
        return true;
    cout << "checkRows returned false\n";
    if (checkColumns(board))
        return true;
    cout << "checkColumns returned false\n";
    return checkDiagonals(board);
}

int test_positive()
{
    TicTacToeBoard board = {
        {X, X, X},
        {B, O, O},
        {X, O, O}};
    cout << hasWon(board) << endl;

    TicTacToeBoard board2 = {
        {X, B, O},
        {X, B, O },
        {B, B, O}};
    cout << hasWon(board2) << endl;

    TicTacToeBoard board3 = {
        {X, O, O},
        {B, X, O},
        {X, O, X}};
    cout << hasWon(board3) << endl;

    TicTacToeBoard board4 = {
        {X, X, O},
        {B, O, X},
        {O, B, O}};
    cout << hasWon(board4) << endl;
    return 0;
}

int test_negative()
{
    TicTacToeBoard board = {
        {X, B, X},
        {B, O, O},
        {X, O, O}};
    cout << hasWon(board) << endl;

    TicTacToeBoard board2 = {
        {X, B, O},
        {X, O, X},
        {B, B, O}};
    cout << hasWon(board2) << endl;

    TicTacToeBoard board3 = {
        {X, O, O},
        {B, X, O},
        {X, O, B}};
    cout << hasWon(board3) << endl;

    TicTacToeBoard board4 = {
        {X, X, O},
        {B, X, X},
        {O, B, O}};
    cout << hasWon(board4) << endl;
    return 0;
}


int main()
{
    assert(test_positive() == 0);
    assert(test_negative() == 0);
    return 0;
}