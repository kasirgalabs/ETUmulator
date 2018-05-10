package com.kasirgalabs.etumulator.processor;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.util.BaseDispatcher;
import com.kasirgalabs.etumulator.util.Dispatcher;
import com.kasirgalabs.etumulator.util.Observable;
import com.kasirgalabs.etumulator.util.Observer;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class RegisterFile implements Observable {
    private final Map<String, Integer> registers = new HashMap<>(14);
    private final Dispatcher dispatcher;

    public RegisterFile() {
        for(int i = 0; i < 13; i++) {
            registers.put("r" + Integer.toString(i), 0);
        }
        this.dispatcher = new BaseDispatcher();
    }

    @Inject
    public RegisterFile(Dispatcher dispatcher) {
        for(int i = 0; i < 13; i++) {
            registers.put("r" + Integer.toString(i), 0);
        }
        this.dispatcher = dispatcher;
    }

    @Override
    public void addObserver(Observer observer) {
        dispatcher.addObserver(observer);
    }

    public void setValue(String registerName, int value) {
        registers.replace(registerName, value);
        dispatcher.notifyObservers(RegisterFile.class, registerName);
    }

    public int getValue(String registerName) {
        return registers.get(registerName);
    }

    public void reset() {
        registers.clear();
        for(int i = 0; i < 13; i++) {
            registers.put("r" + Integer.toString(i), 0);
        }
        dispatcher.notifyObservers(RegisterFile.class, "clear");
    }
}
