package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class ReverseVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final RegisterVisitor registerVisitor;

    public ReverseVisitor(RegisterFile registerFile) {
        this.registerFile = registerFile;
        this.registerVisitor = new RegisterVisitor();
    }

    @Override
    public Void visitRbit(ProcessorParser.RbitContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        registerFile.setValue(destRegister, Integer.reverse(value));
        return null;
    }
}
