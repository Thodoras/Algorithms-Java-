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

	public static void main(String[] args) {
		Edge e1 = new Edge(0,1,3);
		Edge e2 = new Edge(0,2,1);
		Edge e3 = new Edge(1,3,2);
		Edge e4 = new Edge(2,3,4);
		EdgeWeightedGraph g = new EdgeWeightedGraph(4);
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e4);
		EdgeWeightedGraph h = KruskalMST.MST(g);
		System.out.println(h.toStringVertices());
	}
}