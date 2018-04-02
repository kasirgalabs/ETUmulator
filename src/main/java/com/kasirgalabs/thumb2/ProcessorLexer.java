// Generated from Processor.g4 by ANTLR 4.7
package com.kasirgalabs.thumb2;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ProcessorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, REGISTER=79, PC=80, 
		LR=81, LABEL=82, STRING=83, DECIMAL=84, HEX=85, DOUBLE_QUOTE=86, COLON=87, 
		LBRACE=88, RBRACE=89, ASSIGN=90, LBRACK=91, RBRACK=92, DASH=93, COMMA=94, 
		COMMENT=95, LINE_COMMENT=96, WS=97, EOL=98;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
		"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
		"T__41", "T__42", "T__43", "T__44", "T__45", "T__46", "T__47", "T__48", 
		"T__49", "T__50", "T__51", "T__52", "T__53", "T__54", "T__55", "T__56", 
		"T__57", "T__58", "T__59", "T__60", "T__61", "T__62", "T__63", "T__64", 
		"T__65", "T__66", "T__67", "T__68", "T__69", "T__70", "T__71", "T__72", 
		"T__73", "T__74", "T__75", "T__76", "T__77", "REGISTER", "PC", "LR", "LABEL", 
		"STRING", "CHARACTERS", "CHARACTER", "DECIMAL", "HEX", "DOUBLE_QUOTE", 
		"COLON", "LBRACE", "RBRACE", "ASSIGN", "LBRACK", "RBRACK", "DASH", "COMMA", 
		"COMMENT", "LINE_COMMENT", "WS", "EOL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'nop'", "'add'", "'adds'", "'adc'", "'adcs'", "'sub'", "'subs'", 
		"'sbc'", "'sbcs'", "'rsb'", "'rsbs'", "'rsc'", "'rscs'", "'mul'", "'muls'", 
		"'mla'", "'mlas'", "'mls'", "'sdiv'", "'udiv'", "'mov'", "'movs'", "'mvn'", 
		"'mvns'", "'movt'", "'asr'", "'asrs'", "'lsl'", "'lsls'", "'lsr'", "'lsrs'", 
		"'ror'", "'rors'", "'rrx'", "'rrxs'", "'cmp'", "'cmn'", "'tst'", "'teq'", 
		"'and'", "'ands'", "'eor'", "'eors'", "'orr'", "'orrs'", "'orn'", "'orns'", 
		"'bfi'", "'bic'", "'bics'", "'b'", "'beq'", "'bne'", "'bcs'", "'bhs'", 
		"'bcc'", "'blo'", "'bmi'", "'bpl'", "'bvs'", "'bvc'", "'bhi'", "'bls'", 
		"'bge'", "'blt'", "'bgt'", "'ble'", "'bal'", "'bl'", "'ldr'", "'ldrb'", 
		"'ldrh'", "'str'", "'strb'", "'strh'", "'push'", "'pop'", "'.asciz'", 
		null, "'pc'", "'lr'", null, null, null, null, "'\"'", "':'", "'{'", "'}'", 
		"'='", "'['", "']'", "'#'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "REGISTER", "PC", "LR", "LABEL", 
		"STRING", "DECIMAL", "HEX", "DOUBLE_QUOTE", "COLON", "LBRACE", "RBRACE", 
		"ASSIGN", "LBRACK", "RBRACK", "DASH", "COMMA", "COMMENT", "LINE_COMMENT", 
		"WS", "EOL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ProcessorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Processor.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2d\u029d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\""+
		"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3"+
		"\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3,\3,\3,\3,"+
		"\3,\3-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3"+
		"\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\64\3"+
		"\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\38\38"+
		"\38\38\39\39\39\39\3:\3:\3:\3:\3;\3;\3;\3;\3<\3<\3<\3<\3=\3=\3=\3=\3>"+
		"\3>\3>\3>\3?\3?\3?\3?\3@\3@\3@\3@\3A\3A\3A\3A\3B\3B\3B\3B\3C\3C\3C\3C"+
		"\3D\3D\3D\3D\3E\3E\3E\3E\3F\3F\3F\3G\3G\3G\3G\3H\3H\3H\3H\3H\3I\3I\3I"+
		"\3I\3I\3J\3J\3J\3J\3K\3K\3K\3K\3K\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3N\3N"+
		"\3N\3N\3O\3O\3O\3O\3O\3O\3O\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P"+
		"\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\5P\u023d\nP\3Q\3Q\3Q\3R"+
		"\3R\3R\3S\3S\6S\u0247\nS\rS\16S\u0248\3T\3T\6T\u024d\nT\rT\16T\u024e\3"+
		"T\3T\3U\6U\u0254\nU\rU\16U\u0255\3V\3V\3W\6W\u025b\nW\rW\16W\u025c\3X"+
		"\3X\3X\3X\6X\u0263\nX\rX\16X\u0264\3Y\3Y\3Z\3Z\3[\3[\3\\\3\\\3]\3]\3^"+
		"\3^\3_\3_\3`\3`\3a\3a\3b\3b\3b\3b\7b\u027d\nb\fb\16b\u0280\13b\3b\3b\3"+
		"b\3b\3b\3c\3c\3c\3c\7c\u028b\nc\fc\16c\u028e\13c\3c\3c\3d\6d\u0293\nd"+
		"\rd\16d\u0294\3d\3d\3e\5e\u029a\ne\3e\3e\3\u027e\2f\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177"+
		"A\u0081B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093"+
		"K\u0095L\u0097M\u0099N\u009bO\u009dP\u009fQ\u00a1R\u00a3S\u00a5T\u00a7"+
		"U\u00a9\2\u00ab\2\u00adV\u00afW\u00b1X\u00b3Y\u00b5Z\u00b7[\u00b9\\\u00bb"+
		"]\u00bd^\u00bf_\u00c1`\u00c3a\u00c5b\u00c7c\u00c9d\3\2\t\5\2C\\aac|\6"+
		"\2\62;C\\aac|\6\2\"\"\62;C\\c|\3\2\62;\5\2\62;CHch\4\2\f\f\17\17\5\2\13"+
		"\13\16\17\"\"\2\u02af\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2"+
		"\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2"+
		"w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2"+
		"\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b"+
		"\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2"+
		"\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d"+
		"\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2"+
		"\2\2\u00a7\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3"+
		"\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2"+
		"\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00c3\3\2\2\2\2\u00c5"+
		"\3\2\2\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2\2\3\u00cb\3\2\2\2\5\u00cf\3\2\2"+
		"\2\7\u00d3\3\2\2\2\t\u00d8\3\2\2\2\13\u00dc\3\2\2\2\r\u00e1\3\2\2\2\17"+
		"\u00e5\3\2\2\2\21\u00ea\3\2\2\2\23\u00ee\3\2\2\2\25\u00f3\3\2\2\2\27\u00f7"+
		"\3\2\2\2\31\u00fc\3\2\2\2\33\u0100\3\2\2\2\35\u0105\3\2\2\2\37\u0109\3"+
		"\2\2\2!\u010e\3\2\2\2#\u0112\3\2\2\2%\u0117\3\2\2\2\'\u011b\3\2\2\2)\u0120"+
		"\3\2\2\2+\u0125\3\2\2\2-\u0129\3\2\2\2/\u012e\3\2\2\2\61\u0132\3\2\2\2"+
		"\63\u0137\3\2\2\2\65\u013c\3\2\2\2\67\u0140\3\2\2\29\u0145\3\2\2\2;\u0149"+
		"\3\2\2\2=\u014e\3\2\2\2?\u0152\3\2\2\2A\u0157\3\2\2\2C\u015b\3\2\2\2E"+
		"\u0160\3\2\2\2G\u0164\3\2\2\2I\u0169\3\2\2\2K\u016d\3\2\2\2M\u0171\3\2"+
		"\2\2O\u0175\3\2\2\2Q\u0179\3\2\2\2S\u017d\3\2\2\2U\u0182\3\2\2\2W\u0186"+
		"\3\2\2\2Y\u018b\3\2\2\2[\u018f\3\2\2\2]\u0194\3\2\2\2_\u0198\3\2\2\2a"+
		"\u019d\3\2\2\2c\u01a1\3\2\2\2e\u01a5\3\2\2\2g\u01aa\3\2\2\2i\u01ac\3\2"+
		"\2\2k\u01b0\3\2\2\2m\u01b4\3\2\2\2o\u01b8\3\2\2\2q\u01bc\3\2\2\2s\u01c0"+
		"\3\2\2\2u\u01c4\3\2\2\2w\u01c8\3\2\2\2y\u01cc\3\2\2\2{\u01d0\3\2\2\2}"+
		"\u01d4\3\2\2\2\177\u01d8\3\2\2\2\u0081\u01dc\3\2\2\2\u0083\u01e0\3\2\2"+
		"\2\u0085\u01e4\3\2\2\2\u0087\u01e8\3\2\2\2\u0089\u01ec\3\2\2\2\u008b\u01f0"+
		"\3\2\2\2\u008d\u01f3\3\2\2\2\u008f\u01f7\3\2\2\2\u0091\u01fc\3\2\2\2\u0093"+
		"\u0201\3\2\2\2\u0095\u0205\3\2\2\2\u0097\u020a\3\2\2\2\u0099\u020f\3\2"+
		"\2\2\u009b\u0214\3\2\2\2\u009d\u0218\3\2\2\2\u009f\u023c\3\2\2\2\u00a1"+
		"\u023e\3\2\2\2\u00a3\u0241\3\2\2\2\u00a5\u0244\3\2\2\2\u00a7\u024a\3\2"+
		"\2\2\u00a9\u0253\3\2\2\2\u00ab\u0257\3\2\2\2\u00ad\u025a\3\2\2\2\u00af"+
		"\u025e\3\2\2\2\u00b1\u0266\3\2\2\2\u00b3\u0268\3\2\2\2\u00b5\u026a\3\2"+
		"\2\2\u00b7\u026c\3\2\2\2\u00b9\u026e\3\2\2\2\u00bb\u0270\3\2\2\2\u00bd"+
		"\u0272\3\2\2\2\u00bf\u0274\3\2\2\2\u00c1\u0276\3\2\2\2\u00c3\u0278\3\2"+
		"\2\2\u00c5\u0286\3\2\2\2\u00c7\u0292\3\2\2\2\u00c9\u0299\3\2\2\2\u00cb"+
		"\u00cc\7p\2\2\u00cc\u00cd\7q\2\2\u00cd\u00ce\7r\2\2\u00ce\4\3\2\2\2\u00cf"+
		"\u00d0\7c\2\2\u00d0\u00d1\7f\2\2\u00d1\u00d2\7f\2\2\u00d2\6\3\2\2\2\u00d3"+
		"\u00d4\7c\2\2\u00d4\u00d5\7f\2\2\u00d5\u00d6\7f\2\2\u00d6\u00d7\7u\2\2"+
		"\u00d7\b\3\2\2\2\u00d8\u00d9\7c\2\2\u00d9\u00da\7f\2\2\u00da\u00db\7e"+
		"\2\2\u00db\n\3\2\2\2\u00dc\u00dd\7c\2\2\u00dd\u00de\7f\2\2\u00de\u00df"+
		"\7e\2\2\u00df\u00e0\7u\2\2\u00e0\f\3\2\2\2\u00e1\u00e2\7u\2\2\u00e2\u00e3"+
		"\7w\2\2\u00e3\u00e4\7d\2\2\u00e4\16\3\2\2\2\u00e5\u00e6\7u\2\2\u00e6\u00e7"+
		"\7w\2\2\u00e7\u00e8\7d\2\2\u00e8\u00e9\7u\2\2\u00e9\20\3\2\2\2\u00ea\u00eb"+
		"\7u\2\2\u00eb\u00ec\7d\2\2\u00ec\u00ed\7e\2\2\u00ed\22\3\2\2\2\u00ee\u00ef"+
		"\7u\2\2\u00ef\u00f0\7d\2\2\u00f0\u00f1\7e\2\2\u00f1\u00f2\7u\2\2\u00f2"+
		"\24\3\2\2\2\u00f3\u00f4\7t\2\2\u00f4\u00f5\7u\2\2\u00f5\u00f6\7d\2\2\u00f6"+
		"\26\3\2\2\2\u00f7\u00f8\7t\2\2\u00f8\u00f9\7u\2\2\u00f9\u00fa\7d\2\2\u00fa"+
		"\u00fb\7u\2\2\u00fb\30\3\2\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7u\2\2\u00fe"+
		"\u00ff\7e\2\2\u00ff\32\3\2\2\2\u0100\u0101\7t\2\2\u0101\u0102\7u\2\2\u0102"+
		"\u0103\7e\2\2\u0103\u0104\7u\2\2\u0104\34\3\2\2\2\u0105\u0106\7o\2\2\u0106"+
		"\u0107\7w\2\2\u0107\u0108\7n\2\2\u0108\36\3\2\2\2\u0109\u010a\7o\2\2\u010a"+
		"\u010b\7w\2\2\u010b\u010c\7n\2\2\u010c\u010d\7u\2\2\u010d \3\2\2\2\u010e"+
		"\u010f\7o\2\2\u010f\u0110\7n\2\2\u0110\u0111\7c\2\2\u0111\"\3\2\2\2\u0112"+
		"\u0113\7o\2\2\u0113\u0114\7n\2\2\u0114\u0115\7c\2\2\u0115\u0116\7u\2\2"+
		"\u0116$\3\2\2\2\u0117\u0118\7o\2\2\u0118\u0119\7n\2\2\u0119\u011a\7u\2"+
		"\2\u011a&\3\2\2\2\u011b\u011c\7u\2\2\u011c\u011d\7f\2\2\u011d\u011e\7"+
		"k\2\2\u011e\u011f\7x\2\2\u011f(\3\2\2\2\u0120\u0121\7w\2\2\u0121\u0122"+
		"\7f\2\2\u0122\u0123\7k\2\2\u0123\u0124\7x\2\2\u0124*\3\2\2\2\u0125\u0126"+
		"\7o\2\2\u0126\u0127\7q\2\2\u0127\u0128\7x\2\2\u0128,\3\2\2\2\u0129\u012a"+
		"\7o\2\2\u012a\u012b\7q\2\2\u012b\u012c\7x\2\2\u012c\u012d\7u\2\2\u012d"+
		".\3\2\2\2\u012e\u012f\7o\2\2\u012f\u0130\7x\2\2\u0130\u0131\7p\2\2\u0131"+
		"\60\3\2\2\2\u0132\u0133\7o\2\2\u0133\u0134\7x\2\2\u0134\u0135\7p\2\2\u0135"+
		"\u0136\7u\2\2\u0136\62\3\2\2\2\u0137\u0138\7o\2\2\u0138\u0139\7q\2\2\u0139"+
		"\u013a\7x\2\2\u013a\u013b\7v\2\2\u013b\64\3\2\2\2\u013c\u013d\7c\2\2\u013d"+
		"\u013e\7u\2\2\u013e\u013f\7t\2\2\u013f\66\3\2\2\2\u0140\u0141\7c\2\2\u0141"+
		"\u0142\7u\2\2\u0142\u0143\7t\2\2\u0143\u0144\7u\2\2\u01448\3\2\2\2\u0145"+
		"\u0146\7n\2\2\u0146\u0147\7u\2\2\u0147\u0148\7n\2\2\u0148:\3\2\2\2\u0149"+
		"\u014a\7n\2\2\u014a\u014b\7u\2\2\u014b\u014c\7n\2\2\u014c\u014d\7u\2\2"+
		"\u014d<\3\2\2\2\u014e\u014f\7n\2\2\u014f\u0150\7u\2\2\u0150\u0151\7t\2"+
		"\2\u0151>\3\2\2\2\u0152\u0153\7n\2\2\u0153\u0154\7u\2\2\u0154\u0155\7"+
		"t\2\2\u0155\u0156\7u\2\2\u0156@\3\2\2\2\u0157\u0158\7t\2\2\u0158\u0159"+
		"\7q\2\2\u0159\u015a\7t\2\2\u015aB\3\2\2\2\u015b\u015c\7t\2\2\u015c\u015d"+
		"\7q\2\2\u015d\u015e\7t\2\2\u015e\u015f\7u\2\2\u015fD\3\2\2\2\u0160\u0161"+
		"\7t\2\2\u0161\u0162\7t\2\2\u0162\u0163\7z\2\2\u0163F\3\2\2\2\u0164\u0165"+
		"\7t\2\2\u0165\u0166\7t\2\2\u0166\u0167\7z\2\2\u0167\u0168\7u\2\2\u0168"+
		"H\3\2\2\2\u0169\u016a\7e\2\2\u016a\u016b\7o\2\2\u016b\u016c\7r\2\2\u016c"+
		"J\3\2\2\2\u016d\u016e\7e\2\2\u016e\u016f\7o\2\2\u016f\u0170\7p\2\2\u0170"+
		"L\3\2\2\2\u0171\u0172\7v\2\2\u0172\u0173\7u\2\2\u0173\u0174\7v\2\2\u0174"+
		"N\3\2\2\2\u0175\u0176\7v\2\2\u0176\u0177\7g\2\2\u0177\u0178\7s\2\2\u0178"+
		"P\3\2\2\2\u0179\u017a\7c\2\2\u017a\u017b\7p\2\2\u017b\u017c\7f\2\2\u017c"+
		"R\3\2\2\2\u017d\u017e\7c\2\2\u017e\u017f\7p\2\2\u017f\u0180\7f\2\2\u0180"+
		"\u0181\7u\2\2\u0181T\3\2\2\2\u0182\u0183\7g\2\2\u0183\u0184\7q\2\2\u0184"+
		"\u0185\7t\2\2\u0185V\3\2\2\2\u0186\u0187\7g\2\2\u0187\u0188\7q\2\2\u0188"+
		"\u0189\7t\2\2\u0189\u018a\7u\2\2\u018aX\3\2\2\2\u018b\u018c\7q\2\2\u018c"+
		"\u018d\7t\2\2\u018d\u018e\7t\2\2\u018eZ\3\2\2\2\u018f\u0190\7q\2\2\u0190"+
		"\u0191\7t\2\2\u0191\u0192\7t\2\2\u0192\u0193\7u\2\2\u0193\\\3\2\2\2\u0194"+
		"\u0195\7q\2\2\u0195\u0196\7t\2\2\u0196\u0197\7p\2\2\u0197^\3\2\2\2\u0198"+
		"\u0199\7q\2\2\u0199\u019a\7t\2\2\u019a\u019b\7p\2\2\u019b\u019c\7u\2\2"+
		"\u019c`\3\2\2\2\u019d\u019e\7d\2\2\u019e\u019f\7h\2\2\u019f\u01a0\7k\2"+
		"\2\u01a0b\3\2\2\2\u01a1\u01a2\7d\2\2\u01a2\u01a3\7k\2\2\u01a3\u01a4\7"+
		"e\2\2\u01a4d\3\2\2\2\u01a5\u01a6\7d\2\2\u01a6\u01a7\7k\2\2\u01a7\u01a8"+
		"\7e\2\2\u01a8\u01a9\7u\2\2\u01a9f\3\2\2\2\u01aa\u01ab\7d\2\2\u01abh\3"+
		"\2\2\2\u01ac\u01ad\7d\2\2\u01ad\u01ae\7g\2\2\u01ae\u01af\7s\2\2\u01af"+
		"j\3\2\2\2\u01b0\u01b1\7d\2\2\u01b1\u01b2\7p\2\2\u01b2\u01b3\7g\2\2\u01b3"+
		"l\3\2\2\2\u01b4\u01b5\7d\2\2\u01b5\u01b6\7e\2\2\u01b6\u01b7\7u\2\2\u01b7"+
		"n\3\2\2\2\u01b8\u01b9\7d\2\2\u01b9\u01ba\7j\2\2\u01ba\u01bb\7u\2\2\u01bb"+
		"p\3\2\2\2\u01bc\u01bd\7d\2\2\u01bd\u01be\7e\2\2\u01be\u01bf\7e\2\2\u01bf"+
		"r\3\2\2\2\u01c0\u01c1\7d\2\2\u01c1\u01c2\7n\2\2\u01c2\u01c3\7q\2\2\u01c3"+
		"t\3\2\2\2\u01c4\u01c5\7d\2\2\u01c5\u01c6\7o\2\2\u01c6\u01c7\7k\2\2\u01c7"+
		"v\3\2\2\2\u01c8\u01c9\7d\2\2\u01c9\u01ca\7r\2\2\u01ca\u01cb\7n\2\2\u01cb"+
		"x\3\2\2\2\u01cc\u01cd\7d\2\2\u01cd\u01ce\7x\2\2\u01ce\u01cf\7u\2\2\u01cf"+
		"z\3\2\2\2\u01d0\u01d1\7d\2\2\u01d1\u01d2\7x\2\2\u01d2\u01d3\7e\2\2\u01d3"+
		"|\3\2\2\2\u01d4\u01d5\7d\2\2\u01d5\u01d6\7j\2\2\u01d6\u01d7\7k\2\2\u01d7"+
		"~\3\2\2\2\u01d8\u01d9\7d\2\2\u01d9\u01da\7n\2\2\u01da\u01db\7u\2\2\u01db"+
		"\u0080\3\2\2\2\u01dc\u01dd\7d\2\2\u01dd\u01de\7i\2\2\u01de\u01df\7g\2"+
		"\2\u01df\u0082\3\2\2\2\u01e0\u01e1\7d\2\2\u01e1\u01e2\7n\2\2\u01e2\u01e3"+
		"\7v\2\2\u01e3\u0084\3\2\2\2\u01e4\u01e5\7d\2\2\u01e5\u01e6\7i\2\2\u01e6"+
		"\u01e7\7v\2\2\u01e7\u0086\3\2\2\2\u01e8\u01e9\7d\2\2\u01e9\u01ea\7n\2"+
		"\2\u01ea\u01eb\7g\2\2\u01eb\u0088\3\2\2\2\u01ec\u01ed\7d\2\2\u01ed\u01ee"+
		"\7c\2\2\u01ee\u01ef\7n\2\2\u01ef\u008a\3\2\2\2\u01f0\u01f1\7d\2\2\u01f1"+
		"\u01f2\7n\2\2\u01f2\u008c\3\2\2\2\u01f3\u01f4\7n\2\2\u01f4\u01f5\7f\2"+
		"\2\u01f5\u01f6\7t\2\2\u01f6\u008e\3\2\2\2\u01f7\u01f8\7n\2\2\u01f8\u01f9"+
		"\7f\2\2\u01f9\u01fa\7t\2\2\u01fa\u01fb\7d\2\2\u01fb\u0090\3\2\2\2\u01fc"+
		"\u01fd\7n\2\2\u01fd\u01fe\7f\2\2\u01fe\u01ff\7t\2\2\u01ff\u0200\7j\2\2"+
		"\u0200\u0092\3\2\2\2\u0201\u0202\7u\2\2\u0202\u0203\7v\2\2\u0203\u0204"+
		"\7t\2\2\u0204\u0094\3\2\2\2\u0205\u0206\7u\2\2\u0206\u0207\7v\2\2\u0207"+
		"\u0208\7t\2\2\u0208\u0209\7d\2\2\u0209\u0096\3\2\2\2\u020a\u020b\7u\2"+
		"\2\u020b\u020c\7v\2\2\u020c\u020d\7t\2\2\u020d\u020e\7j\2\2\u020e\u0098"+
		"\3\2\2\2\u020f\u0210\7r\2\2\u0210\u0211\7w\2\2\u0211\u0212\7u\2\2\u0212"+
		"\u0213\7j\2\2\u0213\u009a\3\2\2\2\u0214\u0215\7r\2\2\u0215\u0216\7q\2"+
		"\2\u0216\u0217\7r\2\2\u0217\u009c\3\2\2\2\u0218\u0219\7\60\2\2\u0219\u021a"+
		"\7c\2\2\u021a\u021b\7u\2\2\u021b\u021c\7e\2\2\u021c\u021d\7k\2\2\u021d"+
		"\u021e\7|\2\2\u021e\u009e\3\2\2\2\u021f\u0220\7t\2\2\u0220\u023d\7\62"+
		"\2\2\u0221\u0222\7t\2\2\u0222\u023d\7\63\2\2\u0223\u0224\7t\2\2\u0224"+
		"\u023d\7\64\2\2\u0225\u0226\7t\2\2\u0226\u023d\7\65\2\2\u0227\u0228\7"+
		"t\2\2\u0228\u023d\7\66\2\2\u0229\u022a\7t\2\2\u022a\u023d\7\67\2\2\u022b"+
		"\u022c\7t\2\2\u022c\u023d\78\2\2\u022d\u022e\7t\2\2\u022e\u023d\79\2\2"+
		"\u022f\u0230\7t\2\2\u0230\u023d\7:\2\2\u0231\u0232\7t\2\2\u0232\u023d"+
		"\7;\2\2\u0233\u0234\7t\2\2\u0234\u0235\7\63\2\2\u0235\u023d\7\62\2\2\u0236"+
		"\u0237\7t\2\2\u0237\u0238\7\63\2\2\u0238\u023d\7\63\2\2\u0239\u023a\7"+
		"t\2\2\u023a\u023b\7\63\2\2\u023b\u023d\7\64\2\2\u023c\u021f\3\2\2\2\u023c"+
		"\u0221\3\2\2\2\u023c\u0223\3\2\2\2\u023c\u0225\3\2\2\2\u023c\u0227\3\2"+
		"\2\2\u023c\u0229\3\2\2\2\u023c\u022b\3\2\2\2\u023c\u022d\3\2\2\2\u023c"+
		"\u022f\3\2\2\2\u023c\u0231\3\2\2\2\u023c\u0233\3\2\2\2\u023c\u0236\3\2"+
		"\2\2\u023c\u0239\3\2\2\2\u023d\u00a0\3\2\2\2\u023e\u023f\7r\2\2\u023f"+
		"\u0240\7e\2\2\u0240\u00a2\3\2\2\2\u0241\u0242\7n\2\2\u0242\u0243\7t\2"+
		"\2\u0243\u00a4\3\2\2\2\u0244\u0246\t\2\2\2\u0245\u0247\t\3\2\2\u0246\u0245"+
		"\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u0246\3\2\2\2\u0248\u0249\3\2\2\2\u0249"+
		"\u00a6\3\2\2\2\u024a\u024c\5\u00b1Y\2\u024b\u024d\5\u00a9U\2\u024c\u024b"+
		"\3\2\2\2\u024d\u024e\3\2\2\2\u024e\u024c\3\2\2\2\u024e\u024f\3\2\2\2\u024f"+
		"\u0250\3\2\2\2\u0250\u0251\5\u00b1Y\2\u0251\u00a8\3\2\2\2\u0252\u0254"+
		"\5\u00abV\2\u0253\u0252\3\2\2\2\u0254\u0255\3\2\2\2\u0255\u0253\3\2\2"+
		"\2\u0255\u0256\3\2\2\2\u0256\u00aa\3\2\2\2\u0257\u0258\t\4\2\2\u0258\u00ac"+
		"\3\2\2\2\u0259\u025b\t\5\2\2\u025a\u0259\3\2\2\2\u025b\u025c\3\2\2\2\u025c"+
		"\u025a\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u00ae\3\2\2\2\u025e\u025f\7\62"+
		"\2\2\u025f\u0260\7z\2\2\u0260\u0262\3\2\2\2\u0261\u0263\t\6\2\2\u0262"+
		"\u0261\3\2\2\2\u0263\u0264\3\2\2\2\u0264\u0262\3\2\2\2\u0264\u0265\3\2"+
		"\2\2\u0265\u00b0\3\2\2\2\u0266\u0267\7$\2\2\u0267\u00b2\3\2\2\2\u0268"+
		"\u0269\7<\2\2\u0269\u00b4\3\2\2\2\u026a\u026b\7}\2\2\u026b\u00b6\3\2\2"+
		"\2\u026c\u026d\7\177\2\2\u026d\u00b8\3\2\2\2\u026e\u026f\7?\2\2\u026f"+
		"\u00ba\3\2\2\2\u0270\u0271\7]\2\2\u0271\u00bc\3\2\2\2\u0272\u0273\7_\2"+
		"\2\u0273\u00be\3\2\2\2\u0274\u0275\7%\2\2\u0275\u00c0\3\2\2\2\u0276\u0277"+
		"\7.\2\2\u0277\u00c2\3\2\2\2\u0278\u0279\7\61\2\2\u0279\u027a\7,\2\2\u027a"+
		"\u027e\3\2\2\2\u027b\u027d\13\2\2\2\u027c\u027b\3\2\2\2\u027d\u0280\3"+
		"\2\2\2\u027e\u027f\3\2\2\2\u027e\u027c\3\2\2\2\u027f\u0281\3\2\2\2\u0280"+
		"\u027e\3\2\2\2\u0281\u0282\7,\2\2\u0282\u0283\7\61\2\2\u0283\u0284\3\2"+
		"\2\2\u0284\u0285\bb\2\2\u0285\u00c4\3\2\2\2\u0286\u0287\7\61\2\2\u0287"+
		"\u0288\7\61\2\2\u0288\u028c\3\2\2\2\u0289\u028b\n\7\2\2\u028a\u0289\3"+
		"\2\2\2\u028b\u028e\3\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2\2\2\u028d"+
		"\u028f\3\2\2\2\u028e\u028c\3\2\2\2\u028f\u0290\bc\2\2\u0290\u00c6\3\2"+
		"\2\2\u0291\u0293\t\b\2\2\u0292\u0291\3\2\2\2\u0293\u0294\3\2\2\2\u0294"+
		"\u0292\3\2\2\2\u0294\u0295\3\2\2\2\u0295\u0296\3\2\2\2\u0296\u0297\bd"+
		"\2\2\u0297\u00c8\3\2\2\2\u0298\u029a\7\17\2\2\u0299\u0298\3\2\2\2\u0299"+
		"\u029a\3\2\2\2\u029a\u029b\3\2\2\2\u029b\u029c\7\f\2\2\u029c\u00ca\3\2"+
		"\2\2\r\2\u023c\u0248\u024e\u0255\u025c\u0264\u027e\u028c\u0294\u0299\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}