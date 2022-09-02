package edu.princeton.cs._12types;

/*
 *  Compilation:  javac IntOps.java
 *  Execution:    java IntOps a b
 *  
 *  Illustrates the integer operations a + b, a * b, a / b, and a % b.
 *
 *  % java IntOps 1234 99
 *  1234 + 99 = 1333
 *  1234 * 99 = 122166
 *  1234 / 99 = 12
 *  1234 % 99 = 46
 *  1234 = 12 * 99 + 46
 * 
 *  % java IntOps 10 -3
 *  10 + -3 = 7
 *  10 * -3 = -30
 *  10 / -3 = -3
 *  10 % -3 = 1
 *  10 = -3 * -3 + 1
 */

import edu.princeton.cs.stdlib.StdOut;

public class IntOps {

    public static void main(String[] args) {

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        int sum  = a + b;
        int prod = a * b;
        int quot = a / b;
        int rem  = a % b;

        StdOut.println(a + " + " + b + " = " + sum);
        StdOut.println(a + " * " + b + " = " + prod);
        StdOut.println(a + " / " + b + " = " + quot);
        StdOut.println(a + " % " + b + " = " + rem);
        StdOut.println(a + " = " + quot + " * " + b + " + " + rem);
    }
}
