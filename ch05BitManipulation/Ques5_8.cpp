#include <bits/stdc++.h>
using namespace std;

void drawLine(uint8_t screen[], unsigned width, unsigned x1, unsigned x2, unsigned y)
{
    unsigned start_offset = x1 % 8;    //from which bit line would start?
    unsigned first_full_byte = x1 / 8; //
    if (start_offset != 0)
        first_full_byte++;

    unsigned end_offset = x2 % 8; //at which bit line would end?
    unsigned last_full_byte = x2 / 8;
    if (end_offset != 7)
        last_full_byte--;

    // set full byte
    for (unsigned i = first_full_byte; i <= last_full_byte; i++)
        screen[y * (width / 8) + i] = (uint8_t)0xFF;

    // create masks for start and end of line;
    uint8_t startMask = (uint8_t)(0xFF >> start_offset);
    uint8_t endMask = (uint8_t) ~(0xFF >> (end_offset + 1));
    // set start and end of line
    if ((x1 / 8) == (x2 / 8))
    {
        // x1 and x2 are in the same uint8_t
        uint8_t mask = (uint8_t)(startMask & endMask);
        screen[y * (width / 8) + x1 / 8] |= mask;
    }
    else
    {
        if (start_offset != 0)
        {
            screen[y * (width / 8) + first_full_byte - 1] |= startMask;
        }
        if (end_offset != 7)
        {
            screen[y * (width / 8) + last_full_byte + 1] |= endMask;
        }
    }
}

int computeByteNum(int width, int x, int y)
{
    return (width * y + x) / 8;
}

void printScreen(uint8_t screen[], unsigned n, int width)
{
    int height = n * 8 / width;
    for (int r = 0; r < height; r++)
    {
        for (int c = 0; c < width; c += 8)
        {
            int i = computeByteNum(width, c, r);
            cout << bitset<8>(screen[i]);
            //printByte(b);
        }
        cout << endl;
    }
}

int main()
{
    int n = 16;
    uint8_t screen[n] = {0};
    unsigned width = 32u;
    printScreen(screen, n, width);
    //y should be within the height n*8/width
    drawLine(screen, width, 3, 15, 2);
    cout << "After draw line\n";
    printScreen(screen, n, width);
    return 0;
}