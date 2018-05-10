package com.kasirgalabs.etumulator.processor;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.util.BaseDispatcher;
import com.kasirgalabs.etumulator.util.Dispatcher;
import com.kasirgalabs.etumulator.util.Observable;
import com.kasirgalabs.etumulator.util.Observer;
import java.util.LinkedList;

@Singleton
public class Stack implements Observable {
    private final LinkedList<Integer> list = new LinkedList<>();
    private final Dispatcher dispatcher;

    public Stack() {
        this.dispatcher = new BaseDispatcher();
    }

    @Inject
    public Stack(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void addObserver(Observer observer) {
        dispatcher.addObserver(observer);
    }

    public void push(Integer item) {
        list.push(item);
        dispatcher.notifyObservers(Stack.class, "push");
    }

    public int pop() {
        if(list.isEmpty()) {
            return (int) (Math.random() * Integer.MAX_VALUE);
        }
        int result = list.pop();
        dispatcher.notifyObservers(Stack.class, "pop");
        return result;
    }

    public int peek() {
        return list.peek();
    }

    public void reset() {
        list.clear();
        dispatcher.notifyObservers(Stack.class, "clear");
    }
}
