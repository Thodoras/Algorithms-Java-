//  Kosaraju-Sharir two-pass algorithm. 
//  A function than computes the number of the stongly connected components of a directed graph in O(n + m) time.
//  A strongly connected component is a subset of the set of nodes in a graph that for every two nodes p,q
//  in it there is a path from p to q and a path from q to p.
//  Notice that it uses some extra space (+~30%) than the optimal algorithm for keeping an extra stack (tempOrder). But
//  I found it more convinient for understanding it and more compact to implement.
//  Dependencies: DiGraph API.

import java.util.*;

public class StrongComponents {
	
	int count;
	int id[];
	boolean marked[];
	ArrayDeque<Integer> order;       // Used as a Stack.
	ArrayDeque<Integer> tempOrder;	// Used as a Stack.

	public StrongComponents(DiGraph g) {
		// Constructor

		id = new int[g.V()];
		marked = new boolean[g.V()];
		order = new ArrayDeque<Integer>();
		tempOrder = new ArrayDeque<Integer>();
		for (int i = 0; i < g.V(); i++) {
			order.push(i);
		}
		dfsLoop(g.reverse());
		order = tempOrder.clone();
		dfsLoop(g);
	}

	public int component(int vertex) {
		// Returns the id of a component.

		return id[vertex];
	}

	public boolean sameComponent(int vertex1, int vertex2) {
		// Returns true iff two vertices are in the same strong component.

		return id[vertex1] == id[vertex2];
	}

	public int numOfComponents() {
		// Returns the number of components.

		return count;
	}

	private void dfsLoop(DiGraph g) {
		count = 0;
		for (int i = 0; i < g.V(); i++) {
			marked[i] = false;
		}
		for (Integer vertex : order) {
			if (!marked[vertex]) {
				dfs(g, vertex);
				count++;
			}
		}
	}

	private void dfs(DiGraph g, int vertex) {
		marked[vertex] = true;
		for (Integer v : g.adj(vertex)) {
			if (!marked[v]) {
				dfs(g, v);
			}
		}
		tempOrder.push(vertex);
		id[vertex] = count;
	}
}