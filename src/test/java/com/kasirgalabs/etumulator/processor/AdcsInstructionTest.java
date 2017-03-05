/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.processor;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.ProcessorTester;
import org.junit.Test;

public class AdcsInstructionTest extends ProcessorTester {
    /**
     * Test of exitAdcs method, of class Processor.
     */
    @Test
    public void testExitAdcs() {
        cpsr.setCarry(false);
        char[] code = ("mov r1, #1\n"
                + "adc r0, r1, r1\n").toCharArray();
        runTestCode(code, false);
        assertEquals("Addition result is wrong.", registerFile.getValue(0), 2);

        cpsr.setCarry(true);
        code = ("ldr r1, =0x7fffffff\n"
                + "adcs r0, r1, r1\n").toCharArray();
        runTestCode(code, false);
        assertEquals("Addition result is wrong.", registerFile.getValue(0), -1);
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());

        cpsr.setCarry(true);
        code = ("ldr r1, =0x7fffffff\n"
                + "adcs r0, r1, #0\n").toCharArray();
        runTestCode(code, false);
        assertEquals("Addition result is wrong.", registerFile.getValue(0), Integer.MIN_VALUE);
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
        assertEquals("Overflow flag is wrong.", true, cpsr.isOverflow());
    }
}
