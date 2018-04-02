// Generated from Assembler.g4 by ANTLR 4.7
package com.kasirgalabs.thumb2;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AssemblerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AssemblerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(AssemblerParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(AssemblerParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(AssemblerParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(AssemblerParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#multiplyAndDivide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyAndDivide(AssemblerParser.MultiplyAndDivideContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(AssemblerParser.MoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#shift}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift(AssemblerParser.ShiftContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(AssemblerParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#logical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical(AssemblerParser.LogicalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#reverse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReverse(AssemblerParser.ReverseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(AssemblerParser.BranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#singleDataMemory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleDataMemory(AssemblerParser.SingleDataMemoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#stack}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStack(AssemblerParser.StackContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bitfield}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitfield(AssemblerParser.BitfieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(AssemblerParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#adds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdds(AssemblerParser.AddsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#adc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdc(AssemblerParser.AdcContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#adcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdcs(AssemblerParser.AdcsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#sub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(AssemblerParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#subs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubs(AssemblerParser.SubsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#sbc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSbc(AssemblerParser.SbcContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#sbcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSbcs(AssemblerParser.SbcsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rsb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsb(AssemblerParser.RsbContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rsbs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsbs(AssemblerParser.RsbsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rsc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsc(AssemblerParser.RscContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rscs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRscs(AssemblerParser.RscsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#mul}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(AssemblerParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#muls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuls(AssemblerParser.MulsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#mla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMla(AssemblerParser.MlaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#mlas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMlas(AssemblerParser.MlasContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#mls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMls(AssemblerParser.MlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#sdiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSdiv(AssemblerParser.SdivContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#udiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUdiv(AssemblerParser.UdivContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#mov}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMov(AssemblerParser.MovContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#movs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovs(AssemblerParser.MovsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#mvn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMvn(AssemblerParser.MvnContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#mvns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMvns(AssemblerParser.MvnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#movt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovt(AssemblerParser.MovtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#asr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsr(AssemblerParser.AsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#asrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsrs(AssemblerParser.AsrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#lsl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsl(AssemblerParser.LslContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#lsls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsls(AssemblerParser.LslsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#lsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsr(AssemblerParser.LsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#lsrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsrs(AssemblerParser.LsrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#ror}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRor(AssemblerParser.RorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rors}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRors(AssemblerParser.RorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rrx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRrx(AssemblerParser.RrxContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rrxs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRrxs(AssemblerParser.RrxsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#cmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmp(AssemblerParser.CmpContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#cmn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmn(AssemblerParser.CmnContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#tst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTst(AssemblerParser.TstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#teq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTeq(AssemblerParser.TeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(AssemblerParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#ands}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnds(AssemblerParser.AndsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#eor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEor(AssemblerParser.EorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#eors}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEors(AssemblerParser.EorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#orr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrr(AssemblerParser.OrrContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#orrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrrs(AssemblerParser.OrrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#orn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrn(AssemblerParser.OrnContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#orns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrns(AssemblerParser.OrnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBic(AssemblerParser.BicContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bics}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBics(AssemblerParser.BicsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rbit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRbit(AssemblerParser.RbitContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB(AssemblerParser.BContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#beq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeq(AssemblerParser.BeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBne(AssemblerParser.BneContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBcs(AssemblerParser.BcsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBhs(AssemblerParser.BhsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bcc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBcc(AssemblerParser.BccContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#blo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlo(AssemblerParser.BloContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bmi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBmi(AssemblerParser.BmiContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBpl(AssemblerParser.BplContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bvs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBvs(AssemblerParser.BvsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bvc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBvc(AssemblerParser.BvcContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bhi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBhi(AssemblerParser.BhiContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBls(AssemblerParser.BlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBge(AssemblerParser.BgeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#blt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlt(AssemblerParser.BltContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bgt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBgt(AssemblerParser.BgtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#ble}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBle(AssemblerParser.BleContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBal(AssemblerParser.BalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBl(AssemblerParser.BlContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#ldr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdr(AssemblerParser.LdrContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#ldrb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdrb(AssemblerParser.LdrbContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#ldrh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdrh(AssemblerParser.LdrhContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(AssemblerParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#strb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrb(AssemblerParser.StrbContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#strh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrh(AssemblerParser.StrhContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#memoryAddress}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemoryAddress(AssemblerParser.MemoryAddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#immediateOffset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImmediateOffset(AssemblerParser.ImmediateOffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#postIndexedImmediate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIndexedImmediate(AssemblerParser.PostIndexedImmediateContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#registerOffset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterOffset(AssemblerParser.RegisterOffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#postIndexedRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIndexedRegister(AssemblerParser.PostIndexedRegisterContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#relocationDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelocationDirective(AssemblerParser.RelocationDirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#push}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(AssemblerParser.PushContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#pop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(AssemblerParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#regList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegList(AssemblerParser.RegListContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRd(AssemblerParser.RdContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRn(AssemblerParser.RnContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRm(AssemblerParser.RmContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#rs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRs(AssemblerParser.RsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#operand2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperand2(AssemblerParser.Operand2Context ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#registerShiftedByRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterShiftedByRegister(AssemblerParser.RegisterShiftedByRegisterContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#registerShiftedByConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterShiftedByConstant(AssemblerParser.RegisterShiftedByConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#shiftOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftOption(AssemblerParser.ShiftOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bfi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBfi(AssemblerParser.BfiContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#bfc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBfc(AssemblerParser.BfcContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#lsb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsb(AssemblerParser.LsbContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#width}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidth(AssemblerParser.WidthContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#opsh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpsh(AssemblerParser.OpshContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#sh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSh(AssemblerParser.ShContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#offset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOffset(AssemblerParser.OffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#imm16}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm16(AssemblerParser.Imm16Context ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#imm12}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm12(AssemblerParser.Imm12Context ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#imm8}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm8(AssemblerParser.Imm8Context ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#imm8m}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm8m(AssemblerParser.Imm8mContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(AssemblerParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData(AssemblerParser.DataContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#asciz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsciz(AssemblerParser.AscizContext ctx);
	/**
	 * Visit a parse tree produced by {@link AssemblerParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(AssemblerParser.NumberContext ctx);
}