package edu.princeton.cs.stdlib;

/*
 *  Compilation:  javac Out.java
 *  Execution:    java Out
 *  Dependencies: none
 *
 *  Writes data of various types to: stdout, file, or socket.
 *
 */


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;

/**
 *  This class provides methods for writing strings and numbers to
 *  various output streams, including standard output, file, and sockets.
 *  <p>
 *  For additional documentation, see
 *  <a href="https://introcs.cs.princeton.edu/31datatype">Section 3.1</a> of
 *  <i>Computer Science: An Interdisciplinary Approach</i>
 *  by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Out {
    
    /**
     * Checks if the stream is closed and throws an exception if it is.
     * @throws IllegalStateException if the stream is closed
     */
    private void checkClosed() {
        if (out == null) {
            throw new IllegalStateException("Stream is closed");
        }
    }

    // force Unicode UTF-8 encoding; otherwise it's system dependent
    private static final String CHARSET_NAME = "UTF-8";

    // assume language = English, country = US for consistency with In
    private static final Locale LOCALE = Locale.US;

    private PrintWriter out;

   /**
     * Initializes an output stream from a {@link OutputStream}.
     *
     * @param  os the {@code OutputStream}
     */
    public Out(OutputStream os) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET_NAME);
            out = new PrintWriter(osw, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

   /**
     * Initializes an output stream from standard output.
     */
    public Out() {
        this(System.out);
    }

   /**
     * Initializes an output stream from a socket.
     *
     * @param  socket the socket
     */
    public Out(Socket socket) {
        try {
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET_NAME);
            out = new PrintWriter(osw, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

   /**
     * Initializes an output stream from a file.
     *
     * @param  filename the name of the file
     */
    public Out(String filename) {
        try {
            OutputStream os = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET_NAME);
            out = new PrintWriter(osw, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

   /**
     * Closes the output stream.
     * @throws IllegalStateException if the stream is already closed
     */
    public void close() {
        if (out == null) {
            throw new IllegalStateException("Stream is already closed");
        }
        out.close();
        out = null; // Mark as closed
    }

   /**
     * Terminates the current line by printing the line-separator string.
     * @throws IllegalStateException if the stream is closed
     */
    public void println() {
        checkClosed();
        out.println();
    }

   /**
     * Prints an object to this output stream and then terminates the line.
     *
     * @param x the object to print
     * @throws IllegalStateException if the stream is closed
     */
    public void println(Object x) {
        checkClosed();
        out.println(x);
    }

   /**
     * Prints a boolean to this output stream and then terminates the line.
     *
     * @param x the boolean to print
     * @throws IllegalStateException if the stream is closed
     */
    public void println(boolean x) {
        checkClosed();
        out.println(x);
    }

   /**
     * Prints a character to this output stream and then terminates the line.
     *
     * @param x the character to print
     * @throws IllegalStateException if the stream is closed
     */
    public void println(char x) {
        checkClosed();
        out.println(x);
    }

   /**
     * Prints a double to this output stream and then terminates the line.
     *
     * @param x the double to print
     * @throws IllegalStateException if the stream is closed
     */
    public void println(double x) {
        checkClosed();
        out.println(x);
    }

   /**
     * Prints a float to this output stream and then terminates the line.
     *
     * @param x the float to print
     * @throws IllegalStateException if the stream is closed
     */
    public void println(float x) {
        checkClosed();
        out.println(x);
    }

   /**
     * Prints an integer to this output stream and then terminates the line.
     *
     * @param x the integer to print
     * @throws IllegalStateException if the stream is closed
     */
    public void println(int x) {
        checkClosed();
        out.println(x);
    }

   /**
     * Prints a long to this output stream and then terminates the line.
     *
     * @param x the long to print
     * @throws IllegalStateException if the stream is closed
     */
    public void println(long x) {
        checkClosed();
        out.println(x);
    }

   /**
     * Prints a byte to this output stream and then terminates the line.
     * <p>
     * To write binary data, see {@link BinaryOut}.
     *
     * @param x the byte to print
     * @throws IllegalStateException if the stream is closed
     */
    public void println(byte x) {
        checkClosed();
        out.println(x);
    }



   /**
     * Flushes this output stream.
     * @throws IllegalStateException if the stream is closed
     */
    public void print() {
        checkClosed();
        out.flush();
    }

   /**
     * Prints an object to this output stream and flushes this output stream.
     *
     * @param x the object to print
     * @throws IllegalStateException if the stream is closed
     */
    public void print(Object x) {
        checkClosed();
        out.print(x);
        out.flush();
    }

   /**
     * Prints a boolean to this output stream and flushes this output stream.
     *
     * @param x the boolean to print
     * @throws IllegalStateException if the stream is closed
     */
    public void print(boolean x) {
        checkClosed();
        out.print(x);
        out.flush();
    }

   /**
     * Prints a character to this output stream and flushes this output stream.
     *
     * @param x the character to print
     * @throws IllegalStateException if the stream is closed
     */
    public void print(char x) {
        checkClosed();
        out.print(x);
        out.flush();
    }

   /**
     * Prints a double to this output stream and flushes this output stream.
     *
     * @param x the double to print
     * @throws IllegalStateException if the stream is closed
     */
    public void print(double x) {
        checkClosed();
        out.print(x);
        out.flush();
    }

   /**
     * Prints a float to this output stream and flushes this output stream.
     *
     * @param x the float to print
     * @throws IllegalStateException if the stream is closed
     */
    public void print(float x) {
        checkClosed();
        out.print(x);
        out.flush();
    }

   /**
     * Prints an integer to this output stream and flushes this output stream.
     *
     * @param x the integer to print
     * @throws IllegalStateException if the stream is closed
     */
    public void print(int x) {
        checkClosed();
        out.print(x);
        out.flush();
    }

   /**
     * Prints a long integer to this output stream and flushes this output stream.
     *
     * @param x the long integer to print
     * @throws IllegalStateException if the stream is closed
     */
    public void print(long x) {
        checkClosed();
        out.print(x);
        out.flush();
    }

   /**
     * Prints a byte to this output stream and flushes this output stream.
     *
     * @param x the byte to print
     * @throws IllegalStateException if the stream is closed
     */
    public void print(byte x) {
        checkClosed();
        out.print(x);
        out.flush();
    }

   /**
     * Prints a formatted string to this output stream, using the specified format
     * string and arguments, and then flushes this output stream.
     *
     * @param format the format string
     * @param args   the arguments accompanying the format string
     * @throws IllegalStateException if the stream is closed
     */
    public void printf(String format, Object... args) {
        checkClosed();
        out.printf(LOCALE, format, args);
        out.flush();
    }

   /**
     * Prints a formatted string to this output stream, using the specified
     * locale, format string, and arguments, and then flushes this output stream.
     *
     * @param locale the locale
     * @param format the format string
     * @param args   the arguments accompanying the format string
     * @throws IllegalStateException if the stream is closed
     */
    public void printf(Locale locale, String format, Object... args) {
        checkClosed();
        out.printf(locale, format, args);
        out.flush();
    }


   /**
     * A test client.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Out out;

        // write to stdout
        out = new Out();
        out.println("Test 1");
        out.close();

        // write to a file
        out = new Out("test.txt");
        out.println("Test 2");
        out.close();
    }

}
