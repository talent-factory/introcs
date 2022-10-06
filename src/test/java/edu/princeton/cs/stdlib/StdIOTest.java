package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StdIOTest {

    @Test
    void isNumeric() {
        assertTrue(StdIO.isNumeric("223"));
        assertFalse(StdIO.isNumeric("27.8"));
        assertFalse(StdIO.isNumeric("4.2d"));
        assertFalse(StdIO.isNumeric("abc"));
        assertFalse(StdIO.isNumeric("-223"));
    }
}
