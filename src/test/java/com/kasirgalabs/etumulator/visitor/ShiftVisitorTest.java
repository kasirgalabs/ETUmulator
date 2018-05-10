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

public class ShiftVisitorTest {
    private final Assembler assembler;
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final Processor processor;

    public ShiftVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        assembler = new Assembler(processorUnits.getMemory());
        registerFile = processorUnits.getRegisterFile();
        apsr = processorUnits.getAPSR();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitAsr method, of class ShiftVisitor.
     */
    @Test
    public void testVisitAsr() {
        String code = "ldr r1, =#0x80000000\n"
                + "mov r2, #1\n"
                + "asr r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0x8000_0000 >> 1, registerFile.getValue("r0"));

        code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "asr r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #2\n"
                + "asr r0, r1, #1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitAsrs method, of class ShiftVisitor.
     */
    @Test
    public void testVisitAsrs() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "asrs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "asrs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_ffff, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "asrs r0, r1, #2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_ffff, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());
    }

    /**
     * Test of visitLsl method, of class ShiftVisitor.
     */
    @Test
    public void testVisitLsl() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "lsl r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 2, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "lsl r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_fffe, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "lsl r0, r1, 1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_fffe, registerFile.getValue("r0"));
    }

    /**
     * Test of visitLsls method, of class ShiftVisitor.
     */
    @Test
    public void testVisitLsls() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "lsls r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 2, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertFalse("Carry flag is wrong.", apsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "lsls r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_ffff << 1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "lsls r0, r1, 2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_ffff << 2, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());
    }

    /**
     * Test of visitLsr method, of class ShiftVisitor.
     */
    @Test
    public void testVisitLsr() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "lsr r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "lsr r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0x7fff_ffff, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "lsr r0, r1, #4\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_ffff >>> 4, registerFile.getValue("r0"));
    }

    /**
     * Test of visitLsrs method, of class ShiftVisitor.
     */
    @Test
    public void testVisitLsrs() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "lsrs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "lsrs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0x7fff_ffff, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "lsrs r0, r1, #2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_ffff >>> 2, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());
    }

    /**
     * Test of visitRor method, of class ShiftVisitor.
     */
    @Test
    public void testVisitRor() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "ror r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0x8000_0000, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "ror r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_ffff, registerFile.getValue("r0"));

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "ror r0, r1, #2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", Integer.rotateRight(0xffff_ffff, 2),
                registerFile.getValue("r0"));
    }

    /**
     * Test of visitRors method, of class ShiftVisitor.
     */
    @Test
    public void testVisitRors() {
        String code = "mov r1, 1\n"
                + "mov r2, #1\n"
                + "rors r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0x8000_0000, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());

        code = "mov r1, 0\n"
                + "mov r2, #4\n"
                + "rors r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertFalse("Carry flag is wrong.", apsr.isCarry());

        code = "ldr r1, =#0xffffffff\n"
                + "mov r2, #1\n"
                + "rors r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_ffff, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());

        code = "ldr r1, =#0xfffff0ff\n"
                + "mov r2, #1\n"
                + "rors r0, r1, #3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", Integer.rotateRight(0xffff_f0ff, 3),
                registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());
    }

    /**
     * Test of visitRrx method, of class ShiftVisitor.
     */
    @Test
    public void testVisitRrx() {
        String code = "mov r1, 1\n"
                + "rrx r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
    }

    /**
     * Test of visitRrxs method, of class ShiftVisitor.
     */
    @Test
    public void testVisitRrxs() {
        apsr.setCarry(true);
        String code = "ldr r1, =#0xffffffff\n"
                + "rrxs r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0xffff_ffff, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());

        apsr.setCarry(false);
        code = "ldr r1, =#0xffffffff\n"
                + "rrxs r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", Integer.MAX_VALUE, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());

        apsr.setCarry(false);
        code = "mov r1, 0\n"
                + "rrxs r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertFalse("Carry flag is wrong.", apsr.isCarry());

        apsr.setCarry(false);
        code = "mov r1, 1\n"
                + "rrxs r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertTrue("Carry flag is wrong.", apsr.isCarry());
    }
}
