package edu.princeton.cs._13flow;

/*
 *  Compilation:  javac Binary.java
 *  Execution:    java Binary n
 *
 *  Prints out n in binary.
 *
 *  % java Binary 5
 *  101
 *
 *  % java Binary 106
 *  1101010
 *
 *  % java Binary 0
 *  0
 *
 *  % java Binary 16
 *  10000
 *
 *  Limitations
 *  -----------
 *  Does not handle negative integers.
 *
 *  Remarks
 *  -------
 *  We could use Integer.toBinaryString(N) instead.
 */

import edu.princeton.cs.stdlib.StdOut;

public class Binary {
    public static void main(String[] args) {

        // read in the command-line argument
        int n = Integer.parseInt(args[0]);

        // set power to the largest power of 2 that is <= n
        int power = 1;
        while (power <= n / 2) {
            power *= 2;
        }

        // check for presence of powers of 2 in n, from largest to smallest
        while (power > 0) {

            // power is not present in n 
            if (n < power) {
                StdOut.print(0);
            }

            // power is present in n, so subtract power from n
            else {
                StdOut.print(1);
                n -= power;
            }

            // next smallest power of 2
            power /= 2;
        }

        StdOut.println();

    }

}
