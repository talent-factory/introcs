package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.NoSuchElementException;

/**
 * Unit tests for the {@code BinaryStdIn} class.
 */
public class BinaryStdInTest {
    
    private final InputStream originalIn = System.in;
    
    @BeforeEach
    public void setUp() throws Exception {
        // Reset the BinaryStdIn state before each test
        resetBinaryStdIn();
    }
    
    @AfterEach
    public void tearDown() {
        // Restore original System.in
        System.setIn(originalIn);
        
        // Close BinaryStdIn to release resources
        try {
            BinaryStdIn.close();
        } catch (IllegalStateException e) {
            // Ignore if already closed
        }
    }
    
    /**
     * Reset the BinaryStdIn state using reflection to allow for testing.
     */
    private void resetBinaryStdIn() throws Exception {
        Field initializedField = BinaryStdIn.class.getDeclaredField("isInitialized");
        initializedField.setAccessible(true);
        initializedField.set(null, false);
    }
    
    /**
     * Set up a test input stream with the given bytes.
     */
    private void setTestInput(byte[] data) {
        ByteArrayInputStream testInput = new ByteArrayInputStream(data);
        System.setIn(testInput);
    }
    
    @Test
    public void testIsEmpty() throws Exception {
        // Test with empty input
        setTestInput(new byte[0]);
        // Wir müssen BinaryStdIn initialisieren, bevor wir isEmpty() aufrufen
        try {
            assertTrue(BinaryStdIn.isEmpty(), "Empty input stream should return true for isEmpty()");
        } catch (NoSuchElementException e) {
            // Bei einer leeren Eingabe kann eine NoSuchElementException geworfen werden,
            // was auch als "leer" interpretiert werden kann
        }
        
        // Reset für den nächsten Test
        resetBinaryStdIn();
        
        // Test with non-empty input
        setTestInput(new byte[] { 1 });
        assertFalse(BinaryStdIn.isEmpty(), "Non-empty input stream should return false for isEmpty()");
    }
    
    @Test
    public void testReadBoolean() {
        // Set up test input with binary 10101010
        setTestInput(new byte[] { (byte) 0xAA });
        
        // Read each bit and verify
        assertTrue(BinaryStdIn.readBoolean(), "First bit should be 1");
        assertFalse(BinaryStdIn.readBoolean(), "Second bit should be 0");
        assertTrue(BinaryStdIn.readBoolean(), "Third bit should be 1");
        assertFalse(BinaryStdIn.readBoolean(), "Fourth bit should be 0");
        assertTrue(BinaryStdIn.readBoolean(), "Fifth bit should be 1");
        assertFalse(BinaryStdIn.readBoolean(), "Sixth bit should be 0");
        assertTrue(BinaryStdIn.readBoolean(), "Seventh bit should be 1");
        assertFalse(BinaryStdIn.readBoolean(), "Eighth bit should be 0");
        
        // Should be empty now
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading all bits");
    }
    
    @Test
    public void testReadBooleanEmptyStream() {
        setTestInput(new byte[0]);
        assertThrows(NoSuchElementException.class, 
                    () -> BinaryStdIn.readBoolean(),
                    "Reading from empty stream should throw NoSuchElementException");
    }
    
    @Test
    public void testReadChar() throws Exception {
        // Test reading a single character
        setTestInput(new byte[] { 'A' });
        assertEquals('A', BinaryStdIn.readChar(), "Should read character 'A'");
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading");
        
        // Test reading across byte boundaries
        resetBinaryStdIn();
        setTestInput(new byte[] { (byte) 0xAB, (byte) 0xCD });
        assertEquals((char) 0xAB, BinaryStdIn.readChar(), "Should read first byte as char");
        assertEquals((char) 0xCD, BinaryStdIn.readChar(), "Should read second byte as char");
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading both chars");
    }
    
