#include <bits/stdc++.h>
using namespace std;

struct Point
{
    double x, y;
    Point(double a, double b)
    {
        x = a;
        b = y;
    }
};

void display(Point par)
{
    cout << "(" << par.x << ", " << par.y << ")" << endl;
}
Point intersection(Point A, Point B, Point C, Point D)
{
    // Line AB represented as a1x + b1y = c1
    double a1 = std::fabs(B.y - A.y);
    double b1 = std::fabs(A.x - B.x);
    double c1 = a1 * (A.x) + b1 * (A.y);
    // Line CD represented as a2x + b2y = c2
    double a2 = std::fabs(D.y - C.y);
    double b2 = std::fabs(C.x - D.x);
    double c2 = a2 * (C.x) + b2 * (C.y);
    //Calculate determinant
    // |a1 b1|
    // |a2 b2|
    double det = std::fabs(a1 * b2 - a2 * b1);
    if (det == 0)
    {
        double max = std::numeric_limits<double>::max();
        return Point(max, max);
    }
    else
    {
        double x = (b1 * c2 - b2 * c1) / det;
        double y = (a2 * c1 - a1 * c2) / det; //https://byjus.com/point-of-intersection-formula/
        return Point(x, y);
    }
}

int test1()
{
    Point q = Point(2, 1);
    Point r = Point(2, 7);
    Point s = Point(4, 4);
    Point t = Point(6, 4);
    Point inter = intersection(q, r, s, t);
    if (inter.x == std::numeric_limits<double>::max() && inter.y == std::numeric_limits<double>::max())
    {
        cout << "The given lines AB and CD are parallel.\n";
    }
    else
    {
        cout << "The intersection of the given lines AB and CD is: ";
        display(inter);
    }
    return 0;
}

int test2()
{
    Point s1(2147000000, 1);
    Point e1(-2147000000, -1);
    Point s2(-10, 0);
    Point e2(0, 0);
    Point inter = intersection(s1, e1, s2, e2);
    if (inter.x == std::numeric_limits<double>::max() && inter.y == std::numeric_limits<double>::max())
    {
        cout << "The given lines AB and CD are parallel.\n";
    }
    else
    {
        cout << "The intersection of the given lines AB and CD is: ";
        display(inter);
    }
    return 0;
}

int main()
{
    assert(test1() == 0);
    assert(test2() == 0);
    return 0;
}

/*class LineSegment
{
    Point start, end;

public:
    LineSegment(long long start1, long long end1, long long start2, long long end2)
    {
        start.x = start1, start.y = end1;
        end.x = start2, end.y = end2;
    }

    double slope()
    {
        //y = mx + c
        // (y - y1)/(x - x1) = (y - y2)/(x - x2)
        long long &y1 = start.y, &y2 = end.y, &x1 = start.x, &x2 = end.x;
        ;
    }

    bool intersect(LineSegment two)
    {
        long long &y1 = start.y, &y2 = end.y, &x1 = start.x, &x2 = end.x;

        return slope() == two.slope();
    }
};*/