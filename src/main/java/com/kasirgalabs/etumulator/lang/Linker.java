package com.kasirgalabs.etumulator.lang;

import com.kasirgalabs.thumb2.AssemblerBaseVisitor;
import com.kasirgalabs.thumb2.AssemblerLexer;
import com.kasirgalabs.thumb2.AssemblerParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * If piece of code that given to the linker needs labels to be resolved, linker link them to make
 * it a executable. The executable output by the linker needs to be passed to {@link Loader} for
 * memory allocation to data section since loading is considered a separate process.
 * <p>
 * Branches and data can be made in terms of labels. This means processor doesn't know the absolute
 * addresses at which code and data will lie.<br>
 * For branches, linker calculates the address of instruction from target label and then replaces
 * the branch label with absolute address.<br>
 * For data, linker simply generates random addresses and modifies the code in a way that the
 * memory instruction labels are replaced with generated addresses.
 * <p>
 * Consider below code:<br>
 * <code>
 * &nbsp;b label<br>
 * &nbsp;nop<br>
 * &nbsp;label:<br>
 * &nbsp;&nbsp;&nbsp;nop<br>
 * </code>
 * after linking:<br>
 * <code>
 * &nbsp;b 2<br>
 * &nbsp;nop<br>
 * &nbsp;label:<br>
 * &nbsp;&nbsp;&nbsp;nop<br>
 * </code>
 * Linker simply replaced the branch label with the PC value of branch target.
 *
 * @author Görkem Mülayim
 * @see Loader
 * @see Linker#link(String)
 */
public class Linker extends AssemblerBaseVisitor<Void> {
    /**
     * Used to hold defined branch labels and their PC value.
     */
    private final Map<String, Integer> definedBranches;
    /**
     * Used to hold defined data labels and their value.
     */
    private final Map<String, Data> definedData;
    /**
     * To prevent mapping two data to same address we need to remember address space.
     */
    private final Set<Integer> addressBook;
    /**
     * Indicates whether linker is on it's second pass inspecting code.
     */
    private boolean secondPass;
    /**
     * Code that given to {@link Linker#link(String)} method which split by newline character.
     */
    private String[] code;

    /**
     * Constructs a Linker object.
     */
    public Linker() {
        definedBranches = new HashMap<>(16);
        definedData = new HashMap<>(16);
        addressBook = new HashSet<>(16);
    }

