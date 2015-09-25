public class Quicksort3way {
	// Elegant algorithm for sorting arrays with many dublicate key. If the dublicate keys are "sufficient"
	// it achieves O(N) time.

	public static void sort(Comparable[] array) {
		sort(array, 0, array.length - 1);
	}

	private static void sort(Comparable[] array, int low, int high) {
		if (high <= low)
			return;
		int dup = low;
		int i = low;
		int j = high;
		while (i <= j) {
			int cmp = array[i].compareTo(array[dup]);
			if (cmp < 0)
				exch(array, i++, dup++);
			else if (cmp > 0)
				exch(array, i, j--);
			else
				i++;
		}
		sort(array, 0, dup - 1);
		sort(array, j + 1, high);
	}

	private static void exch(Comparable[] array, int i, int j) {
		Comparable swap = array[i];
		array[i] = array[j];
		array[j] = swap;
	}
}