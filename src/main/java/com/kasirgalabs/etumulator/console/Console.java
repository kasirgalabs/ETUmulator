package com.kasirgalabs.etumulator.console;

public interface Console {
    void write(char data);

    char read();

    String getText();

    void clearArea();
}
