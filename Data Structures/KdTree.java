// A data type to represent a set of points in the unit square (all points have x- 
// and y-coordinates between 0 and 1) using a 2d-tree to support efficient 
// range search (find all of the points contained in a query rectangle in with order 
// of growth O(logN)) and nearest neighbor search (find a closest point to a query 
// point with the same order of growth as for range search). 2d-trees have numerous applications, 
// ranging from classifying astronomical objects to computer animation to speeding up 
// neural networks to mining data to image retrieval.

public class KdTree {

	private final static int VERT = 1;
	private final static int HOR = -1;
	private final static RectHV FRAME = new RectHV(0.0, 0.0, 1.0, 1.0);
	private Node root;
	private int size;

	private static class Node {
		private Point2D point;
		private int axis;
		private RectHV rect;
		private Node right;
		private Node left;

		public Node(Point2D p, int axis, RectHV rect) {
			this.point = p;
			this.axis = axis;
			this.rect = rect;
			this.right = null;
			this.left = null;
		}
	}

	public KdTree() {
		root = null;
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
		root = insert(root, VERT, FRAME, p);
	}

	private Node insert(Node node, int axis, RectHV rect, Point2D p) {
		if (node == null) {
			node = new Node(p, axis, rect);
			size++;
			return node;
		}
		double cmpx = p.x() - node.point.x();
		double cmpy = p.y() - node.point.y();
		if (axis > 0) {
			
			if (cmpx < 0.0) {
				if (node.left == null) {
					node.left = insert(node.left, axis*(-1), new RectHV(rect.xmin(), rect.ymin(), node.point.x(),  rect.ymax()), p);
				}
				else {
					node.left = insert(node.left, axis*(-1), node.left.rect, p);
				}	
			}
			else if (cmpx > 0.0 || cmpy != 0) {
				if (node.right == null) {
					node.right = insert(node.right, axis*(-1), new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax()), p);
				}
				else {
					node.right = insert(node.right, axis*(-1), node.right.rect, p);
				}
			}
			else {

				return node;
			}
		}
		else {
			
			if (cmpy < 0.0) {
				if (node.left == null) {
					node.left = insert(node.left, axis*(-1), new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y()), p);
				}
				else {
					node.left = insert(node.left, axis*(-1), node.left.rect, p);
				}
			}
			else if (cmpy > 0.0 || cmpx != 0) {
				if (node.right == null) {
					node.right = insert(node.right, axis*(-1), new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax()), p);
				}
				else {
					node.right = insert(node.right, axis*(-1), node.right.rect, p);	
				}
			}
			else {
				return node;
			}
		}
		return node;
	}

	public boolean contains(Point2D p) {
		if (p == null) {throw new NullPointerException("null argument insertion");}
		return contains(root, p);
	}

	private boolean contains(Node node, Point2D p) {
		if (node == null) {return false;}
		if (node.point.equals(p)) {return true;}
		double cmpx = p.x() - node.point.x();
		double cmpy = p.y() - node.point.y();
		if (node.axis > 0) {
			if (cmpx < 0) {
				return contains(node.left, p);
			}
			else {
				return contains(node.right, p);
			}
		}
		else {
			if (cmpy < 0) {
				return contains(node.left, p);
			}
			else {
				return contains(node.right, p);
			}
		}
	}

	public void draw() {
		for (Node node : _range(root.rect)) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(.01);
			node.point.draw();
			StdDraw.setPenRadius();
			if (node.axis > 0) {
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
			}
			else {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
			}
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {throw new NullPointerException("null argument insertion");}
		Queue<Point2D> queue = new Queue<Point2D>();
		range(root, rect, queue);
		return queue;
	}

	private void range(Node node, RectHV rect, Queue<Point2D> queue) {
		if (node == null) {return;}

		if (node.rect.intersects(rect)) {
			if (rect.contains(node.point)) {
				queue.enqueue(node.point);
		}

			range(node.left, rect, queue);
			range(node.right, rect, queue);
		}
	}

	public Point2D nearest(Point2D p) {
		if (p == null) {throw new NullPointerException("null argument insertion");}
		if (isEmpty()) {return null;}
		Point2D minpoint = new Point2D(root.rect.xmax()*2, root.rect.ymax()*2);
		return nearest(p, root, minpoint);
	}

	private Point2D nearest(Point2D p, Node node, Point2D minpoint) {
		if (node == null) {return minpoint;}
		if (node.rect.distanceTo(p) < minpoint.distanceTo(p)) {
			if (node.point.distanceTo(p) < minpoint.distanceTo(p)) {
				minpoint = node.point;
			}
			double cmpx = p.x() - node.point.x();
			double cmpy = p.y() - node.point.y();
			if (node.axis > 0) {
				if (cmpx < 0) {
					minpoint = nearest(p, node.left, minpoint);
					minpoint = nearest(p, node.right, minpoint);
				}
				else {
					minpoint = nearest(p, node.right, minpoint);
					minpoint = nearest(p, node.left, minpoint);
				}
			}
			else {
				if (cmpy < 0) {
					minpoint = nearest(p, node.left, minpoint);
					minpoint = nearest(p, node.right, minpoint);
				}
				else {
					minpoint = nearest(p, node.right, minpoint);
					minpoint = nearest(p, node.left, minpoint);
				}
			}	
		}
		return minpoint;
	}

	private Iterable<Node> _range(RectHV rect) {
		Queue<Node> queue = new Queue<Node>();
		_range(root, rect, queue);
		return queue;
	}

	private void _range(Node node, RectHV rect, Queue<Node> queue) {
		if (node == null) {return;}

		if (node.rect.intersects(rect)) {
			if (rect.contains(node.point)) {
				queue.enqueue(node);
		}

			_range(node.left, rect, queue);
			_range(node.right, rect, queue);
		}
	}

	public static void main(String[] args) {
		KdTree tree = new KdTree();
		Point2D point = new Point2D(0.5, 0.5);
		Point2D point2 = new Point2D(0.1, 0.1);
		Point2D point3 = new Point2D(0.9, 0.9);
		Point2D point4 = new Point2D(0.7, 0.7);
		tree.insert(point);
		tree.insert(point2);
		tree.insert(point3);
		tree.insert(point4);
		tree.insert(new Point2D(0.95, 0.95));
		tree.insert(new Point2D(0.97, 0.97));
		tree.insert(point);
		tree.draw();
		System.out.println(tree.range(null));
		System.out.println(tree.size());
		for (Point2D p : tree.range(new RectHV(0.7, 0.7, 0.99, 0.99))) {
			System.out.println(p.toString());
		}
		System.out.println(tree.nearest(point3));
	}
}
