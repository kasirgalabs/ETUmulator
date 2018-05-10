package com.kasirgalabs.etumulator.navigator;

import com.kasirgalabs.etumulator.string.StringUtils;

public class NavigatorRow {
    private static Type type = Type.DECIMAL;
    private final String property;
    private int value;

    public NavigatorRow(String property, int value) {
        this.property = property;
        this.value = value;
    }

    public NavigatorRow(int property, int value) {
        this.property = Integer.toString(property);
        this.value = value;
    }

    public static void setType(Type type) {
        NavigatorRow.type = type;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        switch(type) {
            case DECIMAL:
                return Integer.toString(value);
            case HEX:
                return StringUtils.toHexString(value);
            case ASCII:
                return StringUtils.toAsciiString(value);
            case BINARY:
                return StringUtils.toBinaryString(value);
            default:
                return Integer.toString(value);
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public enum Type {
        DECIMAL, HEX, ASCII, BINARY;
    }
}
