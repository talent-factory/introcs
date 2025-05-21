package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

/**
 * Unit tests for the {@code In} class.
 */
public class InTest {
    
    private In in;
    private static final String TEST_STRING = "123\nTest line\n3.14\ntrue\nend";
    private InputStream originalIn;
    
    @BeforeEach
    public void setUp() {
        // Save the original System.in
        originalIn = System.in;
        // Set up test input
        setTestInput(TEST_STRING);
    }
    
    @AfterEach
    public void tearDown() {
        // Restore System.in
        System.setIn(originalIn);
    }
    
    private void setTestInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        in = new In();
    }
    
    @Test
    public void testHasNextLine() {
        assertTrue(in.hasNextLine());
        
        // Read all lines
        while (in.hasNextLine()) {
            in.readLine();
        }
        
        assertFalse(in.hasNextLine());
    }
    
    @Test
    public void testReadLine() {
        assertEquals("123", in.readLine());
        assertEquals("Test line", in.readLine());
        assertEquals("3.14", in.readLine());
        assertEquals("true", in.readLine());
        assertEquals("end", in.readLine());
        // The In.readLine() method returns null when there are no more lines
        // instead of throwing a NoSuchElementException
        assertNull(in.readLine());
    }
    
    @Test
    public void testReadString() {
        setTestInput("test string 123");
        String result = in.readString();
        assertEquals("test", result);
        
        result = in.readString();
        assertEquals("string", result);
        
        result = in.readString();
        assertEquals("123", result);
        
        assertThrows(NoSuchElementException.class, in::readString);
    }
    
    @Test
    public void testReadInt() {
        assertEquals(123, in.readInt());
        // Skip the rest of the line
        in.readLine();
    }
    
    @Test
    public void testReadDouble() {
        // Skip first two lines
        in.readLine();
        in.readLine();
        
        assertEquals(3.14, in.readDouble(), 0.0001);
    }
    
    @Test
    public void testReadBoolean() {
        // Skip first three lines
        in.readLine();
        in.readLine();
        in.readLine();
        
        assertTrue(in.readBoolean());
    }
    
    @Test
    public void testReadAllStrings() {
        String[] expected = {"123", "Test", "line", "3.14", "true", "end"};
        String[] result = in.readAllStrings();
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testReadAllLines() {
        String[] expected = {"123", "Test line", "3.14", "true", "end"};
        String[] result = in.readAllLines();
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testReadAll() {
        String expected = "123\nTest line\n3.14\ntrue\nend";
        assertEquals(expected, in.readAll());
    }
    
    @Test
    public void testIsEmpty() {
        assertFalse(in.isEmpty());
        
        // Read all data
        in.readAll();
        
        assertTrue(in.isEmpty());
    }
}
