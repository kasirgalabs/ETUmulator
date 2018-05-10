package com.kasirgalabs.etumulator.processor;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.util.BaseDispatcher;
import com.kasirgalabs.etumulator.util.Dispatcher;
import com.kasirgalabs.etumulator.util.Observable;
import com.kasirgalabs.etumulator.util.Observer;
import java.util.concurrent.CountDownLatch;

@Singleton
public class UART implements Observable {
    private RegisterFile registerFile;
    private char input;
    private final Dispatcher dispatcher;
    private CountDownLatch latch;

    public UART(RegisterFile registerFile) {
        this.registerFile = registerFile;
        this.dispatcher = new BaseDispatcher();
    }

    @Inject
    public UART(RegisterFile registerFile, Dispatcher dispatcher) {
        this.registerFile = registerFile;
        this.dispatcher = dispatcher;
    }

    public void setRegisterFile(RegisterFile registerFile) {
        this.registerFile = registerFile;
    }

    @Override
    public void addObserver(Observer observer) {
        dispatcher.addObserver(observer);
    }

    public void read() throws InterruptedException {
        latch = new CountDownLatch(1);
        dispatcher.notifyObservers(UART.class, "read");
        latch.await();
        registerFile.setValue("r0", input);
    }

    public void write() {
        dispatcher.notifyObservers(UART.class, (char) registerFile.getValue("r0"));
    }

    public void feed(char input) {
        this.input = input;
        latch.countDown();
    }
}
