package com.kasirgalabs.etumulator;

import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Ignore;
import org.junit.Test;

public class ETUmulatorTest {
    private static final Logger LOGGER = Logger.getLogger(ETUmulatorTest.class.getName());
    private volatile boolean success;
    
    /**
     * Test of main method, of class ETUmulator.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    @Ignore
    public void testMain() throws InterruptedException {
/*        Thread thread = new Thread() { // Wrapper thread.
            @Override
            public void run() {
                success = true;
                try {
                    ETUmulator.main(new String[]{}); // Run JavaFX application.
                } catch(Throwable t) {
                    Throwable cause = t.getCause();
                    if(cause != null && cause.getClass().equals(InterruptedException.class)) {
                        // We expect to get this exception since we interrupted
                        // the JavaFX application.
                        return;
                    }
                    // This is not the exception we are looking for so log it.
                    LOGGER.log(Level.SEVERE, null, t);
                }
                success = false;
            }
        };
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(4000);  // Wait for 4 seconds before interrupting JavaFX application
        thread.interrupt();
        thread.join(1000); // Wait 1 second for our wrapper thread to finish.
        assertTrue("Something went wrong while starting GUI.", success);*/
    }
}
