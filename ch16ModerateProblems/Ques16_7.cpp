#include <bits/stdc++.h>
using namespace std;

//max_of_two_without_if_else_or_comparision_operator

int minOfTwo(int a, int b)
{
    int c = a - b;
    bool is_a_negative = (a >> 31) & 1;
    bool is_b_negative = (b >> 31) & 1;
    bool is_c_negative = (c >> 31) & 1;

    // check if sign of a is different from b's
    bool is_sign_of_a_Different_from_sign_of_b = is_a_negative xor is_b_negative;

    //if yes, use sign of a
    bool use_sign_of_a = is_sign_of_a_Different_from_sign_of_b; 

    //if not, use sign of c
    bool use_sign_of_c = not (is_sign_of_a_Different_from_sign_of_b); //flip the sign

    bool k = (use_sign_of_a * is_a_negative) + (use_sign_of_c * is_c_negative);
    bool not_k = not k;

    return a * k + b * not_k; // either a or b will be returned
}

int maxOfTwo(int a, int b)
{
    int c = a - b;
    bool is_a_positive = not ((a >> 31) & 1);
    bool is_b_positive = not ((b >> 31) & 1);
    bool is_c_positive = not ((c >> 31) & 1);

    // check if sign of a is different from b's
    bool is_sign_of_a_Different_from_sign_of_b = is_a_positive xor is_b_positive;

    //if yes, use sign of a
    bool use_sign_of_a = is_sign_of_a_Different_from_sign_of_b;
    
    //if not, use sign of c
    bool use_sign_of_c = not (is_sign_of_a_Different_from_sign_of_b); //flip the sign

    bool k = (use_sign_of_a * is_a_positive) + (use_sign_of_c * is_c_positive);
    bool not_k = not k;

    return a * k + b * not_k;
}

int main(int argc, char const *argv[])
{
    cout << maxOfTwo(5,6) << endl;
    cout << maxOfTwo(6,-6) << endl;

    cout << minOfTwo(5,6) << endl;
    cout << minOfTwo(6,-6) << endl;
    return 0;
}
