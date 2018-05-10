package com.kasirgalabs.etumulator.navigator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NavigatorRowTest {
    /**
     * Test of SetValueType and getValue methods, of class NavigatorRow.
     */
    @Test
    public void testSetValueTypeAndGetValue() {
        NavigatorRow navigatorRow = new NavigatorRow("r0", 0xffff_ffff);
        NavigatorRow.setType(NavigatorRow.Type.HEX);
        assertEquals("NavigatorRow result is wrong.",
                "0xffffffff",
                navigatorRow.getValue());

        NavigatorRow.setType(NavigatorRow.Type.DECIMAL);
        assertEquals("NavigatorRow result is wrong.", "-1", navigatorRow.getValue());

        NavigatorRow.setType(NavigatorRow.Type.BINARY);
        assertEquals("NavigatorRow result is wrong.",
                String.format("%0" + 32 + "d", 0).replace('0', '1'),
                navigatorRow.getValue());

        navigatorRow.setValue('x');
        NavigatorRow.setType(NavigatorRow.Type.ASCII);
        assertEquals("NavigatorRow result is wrong.", "x", navigatorRow.getValue());

        navigatorRow.setValue('\n');
        NavigatorRow.setType(NavigatorRow.Type.ASCII);
        assertEquals("NavigatorRow result is wrong.", "\\n", navigatorRow.getValue());

        navigatorRow.setValue(9999);
        NavigatorRow.setType(NavigatorRow.Type.ASCII);
        assertEquals("NavigatorRow result is wrong.", "Non-ASCII", navigatorRow.getValue());

        navigatorRow.setValue(1);
        NavigatorRow.setType(NavigatorRow.Type.BINARY);
        assertEquals("NavigatorRow result is wrong.",
                String.format("%0" + 32 + "d", 1),
                navigatorRow.getValue());

        navigatorRow.setValue(1);
        NavigatorRow.setType(NavigatorRow.Type.HEX);
        assertEquals("NavigatorRow result is wrong.",
                "0x" + String.format("%0" + 8 + "d", 1),
                navigatorRow.getValue());
    }
}
