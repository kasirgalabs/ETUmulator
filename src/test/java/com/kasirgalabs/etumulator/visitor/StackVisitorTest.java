package com.kasirgalabs.etumulator.visitor;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.lang.Assembler;
import com.kasirgalabs.etumulator.processor.BaseProcessor;
import com.kasirgalabs.etumulator.processor.BaseProcessorUnits;
import com.kasirgalabs.etumulator.processor.Processor;
import com.kasirgalabs.etumulator.processor.ProcessorUnits;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.Stack;
import org.junit.Test;

public class StackVisitorTest {
    private final ProcessorUnits processorUnits;
    private final Assembler assembler;
    private final RegisterFile registerFile;
    private final Stack stack;
    private final Processor processor;

    public StackVisitorTest() {
        processorUnits = new BaseProcessorUnits();
        assembler = new Assembler(processorUnits.getMemory());
        registerFile = processorUnits.getRegisterFile();
        stack = processorUnits.getStack();
        processor = new BaseProcessor(processorUnits);
    }

    /**
     * Test of visitPush method, of class StackVisitor.
     */
    @Test
    public void testVisitPush() {
        String code = "mov r0, #4\n"
                + "ldr r1, =0xffffffff\n"
                + "push {r0,r1}\n";
        processor.run(assembler.assemble(code));
        assertEquals("Push result is wrong.", 0xffff_ffff, stack.pop());
        assertEquals("Push result is wrong.", 4, stack.pop());

        code = "mov r0, #1\n"
                + "bl increment\n"
                + "add r0, r0, #1\n"
                + "b exit\n"
                + "increment:\n"
                + "push {lr}\n"
                + "pop {pc}\n"
                + "mov r0, #12\n"
                + "exit:\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 2, registerFile.getValue("r0"));

        code = "nop\n"
                + "nop\n"
                + "nop\n"
                + "push {pc}\n"
                + "pop {r0}\n";
        processor.run(assembler.assemble(code));
        assertEquals("Branch instruction does not work properly.", 3, registerFile.getValue("r0"));
    }

    /**
     * Test of visitPop method, of class StackVisitor.
     */
    @Test
    public void testVisitPop() {
        String code = "mov r0, #4\n"
                + "ldr r1, =0xffffffff\n"
                + "push {r0,r1}\n"
                + "pop {r1, r0}\n";
        processor.run(assembler.assemble(code));
        assertEquals("Pop result is wrong.", 4, registerFile.getValue("r0"));
        assertEquals("Pop result is wrong.", 0xffff_ffff, registerFile.getValue("r1"));

        code = "mov r0, #2\n"
                + "push {r0}\n"
                + "pop {lr}\n"
                + "push {lr}\n"
                + "pop {r1}\n";
        processor.run(assembler.assemble(code));
        assertEquals("Pop result is wrong.", 2, registerFile.getValue("r1"));
        stack.pop();

        code = "mov r0, #1\n";
        processor.run(assembler.assemble(code));
        assertEquals("Pop result is wrong.", 1, registerFile.getValue("r0"));
        assertEquals("Pop result is wrong.", 2, registerFile.getValue("r1"));
    }
}
