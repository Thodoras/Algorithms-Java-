// A class that does a topological sort for a given directed acyclic graph.
// It does it with an order of growth of O(n+m).
// Dependencies: A DiGraph API.

import java.util.*;

public class TopologicalSort {

	private boolean marked[];
	private int id[];
	private int order;

	public TopologicalSort(DiGraph g) {
		// Constructor.

		marked = new boolean[g.V()];
		id = new int[g.V()];
		order = g.V();
		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) {
				dfs(g, v);
			}
		}
	}

	public int vertexOrder(int ord) {
		// Returns a vertex of a given order.

		return id[ord];
	}

	private void dfs(DiGraph g, int vertex) {
		marked[vertex] = true;
		for (int v : g.adj(vertex)) {
			if (!marked[v]) {
				dfs(g, v);
			}
		}
		id[--order] = vertex;
	}
}