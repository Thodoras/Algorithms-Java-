import java.util.Random;

public class KnuthShuffle {
	// Simple shuffling algorithm for arrays of any object. Completes in O(N) time.

	public static void shuffle(Object[] array) {
		Random randomGenerator = new Random();
		int size = array.length;
		for (int i = 1; i < size; i++) {
			int randj = randomGenerator.nextInt(i + 1);
			exch(array, i, randj);
		}
	}

	public static void exch(Object[] array, int i, int j) {
		Object swap = array[i];
		array[i] = array[j];
		array[j] = swap;
	}
}