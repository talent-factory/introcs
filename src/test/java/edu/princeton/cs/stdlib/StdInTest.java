package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

/**
 * Unit tests for the {@code StdIn} class.
 */
public class StdInTest {
    
    private final InputStream originalIn = System.in;
    
    @BeforeEach
    public void setUp() throws Exception {
        // Reset StdIn's static state before each test
        resetStdIn();
    }
    
    @AfterEach
    public void tearDown() {
        // Restore original System.in
        System.setIn(originalIn);
    }
    
    /**
     * Reset StdIn's static state using reflection to allow for testing.
     */
    private void resetStdIn() throws Exception {
        // StdIn hat keine initialized-Variable, sondern nur scanner
        Field scannerField = StdIn.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(null, null);
        
        // Rufe die resync-Methode auf, um den Scanner neu zu initialisieren
        Method resyncMethod = StdIn.class.getDeclaredMethod("resync");
        resyncMethod.setAccessible(true);
        resyncMethod.invoke(null);
    }
    
    /**
     * Set up a test input stream with the given string.
     */
    private void setTestInput(String data) throws Exception {
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
        
        // Reset StdIn nach dem Ändern von System.in
        resetStdIn();
    }
    
    @Test
    public void testIsEmpty() throws Exception {
        // Test with empty input
        setTestInput("");
        assertTrue(StdIn.isEmpty(), "Empty input stream should return true for isEmpty()");
        
        // Test with non-empty input
        resetStdIn();
        setTestInput("Test data");
        assertFalse(StdIn.isEmpty(), "Non-empty input stream should return false for isEmpty()");
    }
    
    @Test
    public void testReadString() throws Exception {
        String testString = "Hello";
        setTestInput(testString + " World");
        
        assertEquals(testString, StdIn.readString(), "Should read the correct string");
    }
    
    @Test
    public void testReadInt() throws Exception {
        int testInt = 42;
        setTestInput(Integer.toString(testInt));
        
        assertEquals(testInt, StdIn.readInt(), "Should read the correct integer");
    }
    
    @Test
    public void testReadDouble() throws Exception {
        double testDouble = 3.14159;
        setTestInput(Double.toString(testDouble));
        
        assertEquals(testDouble, StdIn.readDouble(), 0.00001, "Should read the correct double");
    }
    
    @Test
    public void testReadBoolean() throws Exception {
        // Test true value
        setTestInput("true");
        assertTrue(StdIn.readBoolean(), "Should read 'true' as true");
        
        // Test false value
        resetStdIn();
        setTestInput("false");
        assertFalse(StdIn.readBoolean(), "Should read 'false' as false");
    }
    
    @Test
    public void testReadLine() throws Exception {
        String testLine = "This is a test line";
        setTestInput(testLine + "\nSecond line");
        
        assertEquals(testLine, StdIn.readLine(), "Should read the first line correctly");
        assertEquals("Second line", StdIn.readLine(), "Should read the second line correctly");
    }
    
    @Test
    public void testReadAllStrings() throws Exception {
        String testData = "This is a test with multiple words";
        setTestInput(testData);
        
        String[] expected = testData.split("\\s+");
        String[] result = StdIn.readAllStrings();
        
        assertArrayEquals(expected, result, "Should read all strings correctly");
    }
    
    @Test
    public void testReadAllLines() throws Exception {
        String testData = "Line 1\nLine 2\nLine 3";
        setTestInput(testData);
        
        String[] expected = testData.split("\\n");
        String[] result = StdIn.readAllLines();
        
        assertArrayEquals(expected, result, "Should read all lines correctly");
    }
    
    @Test
    public void testReadAllInts() throws Exception {
        String testData = "1 2 3 4 5";
        setTestInput(testData);
        
        int[] expected = {1, 2, 3, 4, 5};
        int[] result = StdIn.readAllInts();
        
        assertArrayEquals(expected, result, "Should read all integers correctly");
    }
    
    @Test
    public void testReadAllDoubles() throws Exception {
        String testData = "1.1 2.2 3.3 4.4 5.5";
        setTestInput(testData);
        
        double[] expected = {1.1, 2.2, 3.3, 4.4, 5.5};
        double[] result = StdIn.readAllDoubles();
        
        assertArrayEquals(expected, result, 0.00001, "Should read all doubles correctly");
    }
    
    @Test
    public void testReadChar() throws Exception {
        String testChar = "A";
        setTestInput(testChar);
        
        assertEquals('A', StdIn.readChar(), "Should read the correct character");
    }
    
    @Test
    public void testHasNextChar() throws Exception {
        setTestInput("A");
        
        assertTrue(StdIn.hasNextChar(), "Should return true when there is a next character");
        StdIn.readChar();
        assertFalse(StdIn.hasNextChar(), "Should return false when there are no more characters");
    }
    
    @Test
    public void testExceptionHandling() throws Exception {
        // Test reading from empty input
        setTestInput("");
        
        assertThrows(NoSuchElementException.class, () -> StdIn.readInt(), 
                    "Should throw NoSuchElementException when reading int from empty input");
        
        resetStdIn();
        setTestInput("");
        assertThrows(NoSuchElementException.class, () -> StdIn.readDouble(), 
                    "Should throw NoSuchElementException when reading double from empty input");
    }
}
