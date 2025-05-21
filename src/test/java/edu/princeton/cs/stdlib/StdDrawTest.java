package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

/**
 * Unit tests for the {@code StdDraw} class.
 * Note: These tests are limited as they test the API rather than visual output.
 */
public class StdDrawTest {
    
    private static final double DELTA = 1e-10;
    
    @BeforeEach
    public void setUp() {
        // Set up StdDraw for testing (non-visible)
        StdDraw.setCanvasSize(800, 600);
        StdDraw.clear();
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLACK);
    }
    
    @AfterEach
    public void tearDown() {
        // Reset StdDraw state
        StdDraw.clear();
    }
    
    @Test
    public void testSetPenColor() {
        StdDraw.setPenColor(255, 0, 0);
        assertEquals(Color.RED, StdDraw.getPenColor());
        
        StdDraw.setPenColor(StdDraw.BLUE);
        assertEquals(StdDraw.BLUE, StdDraw.getPenColor());
    }
    
    @Test
    public void testSetPenRadius() {
        double radius = 0.02;
        StdDraw.setPenRadius(radius);
        assertEquals(radius, StdDraw.getPenRadius(), DELTA);
    }
    
    @Test
    public void testSetCanvasSize() {
        int width = 1024;
        int height = 768;
        StdDraw.setCanvasSize(width, height);
        // Note: No getter for canvas size, so we can't directly test this
        // This test is just to verify no exceptions are thrown
        assertTrue(true);
    }
    
    @Test
    public void testDrawingMethods() {
        // Test various drawing methods to ensure they don't throw exceptions
        
        // Test line drawing
        StdDraw.line(0.1, 0.1, 0.9, 0.9);
        
        // Test point drawing
        StdDraw.point(0.5, 0.5);
        
        // Test circle drawing
        StdDraw.circle(0.5, 0.5, 0.2);
        
        // Test square drawing
        StdDraw.square(0.5, 0.5, 0.2);
        
        // Test filled shapes
        StdDraw.filledCircle(0.3, 0.3, 0.1);
        StdDraw.filledSquare(0.7, 0.7, 0.1);
        
        // If we get here without exceptions, the test passes
        assertTrue(true);
    }
    
    @Test
    public void testTextMethods() {
        // Test basic text drawing
        StdDraw.text(0.1, 0.1, "Test Text");
        
        // Test text with different font size
        StdDraw.text(0.5, 0.5, "Centered Text");
        
        // If we get here without exceptions, the test passes
        assertTrue(true);
    }
    
    @Test
    public void testSave() {
        // Test saving an image
        String filename = "test_save.png";
        
        // Draw something
        StdDraw.filledCircle(0.5, 0.5, 0.2);
        
        // Save
        StdDraw.save(filename);
        
        // Note: We can't easily test the file was actually saved without file system access
        // This test is just to verify the method doesn't throw exceptions
        assertTrue(true);
    }
    
    @Test
    public void testSetScale() {
        double min = -10.0;
        double max = 10.0;
        
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);
        
        // Note: No getter for scale, so we can't directly test this
        // This test is just to verify no exceptions are thrown
        assertTrue(true);
    }
    
    @Test
    public void testClear() {
        // This test just verifies that clear doesn't throw an exception
        StdDraw.clear();
        StdDraw.clear(StdDraw.LIGHT_GRAY);
        assertTrue(true);
    }
}
