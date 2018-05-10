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
import javafx.application.Platform;

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
        } catch (CancellationException ex) {
        } catch (Exception ex) {
            PopUp p = new PopUp();
            p.exceptionStacktraceToString(ex);
            Platform.runLater(() -> {
                p.createPopup();
            });
        }
        return null;
    }
}
