package edu.princeton.cs.stdlib;

public final class StdIO {

    /**
     * Es macht keinen Sinn, diese Klasse zu instanziieren
     */
    private StdIO() {
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
