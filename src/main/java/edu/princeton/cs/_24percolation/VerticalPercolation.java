package edu.princeton.cs._24percolation;

import edu.princeton.cs.stdlib.StdArrayIO;
import edu.princeton.cs.stdlib.StdDraw;
import edu.princeton.cs.stdlib.StdOut;
import edu.princeton.cs.stdlib.StdRandom;

/******************************************************************************
 *  Compilation:  javac VerticalPercolation.java<br>
 *  Execution:    java VerticalPercolation &lt; input.txt <br>
 *  Dependencies: StdArrayIO.java StdOut.java  <br>
 *  Data files:   http://www.cs.princeton.edu/introcs/24percolation/test5.txt<br>
 *                http://www.cs.princeton.edu/introcs/24percolation/test8.txt<br>
 *                http://www.cs.princeton.edu/introcs/24percolation/testD.txt<br>
 *                http://www.cs.princeton.edu/introcs/24percolation/testV.txt<br>
 *                http://www.cs.princeton.edu/introcs/24percolation/testT.txt<br>
 *                http://www.cs.princeton.edu/introcs/24percolation/testF.txt<br>
 *                http://www.cs.princeton.edu/introcs/24percolation/testTiny.txt<br>
 *                https://introcs.cs.princeton.edu/24percolation/testV.txt
 *
 *  % java VerticalPercolation &lt; test5.txt
 *  <pre>
 *  5 5
 *  0 1 1 0 1 
 *  0 0 1 0 1 
 *  0 0 0 0 1 
 *  0 0 0 0 1 
 *  0 0 0 0 1 
 *  true
 *  </pre>
 *
 *  % java VerticalPercolation &lt; testD.txt
 *  <pre>
 *  8 8
 *  0 0 0 1 1 1 0 1 
 *  0 0 0 0 0 1 0 1 
 *  0 0 0 0 0 1 0 0 
 *  0 0 0 0 0 1 0 0 
 *  0 0 0 0 0 1 0 0 
 *  0 0 0 0 0 0 0 0 
 *  0 0 0 0 0 0 0 0 
 *  0 0 0 0 0 0 0 0 
 *  false
 *  </pre>
 *
 *  % java VerticalPercolation &lt; testV.txt
 *  <pre>
 *  8 8
 *  0 0 0 1 1 1 0 1 
 *  0 0 0 0 0 1 0 1 
 *  0 0 0 0 0 1 0 0 
 *  0 0 0 0 0 1 0 0 
 *  0 0 0 0 0 1 0 0 
 *  0 0 0 0 0 1 0 0 
 *  0 0 0 0 0 1 0 0 
 *  0 0 0 0 0 1 0 0 
 *  true
 *  </pre>
 *
 ******************************************************************************/

public class VerticalPercolation {

    // given an n-by-n matrix of open sites, return an n-by-n matrix
    // of sites reachable from the top via a vertical path of open sites
    public static boolean[][] flow(boolean[][] isOpen) {
        int n = isOpen.length;
        boolean[][] isFull = new boolean[n][n];

        // identify full sites in row 0
        System.arraycopy(isOpen[0], 0, isFull[0], 0, n);

        // update remaining rows
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                isFull[i][j] = isOpen[i][j] && isFull[i - 1][j];
            }
        }

        return isFull;
    }


    // does the system percolate?
    public static boolean percolates(boolean[][] isOpen) {
        int n = isOpen.length;
        boolean[][] isFull = flow(isOpen);
        for (int j = 0; j < n; j++) {
            if (isFull[n - 1][j]) return true;
        }
        return false;
    }

    // draw the n-by-n boolean matrix to standard draw
    public static void show(boolean[][] a, boolean which) {
        int n = a.length;
        StdDraw.setXscale(-1, n);
        StdDraw.setYscale(-1, n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (a[i][j] == which)
                    StdDraw.filledSquare(j, n - i - 1, 0.5);
    }

    // return a random n-by-n boolean matrix, where each entry is
    // true with probability p
    public static boolean[][] random(int n, double p) {
        boolean[][] a = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = StdRandom.bernoulli(p);
        return a;
    }

    // test client
    public static void main(String[] args) {
        boolean[][] isOpen = StdArrayIO.readBoolean2D();
        StdArrayIO.print(flow(isOpen));
        StdOut.println(percolates(isOpen));
    }

}
