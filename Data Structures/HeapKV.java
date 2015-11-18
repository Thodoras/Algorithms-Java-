public class HeapKV<Key extends Comparable<Key>, Value> {

	private int size;
	private Key[] keys;
	private Value[] values;

	public HeapKV() {
		// Constructor.

		size = 0;
		keys = (Key[]) new Comparable[1];
		values = (Value[]) new Comparable[1];
	}

	public boolean isEmpty() {
		// Returns true iff heap is empty.

		return size == 0;
	}

	public void insert(Key key, Value value) {
		// Inserts a key in the heap.
		
		keys[size] = key;
		values[size] = value;
		size++;
		if (size == keys.length)
			resize(2*keys.length);
		swim();
	}

	public Value peek() {
		// Returns value with minimum key without removing it.

		return values[0];
	}

	public Value removeMin() {
		// Returns and removes the value of the minimum key.

		Value value = values[0];
		exch(0, size - 1);
		--size;
		keys[size] = null;
		values[size] = null;
		if (size <= keys.length/4) {
			resize(keys.length/2);
		}
		sink(0);
		return value;
	}

	public void print() {
		for (int i = 0; i < size; i++) {
			System.out.print(keys[i]);
			System.out.print(" ");
			System.out.println(values[i]);
		}
	}

	private void swim() {
		int temp = size;
		while (temp > 1) {
			if (less(keys[temp-1], keys[temp/2 - 1])) {
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
			if (less(keys[min], keys[pos - 1])) {
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
		if (less(keys[j], keys[i]))
			return j;
		else 
			return i;
	}

	private void exch(int i, int j) {
		Key kswap = keys[i];
		Value vswap = values[i]; 
		keys[i] = keys[j];
		values[i] = values[j];
		keys[j] = kswap;
		values[j] = vswap;
	}

	private boolean less(Key x, Key y) {
		return x.compareTo(y) < 0;
	}

	private void resize(int tolength) {
        Key[] ktemp = (Key[]) new Comparable[tolength];
        Value[] vtemp = (Value[]) new Comparable[tolength];
        for (int i = 0; i < size; i++) {
            ktemp[i] = keys[i];
        	vtemp[i] = values[i];
        }
        keys = ktemp;
        values = vtemp;
    }	
}