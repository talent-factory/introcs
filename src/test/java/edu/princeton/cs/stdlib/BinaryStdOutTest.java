package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Unit tests for the {@code BinaryStdOut} class.
 */
public class BinaryStdOutTest {
    
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;
    
    @BeforeEach
    public void setUp() throws Exception {
        // Redirect System.out to capture output
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        // Reset BinaryStdOut's static state using reflection
        resetBinaryStdOut();
    }
    
    @AfterEach
    public void tearDown() {
        // Restore original System.out
        System.setOut(originalOut);
    }
    
    /**
     * Reset BinaryStdOut's static state using reflection to allow for testing.
     */
    private void resetBinaryStdOut() throws Exception {
        Field initializedField = BinaryStdOut.class.getDeclaredField("isInitialized");
        initializedField.setAccessible(true);
        initializedField.set(null, false);
        
        Field bufferField = BinaryStdOut.class.getDeclaredField("buffer");
        bufferField.setAccessible(true);
        bufferField.set(null, 0);
        
        Field nField = BinaryStdOut.class.getDeclaredField("n");
        nField.setAccessible(true);
        nField.set(null, 0);
        
        Field outField = BinaryStdOut.class.getDeclaredField("out");
        outField.setAccessible(true);
        outField.set(null, null);
    }
    
