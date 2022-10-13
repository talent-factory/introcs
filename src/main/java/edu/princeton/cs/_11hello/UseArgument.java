package edu.princeton.cs._11hello;

/*
 *  Compilation:  javac UseArgument.java
 *  Execution:    java UseArgument your name
 *
 *  Prints "Hi, Bob. How are you?" where "Bob" is replaced by the
 *  command-line argument.
 *
 *  % java UseArgument Bob
 *  Hi, Bob. How are you?
 *
 *  % java UseArgument Alice
 *  Hi, Alice. How are you?
 */

import edu.princeton.cs.stdlib.StdOut;

/**
 * Diese Klasse soll zeigen, wie die Kommandozeilen-Argumente eingelesen
 * werden können.
 */
@SuppressWarnings("CheckStyle")
public class UseArgument {

    public static void main(String[] args) {
        StdOut.print("Hi, ");
        StdOut.print(args[0]);
        StdOut.println(". How are you?");
    }

}
