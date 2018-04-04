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
package com.kasirgalabs.etumulator.lang;

import com.kasirgalabs.etumulator.visitor.Shift;
import com.kasirgalabs.thumb2.AssemblerBaseVisitor;
import com.kasirgalabs.thumb2.AssemblerLexer;
import com.kasirgalabs.thumb2.AssemblerParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.math.BigInteger;

public final class ConstantValidator extends AssemblerBaseVisitor<Integer> {
    private ConstantValidator() {
    }

    public static void validate(String code) {
        ConstantValidator constantValidator = new ConstantValidator();
        AssemblerLexer lexer = new AssemblerLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AssemblerParser parser = new AssemblerParser(tokens);
        AssemblerParser.ProgContext program = parser.prog();
        constantValidator.visit(program);
    }

    @Override
    public Integer visitRegisterShiftedByConstant(
            AssemblerParser.RegisterShiftedByConstantContext ctx) {
        int shiftOption = visitShiftOption(ctx.shiftOption());
        int shiftAmount = visit(ctx.number());
        switch(Shift.values()[shiftOption]) {
            case LSL:
                if(shiftAmount < 0 || shiftAmount > 31) {
                    throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                            + ": LSL allowed shifts 0-31");
                }
                break;
            case LSR:
                if(shiftAmount < 1 || shiftAmount > 32) {
                    throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                            + ": LSR allowed shifts 1-32");
                }
                break;
            case ASR:
                if(shiftAmount < 1 || shiftAmount > 32) {
                    throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                            + ": ASR allowed shifts 1-32");
                }
                break;
            case ROR:
                if(shiftAmount < 1 || shiftAmount > 31) {
                    throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                            + ": ROR allowed shifts 1-31");
                }
                break;

        }
        return null;
    }

    @Override
    public Integer visitShiftOption(AssemblerParser.ShiftOptionContext ctx) {
        return Shift.valueOf(ctx.getText().toUpperCase()).ordinal();
    }

    @Override
    public Integer visitImm16(AssemblerParser.Imm16Context ctx) {
        int constant = visitNumber(ctx.number());
        if(constant < 0 || constant > 65535) {
            throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                    + ": 16 bit immediate can be in range 0 to 65535");
        }
        return constant;
    }

    @Override
    public Integer visitImm12(AssemblerParser.Imm12Context ctx) {
        int constant = visitNumber(ctx.number());
        if(constant < 0 || constant > 4095) {
            throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                    + ": 12 bit immediate can be in range 0 to 4095");
        }
        return constant;
    }

    @Override
    public Integer visitImm8m(AssemblerParser.Imm8mContext ctx) {
        int constant = visitNumber(ctx.number());
        if(constant < 0 || constant > 256) {
            throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                    + ": 8 bit immediate can be in range 0 to 256");
        }
        return constant;
    }

    @Override
    public Integer visitBfc(AssemblerParser.BfcContext ctx) {
        int lsb = visitNumber(ctx.lsb().number());
        int width = visitNumber(ctx.width().number());
        int maxLength=32;
        if(lsb+width>maxLength){
            throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                    + ": the sum of lsb and width cannot exceed the value of 32");
        }
        return visitChildren(ctx);
    }

    @Override
    public Integer visitBfi(AssemblerParser.BfiContext ctx) {
        int lsb = visitNumber(ctx.lsb().number());
        int width = visitNumber(ctx.width().number());
        int maxLength = 32;
        if (lsb + width > maxLength) {
            throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                    + ": the sum of lsb and width cannot exceed the value of 32");
        }
        return null;
    }

    @Override
    public Integer visitSh(AssemblerParser.ShContext ctx) {
        return visitNumber(ctx.number());
    }

    @Override
    public Integer visitNumber(AssemblerParser.NumberContext ctx) {
        if(ctx.DECIMAL() != null) {
            return new BigInteger(ctx.DECIMAL().getText()).intValue();
        }
        return new BigInteger(ctx.HEX().getText().substring(2), 16).intValue();
    }

    @Override
    public Integer visitOffset(AssemblerParser.OffsetContext ctx) {
        int shiftAmount = visit(ctx.number());
        if(shiftAmount < 0 || shiftAmount > 3) {
            throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                    + ": Shift amount can be in range 0 to 3");
        }
        return shiftAmount;
    }

    @Override
    public Integer visitOpsh(AssemblerParser.OpshContext ctx) {
        int shiftAmount = visit(ctx.sh());
        if(shiftAmount < 0 || shiftAmount > 3) {
            throw new NumberFormatException("Number error on line " + ctx.start.getLine()
                    + ": Shift amount can be in range 0 to 3");
        }
        return shiftAmount;
    }
}