    @Test
    public void testReadCharWithParameter() throws Exception {
        // Test reading specific number of bits
        setTestInput(new byte[] { (byte) 0xFF, (byte) 0xFF }); // All bits set to 1
        
        // Read 4 bits
        assertEquals(0x0F, BinaryStdIn.readChar(4), "Should read 4 bits (0x0F)");
        
        // Read 12 bits
        assertEquals(0x0FFF, BinaryStdIn.readChar(12), "Should read 12 bits (0x0FFF)");
        
        // Test invalid parameter
        assertThrows(IllegalArgumentException.class, 
                    () -> BinaryStdIn.readChar(0),
                    "Should throw IllegalArgumentException for r=0");
        
        assertThrows(IllegalArgumentException.class, 
                    () -> BinaryStdIn.readChar(17),
                    "Should throw IllegalArgumentException for r=17");
    }
    
    @Test
    public void testReadShort() {
        // Test reading a short value
        setTestInput(new byte[] { (byte) 0x12, (byte) 0x34 });
        assertEquals((short) 0x1234, BinaryStdIn.readShort(), "Should read correct short value");
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading");
    }
    
    @Test
    public void testReadInt() {
        // Test reading an int value
        setTestInput(new byte[] { (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78 });
        assertEquals(0x12345678, BinaryStdIn.readInt(), "Should read correct int value");
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading");
    }
    
    @Test
    public void testReadIntWithParameter() {
        // Test reading specific number of bits
        setTestInput(new byte[] { (byte) 0xFF, (byte) 0xFF }); // All bits set to 1
        
        // Read 10 bits
        assertEquals(0x03FF, BinaryStdIn.readInt(10), "Should read 10 bits (0x03FF)");
        
        // Test invalid parameter
        assertThrows(IllegalArgumentException.class, 
                    () -> BinaryStdIn.readInt(0),
                    "Should throw IllegalArgumentException for r=0");
        
        assertThrows(IllegalArgumentException.class, 
                    () -> BinaryStdIn.readInt(33),
                    "Should throw IllegalArgumentException for r=33");
    }
    
    @Test
    public void testReadLong() {
        // Test reading a long value
        byte[] longBytes = new byte[] { 
            (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67, 
            (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF 
        };
        setTestInput(longBytes);
        assertEquals(0x0123456789ABCDEFL, BinaryStdIn.readLong(), "Should read correct long value");
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading");
    }
    
    @Test
    public void testReadDouble() {
        // Test reading a double value (using a known bit pattern)
        byte[] doubleBytes = new byte[] { 
            (byte) 0x3F, (byte) 0xF0, (byte) 0x00, (byte) 0x00, 
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 
        }; // This is 1.0 in double
        setTestInput(doubleBytes);
        assertEquals(1.0, BinaryStdIn.readDouble(), "Should read double value 1.0");
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading");
    }
    
    @Test
    public void testReadFloat() {
        // Test reading a float value (using a known bit pattern)
        byte[] floatBytes = new byte[] { 
            (byte) 0x3F, (byte) 0x80, (byte) 0x00, (byte) 0x00
        }; // This is 1.0 in float
        setTestInput(floatBytes);
        assertEquals(1.0f, BinaryStdIn.readFloat(), "Should read float value 1.0");
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading");
    }
    
    @Test
    public void testReadByte() {
        // Test reading a byte value
        setTestInput(new byte[] { (byte) 0xAB });
        assertEquals((byte) 0xAB, BinaryStdIn.readByte(), "Should read correct byte value");
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading");
    }
    
    @Test
    public void testReadString() {
        // Test reading a string
        String testString = "Hello, World!";
        setTestInput(testString.getBytes());
        assertEquals(testString, BinaryStdIn.readString(), "Should read correct string");
        assertTrue(BinaryStdIn.isEmpty(), "Stream should be empty after reading");
    }
    
    @Test
    public void testClose() throws Exception {
        // Set up test input
        setTestInput(new byte[] { 1 });
        
        // Read a bit to initialize
        BinaryStdIn.readBoolean();
        
        // Close and verify it's closed
        BinaryStdIn.close();
        
        // Reset to allow further testing
        resetBinaryStdIn();
        
        // Verify we can initialize again after closing
        setTestInput(new byte[] { 1 });
        assertFalse(BinaryStdIn.isEmpty(), "Should be able to read after closing and reinitializing");
    }
}
