public class QuickUnionUF{
	// A basic algorithm that connects nodes in O(N) time 
	// and finds connectivity in O(N) (in both cases worst case).

	int[] id;

	public QuickUnionUF(int numb_nodes){
		id = new int[numb_nodes];
		for (int i = 0; i < numb_nodes; i++)
			id[i] = i;
	}

	private int root(int node){
		while (node != id[node])
			node = id[node];
		return node;
	}

	public boolean connected(int node1, int node2) {
		return this.root(node1) == this.root(node2);
	}

	public void union(int node1, int node2) {
		id[this.root(node1)] = this.root(node2);
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