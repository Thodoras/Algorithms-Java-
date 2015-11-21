// Heap like data structure that makes a copy of an array to be heapsorted.
// It gives the option to alterate any values in the heap in constant time.
// This is a custom data structure suitale for Dijkstra's algorithm.

public class GreedyHeap<Key extends Comparable<Key>> {

	private Key[] heap;
	private int[] indices;
	private int size;
	private int firstSize;

	public GreedyHeap(Key[] array) {
		// Constructor, takes an array of keys as an argument.

		size = array.length;
		firstSize = size;
		heap = (Key[]) new Comparable[size];
		indices = new int[size];
		for (int i = 0; i < size; i++) {
			heap[i] = array[i];
			indices[i] = i;
		}
		heapSort();
	}

	public boolean isEmpty() {
		// Return true iff all elements of heap are of null value.

		return size == 0;
	}

	public void updateKey(int pos, Key key) {
		// Update a position of the input array with a given key. It updates
		// the heap.

		if (heap[indices[pos]] != null) {
			heap[indices[pos]] = key;
		}
		swim(indices[pos]);
		sink(indices[pos]);
	}

	public Key getKey(int pos) {
		// Return the key in the specific pos(ition).

		return heap[indices[pos]];
	}

	public int removeMin() {
		// Deletes and returns the index of the key with the minimum value.

		size--;
		int index = indices[0];
		exch(0, size);
		heap[size] = null;
		sink(0);
		return index;
	}

	public void print() {
		// Prints the heap in the console in height order.

		for (int i = 0; i < firstSize; i++) {
			System.out.println(heap[i]);
		}
	}

	public void heapSort() {
		// Heapsorts the heap.

		int temp = size / 2 - 1;
		for (int i = temp; i >= 0; i--) {
			sink(i);
		}
	}

	private void sink(int pos) {
		pos += 1;
		while (2 * pos <= size) {
			int min = minIndex(2*pos -1, 2*pos);
			if (less(heap[min], heap[pos - 1])) {
				exch(min, pos - 1);
				pos = min + 1;
			}
			else {
				break;
			}
		}
	}

	private void swim(int pos) {
		int temp = pos + 1;
		while (temp > 1) {
			if (less(heap[temp-1], heap[temp/2 - 1])) {
				exch(temp-1, temp/2 - 1);
				temp /= 2;
			}
			else {
				break;
			}
		}
	}

	private int minIndex(int i, int j) {
		if (i == size) {return j;}
		if (j == size) {return i;}
		if (less(heap[j], heap[i]))
			return j;
		else 
			return i;
	}

	private boolean less(Key x, Key y) {
		return x.compareTo(y) < 0;
	}

	private void exch(int i, int j) {
		Key swap = heap[i];
		heap[i] = heap[j];
		heap[j] = swap;
		int swap2 = indices[i];
		indices[i] = indices[j];
		indices[j] = swap2;
	}
}
