// A simple directed graph data structure API.

import java.util.*;

public class DiGraph {

	private final int V;
	private LinkedList<Integer>[] adj;
	private int edges;

	public DiGraph(int V) {
		// Constructor.

		this.V = V;
		edges = 0;
		adj = (LinkedList<Integer>[]) new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int v, int w) {
		// Add an edge from v to w.

		adj[v].add(w);
		edges++;
	}

	public int V() {
		// Return number of vertices.

		return V;
	}

	public int E() {
		// Return number of edges.

		return edges;
	}

	public String toString() {
		// Return a string representaton of the graph.

		String retstr = "";
		for (int i = 0; i < V ; i++) {
			retstr += i + " -> ";
			for (int x : adj(i)) {
				retstr += x + " ";
			}
			retstr += "\n";
		}
		return retstr;
	}

	public Iterable<Integer> adj(int v) {
		// Return an iterator of all the adjacent vertices of a given vertex. 

		return adj[v];
	}

	public DiGraph reverse() {
		// Return a clone graph with opposite direction edges of "this" graph. 

		DiGraph rev = new DiGraph(V());
		for (int stVertex = 0; stVertex < V(); stVertex++) {
			for (int endVertex : adj(stVertex)) {
				rev.addEdge(endVertex, stVertex);
			}
		}
		return rev;
	} 
}