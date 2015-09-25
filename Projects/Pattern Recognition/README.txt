Pattern Recognition

The problem. Given a set of N distinct points in the plane, find every (maximal) line segment that connects a subset of 4 or more of the points.



Point 2D

Model: Represents a point in the plane.

API: 

public class Point implements Comparable<Point> {
   public Point(int x, int y)                         // constructs the point (x, y)

   public   void draw()                               // draws this point
   public   void drawTo(Point that)                   // draws the line segment from this point to that point
   public String toString()                           // string representation

   public               int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
   public            double slopeTo(Point that)       // the slope between this point and that point
   public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
}



Line segment

Model: Represents line segments in the plane.

API:

public class LineSegment {
   public LineSegment(Point p, Point q)        // constructs the line segment between points p and q
   public   void draw()                        // draws this line segment
   public String toString()                    // string representation
}



Brute force

Model: A program that examines 4 points at a time and checks whether they all lie on the same line segment, returning all such line segments. The order of growth of the running time of the is O(N^4).

API:

public class BruteCollinearPoints {
   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
   public           int numberOfSegments()        // the number of line segments
   public LineSegment[] segments()                // the line segments
}



Fast implementation.

Model: Remarkably, it is possible to solve the problem much faster than the brute-force solution described above. Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.

Think of p as the origin.
-For each other point q, determine the slope it makes with p.
-Sort the points according to the slopes they makes with p.
-Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.

API:

public class FastCollinearPoints {
   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
   public           int numberOfSegments()        // the number of line segments
   public LineSegment[] segments()                // the line segments
}

