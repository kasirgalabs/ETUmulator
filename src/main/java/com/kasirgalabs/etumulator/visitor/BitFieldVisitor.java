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

public class BitFieldVisitor extends ProcessorBaseVisitor<Void> {
    private final RegisterFile registerFile;
    private final APSR apsr;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;

    public BitFieldVisitor(RegisterFile registerFile, APSR apsr) {
        this.registerFile = registerFile;
        this.apsr = apsr;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
    }


    public Void visitBitField(ProcessorParser.BfiContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rd());
        int srcRegister =  registerFile.getValue(registerVisitor.visit(ctx.rn()));
        int lsbPositionValue;
        int widthValue;
        int bitMaskValue;
        int value;

        if(ctx.imm16() != null) {
            lsbPositionValue = numberVisitor.visit(ctx.imm16());
        }
        if(ctx.imm8() != null) {
            widthValue = numberVisitor.visit(ctx.imm8());
        }

//        bitMaskValue = convertIntoToHex(widthValue);
//        srcRegister &= bitMaskValue;
//        value = srcRegister << lsbPositionValue;

//        registerFile.setValue(destRegister, value);
            return null;
        }

    public int convertIntoToHex(int IntValue)
	{
		int HexValue = 0;
                //HexValue = IntValue

		return HexValue;
	}
}

