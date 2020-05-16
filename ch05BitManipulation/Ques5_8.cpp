#include <bits/stdc++.h>
using namespace std;


void drawLine(uint8_t screen[], int width, int x1, int x2, int y)
{
    int start_offset = x1 % 8; //from which bit line would start?
    int first_full_byte = x1 / 8; //
    if (start_offset != 0)
        first_full_byte++;
    
    int end_offset = x2 % 8; //at which bit line would end?
    int last_full_byte = x2 / 8;
    if (end_offset != 7)
        last_full_byte--;
    
    // set full byte
    for (int i = first_full_byte; i <= last_full_byte; i++)
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


int main()
{
    uint8_t screen[8] = {0};
    for(uint8_t i:screen) cout << bitset<8>(i) << endl;
    drawLine(screen, 8, 3, 9, 4);
    cout << "After draw line\n";
    for(uint8_t i:screen) cout << bitset<8>(i) << endl;
    
    return 0;
}