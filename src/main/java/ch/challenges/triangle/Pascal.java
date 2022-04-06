package ch.challenges.triangle;

import com.sharkysoft.printf.Printf;
import edu.princeton.cs.stdlib.StdIO;
import edu.princeton.cs.stdlib.StdOut;

import static ch.challenges.math.Math.*;

public class Pascal {


    /**
     * Berechnet den maximalen Wert eines 2D-Arrays.
     *
     * @param array zu untersuchender Array
     * @return maximaler Wert innerhalb des Arrays
     */
    public static int maxValueOfX(int[][] array) {
        int maxValue = 0;
        for (int[] ints : array) {
            for (int value : ints) {
                if (value > maxValue)
                    maxValue = value;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {

        int value = StdIO.readInt("Eingabe einer Zahl: ");

        int[][] array = new int[value][value];

        // Array mit Daten befüllen
        for (int row = 0; row < value; row++) {
            for (int col = 0; col < row + 1; col++) {
                if (col == 0 || col == row)
                    array[row][col] = 1;
                if (col > 0 && row > 1)
                    array[row][col] = array[row - 1][col - 1] + array[row - 1][col];
            }
        }

        // Bestimmen der Breite einer Zelle bei der Ausgabe
        int digits = numberOfDigits(maxValueOf(flatten(array))) + 1;

        // Array auf der Konsole ausgeben
        for (int row = 0; row < value; row++) {
            // Leerzeichen vor den Sternen einfügen
            for (int i = row; i < value - 1; i++)
                Printf.out("%^" + (digits / 2) + "s", new Object[]{" "});
            for (int col = 0; col < row + 1; col++) {
                Printf.out("%^" + digits + "d", new Object[]{array[row][col]});
            }
            StdOut.println();
        }
    }
}
