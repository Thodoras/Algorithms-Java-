import java.util.Iterator;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // Data structure which enques and dequeues in random order.
    
    private Item[] array;
    private int size;
    
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Null or undefined item to insert.");
        array[size] = item;
        size++;
        if (size == array.length)
            resize(2*array.length);
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        int index = StdRandom.uniform(size);
        exch(index, --size);
        Item item = array[size];
        array[size] = null;
        if (size <= (array.length / 4) && size != 0)
            resize(array.length / 2);
        return item;
    }
    
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        int index = StdRandom.uniform(size);
        return array[index];
    }
    
    private void resize(int tolength) {
        Item[] temp = (Item[]) new Object[tolength];
        for (int i = 0; i < size; i++)
            temp[i] = array[i];
        array = temp;
    }
    
    private void exch(int i, int j) {
        Item swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }
    
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item> {
        int current;
        Item[] tempArray;
        
        public ArrayIterator() {
            current = 0;
            tempArray = (Item[]) new Object[size];
            for (int i=0; i < size; i++)
                tempArray[i] = array[i];
            StdRandom.shuffle(tempArray);
        }
        
        public boolean hasNext() { return current != tempArray.length; }
        public void remove() {throw new UnsupportedOperationException("remove method is unavailable");};
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There is no next item.");
            Item item = tempArray[current];
            current++;
            return item;
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        q.enqueue(5);
        q.enqueue(7);
        //System.out.println(q.dequeue());
        q.enqueue(17);
        q.enqueue(20);
        //System.out.println(q.sample());
        Iterator<Integer> a = q.iterator();
        Iterator<Integer> b = q.iterator();
        while (a.hasNext())
            System.out.println(a.next());
        while (b.hasNext())
            System.out.println(b.next());
        for (Integer i : q)
            System.out.println(i);
    }
}