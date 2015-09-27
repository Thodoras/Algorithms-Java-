public class SymbolTable<Key extends Comparable<Key>, Value> {
	// A simple dictionary class, thar inserts/deletes in O(N) time
	// and searches in O(logN) time;

	private Key[] keys;
	private Value[] values;
	private int size;

	public SymbolTable() {
		keys = (Key[]) new Comparable[1];
		values = (Value[]) new Object[1];
	}

	public Value get(Key key) {
		if (!contains(key)) throw new NullPointerException("No such key in the Symbol Table.");
		int pos = rank(key);
		return values[pos];
	}

	public void insert(Key key, Value value) {
		int pos = rank(key);
		if (contains(key)) {
			values[pos] = value;
		}
		else {
			moveLeftFrom(pos);
			keys[pos] = key;
			values[pos] = value;
			size++;
			if (size == keys.length) {
				resizeKey(2*size);
				resizeVal(2*size);
			}
		}
	}

	public void remove(Key key) {
		if (contains(key)) {
			int pos = rank(key);
			moveRightTo(pos);
			size--;
			if (size <= keys.length / 4) {
				resizeKey(2 * size);
				resizeVal(2 * size);
			}
		}
	}

	public boolean contains(Key key) {
		int low = 0;
		int high = size - 1;
		while (low <= high) {
			int mid = low + (high - low)/2;
			if (key.compareTo(keys[mid]) < 0) {
				high = mid - 1;
			}
			else if (key.compareTo(keys[mid]) > 0) {
				low = mid + 1;
			}
			else {
				return true;
			}
		}
		return false;
	}

	public int rank(Key key) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (key.compareTo(keys[mid]) < 0)
                high = mid - 1;
            else if (key.compareTo(keys[mid]) > 0)
                low = mid + 1;
            else
                return mid;
        }
        return low;
    }

	private void resizeKey(int tolength) {
        Key[] temp = (Key[]) new Comparable[tolength];
        for (int i = 0; i < size; i++)
            temp[i] = keys[i];
        keys = temp;
    }

    private void resizeVal(int tolength) {
    	Value[] temp = (Value[]) new Object[tolength];
        for (int i = 0; i < size; i++)
            temp[i] = values[i];
        values = temp;
    }

    private void moveLeftFrom(int pos) {
    	for (int i = size; i > pos; i--) {
    		keys[i] = keys[i-1];
    		values[i] = values[i-1];
    	}
    }

    private void moveRightTo(int pos) {
    	for (int i = pos; i < size - 1; i++) {
    		keys[i] = keys[i + 1];
    		values[i] = values[i + 1];
    	}
    	keys[size - 1] = null;
    	values[size - 1] = null;
    }

    public void printk() {
    	for (int i = 0; i < size; i++)
    		System.out.println(keys[i] + " " + values[i]);
    }

	private void exchKey(int i , int j) {
		Key swap = keys[i];
		keys[i] = keys[j];
		keys[j] = swap;
    }

    private void exchValue(int i, int j) {
    	Value swap = values[i];
    	values[i] = values[j];
    	values[j] = swap;
    }

	public static void main(String[] args) {
		SymbolTable<String, Integer> st = new SymbolTable<String, Integer>();
		st.insert("c", 2);
		st.insert("b", 1);
		st.insert("d", 4);
		st.printk();
		System.out.println("===============");
		st.insert("a", 3);
		st.insert("b", 3);
		st.printk();
		System.out.println("===============");
		st.remove("d");
		st.printk();
		System.out.println("===============");
		st.remove("a");
		st.printk();
		System.out.println("===============");
	}
}

