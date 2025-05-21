package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Unit tests for the {@code StdAudio} class.
 */
public class StdAudioTest {
    
    @TempDir
    Path tempDir;
    
    private File tempWavFile;
    
    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary WAV file for testing
        tempWavFile = tempDir.resolve("test.wav").toFile();
    }
    
    @AfterEach
    public void tearDown() {
        // Close StdAudio to release resources
        StdAudio.close();
    }
    
    @Test
    public void testPlaySample() {
        // Test playing a single sample
        // This mainly tests that no exceptions are thrown
        StdAudio.play(0.5);
        
        // Test with boundary values
        StdAudio.play(1.0);
        StdAudio.play(-1.0);
        
        // Test clipping for values outside range
        StdAudio.play(1.5);  // Should be clipped to 1.0
        StdAudio.play(-1.5); // Should be clipped to -1.0
    }
    
    @Test
    public void testPlaySampleInvalid() {
        // Test with NaN value
        assertThrows(IllegalArgumentException.class, 
                    () -> StdAudio.play(Double.NaN),
                    "Should throw IllegalArgumentException for NaN");
    }
    
    @Test
    public void testPlaySamples() {
        // Test playing an array of samples
        double[] samples = { 0.1, 0.2, 0.3, 0.4, 0.5 };
        StdAudio.play(samples);
        
        // Test with empty array
        StdAudio.play(new double[0]);
    }
    
    @Test
    public void testPlaySamplesInvalid() {
        // Test with null array
        assertThrows(IllegalArgumentException.class, 
                    () -> StdAudio.play((double[]) null),
                    "Should throw IllegalArgumentException for null array");
        
        // Test with array containing NaN
        double[] samplesWithNaN = { 0.1, 0.2, Double.NaN, 0.4 };
        assertThrows(IllegalArgumentException.class, 
                    () -> StdAudio.play(samplesWithNaN),
                    "Should throw IllegalArgumentException for array with NaN");
    }
    
    @Test
    public void testSaveAndRead() {
        // Create a simple sine wave
        double[] samples = createSineWave(440.0, 0.1);
        
        // Save to file
        StdAudio.save(tempWavFile.getAbsolutePath(), samples);
        
        // Read back from file
        double[] readSamples = StdAudio.read(tempWavFile.getAbsolutePath());
        
        // Verify the samples are approximately the same
        // Due to quantization in the WAV format, they won't be exactly the same
        assertEquals(samples.length, readSamples.length, "Sample count should match");
        
        // Check a few samples to ensure they're close
        for (int i = 0; i < Math.min(10, samples.length); i++) {
            assertEquals(samples[i], readSamples[i], 0.01, 
                       "Sample at index " + i + " should be approximately the same");
        }
    }
    
    @Test
    public void testSaveInvalidFilename() {
        double[] samples = { 0.1, 0.2, 0.3 };
        
        // Test with null filename
        assertThrows(IllegalArgumentException.class, 
                    () -> StdAudio.save(null, samples),
                    "Should throw IllegalArgumentException for null filename");
        
        // Test with invalid extension
        assertThrows(IllegalArgumentException.class, 
                    () -> StdAudio.save("test.mp3", samples),
                    "Should throw IllegalArgumentException for invalid extension");
    }
    
    @Test
    public void testSaveInvalidSamples() {
        // Test with null samples
        assertThrows(IllegalArgumentException.class, 
                    () -> StdAudio.save(tempWavFile.getAbsolutePath(), null),
                    "Should throw IllegalArgumentException for null samples");
    }
    
    @Test
    public void testNote() {
        // Test the note generation by using reflection to access the private method
        try {
            java.lang.reflect.Method noteMethod = StdAudio.class.getDeclaredMethod("note", double.class, double.class, double.class);
            noteMethod.setAccessible(true);
            
            // Generate a note at 440 Hz (A4) for 0.1 seconds at half volume
            double[] noteA4 = (double[]) noteMethod.invoke(null, 440.0, 0.1, 0.5);
            
            // Verify the length is correct
            assertEquals((int)(StdAudio.SAMPLE_RATE * 0.1) + 1, noteA4.length, 
                       "Note length should match expected sample count");
            
            // Verify the amplitude is correct (maximum should be close to 0.5)
            double maxAmplitude = Arrays.stream(noteA4).max().getAsDouble();
            assertEquals(0.5, maxAmplitude, 0.01, "Maximum amplitude should be approximately 0.5");
            
        } catch (Exception e) {
            fail("Exception occurred while testing note method: " + e.getMessage());
        }
    }
    
    // Helper method to create a sine wave
    private double[] createSineWave(double frequency, double duration) {
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] samples = new double[n];
        for (int i = 0; i < n; i++) {
            samples[i] = 0.5 * Math.sin(2 * Math.PI * frequency * i / StdAudio.SAMPLE_RATE);
        }
        return samples;
    }
}
