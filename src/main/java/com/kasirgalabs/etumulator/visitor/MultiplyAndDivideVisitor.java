package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class MultiplyAndDivideVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final RegisterVisitor registerVisitor;

    public MultiplyAndDivideVisitor(RegisterFile registerFile, APSR apsr) {
        this.registerFile = registerFile;
        this.apsr = apsr;
        registerVisitor = new RegisterVisitor();
    }

    @Override
    public Void visitMul(ProcessorParser.MulContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        registerFile.setValue(destRegister, left * right);
        return null;
    }

    @Override
    public Void visitMuls(ProcessorParser.MulsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        apsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitMla(ProcessorParser.MlaContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        result += registerFile.getValue(registerVisitor.visit(ctx.rn()));
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitMlas(ProcessorParser.MlasContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        result += registerFile.getValue(registerVisitor.visit(ctx.rn()));
        apsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitMls(ProcessorParser.MlsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rs()));
        int result = left * right;
        result = registerFile.getValue(registerVisitor.visit(ctx.rn())) - result;
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitSdiv(ProcessorParser.SdivContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        if(right == 0) {
            registerFile.setValue(destRegister, 0);
        }
        else {
            registerFile.setValue(destRegister, left / right);
        }
        return null;
    }

    @Override
    public Void visitUdiv(ProcessorParser.UdivContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        if(right == 0) {
            registerFile.setValue(destRegister, 0);
        }
        else {
            registerFile.setValue(destRegister, Integer.divideUnsigned(left, right));
        }
        return null;
    }
}
