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
