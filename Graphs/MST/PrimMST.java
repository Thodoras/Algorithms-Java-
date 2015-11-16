// Prim' s algorithm for finding a minimum spanning tree in O((n + m)logm) time.
// Dependencies: EdgeWeightedGraph.java, Edge.java, WeightedQuickUF.java, Heap.java

public class PrimMST {

	public static EdgeWeightedGraph MST(EdgeWeightedGraph g) {
		// Main static method.

		EdgeWeightedGraph tree = new EdgeWeightedGraph(g.V());
		Heap<Edge> heap = new Heap<Edge>();
		boolean marked[] = new boolean[g.V()];

		visit(g, heap, marked, 0);

		while (!heap.isEmpty()) {
			Edge e = heap.removeMin();
			int v = e.getFirstV();
			int u = e.getOtherV(v);
			if (!marked[v] || !marked[u]) {
				tree.addEdge(e);
				if (!marked[v]) {
					visit(g, heap, marked, v);
				}
				else {
					visit(g, heap, marked, u);
				}
			}
		}
		return tree;
	}

	private static void visit(EdgeWeightedGraph g, Heap<Edge> heap, boolean marked[], int vertex) {
		
		marked[vertex] = true;
		for (Edge e : g.adjEdges(vertex)) {
			if (!marked[e.getOtherV(vertex)]) {
				heap.insert(e);
			}
		}
	}
}