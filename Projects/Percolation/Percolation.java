public class Percolation {
    // A class that takes as argument the size N of a grid, and creates an NxN grid (modeled in a ~N*N 1-D array),
    // with all the squares closed. It has methods to open any square and check whether
    // a square is opened, is full or the system percolates.
    
    private int size;
    private boolean[] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufnobw;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("Non-positive size of grid.");
        size = N;
        grid = new boolean[N*N + 2];
        uf = new WeightedQuickUnionUF(N*N + 2);
        ufnobw = new WeightedQuickUnionUF(N*N + 1);
        grid[0] = true;
        for (int i = 1; i < N*N + 2; i++) {
            grid[i] = false;
        }
    }
        
    private int ijtoind(int i, int j) {
        return (i - 1)*size + j;
    }
        
    public void open(int i, int j) {
        if (i <= 0 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > size) throw new IndexOutOfBoundsException("col index i out of bounds");
        grid[this.ijtoind(i,j)]=true;
        if (i < size)
            if (this.isOpen(i+1,j)) {
                uf.union(this.ijtoind(i,j), this.ijtoind(i+1, j));
                ufnobw.union(this.ijtoind(i, j), this.ijtoind(i+1, j));
                
            }
        if (i > 1)
            if (this.isOpen(i-1, j)) {
                uf.union(this.ijtoind(i,j), this.ijtoind(i-1, j));
                ufnobw.union(this.ijtoind(i,j), this.ijtoind(i-1, j));
            }
        if (j < size)
            if (this.isOpen(i, j+1)) {
                uf.union(this.ijtoind(i,j), this.ijtoind(i, j+1));
                ufnobw.union(this.ijtoind(i,j), this.ijtoind(i, j+1));
            }
        if (j > 1)
            if (this.isOpen(i, j-1)) {
                uf.union(this.ijtoind(i,j), this.ijtoind(i, j-1));
                ufnobw.union(this.ijtoind(i,j), this.ijtoind(i, j-1));
            }
        if (i == 1) {
            uf.union(this.ijtoind(i, j), 0);
            ufnobw.union(this.ijtoind(i,j), 0);
        }
        if (i == size) {
            uf.union(this.ijtoind(i, j), size*size + 1);
        }
    }
        
    public boolean isOpen(int i, int j) {
        if (i <= 0 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > size) throw new IndexOutOfBoundsException("col index i out of bounds");
        return grid[this.ijtoind(i,j)];
    }
        
    public boolean isFull(int i, int j) {
        if (i <= 0 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > size) throw new IndexOutOfBoundsException("col index i out of bounds");
        return ufnobw.connected(this.ijtoind(i,j), 0);
    }
        
    public boolean percolates() {
        return uf.connected(0, size*size + 1);
    }
}