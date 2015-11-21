public class DijkstraSP {

	private Double dist[];

	public DijkstraSP(EdgeWeightedDiGraph g, int source) {
		dist = new Double[g.V()];
		for (int i = 0; i < g.V(); i++) {
			if (i == source) {
				dist[i] = 0.0;
			}
			else {
				dist[i] = Double.POSITIVE_INFINITY;
			}
		}
		GreedyHeap<Double> heap = new GreedyHeap<Double>(dist);

		while (!heap.isEmpty()) {
			int vertex = heap.removeMin();
			for (Edge edge : g.adjEdges(vertex)) {
				double len = edge.getWeight() + dist[vertex];
				if (len < dist[edge.getOtherV(vertex)]) {
					dist[edge.getOtherV(vertex)] = len;
					heap.updateKey(edge.getOtherV(vertex), len);
				}
			}
		}
	}

	public Double getDistFrom(int vertex) {
		return dist[vertex];
	}
}
