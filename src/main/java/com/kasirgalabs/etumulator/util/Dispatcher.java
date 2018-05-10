package com.kasirgalabs.etumulator.util;

public interface Dispatcher {
    void addObserver(Observer observer);

    void notifyObservers(Class<?> clazz, Object arg);

    void notifyObservers(Class<?> clazz);
}
