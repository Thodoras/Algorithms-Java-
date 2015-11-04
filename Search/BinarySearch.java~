public class BinarySearch {
	// Elegant (non recursive) algorithm, for searching a certain value in a sorted array, in O(logN) time.

	public static int binarySearch(int[] array, int value) {
		//Returns the position of a certain existing value, or else -1.

		int low = 0;
		int high = array.length;
		while (low <= high){
			int mid = low + (high - low)/2;
			if (value < array[mid])
				high = mid - 1;
			else if (value > array[mid])
				low = mid + 1;
			else
				return mid;
		}
		return -1;
	}
}