public class Insertion {
	// Another basic sorting algorithm. Usually takes O(N^2) (~ 1/4*N^2 avg) time but if 
	// array is partially sorted then it might take O(N) time.

	public static void sort(Comparable[] array) {
		int length = array.length;
		for (int i = 0; i < length; i++) {
			for (int j = i; j > 0; j--) {
				if (less(array[j], array[j - 1])) {
					exch(array, j, j-1);
				}
				else {
					break;
				}
			}
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