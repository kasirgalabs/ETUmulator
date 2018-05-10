package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.RegisterFile;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class MemoryAddressVisitor extends ProcessorBaseVisitor<Integer> {
    private final RegisterFile registerFile;
    private final RegisterVisitor registerVisitor;
    private final NumberVisitor numberVisitor;

    public MemoryAddressVisitor(RegisterFile registerFile) {
        this.registerFile = registerFile;
        registerVisitor = new RegisterVisitor();
        numberVisitor = new NumberVisitor();
    }

    @Override
    public Integer visitMemoryAddress(ProcessorParser.MemoryAddressContext ctx) {
        if(ctx.immediateOffset() != null) {
            return visitImmediateOffset(ctx.immediateOffset());
        }
        else if(ctx.postIndexedImmediate() != null) {
            return visit(ctx.postIndexedImmediate());
        }
        else if(ctx.registerOffset() != null) {
            return visit(ctx.registerOffset());
        }
        else {
            return visit(ctx.postIndexedRegister());
        }
    }

    @Override
    public Integer visitImmediateOffset(ProcessorParser.ImmediateOffsetContext ctx) {
        int value = registerFile.getValue(registerVisitor.visit(ctx.rn()));
        if(ctx.offset() != null) {
            value += numberVisitor.visit(ctx.offset());
        }
        return value;
    }

    @Override
    public Integer visitPostIndexedImmediate(ProcessorParser.PostIndexedImmediateContext ctx) {
        String srcRegister = registerVisitor.visit(ctx.rn());
        int value = registerFile.getValue(srcRegister);
        int offset = numberVisitor.visit(ctx.offset());
        registerFile.setValue(srcRegister, value + offset);
        return value;
    }

    @Override
    public Integer visitRegisterOffset(ProcessorParser.RegisterOffsetContext ctx) {
        String destRegister = registerVisitor.visit(ctx.rn());
        int value = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        if(ctx.opsh() != null) {
            int shiftAmount = numberVisitor.visit(ctx.opsh());
            value <<= shiftAmount;
        }
        return registerFile.getValue(destRegister) + value;
    }

    @Override
    public Integer visitPostIndexedRegister(ProcessorParser.PostIndexedRegisterContext ctx) {
        String srcRegister = registerVisitor.visit(ctx.rn());
        int value = registerFile.getValue(srcRegister);
        int offset = registerFile.getValue(registerVisitor.visit(ctx.rm()));
        if(ctx.opsh() != null) {
            int shiftAmount = numberVisitor.visit(ctx.opsh());
            offset <<= shiftAmount;
        }
        registerFile.setValue(srcRegister, value + offset);
        return value;
    }
}