    @Test
    public void testWriteBoolean() throws Exception {
        // Test writing individual bits
        BinaryStdOut.write(true);
        BinaryStdOut.write(false);
        BinaryStdOut.write(true);
        BinaryStdOut.write(false);
        BinaryStdOut.flush();
        
        // 1010 in binary is 0xA0 (padded with 4 zeros to make a full byte)
        byte[] expected = {(byte) 0xA0};
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteByte() throws Exception {
        // Test writing a byte
        byte testByte = 42;
        BinaryStdOut.write(testByte);
        BinaryStdOut.flush();
        
        byte[] expected = {testByte};
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteChar() throws Exception {
        // Test writing a character
        char testChar = 'A';
        BinaryStdOut.write(testChar);
        BinaryStdOut.flush();
        
        byte[] expected = {(byte) testChar};
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteCharWithBits() throws Exception {
        // Test writing a character with specific bits
        char testChar = 0x0F; // 00001111 in binary
        BinaryStdOut.write(testChar, 4); // Write only 4 bits
        BinaryStdOut.flush();
        
        // 1111 in binary is 0xF0 (padded with 4 zeros to make a full byte)
        byte[] expected = {(byte) 0xF0};
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteInt() throws Exception {
        // Test writing an integer
        int testInt = 1234567890;
        BinaryStdOut.write(testInt);
        BinaryStdOut.flush();
        
        byte[] expected = new byte[4];
        expected[0] = (byte) (testInt >>> 24);
        expected[1] = (byte) (testInt >>> 16);
        expected[2] = (byte) (testInt >>> 8);
        expected[3] = (byte) testInt;
        
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteIntWithBits() throws Exception {
        // Test writing an integer with specific bits
        int testInt = 0x0F; // 00001111 in binary
        BinaryStdOut.write(testInt, 4); // Write only 4 bits
        BinaryStdOut.flush();
        
        // 1111 in binary is 0xF0 (padded with 4 zeros to make a full byte)
        byte[] expected = {(byte) 0xF0};
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteShort() throws Exception {
        // Test writing a short
        short testShort = 12345;
        BinaryStdOut.write(testShort);
        BinaryStdOut.flush();
        
        byte[] expected = new byte[2];
        expected[0] = (byte) (testShort >>> 8);
        expected[1] = (byte) testShort;
        
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteLong() throws Exception {
        // Test writing a long
        long testLong = 1234567890123456789L;
        BinaryStdOut.write(testLong);
        BinaryStdOut.flush();
        
        byte[] expected = new byte[8];
        expected[0] = (byte) (testLong >>> 56);
        expected[1] = (byte) (testLong >>> 48);
        expected[2] = (byte) (testLong >>> 40);
        expected[3] = (byte) (testLong >>> 32);
        expected[4] = (byte) (testLong >>> 24);
        expected[5] = (byte) (testLong >>> 16);
        expected[6] = (byte) (testLong >>> 8);
        expected[7] = (byte) testLong;
        
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteFloat() throws Exception {
        // Test writing a float
        float testFloat = 3.14159f;
        BinaryStdOut.write(testFloat);
        BinaryStdOut.flush();
        
        int bits = Float.floatToRawIntBits(testFloat);
        byte[] expected = new byte[4];
        expected[0] = (byte) (bits >>> 24);
        expected[1] = (byte) (bits >>> 16);
        expected[2] = (byte) (bits >>> 8);
        expected[3] = (byte) bits;
        
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteDouble() throws Exception {
        // Test writing a double
        double testDouble = 3.14159265358979;
        BinaryStdOut.write(testDouble);
        BinaryStdOut.flush();
        
        long bits = Double.doubleToRawLongBits(testDouble);
        byte[] expected = new byte[8];
        expected[0] = (byte) (bits >>> 56);
        expected[1] = (byte) (bits >>> 48);
        expected[2] = (byte) (bits >>> 40);
        expected[3] = (byte) (bits >>> 32);
        expected[4] = (byte) (bits >>> 24);
        expected[5] = (byte) (bits >>> 16);
        expected[6] = (byte) (bits >>> 8);
        expected[7] = (byte) bits;
        
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteString() throws Exception {
        // Test writing a string
        String testString = "ABC";
        BinaryStdOut.write(testString);
        BinaryStdOut.flush();
        
        byte[] expected = testString.getBytes();
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testWriteStringWithBits() throws Exception {
        // Test writing a string with specific bits per character
        String testString = "AB"; // 'A' is 01000001, 'B' is 01000010
        BinaryStdOut.write(testString, 8); // Schreibe 8 Bits pro Zeichen
        BinaryStdOut.flush();
        
        // 'A' ist 65 (0x41), 'B' ist 66 (0x42)
        byte[] expected = {(byte) 0x41, (byte) 0x42};
        assertArrayEquals(expected, outputStream.toByteArray());
    }
    
    @Test
    public void testInvalidArguments() {
        // Test invalid arguments for write(char, int)
        assertThrows(IllegalArgumentException.class, () -> BinaryStdOut.write((char) 0, 0), 
                    "Should throw IllegalArgumentException for r=0");
        
        assertThrows(IllegalArgumentException.class, () -> BinaryStdOut.write((char) 0, 17), 
                    "Should throw IllegalArgumentException for r=17");
        
        assertThrows(IllegalArgumentException.class, () -> BinaryStdOut.write((char) 256, 8), 
                    "Should throw IllegalArgumentException for char > 255");
        
        // Test invalid arguments for write(int, int)
        assertThrows(IllegalArgumentException.class, () -> BinaryStdOut.write(0, 0), 
                    "Should throw IllegalArgumentException for r=0");
        
        assertThrows(IllegalArgumentException.class, () -> BinaryStdOut.write(0, 33), 
                    "Should throw IllegalArgumentException for r=33");
        
        assertThrows(IllegalArgumentException.class, () -> BinaryStdOut.write(16, 4), 
                    "Should throw IllegalArgumentException for int >= 2^r");
    }
    
    @Test
    public void testClose() throws Exception {
        // Wir testen nur, dass close keine Exception wirft
        BinaryStdOut.write((byte) 42);
        BinaryStdOut.close();
        
        // Reset für weitere Tests
        outputStream.reset();
        resetBinaryStdOut();
    }
    
    @Test
    public void testWriteBit() throws Exception {
        // Wir testen die öffentliche API statt der privaten Methode
        // Schreibe 8 einzelne Bits
        for (int i = 0; i < 8; i++) {
            BinaryStdOut.write(i % 2 == 0);
        }
        
        // Flush aufrufen
        BinaryStdOut.flush();
        
        // Überprüfe, ob Daten geschrieben wurden
        byte[] output = outputStream.toByteArray();
        assertTrue(output.length > 0, "Output sollte Daten enthalten");
    }
}
