#include <bits/stdc++.h>
using namespace std;

void sortedMerge(vector<int> &A, const vector<int> &B)
{
    size_t index = A.size() - 1;

    //get last positive integer location in A
    vector<int>::iterator last_pos_integer = std::find_if_not(A.begin(), A.end(), [](int x) { return x >= 0; });
    --last_pos_integer;
    size_t i = std::distance(A.begin(), last_pos_integer);
    //cout << A[i] << endl;
    size_t j = B.size() - 1;

    while (index != -1 and i != -1 and j != -1) // "!= -1" for unsigned int reaching zero
    {
        if (A[i] > B[j])
        {
            A[index] = A[i];
            --i;
        }
        else
        {
            A[index] = B[j];
            --j;
        }
        --index;
    }
    if (index != -1)
    {
        while (i != -1 and index != -1)
        {
            A[index] = A[i];
            --i;
            --index;
        }
        while (j != -1 and index != -1)
        {
            A[index] = B[j];
            --j;
            --index;
        }
    }
}

vector<int> generateRandomSortedVector(size_t vector_size, int minVal, int maxVal)
{
    // First create an instance of an engine.
    random_device rnd_device;
    // Specify the engine and distribution.
    mt19937 mersenne_engine {rnd_device()};  // Generates random integers
    uniform_int_distribution<int> dist {minVal, maxVal};

    auto gen = [&dist, &mersenne_engine](){
                   return dist(mersenne_engine);
               };

    vector<int> vec(vector_size);
    generate(begin(vec), end(vec), gen);
    sort(vec.begin(), vec.end());
    return vec;
}

void printVector(const vector<int> &v)
{
     // Optional
    for (auto i : v) {
        cout << i << " ";
    }
    cout << "\n";
}

int test1()
{
    cout << "TEST 1" << endl;
    vector<int> A = {2, 4, 6, 8, 10, 12, 14, 16};
    vector<int> B = {1, 3, 5, 7, 9, 11, 13, 15};
    A.resize(A.size() + B.size(), -1);
    cout << "Appending B.size() -1s to A\n\n";
    printVector(A);
    cout << endl;
    sortedMerge(A, B);
    printVector(A);
    return 0;
}

int test2()
{
    cout << "TEST 1" << endl;
    vector<int> A = generateRandomSortedVector(20, 1, 9999);
    vector<int> B = generateRandomSortedVector(15, 1, 99);
    A.resize(A.size() + B.size(), -1);
    cout << "Appending B.size() -1s to A\n\n";
    printVector(A);
    cout << endl;
    sortedMerge(A, B);
    printVector(A);
    return 0;
}

int main()
{
    assert(test1()==0);
    assert(test2()==0);
    return 0;
}