// Return an array of all the vertices of a graph that holds their shortest distance from a starting vertex s
// where all the edges have the unit length in O(n + m). U stands for unit. 

import java.util.*;

public class ShortestUPath {

	public static int[] distances(Graph g, int s) {
		// For undirected graphs.

		int[] distances = new int[g.V()];
		boolean[] marked = new boolean[g.V()];
 		initializeDist(distances, marked);
		distances[s] = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		marked[s] = true;
		int temp;
		while (queue.size() != 0) {
			temp = queue.remove();
			for (int vertex : g.adj(temp)) {
				if (!marked[vertex]) {
					queue.add(vertex);
					marked[vertex] = true;
					distances[vertex] = distances[temp] + 1;
				}
			}
		}
		return distances;
	}

	public static int[] distances(DiGraph g, int s) {
		// For directed graphs.

		int[] distances = new int[g.V()];
		boolean[] marked = new boolean[g.V()];
 		initializeDist(distances, marked);
		distances[s] = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		marked[s] = true;
		int temp;
		while (queue.size() != 0) {
			temp = queue.remove();
			for (int vertex : g.adj(temp)) {
				if (!marked[vertex]) {
					queue.add(vertex);
					marked[vertex] = true;
					distances[vertex] = distances[temp] + 1;
				}
			}
		}
		return distances;
	}

	private static void initializeDist(int[] distances, boolean[] marked) {
		for (int i = 0; i < distances.length; i++) {
			distances[i] = -1;
			marked[i] = false;
		}
	}
}