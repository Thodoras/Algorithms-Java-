// Kruskal' s algorithm for finding a minimum spanning tree in O((n + m)logm) time.
// Dependencies: EdgeWeightedGraph.java, Edge.java, WeightedQuickUF.java, Heap.java

public class KruskalMST {

	public static EdgeWeightedGraph MST(EdgeWeightedGraph g) {
		// Main static method.
		
		EdgeWeightedGraph tree = new EdgeWeightedGraph(g.V()); 
		
		Heap<Edge> heap = new Heap<Edge>(); 
		for (Edge e : g.edges()) {
			heap.insert(e);
		}

		WeightedQuickUF uf = new WeightedQuickUF(g.V());  	//Initialize weighted quick union among the edges to check cycles.

		while (!heap.isEmpty() && tree.E() < g.V()) {
			Edge e = heap.removeMin();
			int v = e.getFirstV();
			int u = e.getOtherV(v);
			if (!uf.connected(u,v)) {
				uf.union(v, u);
				tree.addEdge(e);
			}
		}
		return tree;
	}
}