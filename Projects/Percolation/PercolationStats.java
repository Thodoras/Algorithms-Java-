public class PercolationStats{
    
    private int num_of_experim;
    private double[] outcomes;
    
    public PercolationStats(int N, int T){
        num_of_experim = T;
        outcomes = new double[T];
        for (int i = 0; i < T; i++){
            Percolation grid = new Percolation(N);
            while (!grid.percolates()){
                int x = StdRandom.uniform(N)+1;
                int y = StdRandom.uniform(N)+1;
                if (!grid.isOpen(x,y)){
                    grid.open(x,y);
                    outcomes[i]++;
                }
            }
            outcomes[i] /= N*N;
        }
    }
    
    public double mean() {
        return StdStats.mean(outcomes);
    }
    
    public double stddev() {
        return StdStats.stddev(outcomes);
    }
    
    public double confidenceLo(){
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(num_of_experim);
    }
    
    public double confidenceHi(){
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(num_of_experim);
    }
    
    public static void main(String[] args){
        In in = new In();
        System.out.println("Input size of grid:");
        int N = in.readInt();
        System.out.println("Input number of experiments:");
        int T = in.readInt();
        PercolationStats percstats = new PercolationStats(N, T);
        System.out.println("mean = " + percstats.mean());
        System.out.println("stddev = " + percstats.stddev());
        System.out.println("95% confidence interval = [" + percstats.confidenceLo() + ", " + percstats.confidenceHi() + "]" );
    }
}