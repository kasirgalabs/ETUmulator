/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.string;

public final class AsciiTable {
    private static final char NULL = 0;
    private static final char BELL = 7;
    private static final char BACKSPACE = 8;
    private static final char HORIZONTAL_TAB = 9;
    private static final char NEWLINE = 10;
    private static final char VERTICAL_TAB = 11;
    private static final char FORMFEED = 12;
    private static final char CARRIAGE_RETURN = 13;
    private static final char ESCAPE = 27;
    private static final char[] EXTENDED_ASCII_TABLE = {0x00C7, 0x00FC, 0x00E9, 0x00E2,
        0x00E4, 0x00E0, 0x00E5, 0x00E7, 0x00EA, 0x00EB, 0x00E8, 0x00EF,
        0x00EE, 0x00EC, 0x00C4, 0x00C5, 0x00C9, 0x00E6, 0x00C6, 0x00F4,
        0x00F6, 0x00F2, 0x00FB, 0x00F9, 0x00FF, 0x00D6, 0x00DC, 0x00A2,
        0x00A3, 0x00A5, 0x20A7, 0x0192, 0x00E1, 0x00ED, 0x00F3, 0x00FA,
        0x00F1, 0x00D1, 0x00AA, 0x00BA, 0x00BF, 0x2310, 0x00AC, 0x00BD,
        0x00BC, 0x00A1, 0x00AB, 0x00BB, 0x2591, 0x2592, 0x2593, 0x2502,
        0x2524, 0x2561, 0x2562, 0x2556, 0x2555, 0x2563, 0x2551, 0x2557,
        0x255D, 0x255C, 0x255B, 0x2510, 0x2514, 0x2534, 0x252C, 0x251C,
        0x2500, 0x253C, 0x255E, 0x255F, 0x255A, 0x2554, 0x2569, 0x2566,
        0x2560, 0x2550, 0x256C, 0x2567, 0x2568, 0x2564, 0x2565, 0x2559,
        0x2558, 0x2552, 0x2553, 0x256B, 0x256A, 0x2518, 0x250C, 0x2588,
        0x2584, 0x258C, 0x2590, 0x2580, 0x03B1, 0x00DF, 0x0393, 0x03C0,
        0x03A3, 0x03C3, 0x00B5, 0x03C4, 0x03A6, 0x0398, 0x03A9, 0x03B4,
        0x221E, 0x03C6, 0x03B5, 0x2229, 0x2261, 0x00B1, 0x2265, 0x2264,
        0x2320, 0x2321, 0x00F7, 0x2248, 0x00B0, 0x2219, 0x00B7, 0x221A,
        0x207F, 0x00B2, 0x25A0, 0x00A0};

    private AsciiTable() {
    }

    public static String getAscii(char code) {
        if(hasEscapeSequence(code)) {
            return escapeSequence(code);
        }
        if(isExtendedAscii(code)) {
            return Character.toString(EXTENDED_ASCII_TABLE[code - 0x80]);
        }
        if(code >= 0 && code <= 127) {
            return Character.toString(code);
        }
        return "Non-ASCII";
    }

    private static boolean hasEscapeSequence(char code) {
        return code >= 7 && code <= 13 || code == NULL || code == ESCAPE;
    }

    private static String escapeSequence(char code) {
        switch(code) {
            case NULL:
                return "\\0";
            case BELL:
                return "\\a";
            case BACKSPACE:
                return "\\b";
            case HORIZONTAL_TAB:
                return "\\t";
            case NEWLINE:
                return "\\n";
            case VERTICAL_TAB:
                return "\\v";
            case FORMFEED:
                return "\\f";
            case CARRIAGE_RETURN:
                return "\\r";
            case ESCAPE:
                return "\\e";
            default:
                return "0";
        }
    }

    private static boolean isExtendedAscii(char code) {
        return code >= 128 && code <= 255;
    }

}
