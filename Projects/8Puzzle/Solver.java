import java.util.Comparator;

public class Solver {
	// A greedy algorithm for finding the fastest way to solve (if solvable) a (2^n)-puzzle,
	// while providing all the steps for the solution.

	private int moves;
	private Node solvedNode;
	private boolean solvable;

	private class Node {
		private Node previous;
		private Board board;
		private int moves;
		private int priority;
		public Comparator<Node> PRIORITY = new ByPriority();

		public Node(Node previous, Board board, int moves, int priority) {
			this.previous = previous;
			this.board = board;
			this.moves = moves;
			this.priority = priority;
		}

		private class ByPriority implements Comparator<Node> {
			public int compare(Node n, Node m) {
				return n.priority - m.priority;
			}
		}
	}

    public Solver(Board initial) {
    	if (initial == null) throw new NullPointerException("passed a null argument");
    	Board twin = initial.twin();
    	Node firstnode = new Node(null, initial, 0, initial.manhattan());
    	Node twinfirstnode = new Node(null, twin, 0, twin.manhattan());
    	MinPQ<Node> pq = new MinPQ<Node>(firstnode.PRIORITY);
    	MinPQ<Node> twinpq = new MinPQ<Node>(firstnode.PRIORITY);
    	pq.insert(firstnode);
    	twinpq.insert(twinfirstnode);
    	while (true) {

    		Node node = pq.delMin();
    		Node twinnode = twinpq.delMin();
    		if (node.board.isGoal()) {
    			solvedNode = node;
    			solvable = true;
    			moves = node.moves;
    			break;
    		}
    		if (twinnode.board.isGoal()) {
    			solvable = false;
    			moves = -1;
    			break;
    		}
    		for (Board board : node.board.neighbors()) {
    			Node neighbor = new Node(node, board, node.moves + 1, node.moves + 1 + board.manhattan());
    			Node temp = new Node(node, board, node.moves + 1, node.moves + 1 + board.manhattan());
    			boolean unique = true;
    			while (temp.previous != null) {
    				temp = temp.previous;
    				if (temp.board.equals(neighbor.board)) {
    					unique = false;
    					break;
    				}
    			}
    			if (unique) {
    				pq.insert(neighbor);
    			}
    		}

    		for (Board board : twinnode.board.neighbors()) {
    			Node twinneighbor = new Node(twinnode, board, twinnode.moves + 1, twinnode.moves + 1 + board.manhattan());
    			Node temp = new Node(twinnode, board, twinnode.moves + 1, twinnode.moves + 1 + board.manhattan());
    			boolean unique = true;
    			while (temp.previous != null) {
    				temp = temp.previous;
    				if (temp.board.equals(twinneighbor.board)) {
    					unique = false;
    					break;
    				}
    			}
    			if (unique) {
    				twinpq.insert(twinneighbor);
    			}
    		}
    	}
    }
    public boolean isSolvable() {
    	return solvable;
    }

    public int moves() {
    	return moves;
    }
    public Iterable<Board> solution() {
    	if (!isSolvable()) return null;
		Stack<Board> stack = new Stack<Board>();
		Node temp = solvedNode;
		while (temp != null) {
			stack.push(temp.board);
			temp = temp.previous;
		}
		return stack;
    }
    
    public static void main(String[] args) {

    	// create initial board from file
    	In in = new In(args[0]);
    	int N = in.readInt();
    	int[][] blocks = new int[N][N];
    	for (int i = 0; i < N; i++)
        	for (int j = 0; j < N; j++)
            	blocks[i][j] = in.readInt();
    	Board initial = new Board(blocks);

    	// solve the puzzle
    	Solver solver = new Solver(initial);

    	// print solution to standard output
    	if (!solver.isSolvable())
        	StdOut.println("No solution possible");
    	else {
        	StdOut.println("Minimum number of moves = " + solver.moves());
        	for (Board board : solver.solution())
            	StdOut.println(board);
    	}
	}
}
