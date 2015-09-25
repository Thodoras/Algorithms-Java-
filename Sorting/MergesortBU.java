import java.lang.Math;

public class MergesortBU {
	// A sorting method similar to Mergesort, that is not recursive.

	public static void sort(Comparable[] array) {
		Comparable[] aux = new Comparable[array.length];
		for (int sz = 1; sz < array.length; sz += sz)
			for (int lo = 0; lo < array.length - 1; lo += sz + sz)
				merge(array, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, array.length-1));
	}

	private static void merge(Comparable[] array, Comparable[] aux, int low, int mid, int high) {
		for (int i = low; i <= high; i++)
			aux[i] = array[i];

		int i = low;
		int j = mid + 1;

		for (int k = low; k <= high; k++) {
			if (i > mid) 
				array[k] = aux[j++];
			else if (j > high) 
				array[k] = aux[i++];
			else if (less(aux[j], aux[i])) 
				array[k] = aux[j++];
			else 
				array[k] = aux[i++];
		}
	}

	private static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}	
}