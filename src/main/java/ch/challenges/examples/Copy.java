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
 */

public final class Copy {

    /**
     * Utility-Klassen werden nicht instanziiert.
     */
    private Copy() {
    }

    /**
     * Diese {@code main()} Methode liest binäre Daten vom STDIN und kopiert
     * sie auf den STDOUT.
     *
     * @param args Wird nicht verwendet
     */
    public static void main(final String[] args) {
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
    }
}
