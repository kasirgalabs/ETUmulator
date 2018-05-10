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
package com.kasirgalabs.etumulator.console;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.etumulator.processor.UART;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import org.junit.Before;
import org.junit.Test;

public class BaseConsoleTest extends UART {
    private Console console;
    private char mockInput;

    public BaseConsoleTest() {
        super(new RegisterFile());
    }

    @Before
    public void setUp() throws IOException, InterruptedException, ExecutionException,
            TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            console = new BaseConsole(this);
            ClassLoader classLoader = BaseConsoleTest.class.getClassLoader();
            FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/Console.fxml"));
            fxmlLoader.setControllerFactory((Class<?> param) -> {
                return console;
            });
            fxmlLoader.load();
            console = fxmlLoader.getController();
            return null;
        });
        Platform.runLater(futureTask);
        futureTask.get(10, TimeUnit.SECONDS);
    }

    /**
     * Test of initialize method, of class BaseConsole.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testInitialize() throws InterruptedException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        mockInput = 'X';
        System.err.print(mockInput);
        Thread.sleep(5000);
        String text = console.getText();
        char result = text.charAt(text.length() - 1);
        assertEquals("BaseConsole does not work properly.", mockInput, result);
    }

    /**
     * Test of write method, of class BaseConsole.
     *
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     * @throws java.util.concurrent.TimeoutException
     */
    @Test
    public void testWrite() throws InterruptedException, ExecutionException, TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            console.write('a');
            String text = console.getText();
            assertEquals("BaseConsole does not work properly.",
                    'a',
                    text.charAt(text.length() - 1));
            return null;
        });
        Platform.runLater(futureTask);
        futureTask.get(10, TimeUnit.SECONDS);
    }

    /**
     * Test of read method, of class BaseConsole.
     *
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     * @throws java.util.concurrent.TimeoutException
     */
    @Test
    public void testRead() throws InterruptedException, ExecutionException, TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            console.write('a');
            char output = console.read();
            assertEquals("BaseConsole does not work properly.", 'a', output);

            console.write('\n');
            output = console.read();
            assertEquals("BaseConsole does not work properly.", '\n', output);
            return null;
        });
        Platform.runLater(futureTask);
        futureTask.get(5, TimeUnit.SECONDS);
    }

    /**
     * Test of update and replaceText methods, of class BaseConsole.
     *
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     * @throws java.util.concurrent.TimeoutException
     */
    @Test
    public void testUpdateAndReplaceText() throws InterruptedException, ExecutionException,
            TimeoutException {
        assert !Platform.isFxApplicationThread();
        new JFXPanel();

        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            BaseConsole baseConsole = (BaseConsole) console;
            baseConsole.update(UART.class, 'x');
            baseConsole.getText();

            mockInput = 'x';
            baseConsole.update(UART.class, "read");
            baseConsole.replaceText(baseConsole.getText().length(),
                    baseConsole.getText().length(),
                    "x");

            mockInput = '\0';
            baseConsole.replaceText(baseConsole.getText().length(),
                    baseConsole.getText().length(),
                    "t");
            return null;
        });
        Platform.runLater(futureTask);
        futureTask.get(5, TimeUnit.SECONDS);
    }

    @Override
    public void feed(char data) {
        assertEquals("BaseConsole does not work properly.", mockInput, data);
    }
}
