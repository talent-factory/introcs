package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StdIOTest {

    @Test
    void isNumeric() {
        assertTrue(StdIO.isNumeric("223"));
        assertTrue(StdIO.isNumeric("27.8"));
        assertFalse(StdIO.isNumeric("4.2d"));
        assertFalse(StdIO.isNumeric("abc"));
        assertTrue(StdIO.isNumeric("-223"));
    }
}
