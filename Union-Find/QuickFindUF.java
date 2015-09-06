public class QuickFindUF {
	// A basic intuitive algorithm that connects nodes in O(N) time,
	// and checks connectivity in O(1) time.

	int[] id;

	public QuickFindUF(int num_nodes){
		id = new int[num_nodes];
		for (int i = 0; i < num_nodes; i++)
			id[i] = i;
	}

	public boolean connetcted(int node1, int node2){
		return id[node1] == id[node2];
	}

	public void union(int node1, int node2){
		int temp_root = id[node1];
		for (int i = 0; i < id.length; i++)
			if (id[i] == temp_root)
				id[i] = id[node2];
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