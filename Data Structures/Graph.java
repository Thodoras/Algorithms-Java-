// A simple undirected graph data structure API.

import java.util.*;

public class Graph {

	private final int V;
	private LinkedList<Integer>[] adj;
	private int edges;

	public Graph(int V) {
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
		adj[w].add(v);
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
}