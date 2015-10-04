import java.util.*;

public class BST <Key extends Comparable<Key>, Value> {
	// A basic class for (unbalanced) binary search trees, with recursive methods
	// that have avg. O(logN) completion time (O(N) worst case).

	private Node root;

	private class Node {
		
		private Key key;
		private Value val;
		private Node left, right;

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		} 
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node node, Key key) {
		if (node == null) return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0) return get(node.left, key);
		else if (cmp > 0) return get(node.right, key);
		else return node.val;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node node, Key key, Value val) {
		if (node == null) return new Node(key, val);
		int cmp = key.compareTo(node.key);
		if (cmp < 0) node.left = put(node.left, key, val);
		else if (cmp > 0) node.right = put(node.right, key, val);
		else node.val = val;
		return node;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node node, Key key) {
		if (node == null) return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0) node.left = delete(node.left, key);
		else if (cmp > 0) node.right = delete(node.right, key);
		else {
			if (node.right == null) return node.left;
			if (node.left == null) return node.right;
			Node temp = node;
			node = min(temp.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left;
		}
		return node;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<Key>();
		inorder(root, queue);
		return queue;
	}

	private void inorder(Node node, Queue<Key> queue) {
		if (node == null) return;
		inorder(node.left, queue);
		queue.add(node.key);
		inorder(node.right, queue);
	}

	public Node max() {
		return max(root);	
	}

	private Node max(Node node) {
		if (node == null) return null;
		if (node.right == null) return node;
		else return max(node.right);
	}

	public Node min() {
		return min(root);
	}

	private Node min(Node node) {
		if (node == null) return null;
		if (node.left == null) return node;
		else return max(node.left);
	}

	private void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node node) {
		if (node.left == null) return node.right;
		node.left = deleteMin(node.left);
		return node;
	}
}