public class WeightedQuickUF{
	// Union - find algorith that uses extra space propotional to the graphs neumber
	// of nodes but keeps union and find at O(logN).

	int[] id;
	int[] sz;

	public WeightedQuickUF(int num_nodes) {
		id = new int[num_nodes];
		sz = new int[num_nodes];
		for (int i = 0; i < num_nodes; i++){
			id[i] = i;
			sz[i] = 1;
		}
	}

	private int root(int node){
		while (node != id[node]) {
			//id[node] = id[id[node]];     //This way the node we are looking for will go up to the root flatening the tree.
			node = id[node];
		}
		return node;
	}

	public boolean connected(int node1, int node2) {
		return this.root(node1) == this.root(node2);
	}

	public void union(int node1, int node2) {
		int root1 = this.root(node1);
		int root2 = this.root(node2);
		if (root1 == root2) return;
		if (sz[root1] < sz[root2]){
			id[root1] = id[root2];
			sz[root2] += sz[root1]; 
		}
		else {
			id[root2] = id[root1];
			sz[root1] += sz[root2];
		}
	}

	public int connected_comps(){
		int[] temp_array = new int[id.length];
		for (int i = 0; i < temp_array.length; i++)
			temp_array[id[i]]++;
		int count = 0;
		for (int i = 0; i < temp_array.length; i++)
			if (temp_array[i] > 0)
				count++;
		return count;
	}
}
