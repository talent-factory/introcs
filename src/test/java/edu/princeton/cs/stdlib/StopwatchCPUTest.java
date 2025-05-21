package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link StopwatchCPU} class.
 * 
 * These tests verify that the StopwatchCPU correctly measures CPU time
 * with millisecond precision.
 */
class StopwatchCPUTest {
    
    private static final double NANOSECONDS_PER_SECOND = 1_000_000_000.0;
    private static final double ACCEPTABLE_ERROR = 0.01; // 1% error margin
    private ThreadMXBean threadTimer;
    
    @BeforeEach
    void setUp() {
        threadTimer = ManagementFactory.getThreadMXBean();
    }
    
    /**
     * Tests that a newly created StopwatchCPU starts timing immediately.
     */
    @Test
    void testInitialization() {
        StopwatchCPU stopwatch = new StopwatchCPU();
        assertNotNull(stopwatch, "StopwatchCPU should be initialized");
        
        // Verify that elapsed time is very small but non-negative
        double elapsed = stopwatch.elapsedTime();
        assertTrue(elapsed >= 0.0, "Elapsed time should be non-negative");
        assertTrue(elapsed < 1.0, "Initial elapsed time should be less than 1 second");
    }
    
    /**
     * Tests that elapsedTime() returns increasing values over time.
     */
    @Test
    void testElapsedTimeIncreases() throws InterruptedException {
        StopwatchCPU stopwatch = new StopwatchCPU();
        
        // First measurement
        double first = stopwatch.elapsedTime();
        assertTrue(first >= 0.0, "First measurement should be non-negative");
        
        // Do some work to consume CPU time
        doWork(100);
        
        // Second measurement should be larger
        double second = stopwatch.elapsedTime();
        assertTrue(second > first, "Second measurement should be larger than first");
        
        // Do more work
        doWork(200);
        
        // Third measurement should be larger still
        double third = stopwatch.elapsedTime();
        assertTrue(third > second, "Third measurement should be larger than second");
    }
    
    /**
     * Tests that the elapsed time is measured with reasonable accuracy.
     */
    @Test
    void testElapsedTimeAccuracy() throws InterruptedException {
        StopwatchCPU stopwatch = new StopwatchCPU();
        
        // Measure a known duration of work
        long startNanos = threadTimer.getCurrentThreadCpuTime();
        doWork(300);
        long endNanos = threadTimer.getCurrentThreadCpuTime();
        
        double expectedSeconds = (endNanos - startNanos) / NANOSECONDS_PER_SECOND;
        double actualSeconds = stopwatch.elapsedTime();
        
        // Allow for some measurement error
        double error = Math.abs(actualSeconds - expectedSeconds);
        double relativeError = error / expectedSeconds;
        
        assertTrue(relativeError < ACCEPTABLE_ERROR, 
            String.format("Relative error %.2f%% exceeds acceptable %.2f%%", 
                        relativeError * 100, ACCEPTABLE_ERROR * 100));
    }
    
    /**
     * Tests that multiple stopwatches can run independently.
     */
    @Test
    void testMultipleStopwatches() throws InterruptedException {
        StopwatchCPU stopwatch1 = new StopwatchCPU();
        doWork(100);
        
        StopwatchCPU stopwatch2 = new StopwatchCPU();
        doWork(100);
        
        double time1 = stopwatch1.elapsedTime();
        double time2 = stopwatch2.elapsedTime();
        
        // First stopwatch should have been running longer
        assertTrue(time1 > time2, "First stopwatch should have been running longer");
        
        // Do more work and verify both stopwatches update
        doWork(100);
        
        double newTime1 = stopwatch1.elapsedTime();
        double newTime2 = stopwatch2.elapsedTime();
        
        assertTrue(newTime1 > time1, "First stopwatch should have advanced");
        assertTrue(newTime2 > time2, "Second stopwatch should have advanced");
        assertTrue(newTime1 > newTime2, "First stopwatch should still be ahead");
    }
    
    /**
     * Helper method to consume CPU time for testing purposes.
     * 
     * @param iterations Number of iterations to perform
     */
    private void doWork(int iterations) {
        // Perform some CPU-intensive work
        double result = 0.0;
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < 100000; j++) {
                result += Math.sin(i) * Math.cos(j);
            }
        }
        // Prevent optimization
        if (result == 0.0) {
            System.out.println("This should never happen");
        }
    }
}
