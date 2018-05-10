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
public class Memory implements Observable {
    private final Map<Integer, Byte> memory = new HashMap<>(16);
    private final Dispatcher dispatcher;

    public Memory() {
        this.dispatcher = new BaseDispatcher();
    }

    @Inject
    public Memory(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void addObserver(Observer observer) {
        dispatcher.addObserver(observer);
    }

    public int get(int address, Size size) {
        if(isAddressEmpty(address, size)) {
            return (int) (Math.random() * Integer.MAX_VALUE);
        }
        int value;
        int temp;
        switch(size) {
            case BYTE:
                return 0x0000_00ff & memory.get(address);
            case HALFWORD:
                value = memory.get(address);
                temp = memory.get(address + 1);
                temp <<= 8;
                value |= temp;
                return 0x0000_ffff & value;
            case WORD:
                value = memory.get(address);
                temp = memory.get(address + 1);
                temp <<= 8;
                value |= temp;
                temp = memory.get(address + 2);
                temp <<= 16;
                value |= temp;
                temp = memory.get(address + 3);
                temp <<= 16;
                value |= temp;
                return value;
            default:
                return memory.get(address);
        }
    }

    public void set(int address, int value, Size size) {
        switch(size) {
            case BYTE:
                memory.put(address, (byte) value);
                dispatcher.notifyObservers(Memory.class, address);
                break;
            case HALFWORD:
                memory.put(address, (byte) value);
                dispatcher.notifyObservers(Memory.class, address);
                memory.put(address + 1, (byte) (value >>> 8));
                dispatcher.notifyObservers(Memory.class, address);
                break;
            case WORD:
                memory.put(address, (byte) value);
                dispatcher.notifyObservers(Memory.class, address);
                memory.put(address + 1, (byte) (value >>> 8));
                dispatcher.notifyObservers(Memory.class, address);
                memory.put(address + 2, (byte) (value >>> 16));
                dispatcher.notifyObservers(Memory.class, address);
                memory.put(address + 3, (byte) (value >>> 24));
                dispatcher.notifyObservers(Memory.class, address);
        }
    }

    public boolean isAddressEmpty(int address, Size size) {
        boolean empty = false;
        for(int i = 0; i <= size.ordinal(); i++) {
            if(!memory.containsKey(address + i)) {
                empty = true;
            }
        }
        if(size == Size.WORD) {
            return empty || !memory.containsKey(address + 3);
        }
        return empty;
    }

    public void reset() {
        memory.clear();
        dispatcher.notifyObservers(Memory.class);
    }

    public enum Size {
        BYTE, HALFWORD, WORD
    }
}
