// Heap like data structure that makes a copy of an array to be heapsorted.
// It gives the option to alterate any values in the heap in constant time.
// This is a custom data structure suitale for Dijkstra's algorithm.
// Warning: If a value is altered, probably the heap won't be heapsorted anymore.
// Therefore after the completion of the alterations heapsort is advised.

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

	public void updateKey(int pos, Key key) {
		// Update a position of the input array with a given key. It updates
		// the heap. WARNING! Heap might cease to be heapsorted. Heapsort call is advised. 

		if (heap[indices[pos]] != null) {
			heap[indices[pos]] = key;
		}
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

	public static void main(String[] args) {
		Double dist[] = {3.2, 4.5, 1.1, 7.7, 3.6};
		GreedyHeap<Double> heap = new GreedyHeap<Double>(dist);
		heap.removeMin();
		//heap.removeMin();
		heap.updateKey(2, 8.2);
		heap.print();
	}
}