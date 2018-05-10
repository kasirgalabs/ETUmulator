package com.kasirgalabs.etumulator;

import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.swing.SwingUtilities;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * A JUnit {@link Rule} for running tests on the JavaFX thread and performing JavaFX initialization.
 * To include in your test case, add the following code:
 * <p>
 * {@literal @}Rule public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();
 */
public class JavaFXThread implements TestRule {
    /**
     * Flag for setting up the JavaFX, we only need to do this once for all tests.
     */
    private static boolean setupStatus;

    @Override
    public Statement apply(Statement statement, Description description) {
        return new JavaFXSatement(statement);
    }

    private static class JavaFXSatement extends Statement {
        private Throwable rethrownException;
        private final Statement statement;

        private JavaFXSatement(Statement aStatement) {
            statement = aStatement;
        }

        @Override
        public void evaluate() throws Throwable {
            if(!setupStatus) {
                setupJavaFX();
                setupStatus = true;
            }
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            Platform.runLater(() -> {
                try {
                    statement.evaluate();
                } catch(Throwable e) {
                    rethrownException = e;
                }
                countDownLatch.countDown();
            });
            countDownLatch.await();
            // If an exception was thrown by the statement during evaluation,
            // then re-throw it to fail the test.
            if(rethrownException != null) {
                throw rethrownException;
            }
        }

        private void setupJavaFX() throws InterruptedException {
            final CountDownLatch latch = new CountDownLatch(1);
            SwingUtilities.invokeLater(() -> {
                // Initializes JavaFX environment.
                new JFXPanel();
                latch.countDown();
            });
            latch.await();
        }
    }
}
