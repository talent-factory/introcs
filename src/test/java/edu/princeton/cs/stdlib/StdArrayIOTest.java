package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.BufferedInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.Locale;

/**
 * Unit tests for the {@code StdArrayIO} class.
 */
public class StdArrayIOTest {
    
    private static final double DELTA = 1e-10;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private InputStream originalIn = System.in;
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    private void setStdIn(String input) {
        try {
            // Ensure consistent line endings and encoding
            String normalizedInput = input.replace("\n", System.lineSeparator());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(normalizedInput.getBytes("UTF-8"));
            
            // Set System.in to our test input
            System.setIn(inputStream);
            
            // Create a new Scanner with the test input
            Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
            scanner.useLocale(Locale.US);
            
            // Use reflection to set the scanner in StdIn
            setScannerUsingReflection(scanner);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set up test input", e);
        }
    }
    
    private void setScannerUsingReflection(Scanner scanner) {
        try {
            // Get the scanner field from StdIn class
            Field scannerField = StdIn.class.getDeclaredField("scanner");
            
            // Make the field accessible
            scannerField.setAccessible(true);
            
            // Set the scanner field value
            scannerField.set(null, scanner);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set scanner using reflection", e);
        }
    }
    
    @Test
    public void testReadAndWriteInt1D() {
        // Set up input with proper format (first number is length, then values)
        String input = "5 1 2 3 4 5\n";
        setStdIn(input);
        
        // Read array from input
        int[] readArray = StdArrayIO.readInt1D();
        
        // Verify the array
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, readArray);
    }
    
    @Test
    public void testReadAndWriteDouble1D() {
        // Set up input with proper format (first number is length, then values)
        String input = "5 1.1 2.2 3.3 4.4 5.5\n";
        setStdIn(input);
        
        // Read array from input
        double[] readArray = StdArrayIO.readDouble1D();
        
        // Verify the array
        assertArrayEquals(new double[]{1.1, 2.2, 3.3, 4.4, 5.5}, readArray, DELTA);
    }
    
    @Test
    public void testReadAndWriteInt2D() {
        // Set up input with proper format (first two numbers are rows and columns, then values)
        String input = "3 3 1 2 3 4 5 6 7 8 9\n";
        setStdIn(input);
        
        // Read array from input
        int[][] readArray = StdArrayIO.readInt2D();
        
        // Verify the array
        assertEquals(3, readArray.length);
        assertArrayEquals(new int[]{1, 2, 3}, readArray[0]);
        assertArrayEquals(new int[]{4, 5, 6}, readArray[1]);
        assertArrayEquals(new int[]{7, 8, 9}, readArray[2]);
    }
    
    @Test
    public void testReadAndWriteDouble2D() {
        // Set up input with proper format (first two numbers are rows and columns, then values)
        String input = "3 3 1.1 2.2 3.3 4.4 5.5 6.6 7.7 8.8 9.9\n";
        setStdIn(input);
        
        // Read array from input
        double[][] readArray = StdArrayIO.readDouble2D();
        
        // Verify the array
        assertEquals(3, readArray.length);
        assertArrayEquals(new double[]{1.1, 2.2, 3.3}, readArray[0], DELTA);
        assertArrayEquals(new double[]{4.4, 5.5, 6.6}, readArray[1], DELTA);
        assertArrayEquals(new double[]{7.7, 8.8, 9.9}, readArray[2], DELTA);
    }
    
    @Test
    public void testPrint1D() {
        int[] array = {1, 2, 3, 4, 5};
        
        // This test just verifies that the method doesn't throw an exception
        StdArrayIO.print(array);
        assertTrue(true);
    }
    
    @Test
    public void testPrint2D() {
        int[][] array = {
            {1, 2, 3},
            {4, 5, 6}
        };
        
        // This test just verifies that the method doesn't throw an exception
        StdArrayIO.print(array);
        assertTrue(true);
    }
    
    @Test
    public void testReadInt1DEmpty() {
        // Set up empty array input
        setStdIn("0\n");
        
        int[] array = StdArrayIO.readInt1D();
        assertEquals(0, array.length);
    }
    
    @Test
    public void testReadDouble1DEmpty() {
        // Set up empty array input
        setStdIn("0\n");
        
        double[] array = StdArrayIO.readDouble1D();
        assertEquals(0, array.length);
    }
    
    @Test
    public void testReadInt2DEmpty() {
        // Set up empty 2D array input
        setStdIn("0 0\n");
        
        int[][] array = StdArrayIO.readInt2D();
        assertEquals(0, array.length);
    }
    
    @Test
    public void testReadDouble2DEmpty() {
        // Set up empty 2D array input
        setStdIn("0 0\n");
        
        double[][] array = StdArrayIO.readDouble2D();
        assertEquals(0, array.length);
    }
}
