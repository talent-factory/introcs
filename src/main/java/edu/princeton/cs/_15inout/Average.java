package edu.princeton.cs._15inout;

import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;

/******************************************************************************
 *  Compilation:  javac Average.java
 *  Execution:    java Average &lt; data.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Reads in a sequence of real numbers, and computes their average.
 *
 *  % java Average
 *  10.0 5.0 6.0
 *  3.0 7.0 32.0
 *  &lt;Ctrl-d&gt;
 *  Average is 10.5
 *
 *  Note &lt;Ctrl-d&gt; signifies the end of file on Unix.
 *  On windows use &lt;Ctrl-z&gt;.
 *
 ******************************************************************************/

public class Average {
    public static void main(String[] args) {
        int count = 0;       // number input values
        double sum = 0.0;    // sum of input values

        // read data and compute statistics
        while (!StdIn.isEmpty()) {
            double value = StdIn.readDouble();
            sum += value;
            count++;
        }

        // compute the average
        double average = sum / count;

        // print results
        StdOut.println("Average is " + average);
    }
}
