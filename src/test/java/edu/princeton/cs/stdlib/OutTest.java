package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit tests for the {@code Out} class.
 */
public class OutTest {
    
    private Out out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStream));
        out = new Out();
    }
    
    @AfterEach
    public void tearDown() {
        // Restore original System.out
        System.setOut(originalOut);
    }
    
    @Test
    public void testPrintln() {
        String testString = "Test output";
        out.println(testString);
        
        assertEquals(testString + System.lineSeparator(), outputStream.toString());
    }
    
    @Test
    public void testPrint() {
        String testString = "Test output";
        out.print(testString);
        
        assertEquals(testString, outputStream.toString());
    }
    
    @Test
    public void testPrintf() {
        String format = "Test %s: %d";
        String arg1 = "value";
        int arg2 = 42;
        
        out.printf(format, arg1, arg2);
        
        assertEquals(String.format(format, arg1, arg2), outputStream.toString());
    }
    
    @Test
    public void testPrintlnWithPrimitives() {
        out.println(123);
        out.println(3.14);
        out.println(true);
        
        String expected = "123" + System.lineSeparator() +
                         "3.14" + System.lineSeparator() +
                         "true" + System.lineSeparator();
        
        assertEquals(expected, outputStream.toString());
    }
    
    @Test
    public void testPrintlnWithObjects() {
        Object obj = new Object() {
            @Override
            public String toString() {
                return "Test Object";
            }
        };
        
        out.println(obj);
        
        assertEquals("Test Object" + System.lineSeparator(), outputStream.toString());
    }
    
    @Test
    public void testClose() {
        out.println("Before close");
        out.close();
        
        // After close, further operations should throw an exception
        assertThrows(IllegalStateException.class, () -> out.println("After close"));
    }

}
