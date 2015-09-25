import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	// Class that finds all collinear sets of 4 or more points in a plane in O(N^4) time.
	// Warning: Still buggy version when there are more than 4 points.

	private Point[] pointz;
	private int numSegments;
	private ArrayList<LineSegment> segments;

	public BruteCollinearPoints(Point[] points) {
		if (points == null) throw new NullPointerException("Argument is null");
		pointz = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			for (int j = i; j < points.length; j++) {
				if (points[j] == null) throw new NullPointerException("Element is null");
				if (j != i && points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("ADuplicate points");
			}
			pointz[i] = points[i];
		}
		Arrays.sort(pointz);
		numSegments = 0;
		segments = new ArrayList<LineSegment>();
		int length = pointz.length;

		for (int i = 0; i < length; i++) {
			for (int j = i; j < length; j++) {
				for (int k = j; k < length; k++) {
					if (pointz[i].slopeTo(pointz[j]) == pointz[i].slopeTo(pointz[k]) && pointz[i].slopeTo(pointz[j]) != Double.NEGATIVE_INFINITY && pointz[j].slopeTo(pointz[k]) != Double.NEGATIVE_INFINITY)
						for (int m = k; m < length; m++) {
							if (pointz[i].slopeTo(pointz[j]) == pointz[i].slopeTo(pointz[m]) && pointz[j].slopeTo(pointz[m]) != Double.NEGATIVE_INFINITY && pointz[k].slopeTo(pointz[m]) != Double.NEGATIVE_INFINITY) {
								segments.add(new LineSegment(pointz[i], pointz[m]));
								numSegments++;
								break;
							}
					}
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

	//public static void main(String[] args) {
	//	Point[] points = {new Point(1,1), new Point(2,2), new Point(3,3), new Point(4,4), new Point(1,0), new Point(2,1), new Point(3,2), new Point(4,3)};
	//	BruteCollinearPoints b = new BruteCollinearPoints(points);
	//	System.out.println(b.numberOfSegments());
	//	for (LineSegment l:b.segments())
	//		System.out.println(l);
	//}

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
    	BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    	for (LineSegment segment : collinear.segments()) {
        	StdOut.println(segment);
        	segment.draw();
    	}
	}
}