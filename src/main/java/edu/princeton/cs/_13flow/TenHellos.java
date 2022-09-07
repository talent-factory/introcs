package edu.princeton.cs._13flow;

/*
 *  Compilation:  javac TenHellos.java
 *  Execution:    java TenHellos
 * 
 *  Prints ith Hello for i = 1 to 10. Illlustrates using a while loop
 *  for a repetitive task.
 *
 *  % java TenHellos
 *  1st Hello
 *  2nd Hello 
 *  3rd Hello
 *  4th Hello
 *  5th Hello
 *  6th Hello
 *  7th Hello
 *  8th Hello
 *  9th Hello
 *  10th Hello
 *
 */

import edu.princeton.cs.stdlib.StdOut;

public class TenHellos {
    public static void main(String[] args) {

        // print out special cases whose ordinal doesn't end in th
        StdOut.println("1st Hello");
        StdOut.println("2nd Hello");
        StdOut.println("3rd Hello");

        // count from i = 4 to 10
        int i = 4;
        while (i <= 10) {
            StdOut.println(i + "th Hello");
            i = i + 1;
        }

    }
}
