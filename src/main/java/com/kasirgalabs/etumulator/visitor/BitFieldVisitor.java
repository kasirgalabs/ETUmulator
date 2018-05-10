package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class BitFieldVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;

    public BitFieldVisitor(RegisterFile registerFile) {
        this.registerFile = registerFile;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Void visitBfc(ProcessorParser.BfcContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int lsb=numberVisitor.visit(ctx.lsb());
        int width=numberVisitor.visit(ctx.width());
        int bitClearValue=0;
        for(int i=lsb;i<lsb+width;i++){
            bitClearValue+=1<<i;
        }
        bitClearValue=~bitClearValue;
        registerFile.setValue(destRegister, registerFile.getValue(destRegister) & bitClearValue);
        return null;
    }

    public Void visitBfi(ProcessorParser.BfiContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rd()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int width = numberVisitor.visit(ctx.width());
        int lsb = numberVisitor.visit(ctx.lsb());
        int bitFieldClearValue = 0;
        for (int i = lsb; i < lsb + width; i++) {
            bitFieldClearValue += 1 << i;
        }
        left = left & bitFieldClearValue;
        right = right << lsb;
        for (int i = 0; i < lsb; i++) {
            right = right & ~(1 << i);
        }
        for (int i = width + lsb; i < 32; i++) {
            right = right & ~(1 << i);
        }
        registerFile.setValue(destRegister, left | right);
        return null;
    }
}
