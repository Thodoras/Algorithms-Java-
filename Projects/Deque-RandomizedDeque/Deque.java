import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import java.lang.NullPointerException;

public class Deque<Item> implements Iterable<Item> {
    // Data structure which combines the properties of a stack and a queue, 
    // i.e. client chooses if he'll imput/output from the beginning or from the end.
    
    private Node first, last;
    private int size;
    
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }
    
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("Null or undefined item to insert.");
        Node oldFirst = first;
        first = new Node();
        size++;
        first.item = item;
        first.next = oldFirst;
        if (size == 1) 
            last = first;
        else
            oldFirst.previous = first; 
    }
    
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("Null or undefined item to insert,");
        Node oldLast = last;
        last = new Node();
        size++;
        last.item = item;
        last.previous = oldLast;
        if (size == 1)
            first = last;
        else
            oldLast.next = last;
    }
        
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is already empty.");
        Item item = first.item;
        size--;
        if (size == 0) {
            last = null;
            first = null;
        }
        else {
            first = first.next;
            first.previous = null;
        }
        return item;
    }
    
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is already empty.");
        Item item = last.item;
        size--;
        if (size == 0) {
            first = null;
            last = null;
        }
        else {
            last = last.previous;
            last.next = null;
        }
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        
        private Node current = first;
        
        public boolean hasNext() { return current != null; }
        public void remove() {throw new UnsupportedOperationException("remove method is unavailable");}
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There is no next item.");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.removeLast();
    }
}