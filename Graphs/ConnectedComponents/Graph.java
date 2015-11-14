// A simple undirected graph data structure API.

import java.util.*;

public class Graph {

	private final int V;
	private LinkedList<Integer>[] adj;
	private int edges;

	public Graph(int V) {
		this.V = V;
		edges = 0;
		adj = (LinkedList<Integer>[]) new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		edges++;
	}

	public int V() {
		return V;
	}

	public int E() {
		return edges;
	}

	public String toString() {
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
		return adj[v];
	}

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(1,0);
		System.out.println(g.E());
	}
}