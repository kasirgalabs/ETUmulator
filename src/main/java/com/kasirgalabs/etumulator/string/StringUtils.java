package com.kasirgalabs.etumulator.string;

public final class StringUtils {
    private static final int BIT_WIDTH = 32;

    private StringUtils() {
    }

    public static String toBinaryString(int value) {
        StringBuilder binaryString = new StringBuilder(32);
        int missingBits = BIT_WIDTH - Integer.toBinaryString(value).length();
        for(int i = 0; i < missingBits; i++) {
            binaryString.append('0');
        }
        binaryString.append(Integer.toBinaryString(value));
        return binaryString.toString();
    }

    public static String toHexString(int value) {
        StringBuilder hexString = new StringBuilder(32);
        hexString.append("0x");
        int missingBits = BIT_WIDTH / 4 - Integer.toHexString(value).length();
        for(int i = 0; i < missingBits; i++) {
            hexString.append('0');
        }
        hexString.append(Integer.toHexString(value));
        return hexString.toString();
    }

    public static String toAsciiString(int value) {
        return AsciiTable.getAscii((char) value);
    }
}
