// A simple undirected weighted graph data structure API.
// Dependencies: Edge API.

import java.util.*;

public class EdgeWeightedGraph {

	private final int V;
	private LinkedList<Integer>[] adjVertices;
	private LinkedList<Edge>[] adjEdges;
	private int edges;

	public EdgeWeightedGraph(int V) {
		// Constructor.

		this.V = V;
		edges = 0;
		adjVertices = (LinkedList<Integer>[]) new LinkedList[V];
		adjEdges = (LinkedList<Edge>[]) new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adjVertices[i] = new LinkedList<Integer>();
			adjEdges[i] = new LinkedList<Edge>();
		}
	}

	public void addEdge(Edge edge) {
		// Add an edge from v to w.

		edges++;
		int v1 = edge.getFirstV();
		int v2 = edge.getOtherV(v1);
		adjVertices[v1].add(v2);
		adjVertices[v2].add(v1);
		adjEdges[v1].add(edge);
		adjEdges[v2].add(edge);
	}

	public int V() {
		// Return number of vertices.

		return V;
	}

	public int E() {
		// Return number of edges.

		return edges;
	}

	public double getWeight(Edge edge) {
		return edge.getWeight();
	}

	public String toStringVertices() {
		// Return a string representaton of the graph depicting adjacent vertices of each vertex.

		String retstr = "";
		for (int i = 0; i < V ; i++) {
			retstr += i + " -> ";
			for (int x : adjVertices(i)) {
				retstr += x + " ";
			}
			retstr += "\n";
		}
		return retstr;
	}

	public String toStringEdges() {
		// Return a string representaton of the graph depicting adjacent edges of each vertex.

		String retstr = "";
		for (int i = 0; i < V ; i++) {
			retstr += i + " -> ";
			for (Edge x : adjEdges(i)) {
				retstr += x.toString() + ", ";
			}
			retstr += "\n";
		}
		return retstr;
	}

	public Iterable<Integer> adjVertices(int v) {
		// Return an iterator of all the adjacent vertices of a given vertex. 

		return adjVertices[v];
	}

	public Iterable<Edge> adjEdges(int v) {
		// Return an iterator of all the adjacent vertices of a given edge.

		return adjEdges[v];
	}

	public static void main(String[] args) {
		EdgeWeightedGraph g = new EdgeWeightedGraph(4);
		Edge e1 = new Edge(0, 1, 3);
		Edge e2 = new Edge(0, 2, 1);
		Edge e3 = new Edge(1, 3, 2);
		Edge e4 = new Edge(2, 3, 5);
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e4);
		System.out.println(e1.toString());
		System.out.println(g.toStringEdges());
	}
}