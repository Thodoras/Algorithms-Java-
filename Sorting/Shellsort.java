public class Shellsort {
	// Non-trivial sorting algorithm, worst case time to complete O(N^(3/2))
	// but in practice is faster. Uses Knuth's 3*x + 1

	public static void sort(Comparable[] array) {

		int size = array.length;

		int h = 1;
		while (h < size/3) 
			h = 3*h + 1;

		while (h > 0) {
			for (int i = 0; i < size; i++) {
				for (int j = i; j >= h; j -= h) {
					if (less(array[j], array[j-1]))
						exch(array, j, j - 1);
					else
						break;
				}
			}
			h /= 3;
		}
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