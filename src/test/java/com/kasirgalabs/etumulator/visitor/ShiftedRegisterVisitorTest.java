package com.kasirgalabs.etumulator.visitor;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.lang.Assembler;
import com.kasirgalabs.etumulator.processor.BaseProcessor;
import com.kasirgalabs.etumulator.processor.BaseProcessorUnits;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import org.junit.Test;

public class ShiftedRegisterVisitorTest {
    private final Assembler assembler;
    private final RegisterFile registerFile;
    private final Processor processor;

    public ShiftedRegisterVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        assembler = new Assembler(processorUnits.getMemory());
        registerFile = processorUnits.getRegisterFile();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitRegisterShiftedByRegister method, of class ShiftedRegisterVisitor.
     */
    @Test
    public void testVisitRegisterShiftedByRegister() {
        String code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, lsl r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 3, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, lsr r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 1, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "ldr r2, =#0xffffffff\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, asr r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "ldr r2, =#0xffffffff\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, ror r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, ror r3\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", Integer.MIN_VALUE + 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitRegisterShiftedByConstant method, of class ShiftedRegisterVisitor.
     */
    @Test
    public void testVisitRegisterShiftedByConstant() {
        String code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "add r0, r1, r2, lsl 1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 3, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "add r0, r1, r2, lsr #1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 1, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "ldr r2, =#0xffffffff\n"
                + "add r0, r1, r2, asr #0x1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "ldr r2, =#0xffffffff\n"
                + "add r0, r1, r2, ror 0x1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", 0, registerFile.getValue("r0"));

        code = "mov r1, #1\n"
                + "mov r2, #1\n"
                + "mov r3, #1\n"
                + "add r0, r1, r2, ror 1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Shift result is wrong.", Integer.MIN_VALUE + 1, registerFile.getValue("r0"));
    }
}
