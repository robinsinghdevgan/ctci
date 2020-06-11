#include <bits/stdc++.h>
using namespace std;

using Point = std::pair<double, double>;
auto hash_Point = [](const Point &v) {
    return std::hash<double>()(v.first) * 8 + std::hash<double>()(v.second);
};

int BestLine(vector<pair<double, double>> points)
{
    double n = points.size();
    if (n < 2)
        return n;
    int maxPoints = 0;
    int currMax, overlapPts, verticalPts;
    //map<pair<double, double>, double> slope; //map of point(x,y) to frequency of slope

    //to make retrieval faster, using hashmap with custom hash
    unordered_map<Point, int, decltype(hash_Point)> slope(8, hash_Point);

    for (double i = 0; i < n; i++)
    {
        currMax = overlapPts = verticalPts = 0;
        for (double j = i + 1; j < n; j++)
        {
            if (points[i] == points[j]) //if the two points are equal, ++overlapPts
                overlapPts++;
            else if (points[i].first == points[j].first) //if x value is same, ++verticalPts
                verticalPts++;
            else
            {
                double y_diff = points[j].second - points[i].second;
                double x_diff = points[j].first - points[i].first;
                double g = __gcd(static_cast<int>(x_diff), static_cast<int>(y_diff));
                y_diff /= g;
                x_diff /= g;
                slope[make_pair(y_diff, x_diff)]++;
                currMax = max(currMax, slope[make_pair(y_diff, x_diff)]); //update currMax
            }
            currMax = max(currMax, verticalPts);
        }
        maxPoints = max(maxPoints, currMax + overlapPts + 1); //update the result to return
        slope.clear();                                        //empty the map
    }
    return maxPoints;
}

int test1()
{
    const int n = 6;
    double arr[n][2] = {{-1, 1}, {0, 0}, {1, 1}, {2, 2}, {3, 3}, {3, 4}};
    vector<Point> points; // vector of points(x,y)
    for (int i = 0; i < n; i++)
        points.push_back(make_pair(arr[i][0], arr[i][1]));
    cout << BestLine(points) << endl;
    return 0;
}
int test2()
{
    double lower_bound = -100;
    double upper_bound = 100;
    std::uniform_real_distribution<double> unif(lower_bound, upper_bound);
    std::random_device rd;  //Will be used to obtain a seed for the random number engine
    std::mt19937 gen(rd() xor std::time(0));

    vector<Point> points; // vector of points(x,y)
    for (int i = 0; i < 500; i++)
        points.emplace_back(make_pair(unif(gen), unif(gen)));
    cout << BestLine(points) << endl;
    return 0;
}

int test3()
{

    vector<Point> points; // vector of points(x,y)
    for (int i = 0; i < 500; i++)
        points.emplace_back(make_pair(i,i));
    for (int i = 0; i < 501; i++)
        points.emplace_back(make_pair(i,i+.05));
    for (int i = 0; i < 499; i++)
        points.emplace_back(make_pair(0,i));
    for (int i = 0; i < 498; i++)
        points.emplace_back(make_pair(i,0));
    cout << BestLine(points) << endl;
    return 0;
}

int main()
{
    assert(test1() == 0);
    assert(test2() == 0);
    assert(test3() == 0);
    return 0;
}
//Inspired from: https://raw.githubusercontent.com/prabodhw96/CtCI-6th-Edition-cpp/master/16-Moderate/16.14.cpp