public class Board {
	// Class to simulate a board of a (2^n)-puzzle. Among other things it provides methods to compute
	// the "distance" between a present board and a solved board.

	private int dim;
	private int[] array;

	public Board(int[][] blocks) {
		dim = blocks[0].length;
		array = new int[dim * dim];
		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim; j++)
				array[i*dim + j] = blocks[i][j];
	}

	public int dimension() {
		return dim;
	}

	public int hamming() {
		int count = 0;
		for (int i = 0; i < dim * dim; i++)
			if (array[i] != 0 && manhattan_dist(i) != 0)
				count++;
		return count;
	}

	public int manhattan() {
		int count = 0;
		for (int i = 0; i < dim * dim; i++)
			if (array[i] != 0)
				count += manhattan_dist(i);
		return count;
	}

	public boolean isGoal() {
		return hamming() == 0;
	}

	public Board twin() {
		int[] clone = cloneArray(array);
		loops:
		for (int i = 0; i < dim; i++) 
			for (int j = 0; j < dim - 1; j++)
				if (clone[i*dim + j] != 0 && clone[i*dim + j + 1] != 0) {
					exch(clone, i*dim + j, i*dim + j+1);
					break loops;
				}
		int[][] new_array = arrayTo2D(clone);
		return new Board(new_array);
	}

	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		Board that = (Board) y;
		if (that.dimension() != this.dimension()) return false;
		for (int i = 0; i < this.dimension() * this.dimension(); i++)
			if (this.array[i] != that.array[i])
				return false;
		return true;
	}

	public Iterable<Board> neighbors() {
		int pos = indexOf(array, 0);
		int i = pos / dim;
		int j = pos % dim;
		Stack<Board> stack = new Stack<Board>();
		if (i > 0) {
			int[] clone = cloneArray(array);
			exch(clone, (i - 1)*dim + j, i*dim + j);
			stack.push(new Board(arrayTo2D(clone)));
		}
		if (i + 1 < dim) {
			int[] clone = cloneArray(array);
			exch(clone, (i + 1)*dim + j, i*dim + j);
			stack.push(new Board(arrayTo2D(clone)));
		}
		if (j > 0) {
			int[] clone = cloneArray(array);
			exch(clone, i*dim + j - 1, i*dim + j);
			stack.push(new Board(arrayTo2D(clone)));
		}
		if (j + 1 < dim) {
			int[] clone = cloneArray(array);
			exch(clone, i*dim + j + 1, i*dim + j);
			stack.push(new Board(arrayTo2D(clone)));
		}
		return stack;
	}

	public String toString() {
		String text = "";
		text += dim + "\n";
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (array[i*dim + j] < 10)
					text += " ";
				text += array[i*dim + j] + " ";
			}
			text += "\n";
		}
		return text;
	}

	private int manhattan_dist(int pos) {
		int ipos = pos / dim;
		int jpos = pos % dim;
		int val = (array[pos] - 1 + dim*dim) % (dim*dim);
		int igoal = val / dim;
		int jgoal = val % dim;
		return Math.abs(ipos - igoal) + Math.abs(jpos - jgoal);
	}

	private int[] cloneArray(int[] array) {
		int len = array.length;
		int[] clone = new int[len];
		for (int i = 0; i < len; i++)
			clone[i] = array[i];
		return clone;
	}

	private int indexOf(int[] array, int val) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == val)
				return i;
		return -1;
	}

	private int[][] arrayTo2D(int[] array) {
		int[][] new_array = new int[dim][dim];
		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim; j++)
				new_array[i][j] = array[i*dim + j];
		return new_array;
	}

	private void exch(int[] array, int i, int j) {
		int swap = array[i];
		array[i] = array[j];
		array[j] = swap;
	}

	public static void main(String[] args) {
		int[][] board = new int[3][3];
		board[0][0] = 1;
		board[0][1] = 2;
		board[0][2] = 3;
		board[1][0] = 4;
		board[1][1] = 0;
		board[1][2] = 5;
		board[2][0] = 7;
		board[2][1] = 8;
		board[2][2] = 6;
		int[][] board2 = new int[3][3];
		Board b = new Board(board);
		Board c = b.twin();
		Board d = c.twin();
		System.out.println(b.toString());
		//for (int i = 0; i < 9; i++)
		//	System.out.println(b.manhattan_dist(i));
		//System.out.println(c.equals(b));
		//System.out.println(d.toString());
		//for (Board brd : b.neighbors())
		//	System.out.println(brd.toString());
	}
}