package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class CompareVisitor extends ProcessorBaseVisitor<Void> {

    private final RegisterFile registerFile;
    private final APSR apsr;
    private final RegisterVisitor registerVisitor;
    private final Operand2Visitor operand2Visitor;

    public CompareVisitor(RegisterFile registerFile, APSR apsr) {
        this.registerFile = registerFile;
        this.apsr = apsr;
        registerVisitor = new RegisterVisitor();
        operand2Visitor = new Operand2Visitor(registerFile);
    }

    @Override
    public Void visitCmp(ProcessorParser.CmpContext ctx) {
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        addUpdateAPSR(left, -right);
        return null;
    }

    @Override
    public Void visitCmn(ProcessorParser.CmnContext ctx) {
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        addUpdateAPSR(left, right);
        return null;
    }

    private int addUpdateAPSR(int left, int right) {
        int result;
        boolean overflow;
        try {
            result = Math.addExact(left, right);
            overflow = false;
        } catch(ArithmeticException e) {
            overflow = true;
            result = left + right;
        }
        apsr.setOverflow(overflow);
        apsr.updateNZ(result);
        return result;
    }
}
