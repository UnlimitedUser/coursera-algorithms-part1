import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException("n <= 0 or trials <= 0");
        double[] acc = new double[trials];
        for (int a = 0; a < trials; a++)
        {
            Percolation p = new Percolation(n);
            int count = 0;
            while (!p.percolates())
            {
                int y = 1 + StdRandom.uniform(n);
                int x = 1 + StdRandom.uniform(n);
                if (!p.isOpen(x, y))
                {
                    p.open(x, y);
                    count++;
                }
            }
            acc[a] = (double)count / (n * n);
        }
        mean = StdStats.mean(acc);
        stddev = StdStats.stddev(acc);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(trials);
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(trials);
    }

    public double mean()                          // sample mean of percolation threshold
    { return mean; }
    public double stddev()                        // sample standard deviation of percolation threshold
    { return stddev; }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    { return confidenceLo; }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    { return confidenceHi; }

    public static void main(String[] args)        // test client (described below)
    {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi + "]");
    }

}