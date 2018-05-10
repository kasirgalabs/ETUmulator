package com.kasirgalabs.etumulator.navigator;

import com.kasirgalabs.etumulator.processor.Stack;
import com.kasirgalabs.etumulator.util.GUISafeDispatcher;
import java.io.IOException;
import java.util.Random;
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

public class StackTabTest {
    private Stack stack;
    private Navigator navigator;
    private StackTab stackTab;

    public StackTabTest() throws IOException, InterruptedException, ExecutionException,
            TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            stack = new Stack(new GUISafeDispatcher());
            navigator = new Navigator();
            stackTab = new StackTab(stack, navigator);
            ClassLoader classLoader = getClass().getClassLoader();
            FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/StackTab.fxml"));
            fxmlLoader.setControllerFactory((Class<?> param) -> {
                return stackTab;
            });
            fxmlLoader.load();
            return null;
        });
        Platform.runLater(futureTask);
        futureTask.get(5, TimeUnit.SECONDS);
    }

    /**
     * Test of update method, of class StackTab.
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
            Random random = new Random();
            final int RANDOM = random.nextInt(Integer.MAX_VALUE);
            stack.push(5);
            stack.push(RANDOM);
            stack.push(RANDOM);
            stack.pop();
            stack.pop();
            stack.pop();
            stack.pop();
            stack.reset();
            return null;
        });
        future.get(5, TimeUnit.SECONDS);
    }
}
