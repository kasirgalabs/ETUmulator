// Generated from Processor.g4 by ANTLR 4.7
package com.kasirgalabs.thumb2;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProcessorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ProcessorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(ProcessorParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(ProcessorParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(ProcessorParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(ProcessorParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#multiplyAndDivide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyAndDivide(ProcessorParser.MultiplyAndDivideContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(ProcessorParser.MoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#shift}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift(ProcessorParser.ShiftContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(ProcessorParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#logical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical(ProcessorParser.LogicalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(ProcessorParser.BranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#singleDataMemory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleDataMemory(ProcessorParser.SingleDataMemoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#stack}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStack(ProcessorParser.StackContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bitfield}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitfield(ProcessorParser.BitfieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(ProcessorParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#adds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdds(ProcessorParser.AddsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#adc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdc(ProcessorParser.AdcContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#adcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdcs(ProcessorParser.AdcsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#sub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(ProcessorParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#subs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubs(ProcessorParser.SubsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#sbc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSbc(ProcessorParser.SbcContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#sbcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSbcs(ProcessorParser.SbcsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rsb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsb(ProcessorParser.RsbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rsbs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsbs(ProcessorParser.RsbsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rsc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsc(ProcessorParser.RscContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rscs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRscs(ProcessorParser.RscsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#mul}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(ProcessorParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#muls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuls(ProcessorParser.MulsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#mla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMla(ProcessorParser.MlaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#mlas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMlas(ProcessorParser.MlasContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#mls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMls(ProcessorParser.MlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#sdiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSdiv(ProcessorParser.SdivContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#udiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUdiv(ProcessorParser.UdivContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#mov}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMov(ProcessorParser.MovContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#movs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovs(ProcessorParser.MovsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#mvn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMvn(ProcessorParser.MvnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#mvns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMvns(ProcessorParser.MvnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#movt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovt(ProcessorParser.MovtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#asr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsr(ProcessorParser.AsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#asrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsrs(ProcessorParser.AsrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#lsl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsl(ProcessorParser.LslContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#lsls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsls(ProcessorParser.LslsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#lsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsr(ProcessorParser.LsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#lsrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsrs(ProcessorParser.LsrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#ror}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRor(ProcessorParser.RorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rors}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRors(ProcessorParser.RorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rrx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRrx(ProcessorParser.RrxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rrxs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRrxs(ProcessorParser.RrxsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#cmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmp(ProcessorParser.CmpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#cmn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmn(ProcessorParser.CmnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#tst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTst(ProcessorParser.TstContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#teq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTeq(ProcessorParser.TeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(ProcessorParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#ands}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnds(ProcessorParser.AndsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#eor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEor(ProcessorParser.EorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#eors}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEors(ProcessorParser.EorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#orr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrr(ProcessorParser.OrrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#orrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrrs(ProcessorParser.OrrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#orn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrn(ProcessorParser.OrnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#orns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrns(ProcessorParser.OrnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBic(ProcessorParser.BicContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bics}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBics(ProcessorParser.BicsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB(ProcessorParser.BContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#beq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeq(ProcessorParser.BeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBne(ProcessorParser.BneContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBcs(ProcessorParser.BcsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBhs(ProcessorParser.BhsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bcc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBcc(ProcessorParser.BccContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#blo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlo(ProcessorParser.BloContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bmi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBmi(ProcessorParser.BmiContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBpl(ProcessorParser.BplContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bvs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBvs(ProcessorParser.BvsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bvc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBvc(ProcessorParser.BvcContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bhi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBhi(ProcessorParser.BhiContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBls(ProcessorParser.BlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBge(ProcessorParser.BgeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#blt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlt(ProcessorParser.BltContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bgt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBgt(ProcessorParser.BgtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#ble}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBle(ProcessorParser.BleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBal(ProcessorParser.BalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBl(ProcessorParser.BlContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#ldr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdr(ProcessorParser.LdrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#ldrb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdrb(ProcessorParser.LdrbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#ldrh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdrh(ProcessorParser.LdrhContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(ProcessorParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#strb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrb(ProcessorParser.StrbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#strh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrh(ProcessorParser.StrhContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#memoryAddress}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemoryAddress(ProcessorParser.MemoryAddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#immediateOffset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImmediateOffset(ProcessorParser.ImmediateOffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#postIndexedImmediate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIndexedImmediate(ProcessorParser.PostIndexedImmediateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#registerOffset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterOffset(ProcessorParser.RegisterOffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#postIndexedRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIndexedRegister(ProcessorParser.PostIndexedRegisterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#push}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(ProcessorParser.PushContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#pop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(ProcessorParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#regList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegList(ProcessorParser.RegListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRd(ProcessorParser.RdContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRn(ProcessorParser.RnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRm(ProcessorParser.RmContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#rs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRs(ProcessorParser.RsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#operand2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperand2(ProcessorParser.Operand2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#registerShiftedByRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterShiftedByRegister(ProcessorParser.RegisterShiftedByRegisterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#registerShiftedByConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterShiftedByConstant(ProcessorParser.RegisterShiftedByConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#shiftOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftOption(ProcessorParser.ShiftOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#bfc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBfc(ProcessorParser.BfcContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#lsb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsb(ProcessorParser.LsbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#width}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidth(ProcessorParser.WidthContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#opsh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpsh(ProcessorParser.OpshContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#sh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSh(ProcessorParser.ShContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#offset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOffset(ProcessorParser.OffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#imm16}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm16(ProcessorParser.Imm16Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#imm12}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm12(ProcessorParser.Imm12Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#imm8m}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm8m(ProcessorParser.Imm8mContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(ProcessorParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData(ProcessorParser.DataContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#asciz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsciz(ProcessorParser.AscizContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessorParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(ProcessorParser.NumberContext ctx);
}