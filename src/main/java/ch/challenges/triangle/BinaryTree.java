package ch.challenges.triangle;

import edu.princeton.cs.stdlib.StdIO;
import edu.princeton.cs.stdlib.StdOut;

/**
 * <b>Aufgabe:</b>
 * <p>Der Benutzer wird aufgeforder eine Zahl einzugeben. Im Anschluss
 * wird ein Baum, bestehend aus alternierend "1" und "0" dergestellt:</p>
 *
 * <p>$ java BinaryTree [ &lt;number&gt; ] </p>
 */
public class BinaryTree {

    /**
     * Darstellen eines Baumes bestehend aus den Zahlen "1" und "0" (alternierend).
     *
     * @param args Anzahl Zeilen des Baumes. Falls dieser Wert beim Start des Programmes
     *             nicht angegeben wird, dann wird der Benutzer aufgeforder einen
     *             Wert einzugeben.
     */
    public static void main(String[] args) {

        int value;
        try {
            value = Integer.parseInt(args[0]);
        } catch (Exception e) {
            value = StdIO.readInt("Eingabe einer Zahl: ");
        }

        boolean eins = false;

        for (int row = 0; row < value; row++) {
            // Leerzeichen vor den Sternen einfügen
            for (int i = row; i < value - 1; i++)
                System.out.print(" ");

            for (int column = 0; column < row + 1; column++) {
                //noinspection AssignmentUsedAsCondition
                StdOut.print((eins = !eins) ? "1 " : "0 ");
            }
            StdOut.println();
        }
    }

}
