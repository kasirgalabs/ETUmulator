package com.kasirgalabs.etumulator.lang;

import static org.junit.Assert.fail;

import com.kasirgalabs.etumulator.processor.Memory;
import org.junit.Test;

public class AssemblerTest {
    /**
     * Test of assemble method, of class Assembler.
     */
    @Test
    public void testAssemble() throws Exception {
        Memory memory = new Memory();
        Assembler assembler = new Assembler(memory);
        String code = "INTENTIONAL_SYNTAX_ERROR";
        try {
            assembler.assemble(code);
            fail("Assembler did not throw SyntaxError.");
        } catch(SyntaxError ex) {
        }
    }
}
