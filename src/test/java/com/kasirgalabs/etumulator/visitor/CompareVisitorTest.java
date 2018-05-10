package com.kasirgalabs.etumulator.visitor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.kasirgalabs.etumulator.lang.Assembler;
import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.BaseProcessor;
import com.kasirgalabs.etumulator.processor.BaseProcessorUnits;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import org.junit.Test;

public class CompareVisitorTest {
    private final Assembler assembler;
    private final APSR apsr;
    private final Processor processor;

    public CompareVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        assembler = new Assembler(processorUnits.getMemory());
        apsr = processorUnits.getAPSR();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitCmp method, of class CompareVisitor.
     */
    @Test
    public void testVisitCmp() {
        String code = "cmp r2, #0\n";
        processor.run(assembler.assemble(code));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        code = "cmp r2, 8\n";
        processor.run(assembler.assemble(code));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        code = "mov r0, #0xf0\n"
                + "cmp r0, 0xf0\n";
        processor.run(assembler.assemble(code));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        code = "ldr r1, =#0x80000000\n"
                + "ldr r2, =0xffffffff\n"
                + "cmp r1, r2\n";
        processor.run(assembler.assemble(code));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());
    }

    /**
     * Test of visitCmn method, of class CompareVisitor.
     */
    @Test
    public void testVisitCmn() {
        String code = "cmn r1,  #0\n";
        processor.run(assembler.assemble(code));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        code = "cmn r2, 8\n";
        processor.run(assembler.assemble(code));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertFalse("Overflow flag is wrong.", apsr.isOverflow());

        code = "ldr r1, =#0x80000000\n"
                + "ldr r2, =0xffffffff\n"
                + "cmn r1, r2\n";
        processor.run(assembler.assemble(code));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
        assertTrue("Overflow flag is wrong.", apsr.isOverflow());
    }
}
