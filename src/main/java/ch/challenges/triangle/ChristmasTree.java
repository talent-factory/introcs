package ch.challenges.triangle;

import edu.princeton.cs.stdlib.StdIO;
import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;


public class ChristmasTree {

    public static void main(String[] args) {
        
        int value = StdIO.readInt("Eingabe einer Zahl: ");

        for (int row = 0; row < value; row++) {
            // Leerzeichen vor den Sternen einfügen
            for (int i = row; i < value - 1; i++)
                System.out.print(" ");

            for (int column = 0; column < row + 1; column++) {
                StdOut.print("* ");
            }
            StdOut.println();
        }
    }

}
