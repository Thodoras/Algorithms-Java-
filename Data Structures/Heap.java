// Heap data structure that supports insertion (O(logN)), deletion of minimum (O(logN)).

public class Heap<Key extends Comparable<Key>> {

	private int size;
	private Key[] array;

	public Heap() {
		// Constructor for an empty heap.

		size = 0;
		array = (Key[]) new Comparable[1];
	}

	public Heap(Key[] keys) {
		// Constructor that takes an array of keys and heapsorts it.

		size = keys.length;
		int length = convSize(size);
		array = (Key[]) new Comparable[length];
		for (int i = 0; i < size; i++) {
			array[i] = keys[i];
		}
		heapsort();
	}

	public boolean isEmpty() {
		// Returns true iff heap is empty.

		return size == 0;
	}

	public void insert(Key key) {
		// Inserts a key in the heap.

		array[size++] = key;
		if (size == array.length)
			resize(2*array.length);
		swim();
	}

	public Key peek() {
		// Returns minimum key without removing it.

		return keys[0];
	}

	public Key removeMin() {
		// Returns and removes the minimum key.

		Key value = array[0];
		exch(0, size - 1);
		array[--size] = null;
		if (size <= array.length/4) {
			resize(array.length/2);
		}
		sink(0);
		return value;
	}

	public void print() {
		// Prints all the keys of the heap ordered by height.

		for (int i = 0; i < size; i++)
			System.out.println(array[i]);
	}

	private void heapsort() {
		int temp = size / 2 - 1;
		for (int i = temp; i >= 0; i--) {
			sink(i);
		}
	}

	private void swim() {
		int temp = size;
		while (temp > 1) {
			if (less(array[temp-1], array[temp/2 - 1])) {
				exch(temp-1, temp/2 - 1);
				temp /= 2;
			}
			else {
				break;
			}
		}
	}

	private void sink(int pos) {
		pos += 1;
		while (2 * pos <= size) {
			int min = minIndex(2*pos -1, 2*pos);
			if (less(array[min], array[pos - 1])) {
				exch(min, pos - 1);
				pos = min + 1;
			}
			else {
				break;
			}
		}
	}

	private void exch(int i, int j) {
		Key swap = array[i];
		array[i] = array[j];
		array[j] = swap;
	}

	private boolean less(Key x, Key y) {
		return x.compareTo(y) < 0;
	}

	private int convSize(int n) {
		return (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
	}

	private int minIndex(int i, int j) {
		if (i == size) {return j;}
		if (j == size) {return i;}
		if (less(array[j], array[i]))
			return j;
		else 
			return i;
	}

	private void resize(int tolength) {
        Key[] temp = (Key[]) new Comparable[tolength];
        for (int i = 0; i < size; i++)
            temp[i] = array[i];
        array = temp;
    }
}
