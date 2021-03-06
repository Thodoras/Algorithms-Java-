public class Subset {
    // Test client for RandomizedQueue.java;
    
    public static void main(String[] args) {
    
        RandomizedQueue<String> Q = new RandomizedQueue<String>();
    
        int N = 0;
    
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            Q.enqueue(s);
            N++;
        }
    
        int k = Integer.parseInt(args[0]);
    
        for (int i = 0; i < k && i < N; i++)
            StdOut.println(Q.dequeue());
    }
}