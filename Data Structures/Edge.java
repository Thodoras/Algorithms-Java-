// A class for creating weighted edges. 

public class Edge implements Comparable<Edge> {

	private final int vertex1, vertex2;
	private final double weight;

	public Edge(int vertex1, int vertex2) {
		// Constructor with default weight 1. Order of the argument matters if the graph
		// client is directed.

		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = 1;
	} 

	public Edge(int vertex1, int vertex2, double weight) {
		// Constructor with variable weight.

		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	public int getFirstV() {
		// Returns the first one of the two vertices of the edge.

		return vertex1;
	}

	public int getOtherV(int vertex) {
		// Returns the opossite vertex of the one inserted in the edge.

		if (vertex == vertex1) {
			return vertex2;
		}
		else {
			return vertex1;
		}
	}

	public double getWeight() {
		// Returns the weight of the edge.

		return weight;
	}

	public int compareTo(Edge that) {
		// The "compareTo" method for the "Comparable" interface. 

		if (this.getWeight() < that.getWeight()) {
			return -1;
		}
		else if (this.getWeight() > that.getWeight()) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public String toString() {
		return String.format("(%d, %d)(%f)", vertex1, vertex2, weight);
	}
}