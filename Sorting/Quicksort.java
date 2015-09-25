public class Quicksort {
	// Legendary sorting algorithm with O(NlogN) avg. completion time, in place, but not stable.

	public static void sort(Comparable[] array) {
		sort(array, 0, array.length - 1);
	}

	private static void sort(Comparable[] array, int low, int high) {
		if (high <= low) return;
		int pivot = partition(array, low, high);
		sort(array, low, pivot - 1);
		sort(array, pivot + 1, high);
	}

	private static int partition(Comparable[] array, int low, int high) {
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

	public static void main(String[] args) {
		Integer[] a = {5,3,4,1,2,3,1,2,5,7};
		sort(a);
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}