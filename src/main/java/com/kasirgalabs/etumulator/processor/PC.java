package com.kasirgalabs.etumulator.processor;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.util.BaseDispatcher;
import com.kasirgalabs.etumulator.util.Dispatcher;
import com.kasirgalabs.etumulator.util.Observable;
import com.kasirgalabs.etumulator.util.Observer;

/**
 * A program counter is a register in a that contains the address (location) of the instruction
 * being executed at the current time. As each instruction gets executed, the program counter
 * increases its stored value by 1. After each instruction is executed, the program counter
 * points to the next instruction in the sequence. When the processor restarts, the program counter
 * reverts to 0.
 * <p>
 * This class represents an observable object. After a state change on PC value, all of it's
 * observers will be notified through default {@link BaseDispatcher} or the Dispatcher initialized
 * during construction.
 *
 * @author Görkem Mülayim
 */
@Singleton
public class PC implements Observable {
    private int pc;
    private final Dispatcher dispatcher;

    /**
     * Construct a PC with {@link BaseDispatcher}.
     *
     * @see BaseDispatcher
     */
    public PC() {
        dispatcher = new BaseDispatcher();
    }

    /**
     * Construct a PC with the given dispatcher as parameter.
     *
     * @param dispatcher The dispatcher which will be used to notify observers.
     *
     * @see Dispatcher
     */
    @Inject
    public PC(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Adds an observer to the list of observers for this object.
     *
     * @param observer The observer to be deleted.
     *
     * @see Observer
     */
    @Override
    public void addObserver(Observer observer) {
        dispatcher.addObserver(observer);
    }

    /**
     * Increases the value of PC by one.
     */
    public void increment() {
        pc++;
        dispatcher.notifyObservers(PC.class);
    }

    /**
     * @param value The PC value to set.
     */
    public void setValue(int value) {
        pc = value;
        dispatcher.notifyObservers(PC.class);
    }

    /**
     * @return The value of PC.
     */
    public int getValue() {
        return pc;
    }

    /**
     * Sets PC value to zero.
     */
    public void reset() {
        pc = 0;
        dispatcher.notifyObservers(PC.class);
    }
}
