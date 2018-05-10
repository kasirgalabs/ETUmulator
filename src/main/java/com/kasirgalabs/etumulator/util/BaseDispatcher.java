package com.kasirgalabs.etumulator.util;

import java.util.ArrayList;
import java.util.List;

public class BaseDispatcher implements Dispatcher {
    private final List<Observer> observers;

    public BaseDispatcher() {
        observers = new ArrayList<>(10);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Class<?> clazz, Object arg) {
        for(int i = 0; i < observers.size(); i++) {
            observers.get(i).update(clazz, arg);
        }
    }

    @Override
    public void notifyObservers(Class<?> clazz) {
        notifyObservers(clazz, null);
    }
}
