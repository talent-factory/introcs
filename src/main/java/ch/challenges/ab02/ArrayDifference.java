package ch.challenges.ab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dein Ziel ist eine Funktion zu implementieren, welche eine Liste von einer
 * anderen Liste subtrahiert und das Resultat zurückliefert. Die Funktion muss
 * alle Werte von Liste A entfernen, welche in Liste B vorhanden sind.
 *
 * <p><b>Beispiel</b><br>
 * Eingabe: A = [1,2,2,2,3], B = [2]<br>
 * Ausgabe: [1,3]
 * </p>
 */
public class ArrayDifference {

    /**
     * Liefert die Differenz der beiden Arrays. Alle Werte des Arrays
     * second werden aus dem Array first entfernt.
     *
     * @param first erster Array, aus welchem die Werte des zweiten Array entfernt werden sollen
     * @param second zui entfernende Elemente des ersten Arrays
     * @return Resultat der Subtraktion der beiden Arrays
     */
    private static List<Integer> subtractArray(List<Integer> first, List<Integer> second) {
        // Bereitstellen einer leeren Liste
        List<Integer> result = new ArrayList<>();

        for (int value : first) {
           if (!second.contains(value))
               result.add(value);
        }

        return result;
    }

    public static void main(String[] args) {

        List<Integer> A = List.of(1, 2, 2, 2, 3);
        List<Integer> B = List.of(2);

        List<Integer> C = subtractArray(A, B);

        System.out.println(C);
    }

}
