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

public class MultiplyAndDivideVisitorTest {
    private final Assembler assembler;
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final Processor processor;

    public MultiplyAndDivideVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        assembler = new Assembler(processorUnits.getMemory());
        registerFile = processorUnits.getRegisterFile();
        apsr = processorUnits.getAPSR();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitMul method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMul() {
        String code = "mul r0, r1, r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "mul r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 32, registerFile.getValue("r0"));
    }

    /**
     * Test of visitMuls method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMuls() {
        String code = "muls r0, r1, r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "muls r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 32, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
    }

    /**
     * Test of visitMla method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMla() {
        String code = "mla r0, r1, r3, r4\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "mov r3, #2\n"
                + "mla r0, r1, r2, r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 34, registerFile.getValue("r0"));
    }

    /**
     * Test of visitMlas method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMlas() {
        String code = "mlas r0, r1, r3, r4\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "mov r3, #2\n"
                + "mlas r0, r1, r2, r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 34, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
    }

    /**
     * Test of visitMls method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitMls() {
        String code = "mls r0, r1, r3, r4\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, 4\n"
                + "mov r2, 8\n"
                + "mov r3, #2\n"
                + "mls r0, r1, r2, r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", -30, registerFile.getValue("r0"));
    }

    /**
     * Test of visitSdiv method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitSdiv() {
        String code = "ldr r0, =50\n"
                + "mov r1, 2\n"
                + "sdiv r0, r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 25, registerFile.getValue("r0"));

        code = "ldr r0, =0xfffffffe\n"
                + "ldr r1, =0xffffffff\n"
                + "sdiv r0, r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 2, registerFile.getValue("r0"));

        code = "ldr r0, =0x80000000\n"
                + "ldr r1, =0xffffffff\n"
                + "sdiv r0, r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 0x8000_0000, registerFile.getValue("r0"));

        code = "ldr r0, =0x80000000\n"
                + "ldr r1, =0\n"
                + "sdiv r0, r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));
    }

    /**
     * Test of visitUdiv method, of class MultiplyAndDivideVisitor.
     */
    @Test
    public void testVisitUdiv() {
        String code = "ldr r0, =50\n"
                + "ldr r1, =0xffffffff\n"
                + "udiv r0, r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", Integer.divideUnsigned(50, 0xffff_ffff),
                registerFile.getValue("r0"));

        code = "ldr r0, =0xffffffff\n"
                + "mov r1, 1\n"
                + "udiv r0, r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 0xffff_ffff, registerFile.getValue("r0"));

        code = "ldr r0, =0x80000000\n"
                + "ldr r1, =0\n"
                + "sdiv r0, r0, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Multiply result is wrong.", 0, registerFile.getValue("r0"));
    }
}
