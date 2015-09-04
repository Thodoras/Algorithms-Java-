public class QuickFindUF {
	// A basic intuitive algorithm that connects nodes in O(N) time,
	// and checks connectivity in O(1) time.

	int[] roots;

	public QuickFindUF(int num_nodes){
		roots = new int[num_nodes];
		for (int i = 0; i < num_nodes; i++)
			roots[i] = i;
	}

	public boolean connetcted(int node1, int node2){
		return roots[node1] == roots[node2];
	}

	public void union(int node1, int node2){
		int temp_root = roots[node1];
		for (int i = 0; i < roots.length; i++)
			if (roots[i] == temp_root)
				roots[i] = roots[node2];
	}

	public int connected_comps(){
		int[] temp_array = new int[roots.length];
		for (int i = 0; i < temp_array.length; i++)
			temp_array[roots[i]]++;
		int count = 0;
		for (int i = 0; i < temp_array.length; i++)
			if (temp_array[i] > 0)
				count++;
		return count;
	}
}