import java.util.Comparator;
import java.util.Arrays;

public class Point implements Comparable<Point> {
    // Class that represents a 2D-point.

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertcal;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     */
    public double slopeTo(Point that) {
        if (this.x != that.x)
            if (this.y != that.y)
                return (double) (that.y - this.y) / (that.x - this.x);
            else
                return +0.0;
        else
            if (this.y != that.y)
                return Double.POSITIVE_INFINITY;
            else
                return Double.NEGATIVE_INFINITY;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     */
    public int compareTo(Point that) {
        if (this.y < that.y)
            return -1;
        else if (this.y > that.y)
            return 1;
        else {
            if (this.x < that.x)
                return -1;
            else if (this.x > that.x)
                return 1;
            else
                return 0;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     */
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            public int compare(Point point1, Point point2) {
                if (slopeTo(point1) < slopeTo(point2))
                    return -1;
                else if (slopeTo(point1) > slopeTo(point2))
                    return 1;
                else 
                    return 0;
            }
        };
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point[] points = {new Point(1,0), new Point(1,1), new Point(0,1), new Point(-1,1)};
        Point p = new Point(0,0);
        Arrays.sort(points, p.slopeOrder());
        for (int i = 0; i < points.length; i++)
            System.out.println(points[i].toString());
    }
}