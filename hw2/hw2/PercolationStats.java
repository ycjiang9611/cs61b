package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] sample;
    private int size;
    private int times;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        times = T;
        sample = new double[T];
        int i = 0;
        while (i < T) {
            Percolation pc = pf.make(size);
            while (!pc.percolates()) {
                pc.open(StdRandom.uniform(size), StdRandom.uniform(size));
            }
            sample[i] = pc.numberOfOpenSites()*1.0/(size*size);
            i += 1;
        }
    }

    public double mean() {
        return StdStats.mean(sample);
    }

    public double stddev() {
        return StdStats.stddev(sample);
    }

    public double confidenceLow() {
        return mean()-1.96*stddev()/Math.sqrt(times);
    }

    public double confidenceHigh() {
        return mean()+1.96*stddev()/Math.sqrt(times);
    }

}
