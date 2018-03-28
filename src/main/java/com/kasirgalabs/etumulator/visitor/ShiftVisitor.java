/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * Voidhis program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Voidhis program is distributed in the hope that it will be useful,
 * but WIVoidHOUVoid ANY WARRANVoidY; without even the implied warranty of
 * MERCHANVoidABILIVoidY or FIVoidNESS FOR A PARVoidICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class ShiftVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;

    public ShiftVisitor(RegisterFile registerFile, APSR apsr) {
        this.registerFile = registerFile;
        this.apsr = apsr;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Void visitAsr(ProcessorParser.AsrContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftAmount;
        if(ctx.rs() != null) {
            shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        }
        else {
            shiftAmount = numberVisitor.visit(ctx.sh());
        }
        shift(value, Shift.ASR, shiftAmount);
        registerFile.setValue(destRegister, shift(value, Shift.ASR, shiftAmount));
        return null;
    }

    @Override
    public Void visitAsrs(ProcessorParser.AsrsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftAmount;
        if(ctx.rs() != null) {
            shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        }
        else {
            shiftAmount = numberVisitor.visit(ctx.sh());
        }
        registerFile.setValue(destRegister, shiftUpdateAPSR(value, Shift.ASR, shiftAmount));
        return null;
    }

    @Override
    public Void visitLsl(ProcessorParser.LslContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftAmount;
        if(ctx.rs() != null) {
            shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        }
        else {
            shiftAmount = numberVisitor.visit(ctx.sh());
        }
        registerFile.setValue(destRegister, shift(value, Shift.LSL, shiftAmount));
        return null;
    }

    @Override
    public Void visitLsls(ProcessorParser.LslsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftAmount;
        if(ctx.rs() != null) {
            shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        }
        else {
            shiftAmount = numberVisitor.visit(ctx.sh());
        }
        registerFile.setValue(destRegister, shiftUpdateAPSR(value, Shift.LSL, shiftAmount));
        return null;
    }

    @Override
    public Void visitLsr(ProcessorParser.LsrContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftAmount;
        if(ctx.rs() != null) {
            shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        }
        else {
            shiftAmount = numberVisitor.visit(ctx.sh());
        }
        registerFile.setValue(destRegister, shift(value, Shift.LSR, shiftAmount));
        return null;
    }

    @Override
    public Void visitLsrs(ProcessorParser.LsrsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftAmount;
        if(ctx.rs() != null) {
            shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        }
        else {
            shiftAmount = numberVisitor.visit(ctx.sh());
        }
        registerFile.setValue(destRegister, shiftUpdateAPSR(value, Shift.LSR, shiftAmount));
        return null;
    }

    @Override
    public Void visitRor(ProcessorParser.RorContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftAmount;
        if(ctx.rs() != null) {
            shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        }
        else {
            shiftAmount = numberVisitor.visit(ctx.sh());
        }
        registerFile.setValue(destRegister, shift(value, Shift.ROR, shiftAmount));
        return null;
    }

    @Override
    public Void visitRors(ProcessorParser.RorsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftAmount;
        if(ctx.rs() != null) {
            shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        }
        else {
            shiftAmount = numberVisitor.visit(ctx.sh());
        }
        registerFile.setValue(destRegister, shiftUpdateAPSR(value, Shift.ROR, shiftAmount));
        return null;
    }

    @Override
    public Void visitRrx(ProcessorParser.RrxContext ctx) {
        final int NOT_USED = Integer.MAX_VALUE;
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        registerFile.setValue(destRegister, shift(value, Shift.RRX, NOT_USED));
        return null;
    }

    @Override
    public Void visitRrxs(ProcessorParser.RrxsContext ctx) {
        final int NOT_USED = Integer.MAX_VALUE;
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        registerFile.setValue(destRegister, shiftUpdateAPSR(value, Shift.RRX, NOT_USED));
        return null;
    }

    private int shift(int value, Shift shiftOption, int shiftAmount) {
        switch(shiftOption) {
            case ASR:
                return value >> shiftAmount;
            case LSL:
                return value << shiftAmount;
            case LSR:
                return value >>> shiftAmount;
            case ROR:
                return Integer.rotateRight(value, shiftAmount);
            case RRX:
                int result = value >>> 1;
                if(apsr.isCarry()) {
                    result |= 0x8000_0000;
                }
                return result;
            default:
                return value;
        }
    }

    private int shiftUpdateAPSR(int value, Shift shiftOption, int shiftAmount) {
        if(shiftOption == Shift.RRX) {
            return rrxUpdateAPSR(value);
        }
        apsr.setCarry(false);
        int result = shift(value, shiftOption, shiftAmount - 1);
        if(shiftOption == Shift.LSL) {
            if(Integer.numberOfLeadingZeros(result) == 0) {
                apsr.setCarry(true);
            }
        }
        else {
            if(Integer.numberOfTrailingZeros(result) == 0) {
                apsr.setCarry(true);
            }
        }
        result = shift(value, shiftOption, shiftAmount);
        apsr.updateNZ(result);
        return result;
    }

    private int rrxUpdateAPSR(int value) {
        final int NOT_USED = Integer.MAX_VALUE;
        boolean newCarry = Integer.numberOfTrailingZeros(value) == 0;
        int result = shift(value, Shift.RRX, NOT_USED);
        apsr.setCarry(newCarry);
        apsr.updateNZ(result);
        return result;
    }
}
