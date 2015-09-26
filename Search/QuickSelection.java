public class QuickSelection {
	// Algorithm that searches the k-th order element in an array in O(N) time on avg.

	public static Comparable select(Comparable[] array, int order) {
		int low = 0;
		int high = array.length - 1;
		order -= 1;

		while (high > low) {
			int pivot = partition(array, low, high);
			if (pivot > order)
				high = pivot - 1;
			else if (pivot < order)
				low = pivot + 1;
			else
				return array[order];
		}
		return array[order];
	}

	public static int partition(Comparable[] array, int low, int high) {
		int i = low;
		int j = high + 1;

		while (true) {
			while (less(array[++i], array[low])) {
				if (i >= high)
					break;
			}

			while (less(array[low], array[--j])) {
				if (j <= low)
					break;
			}

			if (j <= i)
				break;

			exch(array, i, j);
		}
		exch(array, low, j);
		return j;
	}

	private static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}

	private static void exch(Comparable[] array, int i, int j) {
		Comparable swap = array[i];
		array[i] = array[j];
		array[j] = swap;
	}
}