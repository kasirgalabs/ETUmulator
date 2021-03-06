package com.kasirgalabs.etumulator.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.kasirgalabs.etumulator.lang.Assembler;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

public class GUISafeProcessorTest {
    private final GUISafeProcessor processor;
    private final GUISafeProcessorUnits processorUnits;
    private final Assembler assembler;

    public GUISafeProcessorTest() throws InterruptedException {
        processorUnits = new GUISafeProcessorUnits();
        processor = new GUISafeProcessor(processorUnits);
        assembler = new Assembler(processorUnits.getMemory());
    }

    @After
    public void tearDown() {
        processor.terminate();
    }

    /**
     * Test of run method, of class GUISafeProcessor.
     *
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     * @throws java.util.concurrent.TimeoutException
     */
    @Test
    @Ignore
    public void testRun() throws InterruptedException, ExecutionException, TimeoutException {
/*        new JFXPanel();
        String code = "nop\n"
                + "nop\n"
                + "nop\n";
        processor.run(assembler.assemble(code));
        processor.waitForComplete(5, TimeUnit.SECONDS);

        code = "mov r0, #1\n"
                + "label:\n"
                + "cmp r0, #100\n"
                + "beq exit\n"
                + "add r0, r0, #1\n"
                + "bl uart_write\n"
                + "b label\n"
                + "exit:\n";
        processor.run(assembler.assemble(code));
        processor.waitForComplete(5, TimeUnit.SECONDS);
        assertEquals("GUISafeProcessor does not work properly.", 100,
                processorUnits.getRegisterFile().getValue("r0"));

        code = "mov r0, #1\n"
                + "label:\n"
                + "cmp r0, #100\n"
                + "beq exit\n"
                + "add r0, r0, #1\n"
                + "push {r0}\n"
                + "b label\n"
                + "exit:\n";
        processor.run(assembler.assemble(code));
        processor.waitForComplete(5, TimeUnit.SECONDS);
        assertEquals("GUISafeProcessor does not work properly.", 100,
                processorUnits.getStack().peek());

        code = "here:\n"
                + "b here\n";
        processor.run(assembler.assemble(code));
        Thread.sleep(2000);
        processor.stop();

        code = "here:\n"
                + "b here\n";
        processor.run(assembler.assemble(code));
        processor.run(assembler.assemble(code));
        Thread.sleep(2000);
        try {
            processor.stop();
            processor.waitForComplete(5, TimeUnit.SECONDS);
            fail("GUISafeProcessor should throw CancellationException when stop method is called.");
        } catch(CancellationException ex) {
        }

        processorUnits.reset();
        code = "ldr r0, =0xfffffffe\n"
                + "push {r0}\n"
                + "pop {pc}\n";
        processor.run(assembler.assemble(code));
        try {
            processor.waitForComplete(5, TimeUnit.SECONDS);
            fail("GUISafeProcessor should throw exception when PC is negative.");
        } catch(ExecutionException ex) {
        }*/
    }
}
