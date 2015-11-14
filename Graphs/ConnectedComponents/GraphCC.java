// A class that computes all the connected components of a given graph.
// Dependencies: Graph.java API.

public class GraphCC {

	private boolean[] marked;
	private int[] id;
	private int count;

	public GraphCC(Graph g) {
		// Constructor for undirected graphs.

		marked = new boolean[g.V()];
		id = new int[g.V()];
		count = 0;
		for (int i = 0; i < g.V(); i++) {
			if (!marked[i]) {
				dfs(g, i);
				count++;
			}
		}
	}

	public GraphCC(DiGraph g) {
		// Constructor for directed graphs.

		marked = new boolean[g.V()];
		id = new int[g.V()];
		count = 0;
		for (int i = 0; i < g.V(); i++) {
			if (!marked[i]) {
				dfs(g, i);
				count++;
			}
		}
	}

	public int count() {
		// return the number of components.

		return count;
	}

	public int id(int vertex) {
		// takes as input a vertex and returns the i-th cc component
		// that belongs. 

		return id[vertex];
	}

	private void dfs(Graph g, int v) {
		marked[v] = true;
		id[v] = count;
		for (int vertex : g.adj(v)) {
			if (!marked[vertex]) {
				dfs(g,vertex);
			}
		}
	}
}