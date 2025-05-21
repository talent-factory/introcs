package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Unit tests for the {@code BinaryOut} class.
 */
public class BinaryOutTest {
    
    private BinaryOut binaryOut;
    private ByteArrayOutputStream outputStream;
    
    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        binaryOut = new BinaryOut(outputStream);
    }
    
    @AfterEach
    public void tearDown() throws IOException {
        // No need to close binaryOut here as it's already closed in the test methods
    }
    
    @Test
    public void testWriteBoolean() {
        // Test writing individual bits
        binaryOut.write(true);
        binaryOut.write(false);
        binaryOut.write(true);
        binaryOut.write(false);
        binaryOut.flush();
        
        // 1010 in binary is 0xA0 (padded with 4 zeros to make a full byte)
        byte[] expected = {(byte) 0xA0};
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteChar() {
        // Test writing a character
        char testChar = 'A';
        binaryOut.write(testChar);
        binaryOut.flush();
        
        byte[] expected = {(byte) testChar};
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteString() {
        // Test writing a string
        String testString = "Test";
        binaryOut.write(testString);
        binaryOut.flush();
        
        byte[] expected = testString.getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteInt() {
        // Test writing an integer
        int testInt = 1234567890;
        binaryOut.write(testInt);
        binaryOut.flush();
        
        byte[] expected = new byte[4];
        expected[0] = (byte) (testInt >>> 24);
        expected[1] = (byte) (testInt >>> 16);
        expected[2] = (byte) (testInt >>> 8);
        expected[3] = (byte) testInt;
        
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testFlush() {
        // Test that flush writes buffered data
        binaryOut.write(true);
        binaryOut.flush();
        
        assertEquals(1, outputStream.toByteArray().length);
    }
    
    @Test
    public void testClose() {
        // Test that close flushes and closes the stream
        binaryOut.write(true);
        binaryOut.close();
        
        assertEquals(1, outputStream.toByteArray().length);
        assertThrows(IllegalStateException.class, () -> binaryOut.write(true));
    }
}
