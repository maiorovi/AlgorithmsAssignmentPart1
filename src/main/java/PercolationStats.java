public class PercolationStats {

    private int times;
    private int size;
    private double[] tempResult;

    public PercolationStats(int N, int T) {
        this.times = T;
        this.size = N;
        tempResult = new double[N];

        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(size);
            int counter = 0;
            while(!percolation.percolates()) {
                int row = StdRandom.uniform(N);
                int column = StdRandom.uniform(N);
                if (!percolation.isOpen(row,column)) {
                    percolation.open(row, column);
                    counter++;
                }
            }
            tempResult[i] = (double) counter / (size * size);
        }
    }     // perform T independent experiments on an N-by-N grid


    public double mean() {
        double summary = 0;
        for(int i = 0; i < size; i++ ) {
                summary += tempResult[i];
        }

        return summary / size;
    }                     // sample mean of percolation threshold

    public double stddev() {
        double summary = 0;

        for(int i = 0; i < size; i++) {
            summary += Math.pow(tempResult[i] - mean(), 2);
        }

        return Math.sqrt(summary / (size - 1));
    }                    // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(times));
    }             // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(times));
    }             // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(200, 100);
        System.out.println(stats.mean());
        System.out.println(stats.stddev());
        System.out.println(stats.confidenceLo());
        System.out.println(stats.confidenceHi());

    }
}