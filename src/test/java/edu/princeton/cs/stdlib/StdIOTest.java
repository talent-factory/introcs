package edu.princeton.cs.stdlib;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StdIOTest {

    @Test
    @DisplayName("223")
    void isNumeric1() {
        assertTrue(StdIO.isNumeric("223"));
    }

    @Test
    @DisplayName("27.8")
    void isNumeric2() {
        assertFalse(StdIO.isNumeric("27.8"));
    }

    @Test
    @DisplayName("4.2d")
    void isNumeric3() {
        assertFalse(StdIO.isNumeric("4.2d"));
    }

    @Test
    @DisplayName("abc")
    void isNumeric4() {
        assertFalse(StdIO.isNumeric("abc"));
    }

    @Test
    @DisplayName("-223")
    void isNumeric5() {
        assertFalse(StdIO.isNumeric("-223"));
    }

    @Test
    @DisplayName("<empty>")
    void isNumeric6() {
        assertFalse(StdIO.isNumeric(""));
    }

    @Test
    @DisplayName("<null>")
    void isNumeric7() {
        assertFalse(StdIO.isNumeric(null));
    }
}
