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

public class LogicalVisitorTest {
    private final Assembler assembler;
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final Processor processor;

    public LogicalVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        assembler = new Assembler(processorUnits.getMemory());
        registerFile = processorUnits.getRegisterFile();
        apsr = processorUnits.getAPSR();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitTst method, of class LogicalVisitor.
     */
    @Test
    public void testVisitTst() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "tst r1, r2\n";
        processor.run(assembler.assemble(code));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "tst r1, r1\n";
        processor.run(assembler.assemble(code));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
    }

    /**
     * Test of visitTeq method, of class LogicalVisitor.
     */
    @Test
    public void testVisitTeq() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "teq r1, r2\n";
        processor.run(assembler.assemble(code));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "teq r1, r1\n";
        processor.run(assembler.assemble(code));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "teq r1, 0\n";
        processor.run(assembler.assemble(code));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
    }

    /**
     * Test of visitAnd method, of class LogicalVisitor.
     */
    @Test
    public void testVisitAnd() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "and r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "and r0, r1, 0xf\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.", 0xf, registerFile.getValue("r0"));
    }

    /**
     * Test of visitAnds method, of class LogicalVisitor.
     */
    @Test
    public void testVisitAnds() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "ands r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "ands r0, r1, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
    }

    /**
     * Test of visitEor method, of class LogicalVisitor.
     */
    @Test
    public void testVisitEor() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "eor r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("XOR result is wrong.", 1, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "eor r0, r1, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("XOR result is wrong.", 0, registerFile.getValue("r0"));
    }

    /**
     * Test of visitEors method, of class LogicalVisitor.
     */
    @Test
    public void testVisitEors() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "eors r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("XOR result is wrong.", 1, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "eors r0, r1, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("XOR result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "eors r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("XOR result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());
    }

    /**
     * Test of visitOrr method, of class LogicalVisitor.
     */
    @Test
    public void testVisitOrr() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "orr r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", 1, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "orr r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));

        code = "ldr r1, =0x0f0f0f0f\n"
                + "ldr r2, = 0xf0f0f0f0\n"
                + "orr r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitOrrs method, of class LogicalVisitor.
     */
    @Test
    public void testVisitOrrs() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "orrs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", 1, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "orrs r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0x0f0f0f0f\n"
                + "ldr r2, = 0xf0f0f0f0\n"
                + "orrs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());

        code = "mov r1, 0\n"
                + "mov r2, 0\n"
                + "orrs r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
    }

    /**
     * Test of visitOrn method, of class LogicalVisitor.
     */
    @Test
    public void testVisitOrn() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "orn r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", -2, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "orn r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", 0xffff_ffff | ~0, registerFile.getValue("r0"));

        code = "ldr r1, =0x0f0f0f0f\n"
                + "ldr r2, = 0xf0f0f0f0\n"
                + "orn r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", 0x0f0f_0f0f, registerFile.getValue("r0"));
    }

    /**
     * Test of visitOrns method, of class LogicalVisitor.
     */
    @Test
    public void testVisitOrns() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "orns r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", -2, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "orns r0, r1, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.",
                0xffff_ffff | ~0xffff_ffff,
                registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "orns r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", -1, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());

        code = "mov r1, #0\n"
                + "ldr r2, =0xffffffff\n"
                + "orns r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("OR result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
    }

    /**
     * Test of visitBic method, of class LogicalVisitor.
     */
    @Test
    public void testVisitBic() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "bic r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));

        code = "ldr r1, =0xffffffff\n"
                + "bic r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.", 0xffff_ffff & ~0, registerFile.getValue("r0"));

        code = "ldr r1, =0x0f0f0f0f\n"
                + "ldr r2, = 0xf0f0f0f0\n"
                + "bic r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.",
                0x0f0f_0f0f & ~0xf0f0_f0f0,
                registerFile.getValue("r0"));
    }

    /**
     * Test of visitBics method, of class LogicalVisitor.
     */
    @Test
    public void testVisitBics() {
        String code = "mov r1, #0\n"
                + "mov r2, #1\n"
                + "bics r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "bics r0, r1, r1\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.",
                0xffff_ffff & ~0xffff_ffff,
                registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());

        code = "ldr r1, =0xffffffff\n"
                + "bics r0, r1, 0\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.", 0xffff_ffff | ~0, registerFile.getValue("r0"));
        assertTrue("Negative flag is wrong.", apsr.isNegative());
        assertFalse("Zero flag is wrong.", apsr.isZero());

        code = "ldr r2, =0xffffffff\n"
                + "bics r0, r1, r2\n";
        processor.run(assembler.assemble(code));
        assertEquals("AND result is wrong.", 0, registerFile.getValue("r0"));
        assertFalse("Negative flag is wrong.", apsr.isNegative());
        assertTrue("Zero flag is wrong.", apsr.isZero());
    }
}
