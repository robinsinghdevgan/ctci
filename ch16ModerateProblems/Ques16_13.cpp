#include <bits/stdc++.h>
using namespace std;

class Point
{
public:
    double x, y;
    Point() {}
    Point(double a, double b)
    {
        x = a;
        y = b;
    }
    friend ostream &operator<<(ostream &os, const Point &p);
};

ostream &operator<<(ostream &os, const Point &p)
{
    os << "{" << p.x << "," << p.y << "}";
    return os;
}

class Line
{
public:
    Point a, b;
    double perpendicular;
    double base;
    double hypotenuse; //length
    double &len = hypotenuse;
    double slope;
    double angle_from_x_axis;
    Line(Point start, Point end)
    {
        a = start;
        b = end;

        perpendicular = fabs(b.y - a.y);
        base = fabs(b.x-a.x);
        hypotenuse = sqrt(perpendicular*perpendicular + base*base);
        slope = fabs(b.y - a.y)/fabs(b.x-a.x);
        angle_from_x_axis = atan(perpendicular/base); //radians
    }

    friend ostream &operator<<(ostream &os, const Line &l);
};

ostream &operator<<(ostream &os, const Line &l)
{
    os << l.a << " -> " << l.b << " || Slope: " << l.slope << " || Len : " << l.len << " || Angle: " << l.angle_from_x_axis*180/3.1415 << " degrees";
    return os;
}

//Squares that are parallel to x-axis
class Square
{
public:
    double side;
    Point top_left, top_right, bottom_left, bottom_right;
    Point centre;
    Square(double side, Point bottom_left)
    {
        this->side = side;
        this->bottom_left = bottom_left;
        bottom_right = Point(bottom_left.x + side, bottom_left.y);
        top_right = Point(bottom_left.x + side, bottom_left.y + side);
        top_left = Point(bottom_left.x, bottom_left.y + side);

        centre = calculateCentre();
    }

    Point calculateCentre()
    {
        double centre_x = (bottom_right.x - bottom_left.x) / 2;
        double centre_y = (top_left.y - bottom_left.y) / 2;
        double offset_x = bottom_left.x;
        double offset_y = bottom_left.y;
        return Point(centre_x + offset_x, centre_y + offset_y);
    }
};

//Any Line passing the centers of the two squares will cut them in halves
Line findLineThatCutTwoSquaresInHalves(Square A, Square B)
{
    return Line(A.centre, B.centre);
}

double test1()
{
    Square A(5, Point(0, 0));
    Square B(5, Point(6, 0));
    Line result = findLineThatCutTwoSquaresInHalves(A, B);
    cout << result << endl;
    return 0;
}

double test2()
{
    Square A(5, Point(0, 0));
    Square B(6, Point(10, 10));
    Line result = findLineThatCutTwoSquaresInHalves(A, B);
    cout << result << endl;
    return 0;
}

double test3()
{
    Square A(5, Point(-10, -10));
    Square B(8, Point(15, 10));
    Line result = findLineThatCutTwoSquaresInHalves(A, B);
    cout << result << endl;
    return 0;
}

int main(int argc, char const *argv[])
{
    assert(test1() == 0);
    assert(test2() == 0);
    assert(test3() == 0);
    return 0;
}
