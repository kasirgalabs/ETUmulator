package com.kasirgalabs.etumulator.visitor;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.lang.Assembler;
import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.BaseProcessor;
import com.kasirgalabs.etumulator.processor.BaseProcessorUnits;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.UART;
import org.junit.Test;

public class BranchVisitorTest {
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final UART uart;
    private final Processor processor;
    private final Assembler assembler;

    public BranchVisitorTest() {
        ProcessorUnits processorUnits = new BaseProcessorUnits();
        registerFile = processorUnits.getRegisterFile();
        apsr = processorUnits.getAPSR();
        uart = processorUnits.getUART();
        processor = new BaseProcessor(processorUnits);
        assembler = new Assembler(null);
    }

    /**
     * Test of visitB method, of class BranchVisitor.
     */
    @Test
    public void testVisitB() {
        String code = "mov r0, #1\n"
                + "b target\n"
                + "add r0, r0, r0\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));

        code = "target:\n"
                + "cmp r0, #5\n"
                + "beq target2\n"
                + "add r0, r0, #1\n"
                + "b target\n"
                + "target2:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 5, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBeq method, of class BranchVisitor.
     */
    @Test
    public void testVisitBeq() {
        String code = "mov r0, #4\n"
                + "cmp r0, #4\n"
                + "beq target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 4, registerFile.getValue("r0"));

        code = "target:\n"
                + "cmp r0, #5\n"
                + "beq target2\n"
                + "add r0, r0, #1\n"
                + "b target\n"
                + "target2:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 5, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBne method, of class BranchVisitor.
     */
    @Test
    public void testVisitBne() {
        String code = "mov r0, #4\n"
                + "cmp r0, r1\n"
                + "bne target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 4, registerFile.getValue("r0"));

        code = "mov r0, #4\n"
                + "mov r1, #4\n"
                + "cmp r0, r1\n"
                + "bne target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBcs method, of class BranchVisitor.
     */
    @Test
    public void testVisitBcs() {
        apsr.setCarry(true);
        String code = "mov r0, #4\n"
                + "bcs target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 4, registerFile.getValue("r0"));

        apsr.setCarry(false);
        code = "mov r0, #4\n"
                + "bcs target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBhs method, of class BranchVisitor.
     */
    @Test
    public void testVisitBhs() {
        apsr.setCarry(false);
        String code = "mov r0, #4\n"
                + "bhs target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));

        apsr.setCarry(true);
        code = "mov r0, #4\n"
                + "bhs target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 4, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBcc method, of class BranchVisitor.
     */
    @Test
    public void testVisitBcc() {
        apsr.setCarry(true);
        String code = "mov r0, #4\n"
                + "bcc target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instrucbtion does not work properly.", 1, registerFile.getValue("r0"));

        apsr.setCarry(false);
        code = "mov r0, #4\n"
                + "bcc target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instrucbtion does not work properly.", 4, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBlo method, of class BranchVisitor.
     */
    @Test
    public void testVisitBlo() {
        apsr.setCarry(false);
        String code = "mov r0, #4\n"
                + "blo target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 4, registerFile.getValue("r0"));

        apsr.setCarry(true);
        code = "mov r0, #4\n"
                + "blo target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBmi method, of class BranchVisitor.
     */
    @Test
    public void testVisitBmi() {
        String code = "ldr r0, =0xffffffff\n"
                + "cmp r0, #0\n"
                + "bmi target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 0xffff_ffff, registerFile
                .getValue("r0"));

        code = "ldr r0, =0x7fffffff\n"
                + "cmp r0, #0\n"
                + "bmi target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBpl method, of class BranchVisitor.
     */
    @Test
    public void testVisitBpl() {
        String code = "ldr r0, =0xffffffff\n"
                + "cmp r0, #0\n"
                + "bpl target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));

        code = "ldr r0, =0x7fffffff\n"
                + "cmp r0, #0\n"
                + "bpl target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 0x7fff_ffff,
                registerFile.getValue("r0"));
    }

    /**
     * Test of visitBvs method, of class BranchVisitor.
     */
    @Test
    public void testVisitBvs() {
        String code = "ldr r0, =0x7fffffff\n"
                + "adds r0, r0, #1\n"
                + "bvs target\n"
                + "mov r0, #2\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", Integer.MIN_VALUE, registerFile
                .getValue("r0"));

        code = "mov r0, #2\n"
                + "cmp r0, #0\n"
                + "bvs target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBvc method, of class BranchVisitor.
     */
    @Test
    public void testVisitBvc() {
        String code = "ldr r0, =0x7fffffff\n"
                + "adds r0, r0, #1\n"
                + "bvc target\n"
                + "mov r0, #2\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "mov r0, #2\n"
                + "cmp r0, #0\n"
                + "bvc target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBhi method, of class BranchVisitor.
     */
    @Test
    public void testVisitBhi() {
        String code = "ldr r0, =#0x80000001\n"
                + "lsls r0, r0, #1\n"
                + "bhi target\n"
                + "mov r0, #4\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "mov r0, #0\n"
                + "cmp r0, #0\n"
                + "bhi target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));

        apsr.setCarry(false);
        code = "mov r0, #4\n"
                + "bhi target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBls method, of class BranchVisitor.
     */
    @Test
    public void testVisitBls() {
        String code = "mov r0, #1\n"
                + "lsls r0, r0, #1\n"
                + "bls target\n"
                + "mov r0, #0\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "ldr r0, =#0x80000001\n"
                + "lsls r0, r0, #1\n"
                + "bls target\n"
                + "mov r0, #3\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 3, registerFile.getValue("r0"));

        apsr.setCarry(true);
        apsr.setZero(false);
        code = "mov r0, #4\n"
                + "bls target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));

        apsr.setCarry(false);
        apsr.setZero(true);
        code = "mov r0, #4\n"
                + "bls target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 4, registerFile.getValue("r0"));

        apsr.setCarry(true);
        apsr.setZero(true);
        code = "mov r0, #8\n"
                + "bls target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 8, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBge method, of class BranchVisitor.
     */
    @Test
    public void testVisitBge() {
        String code = "mov r0, #2\n"
                + "mov r1, #1\n"
                + "cmp r0, r1\n"
                + "bge target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "ldr r0, =#0x80000000\n"
                + "ldr r1, =#1\n"
                + "cmp r0, r1\n"
                + "bge target\n"
                + "mov r0, #2\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "mov r0, 1\n"
                + "mov r1, #1\n"
                + "cmp r0, r1\n"
                + "bge target\n"
                + "mov r0, #2\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBlt method, of class BranchVisitor.
     */
    @Test
    public void testVisitBlt() {
        String code = "mov r0, #2\n"
                + "mov r1, #1\n"
                + "cmp r1, r0\n"
                + "blt target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "mov r0, #0\n"
                + "cmp r0, #0\n"
                + "blt target\n"
                + "mov r0, #2\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBgt method, of class BranchVisitor.
     */
    @Test
    public void testVisitBgt() {
        String code = "mov r0, #2\n"
                + "mov r1, #1\n"
                + "cmp r0, r1\n"
                + "bgt target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "ldr r0, =#0x80000000\n"
                + "ldr r1, =#1\n"
                + "cmp r0, r1\n"
                + "bgt target\n"
                + "mov r0, #2\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "mov r0, 1\n"
                + "mov r1, #1\n"
                + "cmp r0, r1\n"
                + "bgt target\n"
                + "mov r0, #2\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBle method, of class BranchVisitor.
     */
    @Test
    public void testVisitBle() {
        String code = "mov r0, #2\n"
                + "mov r1, #1\n"
                + "cmp r1, r0\n"
                + "ble target\n"
                + "mov r0, #1\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "mov r0, #1\n"
                + "cmp r0, #0\n"
                + "ble target\n"
                + "mov r0, #2\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "mov r0, 1\n"
                + "mov r1, #1\n"
                + "cmp r0, r1\n"
                + "ble target\n"
                + "mov r0, #2\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBal method, of class BranchVisitor.
     */
    @Test
    public void testVisitBal() {
        String code = "mov r0, #1\n"
                + "bal target\n"
                + "add r0, r0, r0\n"
                + "target:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));

        code = "mov r0, #0\n"
                + "target:\n"
                + "cmp r0, #5\n"
                + "beq target2\n"
                + "add r0, r0, #1\n"
                + "bal target\n"
                + "target2:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 5, registerFile.getValue("r0"));
    }

    /**
     * Test of visitBl method, of class BranchVisitor.
     */
    @Test
    public void testVisitBl() {
        String code = "mov r0, #1\n"
                + "label:\n"
                + "cmp r0, #100\n"
                + "beq exit\n"
                + "add r0, r0, #1\n"
                + "bl uart_write\n"
                + "b label\n"
                + "exit:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 100,
                registerFile.getValue("r0"));

        registerFile.reset();
        char expResult = '5';
        uart.addObserver((Class<?> clazz, Object arg) -> {
            uart.feed(expResult);
        });
        code = "bl uart_read\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", expResult,
                registerFile.getValue("r0"));

        code = "mov r0, #0\n"
                + "mov r1, #1\n"
                + "bl increment\n"
                + "add r1, r1, #1\n"
                + "increment:\n"
                + "add r0, r0, #1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r0"));
        assertEquals("Branch instruction does not work properly.", 1, registerFile.getValue("r1"));
    }
}
