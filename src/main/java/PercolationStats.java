public class PercolationStats {

    private int times;
    private int size;
    private double[] tempResult;

    public PercolationStats(int N, int T) {
        if (T <= 0 || N <= 0)
            throw new IllegalArgumentException();

        this.times = T;
        this.size = N;
        tempResult = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(size);
            int counter = 0;
            while(!percolation.percolates()) {
                int row = StdRandom.uniform(N) + 1;
                int column = StdRandom.uniform(N) + 1;
                if (!percolation.isOpen(row,column)) {
                    percolation.open(row, column);
                    counter++;
                }
            }
            tempResult[i] = (double) counter / (size * size);
        }
    }


    public double mean() {
        double summary = 0;
        for(int i = 0; i < times; i++ ) {
                summary += tempResult[i];
        }

        return summary / times;
    }

    public double stddev() {
        double summary = 0;

        for(int i = 0; i < times; i++) {
            summary += Math.pow(tempResult[i] - mean(), 2);
        }

        return Math.sqrt(summary / (times - 1));
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(times));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(times));
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new Exception();
        }
        int times = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(times, size);
        System.out.println(stats.mean());
        System.out.println(stats.stddev());
        System.out.println(stats.confidenceLo());
        System.out.println(stats.confidenceHi());

    }
}