package com.kasirgalabs.etumulator.visitor;

import com.kasirgalabs.etumulator.processor.APSR;
import com.kasirgalabs.etumulator.processor.LR;
import com.kasirgalabs.etumulator.processor.PC;
import com.kasirgalabs.etumulator.processor.UART;
import com.kasirgalabs.thumb2.ProcessorBaseVisitor;
import com.kasirgalabs.thumb2.ProcessorParser;
import java.util.concurrent.CancellationException;

public class BranchVisitor extends ProcessorBaseVisitor<Void> {
    private final APSR apsr;
    private final UART uart;
    private final PC pc;
    private final LR lr;

    public BranchVisitor(APSR apsr, UART uart, PC pc, LR lr) {
        this.apsr = apsr;
        this.uart = uart;
        this.pc = pc;
        this.lr = lr;
    }

    @Override
    public Void visitB(ProcessorParser.BContext ctx) {
        pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        return null;
    }

    @Override
    public Void visitBeq(ProcessorParser.BeqContext ctx) {
        if(apsr.isZero()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBne(ProcessorParser.BneContext ctx) {
        if(!apsr.isZero()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBcs(ProcessorParser.BcsContext ctx) {
        if(apsr.isCarry()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBhs(ProcessorParser.BhsContext ctx) {
        if(apsr.isCarry()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBcc(ProcessorParser.BccContext ctx) {
        if(!apsr.isCarry()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBlo(ProcessorParser.BloContext ctx) {
        if(!apsr.isCarry()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBmi(ProcessorParser.BmiContext ctx) {
        if(apsr.isNegative()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBpl(ProcessorParser.BplContext ctx) {
        if(!apsr.isNegative()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBvs(ProcessorParser.BvsContext ctx) {
        if(apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBvc(ProcessorParser.BvcContext ctx) {
        if(!apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBhi(ProcessorParser.BhiContext ctx) {
        if(apsr.isCarry() && !apsr.isZero()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBls(ProcessorParser.BlsContext ctx) {
        if(!apsr.isCarry() || apsr.isZero()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBge(ProcessorParser.BgeContext ctx) {
        if(apsr.isNegative() == apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBlt(ProcessorParser.BltContext ctx) {
        if(apsr.isNegative() != apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBgt(ProcessorParser.BgtContext ctx) {
        if(!apsr.isZero() && apsr.isNegative() == apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBle(ProcessorParser.BleContext ctx) {
        if(apsr.isZero() || apsr.isNegative() != apsr.isOverflow()) {
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }

    @Override
    public Void visitBal(ProcessorParser.BalContext ctx) {
        pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        return null;
    }

    @Override
    public Void visitBl(ProcessorParser.BlContext ctx) {
        lr.setValue((int) (Math.random() * Integer.MAX_VALUE));
        if(ctx.LABEL() != null) {
            String label = ctx.LABEL().getText();
            if("uart_read".equalsIgnoreCase(label)) {
                try {
                    uart.read();
                } catch(InterruptedException ex) {
                    throw new CancellationException();
                }
            }
            else if("uart_write".equalsIgnoreCase(label)) {
                uart.write();
            }
        }
        else {
            lr.setValue(pc.getValue());
            pc.setValue(Integer.parseInt(ctx.DECIMAL().getText()));
        }
        return null;
    }
}
