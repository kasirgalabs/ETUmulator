package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class MoveVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final RegisterVisitor registerVisitor;
    private final Operand2Visitor operand2Visitor;
    private final NumberVisitor numberVisitor;

    public MoveVisitor(RegisterFile registerFile, APSR apsr) {
        this.registerFile = registerFile;
        this.apsr = apsr;
        registerVisitor = new RegisterVisitor();
        operand2Visitor = new Operand2Visitor(registerFile);
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Void visitMov(ProcessorParser.MovContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value;
        if(ctx.imm16() != null) {
            value = numberVisitor.visit(ctx.imm16());
        }
        else {
            value = operand2Visitor.visit(ctx.operand2());
        }
        registerFile.setValue(destRegister, value);
        return null;
    }

    @Override
    public Void visitMovs(ProcessorParser.MovsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = operand2Visitor.visit(ctx.operand2());
        apsr.updateNZ(value);
        registerFile.setValue(destRegister, value);
        return null;
    }

    @Override
    public Void visitMvn(ProcessorParser.MvnContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = 0xffff_ffff ^ operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, value);
        return null;
    }

    @Override
    public Void visitMvns(ProcessorParser.MvnsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = 0xffff_ffff ^ operand2Visitor.visit(ctx.operand2());
        apsr.updateNZ(value);
        registerFile.setValue(destRegister, value);
        return null;
    }

    @Override
    public Void visitMovt(ProcessorParser.MovtContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int value = registerFile.getValue(destRegister);
        value &= 0x0000_ffff;
        value |= numberVisitor.visit(ctx.imm16()) << 16;
        registerFile.setValue(destRegister, value);
        return null;
    }
}
