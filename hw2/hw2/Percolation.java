package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/** UF(int N) initialize union-find data structure with N objects (0-N-1)
 * void union(int p, int q) add connection between p and q
 * boolean connected(int p, int q) are p and q in the same component?
 * int find(int p) component identifier for p
 * int count() number of components
*/

public class Percolation {
    private int size;
    private boolean[][] arr;
    private int cnt;
    private WeightedQuickUnionUF uf; // create elements 0~N^2-1
    private int top;
    private int bottom;

    private int twoDToOneD(int r, int c) {
        return r * size + c;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        arr = new boolean[size][size];
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                arr[i][j] = false;
            }
        }
        cnt = 0;
        top = size * size;
        bottom = size * size + 1;
        uf = new WeightedQuickUnionUF(size * size + 2);
        // virtual top site connected to the top row
        for (int k = 0; k < size; k += 1) {
            uf.union(top, k);
        }
        // virtual bottom site connected to the bottom row
        for (int k = twoDToOneD(size - 1, 0); k < size * size; k += 1) {
            uf.union(bottom, k);
        }
    }

    public void open(int row, int column) {
        if (row < 0 || row > size - 1 || column < 0 || column > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, column)) {
            arr[row][column] = true;
            cnt += 1;
            // check upper
            if (row - 1 >= 0 && isOpen(row - 1, column)) {
                uf.union(twoDToOneD(row, column), twoDToOneD(row - 1, column));
            }
            // check lower
            if (row + 1 < size && isOpen(row + 1, column)) {
                uf.union(twoDToOneD(row, column), twoDToOneD(row + 1, column));
            }
            // check left
            if (column - 1 >= 0 && isOpen(row, column - 1)) {
                uf.union(twoDToOneD(row, column), twoDToOneD(row, column - 1));
            }
            // check right
            if (column + 1 < size && isOpen(row, column + 1)) {
                uf.union(twoDToOneD(row, column), twoDToOneD(row, column + 1));
            }
        }
    }

    public boolean isOpen(int row, int column) {
        if (row < 0 || row > size - 1 || column < 0 || column > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return arr[row][column];
    }

    public boolean isFull(int row, int column) {
        if (row < 0 || row > size - 1 || column < 0 || column > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, column)) {
            return uf.connected(twoDToOneD(row, column), top);
        }
        return false;
    }

    public int numberOfOpenSites() {
        return cnt;
    }

    public boolean percolates() {
        if (cnt > 0) {
            return uf.connected(top, bottom);
        }
        return false;
    }

    public static void main(String[] args) {

    }

}
