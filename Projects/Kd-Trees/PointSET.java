import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;

public class PointSET {

	private SET<Point2D> set;
	private int size;

	public PointSET() {
		set = new SET<Point2D>();
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void insert(Point2D p) {
		if (p == null) {throw new NullPointerException("null argument insertion");}
		if (!contains(p)) { size++; }
		set.add(p);
	}

	public void draw() {
		for (Point2D point : set) {
			point.draw();
		}
	}

	public boolean contains(Point2D p) {
		if (p == null) {throw new NullPointerException("null argument insertion");}
		return set.contains(p);
	}

	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {throw new NullPointerException("null argument insertion");}
		Queue<Point2D> queue = new Queue<Point2D>();
		for (Point2D point : set) {
			if (rect.contains(point)) {
				queue.enqueue(point);
			}
		}
		return queue;
	}

	public Point2D nearest(Point2D p) {
		if (p == null) {throw new NullPointerException("null argument insertion");}
		double min = Double.POSITIVE_INFINITY;
		Point2D minPoint = null;
		for (Point2D point : set) {
			if (point.distanceTo(p) < min) {
				min = point.distanceTo(p);
				minPoint = point;
			}
		}
		return minPoint;
	}

	public static void main(String[] args) {
		PointSET p = new PointSET();
		Point2D point = new Point2D(0.5,0.5);
		RectHV rect = new RectHV(0.55, 0.55, 0.6, 0.6);
		p.insert(point);
		p.insert(new Point2D(0.57, 0.57));
		Point2D qoint = new Point2D(0.4, 0.4);
		System.out.println(p.nearest(qoint));
	}
}