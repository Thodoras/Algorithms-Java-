import java.util.Iterator;

public class QueueLL<Item> implements Iterable<Item> {
	// A simple implementation of a generic queue data structure, using linked lists.

	private Node first = null, last = null;

	private class Node {
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (this.isEmpty())
			first = last;
		else 
			oldlast.next = last;
	}

	public Item dequeue() {
		Node oldfirst = first;
		Item item = oldfirst.item;
		first = oldfirst.next;
		oldfirst = null;
		if (this.isEmpty())
			last = null;
		return item;
	} 

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {

		private Node current = first;

		public boolean hasNext() { return current != null; }
		public void remove() {}
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}