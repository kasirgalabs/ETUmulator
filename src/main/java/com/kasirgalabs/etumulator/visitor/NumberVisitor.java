package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;
import java.math.BigInteger;

public class NumberVisitor extends ProcessorBaseVisitor<Integer> {
    @Override
    public Integer visitImm16(ProcessorParser.Imm16Context ctx) {
        return visitNumber(ctx.number());
    }

    @Override
    public Integer visitImm12(ProcessorParser.Imm12Context ctx) {
        return visitNumber(ctx.number());
    }

    @Override
    public Integer visitImm8m(ProcessorParser.Imm8mContext ctx) {
        return visitNumber(ctx.number());
    }

    @Override
    public Integer visitSh(ProcessorParser.ShContext ctx) {
        return visitNumber(ctx.number());
    }

    @Override
    public Integer visitLsb(ProcessorParser.LsbContext ctx) {
        return visitNumber(ctx.number());
    }

    @Override
    public Integer visitWidth(ProcessorParser.WidthContext ctx) {
        return visitNumber(ctx.number());
    }

    @Override
    public Integer visitNumber(ProcessorParser.NumberContext ctx) {
        if(ctx.DECIMAL() != null) {
            return new BigInteger(ctx.DECIMAL().getText()).intValue();
        }
        return new BigInteger(ctx.HEX().getText().substring(2), 16).intValue();
    }

    @Override
    public Integer visitOffset(ProcessorParser.OffsetContext ctx) {
        return visitNumber(ctx.number());
    }

    @Override
    public Integer visitOpsh(ProcessorParser.OpshContext ctx) {
        return visit(ctx.sh());
    }
}
