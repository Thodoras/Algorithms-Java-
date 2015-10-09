Kd-Tree (2d-Tree in this instance, easily adjustable).

A data type to represent a set of points in the unit square (all points have x- and y-coordinates between 0 and 1) using a 2d-tree to support efficient range search (find all of the points contained in a query rectangle in with order of growth O(logN)) and nearest neighbor search (find a closest point to a query point with the same order of growth as for range search). 2d-trees have numerous applications, ranging from classifying astronomical objects to computer animation to speeding up neural networks to mining data to image retrieval.

More information https://en.wikipedia.org/wiki/K-d_tree.



Auxiliary classes.


Point2D

The Point class is an immutable data type to encapsulate a two-dimensional point with real-value coordinates.

API

public class Point2D implements Comparable<Point2D> {
   public Point2D(double x, double y)              // construct the point (x, y)
   public  double x()                              // x-coordinate 
   public  double y()                              // y-coordinate 
   public  double distanceTo(Point2D that)         // Euclidean distance between two points 
   public  double distanceSquaredTo(Point2D that)  // square of Euclidean distance between two points 
   public     int compareTo(Point2D that)          // for use in an ordered symbol table 
   public boolean equals(Object that)              // does this point equal that object? 
   public    void draw()                           // draw to standard draw 
   public  String toString()                       // string representation 
}


RectHV

The RectHV class is an immutable data type to encapsulate a two-dimensional axis-aligned rectagle with real-value coordinates. The rectangle is closed—it includes the points on the boundary.

API

public class RectHV {
   public    RectHV(double xmin, double ymin,      // construct the rectangle [xmin, xmax] x [ymin, ymax] 
                    double xmax, double ymax)      // throw a java.lang.IllegalArgumentException if (xmin > xmax) or (ymin > ymax)
   public  double xmin()                           // minimum x-coordinate of rectangle 
   public  double ymin()                           // minimum y-coordinate of rectangle 
   public  double xmax()                           // maximum x-coordinate of rectangle 
   public  double ymax()                           // maximum y-coordinate of rectangle 
   public boolean contains(Point2D p)              // does this rectangle contain the point p (either inside or on boundary)? 
   public boolean intersects(RectHV that)          // does this rectangle intersect that rectangle (at one or more points)? 
   public  double distanceTo(Point2D p)            // Euclidean distance from point p to closest point in rectangle 
   public  double distanceSquaredTo(Point2D p)     // square of Euclidean distance from point p to closest point in rectangle 
   public boolean equals(Object that)              // does this rectangle equal that object? 
   public    void draw()                           // draw to standard draw 
   public  String toString()                       // string representation 
}


PointSET

A brute force data type PointSET.java that represents a set of points in the unit square. Implement using a red-black BST.

API

public class PointSET {
   public         PointSET()                               // construct an empty set of points 
   public           boolean isEmpty()                      // is the set empty? 
   public               int size()                         // number of points in the set 
   public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   public           boolean contains(Point2D p)            // does the set contain point p? 
   public              void draw()                         // draw all points to standard draw 
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
   public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 

   public static void main(String[] args)                  // unit testing of the methods (optional) 
}




Primary Class

Kd-Tree (2d-Tree)

API

same as pointset.