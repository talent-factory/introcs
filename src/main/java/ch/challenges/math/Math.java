package ch.challenges.math;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Math {

    /**
     * Erstellt aus einem 2D-Array einen 1D-Array.
     *
     * @param array zu konvertierender 2D-Array
     * @return 1D-Array
     */
    public static int[] flatten(final int[][] array) {
       return Stream.of(array)
          .flatMapToInt(IntStream::of)
          .toArray();
    }

    /**
     * Liefert den grössten der des Arrays {@code array}.
     *
     * @param array zu untersuchender Array
     * @return grösster Wert in der Liste
     */
    public static int maxValueOf(final int[] array) {
        Arrays.sort(array);
        return array[array.length - 1];
    }

    /**
     * Berechnet die Anzahl Ziffern der Zahl {@code numbers}.
     *
     * @param number zu berechnende Zahl
     * @return Anzahl Ziffern
     */
    public static int numberOfDigits(int number) {
        int digits = 0;
        while (number > 0) {
            number /= 10;
            digits++;
        }
        return digits;
    }

}
