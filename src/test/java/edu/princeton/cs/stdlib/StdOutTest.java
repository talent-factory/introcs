package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

/**
 * Unit tests for the {@code StdOut} class.
 */
public class StdOutTest {
    
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUp() throws Exception {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStream));
        
        // Reset StdOut's static state using reflection
        resetStdOut();
    }
    
    @AfterEach
    public void tearDown() {
        // Restore original System.out
        System.setOut(originalOut);
    }
    
    /**
     * Reset StdOut's static state using reflection to allow for testing.
     */
    private void resetStdOut() throws Exception {
        Field outField = StdOut.class.getDeclaredField("out");
        outField.setAccessible(true);
        
        // Erstelle einen neuen PrintWriter für System.out
        PrintWriter newOut = new PrintWriter(new OutputStreamWriter(
                System.out, StandardCharsets.UTF_8), true);
        outField.set(null, newOut);
    }
    
    @Test
    public void testPrintln() {
        String testString = "Test output";
        StdOut.println(testString);
        
        assertEquals(testString + System.lineSeparator(), outputStream.toString());
    }
    
    @Test
    public void testPrint() {
        String testString = "Test output";
        StdOut.print(testString);
        
        assertEquals(testString, outputStream.toString());
    }
    
    @Test
    public void testPrintf() {
        String format = "Test %s: %d";
        String arg1 = "value";
        int arg2 = 42;
        
        StdOut.printf(format, arg1, arg2);
        
        assertEquals(String.format(format, arg1, arg2), outputStream.toString());
    }
    
    @Test
    public void testPrintlnWithPrimitives() {
        StdOut.println(123);
        StdOut.println(3.14);
        StdOut.println(true);
        
        String expected = "123" + System.lineSeparator() +
                         "3.14" + System.lineSeparator() +
                         "true" + System.lineSeparator();
        
        assertEquals(expected, outputStream.toString());
    }
    
    @Test
    public void testPrintWithPrimitives() {
        StdOut.print(123);
        
        outputStream.reset();
        StdOut.print(3.14);
        assertEquals("3.14", outputStream.toString());
        
        outputStream.reset();
        StdOut.print(true);
        assertEquals("true", outputStream.toString());
        
        outputStream.reset();
        StdOut.print('A');
        assertEquals("A", outputStream.toString());
        
        outputStream.reset();
        StdOut.print(42L);
        assertEquals("42", outputStream.toString());
        
        outputStream.reset();
        StdOut.print(2.5f);
        assertEquals("2.5", outputStream.toString());
        
        outputStream.reset();
        StdOut.print((byte) 8);
        assertEquals("8", outputStream.toString());
    }
    
    @Test
    public void testReset() throws Exception {
        StdOut.println("Test before reset");
        
        // Reset StdOut using reflection
        resetStdOut();
        
        // After resetting, StdOut should reinitialize when used again
        outputStream.reset();
        StdOut.println("Test after reset");
        
        assertEquals("Test after reset" + System.lineSeparator(), outputStream.toString());
    }
}
