package edu.princeton.cs.stdlib;

import org.apache.commons.lang3.StringUtils;

public final class StdIO {

    /**
     * Es macht keinen Sinn, diese Klasse zu instanziieren.
     */
    private StdIO() {
    }

    /**
     * Liefert den Wert {@code true}, falls die Zeichenkette einer gültigen
     * Zahl entspricht.
     *
     * @param str zu überprüfende Zeichenkette
     * @return {@code true}, falls die Zeichenkette eine gültige Zahl ist
     */
    public static boolean isNumeric(final String str) {
        return StringUtils.isNumeric(str);
    }


    /**
     * Einlesen eines ganzzahligen Wertes. Der Benutzer wird so lange
     * aufgefordert einen Wert einzugeben, bis die Eingabe gültig ist.
     *
     * @param prompt Eingabeaufforderung
     * @return eingelesener, ganzzahliger Wert
     */
    public static int readInt(final String prompt) {
        int result;
        while (true) {
            StdOut.print(prompt);
            try {
                result = StdIn.readInt();
                break;
            } catch (Exception ignore) {
                // Intentionally empty
            }
        }
        return result;
    }
}
