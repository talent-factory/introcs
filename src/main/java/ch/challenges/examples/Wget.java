package ch.challenges.examples;

import edu.princeton.cs.stdlib.In;
import edu.princeton.cs.stdlib.Out;

/*
 *  Compilation:  javac-introcs ch.challenges.examples.Wget.java
 *  Execution:    java-introcs ch.challenges.examples.Wget url
 *
 *  Reads in a URL specified on the command line and saves its contents
 *  in a file with the given name.
 *
 *  % java ch.challenges.examples.Wget https://introcs.cs.princeton.edu/java/data/codes.csv
 *
 */

public class Wget {

    public static void main(String[] args) {

        // read in data from URL
        String url = args[0];
        In in = new In(url);
        String data = in.readAll();

        // write data to a file
        String filename = url.substring(url.lastIndexOf('/') + 1);
        Out out = new Out(filename);
        out.println(data);
        out.close();
    }

}
