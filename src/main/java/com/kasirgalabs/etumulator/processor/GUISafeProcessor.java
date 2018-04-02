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

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.lang.Linker.ExecutableCode;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Singleton
public class GUISafeProcessor extends BaseProcessor implements Callable<Void> {
    private final ExecutorService executor;
    private Future<Void> future;
    private ExecutableCode executableCode;

    @Inject
    public GUISafeProcessor(GUISafeProcessorUnits processorUnits) {
        super(processorUnits);
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void run(ExecutableCode executableCode) {
        this.executableCode = executableCode;
        if(future == null) {
            future = executor.submit(this);
            return;
        }
        if(future.isDone()) {
            future = executor.submit(this);
        }
        else {
            System.err.println("Processor is busy running previous task.");
        }
    }

    public void waitForComplete(long timeout, TimeUnit unit) throws InterruptedException,
            ExecutionException, TimeoutException {
        future.get(timeout, unit);
    }

    public void stop() {
        if(future != null) {
            future.cancel(true);
        }
    }

    public void terminate() {
        executor.shutdownNow();
    }

    @Override
    public Void call() {
        try {
            super.run(executableCode);
        } catch(IllegalPCException ex) {
            System.err.println(ex.getMessage());
            throw ex;
        } catch(CancellationException ex) {
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
