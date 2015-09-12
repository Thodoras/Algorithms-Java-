public class QueueLL<Item> {
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
}