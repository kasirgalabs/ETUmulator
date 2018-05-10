package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class ArithmeticVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final RegisterVisitor registerVisitor;
    private final Operand2Visitor operand2Visitor;
    private final NumberVisitor numberVisitor;

    public ArithmeticVisitor(RegisterFile registerFile, APSR apsr) {
        this.registerFile = registerFile;
        this.apsr = apsr;
        registerVisitor = new RegisterVisitor();
        operand2Visitor = new Operand2Visitor(registerFile);
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Void visitAdd(ProcessorParser.AddContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right;
        if(ctx.imm12() != null) {
            right = numberVisitor.visit(ctx.imm12());
        }
        else {
            right = operand2Visitor.visit(ctx.operand2());
        }
        registerFile.setValue(destRegister, left + right);
        return null;
    }

    @Override
    public Void visitAdds(ProcessorParser.AddsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateAPSR(left, right);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitAdc(ProcessorParser.AdcContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = left + right;
        if(apsr.isCarry()) {
            result++;
        }
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitAdcs(ProcessorParser.AdcsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateAPSR(left, right);
        if(apsr.isCarry()) {
            if(!apsr.isOverflow()) {
                result = addUpdateAPSR(result, 1);
            }
            else {
                result++;
            }
        }
        apsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitSub(ProcessorParser.SubContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right;
        if(ctx.imm12() != null) {
            right = numberVisitor.visit(ctx.imm12());
        }
        else {
            right = operand2Visitor.visit(ctx.operand2());
        }
        registerFile.setValue(destRegister, left - right);
        return null;
    }

    @Override
    public Void visitSubs(ProcessorParser.SubsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateAPSR(left, -right);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitSbc(ProcessorParser.SbcContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = left - right;
        if(!apsr.isCarry()) {
            result--;
        }
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitSbcs(ProcessorParser.SbcsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateAPSR(left, -right);
        if(!apsr.isCarry()) {
            if(!apsr.isOverflow()) {
                result = addUpdateAPSR(result, -1);
            }
            else {
                result--;
            }
        }
        apsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitRsb(ProcessorParser.RsbContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, right - left);
        return null;
    }

    @Override
    public Void visitRsbs(ProcessorParser.RsbsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateAPSR(right, -left);
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitRsc(ProcessorParser.RscContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = right - left;
        if(!apsr.isCarry()) {
            result--;
        }
        registerFile.setValue(destRegister, result);
        return null;
    }

    @Override
    public Void visitRscs(ProcessorParser.RscsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        int result = addUpdateAPSR(right, -left);
        if(!apsr.isCarry()) {
            if(!apsr.isOverflow()) {
                result = addUpdateAPSR(result, -1);
            }
            else {
                result--;
            }
        }
        apsr.updateNZ(result);
        registerFile.setValue(destRegister, result);
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
