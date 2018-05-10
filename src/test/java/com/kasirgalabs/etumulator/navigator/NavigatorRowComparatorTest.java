package com.kasirgalabs.etumulator.navigator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NavigatorRowComparatorTest {
    /**
     * Test of compare method, of class NavigatorRowComparator.
     */
    @Test
    public void testCompare() {
        NavigatorRowComparator instance = new NavigatorRowComparator();
        assertEquals("Compare result is wrong.", -1, instance.compare("1", "10"));
        assertEquals("Compare result is wrong.", -1, instance.compare("1", "11"));
        assertEquals("Compare result is wrong.", -1, instance.compare("r1", "r10"));
        assertEquals("Compare result is wrong.", -1, instance.compare("r1", "r11"));
        assertEquals("Compare result is wrong.", 1, instance.compare("r1", "r0"));
        assertEquals("Compare result is wrong.", 1, instance.compare("2", "1"));
        assertEquals("Compare result is wrong.", -1, instance.compare("0", "123"));
        assertEquals("Compare result is wrong.", -1, instance.compare("0xffffffff", "0x1"));
        assertEquals("Compare result is wrong.", -1, instance.compare("0x0", "0x1"));
        assertEquals("Compare result is wrong.", 1, instance.compare("0x6", "0x5"));
        assertEquals("Compare result is wrong.", -1, instance.compare(
                "00000000000000000000000000000000",
                "00000000000000000000000000000001"));
        assertEquals("Compare result is wrong.", 1, instance.compare(
                "11111111111111111111111111111111",
                "11111111111111111111111111111110"));
        assertEquals("Compare result is wrong.", -1, instance.compare("PC", "r13"));
        assertEquals("Compare result is wrong.", -1, instance.compare("r13", "PC"));
        assertEquals("Compare result is wrong.", -1, instance.compare("PC", "LR"));
        assertEquals("Compare result is wrong.", -1, instance.compare("LR", "PC"));
        assertEquals("Compare result is wrong.", -1, instance.compare("LR", "r0"));
        assertEquals("Compare result is wrong.", -1, instance.compare("r0", "LR"));
        assertEquals("Compare result is wrong.", -1, instance.compare("LR", "PC"));

    }
}
