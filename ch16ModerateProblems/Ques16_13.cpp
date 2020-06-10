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
    double &delta_x = perpendicular;
    double &delta_y = base;
    double hypotenuse; //length
    double &len = hypotenuse;
    double slope;
    double angle_from_x_axis;
    Line(Point start, Point end)
    {
        a = start;
        b = end;

        perpendicular = fabs(b.y - a.y);
        base = fabs(b.x - a.x);
        hypotenuse = sqrt(perpendicular * perpendicular + base * base);
        slope = (b.x - a.x) != 0 ? (b.y - a.y) / (b.x - a.x) : 0;
        angle_from_x_axis = atan(perpendicular / base); //radians
    }

    friend ostream &operator<<(ostream &os, const Line &l);
};

ostream &operator<<(ostream &os, const Line &l)
{
    os << l.a << " -> " << l.b << " || Slope: " << l.slope << " || Len : " << l.len << " || Angle: " << l.angle_from_x_axis * 180 / 3.1415 << " degrees";
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
/*
Line extend(Line line, double A_side, double B_side) 
{
    double old_x_a = line.a.x;
    double old_y_a = line.a.y;
    double old_x_b = line.b.x;
    double old_y_b = line.b.y;
    double alpha = line.angle_from_x_axis;

    double &length_a = A_side;
    double &length_b = B_side;
    double xa, ya, xb, yb;

    //extend A
    xa = old_x_a + line.delta_x/line.len - length_a;
    ya = old_y_a + line.delta_y/line.len - length_a;

    xb = old_x_b +  line.delta_x/line.len + length_b;
    yb = old_y_b +  line.delta_x/line.len + length_b;;

    Point newA(xa, ya), newB(xb, yb);
    return Line(newA, newB);
}
*/

Point extend(Point mid1, Point mid2, double size)
{
    /* Find what direction the line mid2 -> mid1 goes */
    double xdir = mid1.x < mid2.x ? -1 : 1;
    double ydir = mid1.y < mid2.y ? -1 : 1;

    /* If mid1 and mid2 have the same x value, then the slope
		 * calculation will throw a divide by 0 exception. So, we
		 * compute this specially. */
    if (mid1.x == mid2.x)
    {
        return Point(mid1.x, mid1.y + ydir * size / 2.0);
    }
    double slope = (mid1.y - mid2.y) / (mid1.x - mid2.x);
    double x1 = 0;
    double y1 = 0;

    /* Calculate slope using the equation (y1 - y2) / (x1 - x2).
		 * Note: if the slope is �steep� (>1) then the end of the
		 * line segment will hit size / 2 units away from the middle
		 * on the y axis. If the slope is �shallow� (<1) the end of
		 * the line segment will hit size / 2 units away from the
		 * middle on the x axis. */
    if (fabs(slope) == 1)
    {
        x1 = mid1.x + xdir * size / 2.0;
        y1 = mid1.y + ydir * size / 2.0;
    }
    else if (fabs(slope) < 1)
    {
        x1 = mid1.x + xdir * size / 2.0;
        y1 = slope * (x1 - mid1.x) + mid1.y;
    }
    else
    {
        y1 = mid1.y + ydir * size / 2.0;
        x1 = (y1 - mid1.y) / slope + mid1.x;
    }
    return Point(x1, y1);
}

Line cut(Square A, Square other)
{
    /* Calculate where a line between each middle would collide with the edges of the squares */
    Point p1 = extend(A.centre, other.centre, A.side);
    Point p2 = extend(A.centre, other.centre, -1 * A.side);
    Point p3 = extend(other.centre, A.centre, other.side);
    Point p4 = extend(other.centre, A.centre, -1 * other.side);

    /* Of above points, find start and end of lines. Start is farthest left (with top most as a tie breaker)
		 * and end is farthest right (with bottom most as a tie breaker */
    Point start = p1;
    Point end = p1;
    vector<Point> points = {p2, p3, p4};
    for (int i = 0; i < points.size(); i++)
    {
        if (points[i].x < start.x || (points[i].x == start.x && points[i].y < start.y))
        {
            start = points[i];
        }
        else if (points[i].x > end.x || (points[i].x == end.x && points[i].y > end.y))
        {
            end = points[i];
        }
    }

    return Line(start, end);
}

//Any Line passing the centers of the two squares will cut them in halves
Line findLineThatCutTwoSquaresInHalves(Square A, Square B)
{
    //Line connectCentres = Line(A.centre, B.centre);
    //Point result = extend(A.centre, B.centre, A.side);
    //cout << result << endl;
    Line connectCentres = cut(A, B);
    return connectCentres;
}

void intersection(Point A, Point B, Point C, Point D)
{
    Point inter;
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
        inter = Point(max, max);
    }
    else
    {
        double x = (b1 * c2 - b2 * c1) / det;
        double y = (a2 * c1 - a1 * c2) / det; //https://byjus.com/point-of-intersection-formula/
        inter = Point(x, y);
    }
    if (inter.x == std::numeric_limits<double>::max() && inter.y == std::numeric_limits<double>::max())
    {
        cout << "The given lines are parallel.\n";
    }
    else
    {
        cout << " : ";
        cout << "(" << inter.x << ", " << inter.y << ")" << endl;
    }
}

void intersection_of_square_and_a_line(Square A, Line line)
{
    cout << "West Side: ";
    intersection(A.bottom_left, A.top_left, line.a, line.b);
    cout << "North Side: ";
    intersection(A.top_left, A.top_right, line.a, line.b);
    cout << "East Side: ";
    intersection(A.bottom_right, A.top_right, line.a, line.b);
    cout << "South Side: ";
    intersection(A.bottom_left, A.bottom_right, line.a, line.b);
    cout << endl;
}

void test(const Square &A, const Square &B)
{
    cout << "Square A side: " << A.side << ", Bottom Left: " << A.bottom_left << endl;
    cout << "Square B side: " << B.side << ", Bottom Left: " << B.bottom_left << endl;

    Line result = findLineThatCutTwoSquaresInHalves(A, B);
    cout << result << endl;
    cout << "This line intersects square A at: " << endl;
    intersection_of_square_and_a_line(A, result);
    cout << "This line intersects square B at: " << endl;
    intersection_of_square_and_a_line(B, result);
}

int test1()
{
    Square A(5, Point(0, 0));
    Square B(5, Point(6, 0));
    test(A, B);
    return 0;
}

int test2()
{
    Square A(5, Point(0, 0));
    Square B(6, Point(10, 10));
    test(A, B);
    return 0;
}

int test3()
{
    Square A(5, Point(-10, -10));
    Square B(8, Point(15, 10));
    test(A, B);
    return 0;
}

int test4()
{
    Square A(5, Point(-10, 50));
    Square B(8, Point(15, 0));
    test(A, B);
    return 0;
}

int test5()
{
    Square A(5, Point(0, 0));
    Square B(5, Point(0, 6));
    test(A, B);
    return 0;
}

int main(int argc, char const *argv[])
{
    assert(test1() == 0);
    assert(test2() == 0);
    assert(test3() == 0);
    assert(test4() == 0);
    assert(test5() == 0);
    return 0;
}
