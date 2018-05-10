package com.kasirgalabs.etumulator.util;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Test;

public class GUISafeDispatcherTest implements Observer {
    private CountDownLatch latch;
    private String expResult;
    private final Dispatcher dispatcher;

    public GUISafeDispatcherTest() {
        dispatcher = new GUISafeDispatcher();
    }

    /**
     * Test of notifyObservers method, of class GUISafeDispatcher.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testNotifyObservers_Object() throws InterruptedException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();
        dispatcher.addObserver(this);

        expResult = "test0";
        latch = new CountDownLatch(1);
        dispatcher.notifyObservers(null, expResult);
        latch.await(5, TimeUnit.SECONDS);

        latch = new CountDownLatch(1);
        expResult = "test1";
        dispatcher.notifyObservers(null, expResult);
        latch.await(5, TimeUnit.SECONDS);

        latch = new CountDownLatch(1);
        expResult = "test2";
        dispatcher.notifyObservers(null, expResult);
        latch.await(5, TimeUnit.SECONDS);

    }

    /**
     * Test of notifyObservers method, of class GUISafeDispatcher.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testNotifyObservers() throws InterruptedException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();
        dispatcher.addObserver(this);

        latch = new CountDownLatch(1);
        dispatcher.notifyObservers(null);
        latch.await(5, TimeUnit.SECONDS);
    }

    @Override
    public void update(Class<?> clazz, Object arg) {
        if(arg != null) {
            assertEquals("Observable result is wrong.", expResult, arg);
        }
        latch.countDown();
    }
}
