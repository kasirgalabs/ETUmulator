/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.processor;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.Test;

public class UARTTest implements Observer {
    private final RegisterFile registerFile;
    private final UART uart;
    private char mockInput;
    private char output;

    public UARTTest() {
        registerFile = new RegisterFile();
        uart = new UART(registerFile);
    }

    /**
     * Test of read method, of class UART.
     *
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     * @throws java.util.concurrent.TimeoutException
     */
    @Test
    public void testRead() throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Semaphore semaphore = new Semaphore(0);
        mockInput = '5';
        Future<Void> future = executor.submit(() -> {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            ScheduledFuture<?> releaserHandle = scheduler.schedule(() -> {
                semaphore.release();
            }, 5, TimeUnit.SECONDS);
            uart.read();
            releaserHandle.get(5, TimeUnit.SECONDS);
            return null;
        });
        semaphore.tryAcquire(15, TimeUnit.SECONDS);
        uart.feed(mockInput);
        future.get(25, TimeUnit.SECONDS);
        assertEquals("UART read result is wrong.", mockInput, registerFile.getValue("r0"));
    }

    /**
     * Test of write method, of class UART.
     */
    @Test
    public void testWrite() {
        uart.addObserver(this);
        mockInput = '5';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = 'A';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = '\n';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = '\0';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = '?';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = 'Z';
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = Character.MAX_VALUE;
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);

        mockInput = Character.MIN_SURROGATE;
        registerFile.setValue("r0", mockInput);
        uart.write();
        assertEquals("UART read result is wrong.", mockInput, output);
    }

    @Override
    public void update(Class<?> clazz, Object arg) {
        output = (char) arg;
    }
}
