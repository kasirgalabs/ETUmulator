package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;

public class RegisterVisitor extends ProcessorBaseVisitor<String> {
    @Override
    public String visitRd(ProcessorParser.RdContext ctx) {
        return ctx.REGISTER().getText();
    }

    @Override
    public String visitRn(ProcessorParser.RnContext ctx) {
        return ctx.REGISTER().getText();
    }

    @Override
    public String visitRm(ProcessorParser.RmContext ctx) {
        return ctx.REGISTER().getText();
    }

    @Override
    public String visitRs(ProcessorParser.RsContext ctx) {
        return ctx.REGISTER().getText();
    }
}
