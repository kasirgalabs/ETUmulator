package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class ShiftedRegisterVisitor extends ProcessorBaseVisitor<Integer> {
    private final RegisterFile registerFile;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;

    public ShiftedRegisterVisitor(RegisterFile registerFile) {
        this.registerFile = registerFile;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Integer visitRegisterShiftedByRegister(
            ProcessorParser.RegisterShiftedByRegisterContext ctx) {
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftOption = visitShiftOption(ctx.shiftOption());
        int shiftAmount = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        return shift(value, Shift.values()[shiftOption], shiftAmount);
    }

    @Override
    public Integer visitRegisterShiftedByConstant(
            ProcessorParser.RegisterShiftedByConstantContext ctx) {
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int shiftOption = visitShiftOption(ctx.shiftOption());
        int shiftAmount = numberVisitor.visit(ctx.number());
        return shift(value, Shift.values()[shiftOption], shiftAmount);
    }

    @Override
    public Integer visitShiftOption(ProcessorParser.ShiftOptionContext ctx) {
        return Shift.valueOf(ctx.getText().toUpperCase()).ordinal();
    }

    private int shift(int value, Shift shiftOption, int shiftAmount) {
        switch(shiftOption) {
            case ASR:
                return value >> shiftAmount;
            case LSL:
                return value << shiftAmount;
            case LSR:
                return value >>> shiftAmount;
            default:
                return Integer.rotateRight(value, shiftAmount);
        }
    }
}
