package com.kasirgalabs.etumulator.util;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;

public class GUISafeDispatcher extends BaseDispatcher {
    @Override
    public void notifyObservers(Class<?> clazz, Object arg) {
        final CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            super.notifyObservers(clazz, arg);
            latch.countDown();
        });
        try {
            if(!Platform.isFxApplicationThread()) {
                latch.await();
            }
        } catch(InterruptedException ex) {
            throw new CancellationException();
        }
    }

    @Override
    public void notifyObservers(Class<?> clazz) {
        notifyObservers(clazz, null);
    }
}
