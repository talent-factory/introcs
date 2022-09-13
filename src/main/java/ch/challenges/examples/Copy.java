package ch.challenges.examples;

import edu.princeton.cs.stdlib.BinaryStdIn;
import edu.princeton.cs.stdlib.BinaryStdOut;

/*
 *  Compilation:  javac ch.challenges.examples.Copy.java
 *  Execution:    java ch.challenges.examples.Copy < file
 *  Dependencies: BinaryStdIn.java BinaryStdOut.java
 *
 *  Reads in a binary file from standard input and writes it to standard output.
 *
 *  % java ch.challenges.examples.Copy < mandrill.jpg > copy.jpg
 *
 *  % diff mandrill.jpg copy.jpg
 *
 */

public class Copy {

    public static void main(String[] args) {
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
    }
}
