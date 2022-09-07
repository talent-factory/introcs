package edu.princeton.cs._12types;

/*
 *  Compilation:  javac Ruler.java
 *  Execution:    java Ruler
 *
 *  Prints the relative lengths of the subdivisions on a ruler.
 *
 *  % java Ruler
 *  1 
 *  1 2 1 
 *  1 2 1 3 1 2 1 
 *  1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 
 *  1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 5 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1
 */

import edu.princeton.cs.stdlib.StdOut;

public class Ruler {
    public static void main(String[] args) {
        String ruler1 = " 1 ";
        String ruler2 = ruler1 + "2" + ruler1;
        String ruler3 = ruler2 + "3" + ruler2;
        String ruler4 = ruler3 + "4" + ruler3;
        String ruler5 = ruler4 + "5" + ruler4;

        StdOut.println(ruler1);
        StdOut.println(ruler2);
        StdOut.println(ruler3);
        StdOut.println(ruler4);
        StdOut.println(ruler5);
    }

}
