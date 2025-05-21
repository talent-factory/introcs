package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code Stopwatch} class.
 */
public class StopwatchTest {
    
    private static final double DELTA = 0.1; // Allowing some leeway for timing tests
    
    @Test
    public void testElapsedTime() throws InterruptedException {
        Stopwatch stopwatch = new Stopwatch();
        
        // Sleep for 100ms
        Thread.sleep(100);
        
        double elapsed = stopwatch.elapsedTime();
        
        // Check that elapsed time is approximately 0.1 seconds
        // using the DELTA constant for tolerance
        assertEquals(0.1, elapsed, DELTA, 
                   "Elapsed time should be approximately 0.1 seconds");
    }
    
    @Test
    public void testElapsedTimeUnits() {
        Stopwatch stopwatch = new Stopwatch();
        
        // The elapsed time should be in seconds
        double elapsed = stopwatch.elapsedTime();
        assertTrue(elapsed < DELTA, "Initial elapsed time should be very small");
    }
    
    @Test
    public void testMultipleStarts() throws InterruptedException {
        Stopwatch stopwatch = new Stopwatch();
        
        // First measurement
        Thread.sleep(50);
        double firstElapsed = stopwatch.elapsedTime();
        
        // Second measurement should be greater than or equal to first
        Thread.sleep(50);
        double secondElapsed = stopwatch.elapsedTime();
        
        assertTrue(secondElapsed >= firstElapsed, 
                  "Second measurement should be greater than or equal to first");
    }
    
    @Test
    public void testToString() {
        Stopwatch stopwatch = new Stopwatch();
        String str = stopwatch.toString();
        
        // The string should contain "Elapsed time: " followed by a number
        assertTrue(str.startsWith("Elapsed time: "), 
                  "String should start with 'Elapsed time: '");
        
        // The rest should be a parseable double
        String timeStr = str.substring("Elapsed time: ".length()).split(" ")[0];
        assertDoesNotThrow(() -> Double.parseDouble(timeStr),
                         "Should be able to parse time as double");
    }
    
    @Test
    public void testMultipleInstances() throws InterruptedException {
        Stopwatch sw1 = new Stopwatch();
        Thread.sleep(50);
        
        Stopwatch sw2 = new Stopwatch();
        Thread.sleep(50);
        
        double time1 = sw1.elapsedTime();
        double time2 = sw2.elapsedTime();
        
        // sw1 should have been running longer than sw2
        assertTrue(time1 > time2, "First stopwatch should have been running longer");
    }
}
