package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class Operand2Visitor extends ProcessorBaseVisitor<Integer> {
    private final RegisterFile registerFile;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;
    private final ShiftedRegisterVisitor shiftedRegister;

    public Operand2Visitor(RegisterFile registerFile) {
        this.registerFile = registerFile;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
        shiftedRegister = new ShiftedRegisterVisitor(registerFile);
    }

    @Override
    public Integer visitOperand2(ProcessorParser.Operand2Context ctx) {
        if(ctx.rm() != null) {
            return registerFile.getValue(registerVisitor.visit(ctx.rm()));
        }
        else if(ctx.registerShiftedByRegister() != null) {
            return shiftedRegister.visit(ctx.registerShiftedByRegister());
        }
        else if(ctx.registerShiftedByConstant() != null) {
            return shiftedRegister.visit(ctx.registerShiftedByConstant());
        }
        return numberVisitor.visit(ctx.imm8m());
    }
}
