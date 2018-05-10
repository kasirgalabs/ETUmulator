package com.kasirgalabs.etumulator.navigator;

import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.util.GUISafeDispatcher;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import org.junit.Test;

public class APSRStatusTest {
    private APSR apsr;
    private APSRStatus apsrStatus;

    public APSRStatusTest() throws IOException, InterruptedException, ExecutionException,
            TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            apsr = new APSR(new GUISafeDispatcher());
            apsrStatus = new APSRStatus(apsr);
            ClassLoader classLoader = getClass().getClassLoader();
            FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/APSRStatus.fxml"));
            fxmlLoader.setControllerFactory((Class<?> param) -> {
                return apsrStatus;
            });
            fxmlLoader.load();
            return null;
        });
        Platform.runLater(futureTask);
        futureTask.get(5, TimeUnit.SECONDS);
    }

    /**
     * Test of update method, of class APSRStatus.
     *
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     * @throws java.util.concurrent.TimeoutException
     */
    @Test
    public void testUpdate() throws InterruptedException, ExecutionException, TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Void> future = executor.submit(() -> {
            apsr.setNegative(true);
            apsr.setNegative(false);
            apsr.setZero(true);
            apsr.setZero(false);
            apsr.setCarry(true);
            apsr.setCarry(false);
            apsr.setOverflow(true);
            apsr.setOverflow(false);
            apsr.reset();
            return null;
        });
        future.get(5, TimeUnit.SECONDS);
    }
}
