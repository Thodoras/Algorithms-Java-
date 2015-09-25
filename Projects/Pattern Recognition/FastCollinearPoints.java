import java.util.*;

public class FastCollinearPoints {
	// Class that finds all collinear sets of 4 or more points in a plane in O(N^2*logN) time.

	private ArrayList<Point> pointz;
	private int numSegments;
	private ArrayList<LineSegment> segments;

	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new NullPointerException("Argument is null");
		for (int i = 0; i < points.length; i++) 
			for (int j = i; j < points.length; j++) {
				if (points[j] == null) throw new NullPointerException("Element is null");
				if (j != i && points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("ADuplicate points");
			}
		pointz = new ArrayList<Point>();
		Map<Double, Point> slopes = new HashMap<Double, Point>();
		for (int i = 0; i < points.length; i++)
			pointz.add(points[i]);
		Collections.sort(pointz);
		numSegments = 0;
		segments = new ArrayList<LineSegment>();
		int length = points.length;

		for (int i = 0; i < length; i++) {
			ArrayList<Point> clone = (ArrayList<Point>) pointz.clone();
			Collections.sort(clone, pointz.get(i).slopeOrder());
			double slope = Double.NEGATIVE_INFINITY;
			int count = 1;
			Point min = clone.get(0);
			Point max = clone.get(0);

			for (int j = 1; j < length; j++) {
				if (clone.get(0).slopeTo(clone.get(j)) == slope) {
					count++;
					if (min.compareTo(clone.get(j)) > 0)
						min = clone.get(j);
					if (max.compareTo(clone.get(j)) < 0)
						max = clone.get(j);
				}
				if (clone.get(0).slopeTo(clone.get(j)) > slope || j == length - 1) {
					if (count >= 4) {
						if (!slopes.containsKey(slope)) {
							slopes.put(slope, min);
							segments.add(new LineSegment(min, max));
							numSegments++;
						} 
						else if ( slopes.get(slope).compareTo(min) < 0) {
							slopes.put(slope, min);
							segments.add(new LineSegment(min, max));
							numSegments++;
						}
					}
					if (clone.get(0).compareTo(clone.get(j)) < 0) {
						min = clone.get(0);
						max = clone.get(j);
					}
					else {
						min = clone.get(j);
						max = clone.get(0);
					}
					count = 2;
					slope = clone.get(0).slopeTo(clone.get(j));
				}
			}
		}
	}

	public int numberOfSegments() {
		return segments.size();
	}

	public LineSegment[] segments() {
		return segments.toArray(new LineSegment[segments.size()]);
	}

	public static void main(String[] args) {

    	// read the N points from a file
    	In in = new In(args[0]);
    	int N = in.readInt();
    	Point[] points = new Point[N];
    	for (int i = 0; i < N; i++) {
        	int x = in.readInt();
    	  	int y = in.readInt();
        	points[i] = new Point(x, y);
    	}

    	// draw the points
    	StdDraw.show(0);
    	StdDraw.setXscale(0, 32768);
    	StdDraw.setYscale(0, 32768);
    	for (Point p : points) {
    	  	p.draw();
    	}
    	StdDraw.show();

    	// print and draw the line segments
    	FastCollinearPoints collinear = new FastCollinearPoints(points);
    	for (LineSegment segment : collinear.segments()) {
        	StdOut.println(segment);
        	segment.draw();
    	}
	}
}