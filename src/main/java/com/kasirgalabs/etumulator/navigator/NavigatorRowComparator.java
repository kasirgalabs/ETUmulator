package com.kasirgalabs.etumulator.navigator;

import java.util.Comparator;

public class NavigatorRowComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if("PC".equalsIgnoreCase(o1) && "LR".equalsIgnoreCase(o2)) {
            return -1;
        }
        if("LR".equalsIgnoreCase(o1) && "PC".equalsIgnoreCase(o2)) {
            return -1;
        }
        if("LR".equalsIgnoreCase(o1) || "LR".equalsIgnoreCase(o2)) {
            return -1;
        }
        if("PC".equalsIgnoreCase(o1) || "PC".equalsIgnoreCase(o2)) {
            return -1;
        }
        if(o1.contains("r")) {
            if(Integer.parseInt(o1.substring(1)) < Integer.parseInt(o2.substring(1))) {
                return -1;
            }
            return 1;
        }
        if(o1.contains("0x")) {
            return (int) Long.parseLong(o1.substring(2), 16)
                    < (int) Long.parseLong(o2.substring(2), 16)
                    ? -1 : 1;
        }
        if(o1.length() == 32) {
            return (int) Long.parseLong(o1, 2) < (int) Long.parseLong(o2, 2) ? -1 : 1;
        }
        return Integer.parseInt(o1) < Integer.parseInt(o2) ? -1 : 1;
    }
}
