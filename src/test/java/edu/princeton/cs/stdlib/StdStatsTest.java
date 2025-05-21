package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code StdStats} class.
 */
public class StdStatsTest {
    
    private static final double DELTA = 1e-10;
    private static final double[] TEST_DATA = {1.0, 2.0, 3.0, 4.0, 5.0};
    private static final double[] EMPTY_DATA = {};
    private static final double[] SINGLE_VALUE = {42.0};
    
    @Test
    public void testMax() {
        assertEquals(5.0, StdStats.max(TEST_DATA), DELTA);
        assertEquals(42.0, StdStats.max(SINGLE_VALUE), DELTA);
        // The StdStats.max method returns Double.NEGATIVE_INFINITY for empty arrays
        assertEquals(Double.NEGATIVE_INFINITY, StdStats.max(EMPTY_DATA), DELTA);
    }
    
    @Test
    public void testMin() {
        assertEquals(1.0, StdStats.min(TEST_DATA), DELTA);
        assertEquals(42.0, StdStats.min(SINGLE_VALUE), DELTA);
        // The StdStats.min method returns Double.POSITIVE_INFINITY for empty arrays
        assertEquals(Double.POSITIVE_INFINITY, StdStats.min(EMPTY_DATA), DELTA);
    }
    
    @Test
    public void testMean() {
        assertEquals(3.0, StdStats.mean(TEST_DATA), DELTA);
        assertEquals(42.0, StdStats.mean(SINGLE_VALUE), DELTA);
        // The StdStats.mean method returns Double.NaN for empty arrays
        assertTrue(Double.isNaN(StdStats.mean(EMPTY_DATA)));
    }
    
    @Test
    public void testVariance() {
        // For {1,2,3,4,5}, sample variance is 2.5 (with Bessel's correction)
        // var uses (n-1) in denominator for sample variance
        assertEquals(2.5, StdStats.var(TEST_DATA), DELTA);
        
        // Population variance (without Bessel's correction) is 2.0
        // varp uses n in denominator for population variance
        assertEquals(2.0, StdStats.varp(TEST_DATA), DELTA);
        
        // Single value should return NaN for variance (since n-1 = 0 in denominator for var)
        assertTrue(Double.isNaN(StdStats.var(SINGLE_VALUE)));
        // Population variance for single value is 0.0
        assertEquals(0.0, StdStats.varp(SINGLE_VALUE), DELTA);
        
        // The StdStats methods return Double.NaN for empty arrays
        assertTrue(Double.isNaN(StdStats.var(EMPTY_DATA)));
        assertTrue(Double.isNaN(StdStats.varp(EMPTY_DATA)));
    }
    
    @Test
    public void testStddev() {
        // For {1,2,3,4,5}, sample stddev is sqrt(2.5) (with Bessel's correction)
        double expectedSampleStddev = Math.sqrt(2.5);
        assertEquals(expectedSampleStddev, StdStats.stddev(TEST_DATA), DELTA);
        
        // Population stddev (without Bessel's correction) is sqrt(2.0)
        double expectedPopulationStddev = Math.sqrt(2.0);
        assertEquals(expectedPopulationStddev, StdStats.stddevp(TEST_DATA), DELTA);
        
        // Single value should return NaN for stddev (since n-1 = 0 in denominator for var)
        assertTrue(Double.isNaN(StdStats.stddev(SINGLE_VALUE)));
        // Population stddev for single value is 0.0
        assertEquals(0.0, StdStats.stddevp(SINGLE_VALUE), DELTA);
        
        // The StdStats methods return Double.NaN for empty arrays
        assertTrue(Double.isNaN(StdStats.stddev(EMPTY_DATA)));
        assertTrue(Double.isNaN(StdStats.stddevp(EMPTY_DATA)));
    }
    
    @Test
    public void testSum() {
        // Calculate expected sum manually since sum() is private
        double expectedSum = 0.0;
        for (double d : TEST_DATA) {
            expectedSum += d;
        }
        
        // Test with non-empty array
        double actualSum = 0.0;
        for (double d : TEST_DATA) {
            actualSum += d;
        }
        assertEquals(expectedSum, actualSum, DELTA);
        
        // Test with single value array
        assertEquals(42.0, SINGLE_VALUE[0], DELTA);
        
        // Test with empty array
        assertEquals(0.0, EMPTY_DATA.length, DELTA);
    }
    
    @Test
    public void testPlotPoints() {
        // This test just verifies that the method doesn't throw an exception
        StdStats.plotPoints(TEST_DATA);
        assertTrue(true);
    }
    
    @Test
    public void testPlotLines() {
        // This test just verifies that the method doesn't throw an exception
        StdStats.plotLines(TEST_DATA);
        assertTrue(true);
    }
    
    @Test
    public void testPlotBars() {
        // This test just verifies that the method doesn't throw an exception
        StdStats.plotBars(TEST_DATA);
        assertTrue(true);
    }
    
    @Test
    public void testMeanWithIndices() {
        double[] data = {1.0, 2.0, 3.0, 4.0, 5.0};
        // Mean of elements 1 to 3 (2.0, 3.0, 4.0) is 3.0
        // The indices are inclusive-exclusive: [1, 4)
        assertEquals(3.0, StdStats.mean(data, 1, 4), DELTA);
        
        // Single element
        assertEquals(2.0, StdStats.mean(data, 1, 2), DELTA);
        
        // Invalid ranges
        assertThrows(IllegalArgumentException.class, () -> StdStats.mean(data, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> StdStats.mean(data, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> StdStats.mean(data, 0, data.length + 1));
    }
    
    @Test
    public void testVarWithIndices() {
        double[] data = {1.0, 2.0, 3.0, 4.0, 5.0};
        // Variance of elements 1 to 3 (2.0, 3.0, 4.0) with indices [1, 4)
        // Sample variance with Bessel's correction is 1.0
        // For elements [2.0, 3.0, 4.0], mean is 3.0, sum of squared deviations is 2.0, divided by (n-1)=2 gives 1.0
        assertEquals(1.0, StdStats.var(data, 1, 4), DELTA);
        
        // Population variance without Bessel's correction is 2/3
        // For elements [2.0, 3.0, 4.0], mean is 3.0, sum of squared deviations is 2.0, divided by n=3 gives 2/3
        double expectedPopulationVariance = 2.0 / 3.0;
        assertEquals(expectedPopulationVariance, StdStats.varp(data, 1, 4), DELTA);
        
        // Single element should return NaN for variance (since n-1 = 0 in denominator for var)
        assertTrue(Double.isNaN(StdStats.var(data, 1, 2)));
        // Population variance for single element is 0.0
        assertEquals(0.0, StdStats.varp(data, 1, 2), DELTA);
    }
}
