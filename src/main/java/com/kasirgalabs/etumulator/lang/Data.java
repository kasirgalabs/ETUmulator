package com.kasirgalabs.etumulator.lang;

public class Data {
    private final String data;
    private final int address;

    public Data(Data data) {
        this.data = data.getValue();
        this.address = data.getAddress();
    }

    public Data(String data, int address) {
        this.data = data;
        this.address = address;
    }

    public String getValue() {
        return data;
    }

    public Integer getAddress() {
        return address;
    }
}
