

public class Selection {
    // Most basic sorting algorithm, always sorting in Theta(N^2) time.

    public static void sort(Comparable[] array) {
        int length = array.length;
        for (int i =0; i < length; i++) {
            int min = i;
            for (int j = i; j < length; j++) {
                if (less(array[j], array[min])) {
                    min = j;
                }
            }
        exch(array, i, min);
        } 
    }  

    private static boolean less(Comparable x, Comparable y) {
        return x.compareTo(y) < 0;
    }

    private static void exch(Comparable[] array, int i, int j) {
        Comparable swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }
}