import java.util.Iterator;

public class StackLL<Item> implements Iterable<Item>{
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

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {

		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}
		public void remove() {}
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}