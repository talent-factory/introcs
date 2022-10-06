package edu.princeton.cs.stdlib;

public final class StdIO {

    /**
     * Es macht keinen Sinn, diese Klasse zu instanziieren
     */
    private StdIO() {
    }

    /**
     * Liefert den Wert {@code true}, falls die Zeichenkette einer gültigen Zahl entspricht.
     *
     * @param str zu überprüfende Zeichenkette
     * @return {@code true}, falls die Zeichenkette einer gültigen Zahl entspricht
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * Einlesen eines ganzzahligen Wertes. Der Benutzer wird so lange aufgefordert
     * einen Wert einzugeben, bis die Eingabe gültig ist.
     *
     * @param prompt Eingabeaufforderung
     * @return eingelesener, ganzzahliger Wert
     */
    public static int readInt(String prompt) {
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
