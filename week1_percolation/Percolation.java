import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private int[][] map;
    private int top;
    private int bottom;
    private int open;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0) throw new java.lang.IllegalArgumentException("n <= 0");
        map = new int[n + 1][n + 1];
        uf = new WeightedQuickUnionUF((n + 2) * (n + 2));
        top = (n + 2) * (n + 2) - 1;
        bottom = 0;
        open = 0;
    }

    private void   inRange(int i, int j)
    {
        if (i < 1 || i > map.length || j < 1 || j > map.length)
            throw new java.lang.IllegalArgumentException("n not in range");
    } 

    public    void open(int row, int col)    // open site (row, col) if it is not open already
    {
        inRange(row, col);
        if (map[row][col] == 1) return ;
        map[row][col] = 1;
        open++;
        if (row + 1 < map.length && map[row + 1][col] == 1) uf.union(row * map.length + col, (row + 1) * map.length + col);
        if (col + 1 < map.length && map[row][col + 1] == 1) uf.union(row * map.length + col, row * map.length + col + 1);
        if (row - 1 > 0 && map[row - 1][col] == 1)          uf.union(row * map.length + col, (row - 1) * map.length + col);
        if (row - 1 > 0 && map[row][col - 1] == 1)          uf.union(row * map.length + col, row * map.length + col - 1);
        if (row == 1)                                       uf.union(map.length + col, top);
        else if (row == map.length - 1)                     uf.union(row * map.length + col, bottom);
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        inRange(row, col);
        return map[row][col] == 1;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        inRange(row, col);
        return map[row][col] == 1 && uf.connected(top, row * map.length + col);
    }

    public     int numberOfOpenSites()       // number of open sites
    { return open; }

    public boolean percolates()              // does the system percolate?
    { return uf.connected(top, bottom); }

    public static void main(String[] args)   // test client (optional)
    { }

}