public class StackLinkedListInts {
	// A simple implementation of a stack of integer data structure, using linked lists.

	private Node first = null;

	private class Node {
		int item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void push(int item) {
		Node oldfirst = first;
		first = new Node();
		first.next = oldfirst;
		first.item = item;
	}

	public int pop() {
		int item = first.item;
		Node oldfirst = first;
		first = first.next;
		oldfirst = null;
		return item;
	}
}