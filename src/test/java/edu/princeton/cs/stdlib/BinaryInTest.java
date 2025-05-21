package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Unit tests for the {@code BinaryIn} class.
 */
public class BinaryInTest {
    
    private BinaryIn binaryIn;
    private static final String TEST_STRING = "Test data for BinaryIn";
    
    @BeforeEach
    public void setUp() {
        // Initialize with test data
        byte[] data = TEST_STRING.getBytes(StandardCharsets.UTF_8);
        binaryIn = new BinaryIn(new ByteArrayInputStream(data));
    }
    
    @Test
    public void testReadBoolean() {
        // Test reading individual bits
        // 'T' in binary is 01010100
        assertFalse(binaryIn.readBoolean()); // 0
        assertTrue(binaryIn.readBoolean());  // 1
        assertFalse(binaryIn.readBoolean()); // 0
        assertTrue(binaryIn.readBoolean());  // 1
        assertFalse(binaryIn.readBoolean()); // 0
        assertTrue(binaryIn.readBoolean());  // 1
        assertFalse(binaryIn.readBoolean()); // 0
        assertFalse(binaryIn.readBoolean()); // 0
    }
    
    @Test
    public void testReadChar() {
        // Test reading a character
        assertEquals('T', binaryIn.readChar());
    }
    
    @Test
    public void testReadString() {
        // Test reading the entire string
        String result = "";
        while (!binaryIn.isEmpty()) {
            result += binaryIn.readChar();
        }
        assertEquals(TEST_STRING, result);
    }
    
    @Test
    public void testIsEmpty() {
        // Test isEmpty method
        assertFalse(binaryIn.isEmpty());
        
        // Read all data
        while (!binaryIn.isEmpty()) {
            binaryIn.readChar();
        }
        
        assertTrue(binaryIn.isEmpty());
    }
    
    @Test
    public void testReadInt() {
        // Test reading an integer
        // Create a new BinaryIn with integer data
        int testInt = 1234567890;
        byte[] intData = new byte[4];
        intData[0] = (byte) (testInt >>> 24);
        intData[1] = (byte) (testInt >>> 16);
        intData[2] = (byte) (testInt >>> 8);
        intData[3] = (byte) testInt;
        
        BinaryIn intBinaryIn = new BinaryIn(new ByteArrayInputStream(intData));
        assertEquals(testInt, intBinaryIn.readInt());
    }
}
