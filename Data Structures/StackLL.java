public class StackLL<Item> {
	// A simple implementation of a generic stack data structure, using linked lists.

	private Node first = null;

	private class Node {
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.next = oldfirst;
		first.item = item;
	}

	public Item pop() {
		Item item = first.item;
		Node oldfirst = first;
		first = first.next;
		oldfirst = null;
		return item;
	}
}