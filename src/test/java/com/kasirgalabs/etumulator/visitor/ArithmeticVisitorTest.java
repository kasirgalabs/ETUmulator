package com.kasirgalabs.etumulator.visitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.kasirgalabs.etumulator.lang.Assembler;
import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.BaseProcessor;
import com.kasirgalabs.etumulator.processor.BaseProcessorUnits;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import org.junit.Test;

public class ArithmeticVisitorTest {
    private final Assembler assembler;
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final Processor processor;

    public ArithmeticVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        assembler = new Assembler(processorUnits.getMemory());
        registerFile = processorUnits.getRegisterFile();
        apsr = processorUnits.getAPSR();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitAdd method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitAdd() {
        String code = "add r1, r2, r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 0, registerFile.getValue("r1"));

        code = "add r1, r2, #64\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 64, registerFile.getValue("r1"));

        code = "add r1, r2, 0x0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 0, registerFile.getValue("r1"));

        code = "add r1, r2, #0xff\n"
                + "add r1, r1, 0xff\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 510, registerFile.getValue("r1"));
    }

    /**
     * Test of visitAdds method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitAdds() {
        String code = "adds r1, r2, #0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 0, registerFile.getValue("r1"));

        code = "adds r1, r2, 8\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 8, registerFile.getValue("r1"));

        code = "add r0, r0, #0xf0\n"
                + "adds r0, r0, 0xf0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 0x1e0, registerFile.getValue("r0"));

        code = "ldr r1, =#0x80000000\n"
                + "ldr r2, =0xffffffff\n"
                + "adds r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", Integer.MAX_VALUE, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());
    }

    /**
     * Test of visitAdc method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitAdc() {
        apsr.setCarry(false);
        String code = "adc r1, r2, r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 0, registerFile.getValue("r1"));

        apsr.setCarry(true);
        code = "adc r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 1, registerFile.getValue("r0"));

        apsr.setCarry(false);
        code = "adc r0, r1, 4\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 4, registerFile.getValue("r0"));

        apsr.setCarry(true);
        code = "mov r1, #1\n"
                + "mov r2, #2\n"
                + "adc r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 4, registerFile.getValue("r0"));

        apsr.setCarry(true);
        code = "mov r0, #1\n"
                + "adc r0, r0, r0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 3, registerFile.getValue("r0"));
    }

    /**
     * Test of visitAdcs method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitAdcs() {
        apsr.setCarry(false);
        String code = "mov r1, #1\n"
                + "adcs r0, r1, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", 2, registerFile.getValue("r0"));

        apsr.setCarry(true);
        code = "ldr r1, =0x7fffffff\n"
                + "adcs r0, r1, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());

        apsr.setCarry(true);
        code = "ldr r1, =0x7fffffff\n"
                + "adcs r0, r1, #0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Addition result is wrong.", Integer.MIN_VALUE, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());
    }

    /**
     * Test of visitSub method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitSub() {
        String code = "sub r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r0"));

        code = "sub r0, r1, #1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));

        code = "mov r1, #2\n"
                + "mov r2, #1\n"
                + "sub r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 1, registerFile.getValue("r0"));

        code = "mov r1, #0xf\n"
                + "mov r2, 0xff\n"
                + "sub r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -240, registerFile.getValue("r0"));
    }

    /**
     * Test of visitSubs method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitSubs() {
        String code = "subs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        code = "subs r0, r1, #1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        code = "ldr r1, =0x80000000\n"
                + "mov r2, #1\n"
                + "subs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.",
                Integer.MAX_VALUE,
                registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());

        code = "mov r0, #0\n"
                + "mov r1, #0xf\n"
                + "subs r0, r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -0xf, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());
    }

    /**
     * Test of visitSbc method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitSbc() {
        apsr.setCarry(true);
        String code = "sbc r1, r2, r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r1"));

        apsr.setCarry(false);
        code = "sbc r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));

        apsr.setCarry(true);
        code = "sbc r0, r1, 4\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -4, registerFile.getValue("r0"));

        apsr.setCarry(false);
        code = "mov r1, #1\n"
                + "mov r2, #2\n"
                + "sbc r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -2, registerFile.getValue("r0"));

        apsr.setCarry(false);
        code = "mov r0, #1\n"
                + "sbc r0, r0, r0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitSbcs method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitSbcs() {
        apsr.setCarry(true);
        String code = "ldr r1, =#0x80000000\n"
                + "sbcs r0, r1, #1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.",
                Integer.MAX_VALUE,
                registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());

        apsr.setCarry(false);
        code = "ldr r1, =#0x80000000\n"
                + "sbcs r0, r1, #1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", Integer.MAX_VALUE - 1, registerFile
                .getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());

        apsr.setCarry(true);
        code = "mov r1, #0\n"
                + "sbcs r0, r1, #0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        apsr.setCarry(false);
        code = "ldr r1, =#0x80000001\n"
                + "sbcs r0, r1, #1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.",
                Integer.MAX_VALUE,
                registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());
    }

    /**
     * Test of visitRsb method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitRsb() {
        String code = "rsb r0, r2, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "rsb r0, r1, #0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));

        code = "mov r1, #2\n"
                + "mov r2, #1\n"
                + "rsb r0, r2, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 1, registerFile.getValue("r0"));

        code = "mov r1, #0xf\n"
                + "mov r2, 0xff\n"
                + "rsb r0, r2, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -240, registerFile.getValue("r0"));
    }

    /**
     * Test of visitRsbs method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitRsbs() {
        String code = "rsbs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        code = "mov r1, 0\n"
                + "rsbs r0, r1, 1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 1, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        code = "ldr r1, =0x80000000\n"
                + "mov r2, #1\n"
                + "rsbs r0, r2, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.",
                Integer.MAX_VALUE,
                registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());

        code = "mov r1, #0xf\n"
                + "mov r0, #0\n"
                + "rsbs r0, r1, r0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -0xf, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());
    }

    /**
     * Test of visitRsc method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitRsc() {
        apsr.setCarry(true);
        String code = "rsc r1, r2, r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r1"));

        apsr.setCarry(false);
        code = "rsc r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));

        apsr.setCarry(true);
        code = "rsc r0, r1, 4\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 4, registerFile.getValue("r0"));

        apsr.setCarry(false);
        code = "mov r1, #1\n"
                + "mov r2, #2\n"
                + "rsc r0, r2, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -2, registerFile.getValue("r0"));

        apsr.setCarry(false);
        code = "mov r0, #1\n"
                + "rsc r0, r0, r0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", -1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitRscs method, of class ArithmeticVisitor.
     */
    @Test
    public void testVisitRscs() {
        apsr.setCarry(true);
        String code = "mov r2, #1\n"
                + "ldr r1, =#0x80000000\n"
                + "rscs r0, r2, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.",
                Integer.MAX_VALUE,
                registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());

        apsr.setCarry(false);
        code = "mov r2, #1\n"
                + "ldr r1, =#0x80000000\n"
                + "rscs r0, r2, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", Integer.MAX_VALUE - 1, registerFile
                .getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());

        apsr.setCarry(true);
        code = "mov r1, #0\n"
                + "rscs r0, r1, #0\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        apsr.setCarry(false);
        code = "mov r2, #1\n"
                + "ldr r1, =#0x80000001\n"
                + "rscs r0, r2, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Subtraction result is wrong.",
                Integer.MAX_VALUE,
                registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());
    }
}
