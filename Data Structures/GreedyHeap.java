// To be continued...

public class GreedyHeap<Key extends Comparable<Key>> {

	private Key[] array;
	private Key[] clone;
	private Key[] heap;
	private int[] indices;
	private int size;

	public GreedyHeap(Key[] array) {
		this.array = array;
		size = array.length;
		clone = (Key[]) new Comparable[size];
		heap = (Key[]) new Comparable[size];
		indices = new int[size];
		for (int i = 0; i < size; i++) {
			clone[i] = array[i];
			heap[i] = array[i];
			indices[i] = i;
		}
		heapSort();
	}

	public void print() {
		for (int i = 0; i < size; i++) {
			System.out.println(heap[i]);
		}
	}

	public void heapSort() {
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
		heap.print();
	}
}