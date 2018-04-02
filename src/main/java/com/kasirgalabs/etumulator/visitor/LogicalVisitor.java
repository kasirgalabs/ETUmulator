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
package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class LogicalVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final RegisterVisitor registerVisitor;
    private final Operand2Visitor operand2Visitor;

    public LogicalVisitor(RegisterFile registerFile, APSR apsr) {
        this.registerFile = registerFile;
        this.apsr = apsr;
        registerVisitor = new RegisterVisitor();
        operand2Visitor = new Operand2Visitor(registerFile);
    }

    @Override
    public Void visitTst(ProcessorParser.TstContext ctx) {
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        apsr.updateNZ(left & right);
        return null;
    }

    @Override
    public Void visitTeq(ProcessorParser.TeqContext ctx) {
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        apsr.updateNZ(left ^ right);
        return null;
    }

    @Override
    public Void visitAnd(ProcessorParser.AndContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left & right);
        return null;
    }

    @Override
    public Void visitAnds(ProcessorParser.AndsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, apsr.updateNZ(left & right));
        return null;
    }

    @Override
    public Void visitEor(ProcessorParser.EorContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left ^ right);
        return null;
    }

    @Override
    public Void visitEors(ProcessorParser.EorsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, apsr.updateNZ(left ^ right));
        return null;
    }

    @Override
    public Void visitOrr(ProcessorParser.OrrContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left | right);
        return null;
    }

    @Override
    public Void visitOrrs(ProcessorParser.OrrsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, apsr.updateNZ(left | right));
        return null;
    }

    @Override
    public Void visitOrn(ProcessorParser.OrnContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left | ~right);
        return null;
    }

    @Override
    public Void visitOrns(ProcessorParser.OrnsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, apsr.updateNZ(left | ~right));
        return null;
    }

    @Override
    public Void visitBic(ProcessorParser.BicContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, left & ~right);
        return null;
    }

    @Override
    public Void visitBics(ProcessorParser.BicsContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int left = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int right = operand2Visitor.visit(ctx.operand2());
        registerFile.setValue(destRegister, apsr.updateNZ(left & ~right));
        return null;
    }
}