    @Override
    public Void visitB(AssemblerParser.BContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBeq(AssemblerParser.BeqContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBne(AssemblerParser.BneContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBcs(AssemblerParser.BcsContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBhs(AssemblerParser.BhsContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBcc(AssemblerParser.BccContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBlo(AssemblerParser.BloContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBmi(AssemblerParser.BmiContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBpl(AssemblerParser.BplContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBvs(AssemblerParser.BvsContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBvc(AssemblerParser.BvcContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBhi(AssemblerParser.BhiContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBls(AssemblerParser.BlsContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBge(AssemblerParser.BgeContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBlt(AssemblerParser.BltContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return visitChildren(ctx);
    }

    @Override
    public Void visitBgt(AssemblerParser.BgtContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBle(AssemblerParser.BleContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBal(AssemblerParser.BalContext ctx) {
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitBl(AssemblerParser.BlContext ctx) {
        String label = ctx.LABEL().getText();
        if("uart_read".equalsIgnoreCase(label) || "uart_write".equalsIgnoreCase(label)) {
            return null;
        }
        replaceLabelAddress(ctx, ctx.LABEL());
        return null;
    }

    @Override
    public Void visitRelocationDirective(AssemblerParser.RelocationDirectiveContext ctx) {
        if(!secondPass) {
            return null;
        }
        String label = ctx.LABEL().getText();
        if(!definedData.containsKey(label)) {
            throw new LabelError("\"" + label + "\" is not defined.");
        }
        int lineNumber = ctx.start.getLine() - 1;
        String temp = new String(code[lineNumber]);
        String address = Integer.toString(definedData.get(label).getAddress());
        temp = temp.replace(label, "#" + address);
        code[lineNumber] = temp;
        return null;
    }

    @Override
    public Void visitLabel(AssemblerParser.LabelContext ctx) {
        if(secondPass) {
            return null;
        }
        String label = ctx.LABEL().getText();
        if(definedData.containsKey(label) || definedData.containsKey(label)) {
            throw new LabelError("\"" + label + "\" is already defined.");
        }
        int address = ctx.start.getLine() - 1;
        definedBranches.put(label, address);
        return null;
    }

    @Override
    public Void visitData(AssemblerParser.DataContext ctx) {
        if(secondPass) {
            return null;
        }
        String label = ctx.LABEL().getText();
        if(definedBranches.containsKey(label) || definedData.containsKey(label)) {
            throw new LabelError("\"" + label + "\" is already defined.");
        }
        String asciz = ctx.asciz().STRING().getText().replaceAll("\"", "") + "\0";
        int address = generateAddress(asciz);
        definedData.put(label, new Data(asciz, address));
        return null;
    }

    /**
     * Returns an ExecutableCode that can then be run by the processor. If the given code contains
     * data section, linker will only generate memory addresses. Without loading process, referenced
     * data will be unpredictable.
     *
     * @param code The code that will be linked.
     *
     * @return The executable code.
     *
     * @throws LabelError If an undefined label used or duplicate labels exist.
     * @see Loader#load(Linker.ExecutableCode)
     */
    public ExecutableCode link(String code) throws LabelError {
        definedBranches.clear();
        definedData.clear();
        addressBook.clear();
        secondPass = false;
        this.code = parseCode(code);
        AssemblerLexer lexer = new AssemblerLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AssemblerParser parser = new AssemblerParser(tokens);
        AssemblerParser.ProgContext program = parser.prog();
        visit(program);
        secondPass = true;
        visit(program);
        List<Data> temp = new ArrayList<>(definedData.size());
        definedData.forEach((label, data) -> {
            temp.add(data);
        });
        return new ExecutableCode(this.code, temp);
    }

    /**
     * Splits the given string around matches of newline character. Then every match of strings are
     * converted to char string array.
     *
     * @param code Input string.
     *
     * @return The array of strings that computed by splitting this string around matches of the new
     *         line.
     */
    private String[] parseCode(String code) {
        String[] instructions = code.split("\\n");
        for(int i = 0; i < instructions.length; i++) {
            if(instructions[i].contains("/*") || instructions[i].contains("*/")) {
                instructions[i] = "\n";
            }
        }
        for(int i = 0; i < instructions.length; i++) {
            if(!instructions[i].equals("\n")) {
                String temp = instructions[i] + "\n";
                instructions[i] = temp;
            }
        }
        return instructions;
    }

    private void replaceLabelAddress(ParserRuleContext ctx, TerminalNode terminalNode) {
        if(!secondPass) {
            return;
        }
        String label = terminalNode.getText();
        if(!definedBranches.containsKey(label)) {
            throw new LabelError("\"" + label + "\" is not defined.");
        }
        int lineNumber = ctx.start.getLine() - 1;
        String temp = new String(code[lineNumber]);
        String address = Integer.toString(definedBranches.get(label));
        temp = temp.replace(label, address);
        code[lineNumber] = temp;
    }

    /**
     * Generates continuous addresses that are not in the {@link Linker#addressBook} for the given
     * data. The generated continuous address space length will be equal to <code>data</code>
     * length. Generated addresses then will be inserted into addressBook.
     *
     * @param data The data.
     *
     * @return The starting address of <code>data</code>.
     */
    private int generateAddress(String data) {
        boolean addressNotFound = true;
        Random rand = new Random();
        int address = 0;
        while(addressNotFound) {
            address = rand.nextInt(Integer.MAX_VALUE - data.length());
            for(int i = 0; i < data.length(); i++) {
                if(addressBook.contains(address + i)) {
                    break;
                }
                if(i == data.length() - 1) {
                    addressNotFound = false;
                }
            }
        }
        return address;
    }

    public static class ExecutableCode {
        private final String[] code;
        private final List<Data> data;

        private ExecutableCode(String[] code, List<Data> data) {
            this.code = new String[code.length];
            for(int i = 0; i < code.length; i++) {
                this.code[i] = code[i];
            }
            this.data = new ArrayList<>(data.size());
            for(int i = 0; i < data.size(); i++) {
                this.data.add(new Data(data.get(i)));
            }
        }

        public String[] getCode() {
            String[] temp = new String[code.length];
            for(int i = 0; i < code.length; i++) {
                temp[i] = code[i];
            }
            return temp;
        }

        public List<Data> getData() {
            List<Data> temp = new ArrayList<>(data.size());
            for(int i = 0; i < data.size(); i++) {
                temp.add(new Data(data.get(i)));
            }
            return temp;
        }
    }
}
