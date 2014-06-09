// Generated from SQLANN.g4 by ANTLR 4.1

/*
 * Copyright (c) 2013, Welsiton Ferreira (wfcreations@gmail.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *  
 *  Redistributions in binary form must reproduce the above copyright notice, this
 *  list of conditions and the following disclaimer in the documentation and/or
 *  other materials provided with the distribution.
 *
 *  Neither the name of the WFCreation nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.wfcreations.annms.core.sqlann;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLANNLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPEN_PARENTHESIS=1, CLOSE_PARENTHESIS=2, OPEN_BRACKETS=3, CLOSE_BRACKETS=4, 
		COMMA=5, CLAUSE_END=6, EQUALS=7, INTEGER=8, REAL=9, BOOLEAN=10, STRING=11, 
		DATE=12, CREATE=13, DATA=14, DROP=15, MODEL=16, INSERT=17, INTO=18, NEURALNETWORK=19, 
		NEURALNETWORKS=20, RUN=21, SHOW=22, STATUS=23, TRAIN=24, VALUES=25, TRUE=26, 
		FALSE=27, NOT=28, NULL=29, EXISTS=30, IF=31, LIKE=32, LEARNRULE=33, INPUT=34, 
		OUTPUT=35, Integer=36, Real=37, ID=38, String=39, COMMENT=40, LINE_COMMENT=41, 
		WS=42;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'('", "')'", "'{'", "'}'", "','", "';'", "'='", "INTEGER", "REAL", "BOOLEAN", 
		"STRING", "DATE", "CREATE", "DATA", "DROP", "MODEL", "INSERT", "INTO", 
		"NEURALNETWORK", "NEURALNETWORKS", "RUN", "SHOW", "STATUS", "TRAIN", "VALUES", 
		"TRUE", "FALSE", "NOT", "NULL", "EXISTS", "IF", "LIKE", "LEARNRULE", "INPUT", 
		"OUTPUT", "Integer", "Real", "ID", "String", "COMMENT", "LINE_COMMENT", 
		"WS"
	};
	public static final String[] ruleNames = {
		"A_", "B_", "C_", "D_", "E_", "F_", "G_", "H_", "I_", "J_", "K_", "L_", 
		"M_", "N_", "O_", "P_", "Q_", "R_", "S_", "T_", "U_", "V_", "W_", "X_", 
		"Y_", "Z_", "DIGIT", "EscapeSequence", "OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", 
		"OPEN_BRACKETS", "CLOSE_BRACKETS", "COMMA", "CLAUSE_END", "EQUALS", "INTEGER", 
		"REAL", "BOOLEAN", "STRING", "DATE", "CREATE", "DATA", "DROP", "MODEL", 
		"INSERT", "INTO", "NEURALNETWORK", "NEURALNETWORKS", "RUN", "SHOW", "STATUS", 
		"TRAIN", "VALUES", "TRUE", "FALSE", "NOT", "NULL", "EXISTS", "IF", "LIKE", 
		"LEARNRULE", "INPUT", "OUTPUT", "Integer", "Real", "ID", "String", "COMMENT", 
		"LINE_COMMENT", "WS"
	};


	public SQLANNLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SQLANN.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 67: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 68: LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 69: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}
	private void LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: _channel = HIDDEN;  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = HIDDEN;  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2,\u01e6\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3"+
		" \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'"+
		"\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3"+
		"*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3.\3"+
		".\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\63\3\63\3\63"+
		"\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65"+
		"\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\38\3"+
		"8\38\38\38\38\39\39\39\39\3:\3:\3:\3:\3:\3;\3;\3;\3;\3;\3;\3;\3<\3<\3"+
		"<\3=\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?\3?\3@\3"+
		"@\3@\3@\3@\3@\3@\3A\5A\u0190\nA\3A\6A\u0193\nA\rA\16A\u0194\3B\5B\u0198"+
		"\nB\3B\6B\u019b\nB\rB\16B\u019c\3B\3B\7B\u01a1\nB\fB\16B\u01a4\13B\3B"+
		"\5B\u01a7\nB\3B\3B\6B\u01ab\nB\rB\16B\u01ac\5B\u01af\nB\3C\3C\7C\u01b3"+
		"\nC\fC\16C\u01b6\13C\3D\3D\3D\7D\u01bb\nD\fD\16D\u01be\13D\3D\3D\3E\3"+
		"E\3E\3E\7E\u01c6\nE\fE\16E\u01c9\13E\3E\3E\3E\3E\3E\3F\3F\3F\3F\7F\u01d4"+
		"\nF\fF\16F\u01d7\13F\3F\5F\u01da\nF\3F\3F\3F\3F\3G\6G\u01e1\nG\rG\16G"+
		"\u01e2\3G\3G\3\u01c7H\3\2\1\5\2\1\7\2\1\t\2\1\13\2\1\r\2\1\17\2\1\21\2"+
		"\1\23\2\1\25\2\1\27\2\1\31\2\1\33\2\1\35\2\1\37\2\1!\2\1#\2\1%\2\1\'\2"+
		"\1)\2\1+\2\1-\2\1/\2\1\61\2\1\63\2\1\65\2\1\67\2\19\2\1;\3\1=\4\1?\5\1"+
		"A\6\1C\7\1E\b\1G\t\1I\n\1K\13\1M\f\1O\r\1Q\16\1S\17\1U\20\1W\21\1Y\22"+
		"\1[\23\1]\24\1_\25\1a\26\1c\27\1e\30\1g\31\1i\32\1k\33\1m\34\1o\35\1q"+
		"\36\1s\37\1u \1w!\1y\"\1{#\1}$\1\177%\1\u0081&\1\u0083\'\1\u0085(\1\u0087"+
		")\1\u0089*\2\u008b+\3\u008d,\4\3\2$\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4"+
		"\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOo"+
		"o\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2"+
		"XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\3\2\62;\n\2$$))^^ddhhppttvv\4\2"+
		"--//\5\2&&C\\c|\b\2&&//\62;C_aac|\4\2$$^^\4\2\f\f\17\17\5\2\13\f\16\17"+
		"\"\"\u01d8\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2"+
		"_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3"+
		"\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2"+
		"\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083"+
		"\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2"+
		"\2\2\u008d\3\2\2\2\3\u008f\3\2\2\2\5\u0091\3\2\2\2\7\u0093\3\2\2\2\t\u0095"+
		"\3\2\2\2\13\u0097\3\2\2\2\r\u0099\3\2\2\2\17\u009b\3\2\2\2\21\u009d\3"+
		"\2\2\2\23\u009f\3\2\2\2\25\u00a1\3\2\2\2\27\u00a3\3\2\2\2\31\u00a5\3\2"+
		"\2\2\33\u00a7\3\2\2\2\35\u00a9\3\2\2\2\37\u00ab\3\2\2\2!\u00ad\3\2\2\2"+
		"#\u00af\3\2\2\2%\u00b1\3\2\2\2\'\u00b3\3\2\2\2)\u00b5\3\2\2\2+\u00b7\3"+
		"\2\2\2-\u00b9\3\2\2\2/\u00bb\3\2\2\2\61\u00bd\3\2\2\2\63\u00bf\3\2\2\2"+
		"\65\u00c1\3\2\2\2\67\u00c3\3\2\2\29\u00c5\3\2\2\2;\u00c8\3\2\2\2=\u00ca"+
		"\3\2\2\2?\u00cc\3\2\2\2A\u00ce\3\2\2\2C\u00d0\3\2\2\2E\u00d2\3\2\2\2G"+
		"\u00d4\3\2\2\2I\u00d6\3\2\2\2K\u00de\3\2\2\2M\u00e3\3\2\2\2O\u00eb\3\2"+
		"\2\2Q\u00f2\3\2\2\2S\u00f7\3\2\2\2U\u00fe\3\2\2\2W\u0103\3\2\2\2Y\u0108"+
		"\3\2\2\2[\u010e\3\2\2\2]\u0115\3\2\2\2_\u011a\3\2\2\2a\u0128\3\2\2\2c"+
		"\u0137\3\2\2\2e\u013b\3\2\2\2g\u0140\3\2\2\2i\u0147\3\2\2\2k\u014d\3\2"+
		"\2\2m\u0154\3\2\2\2o\u0159\3\2\2\2q\u015f\3\2\2\2s\u0163\3\2\2\2u\u0168"+
		"\3\2\2\2w\u016f\3\2\2\2y\u0172\3\2\2\2{\u0177\3\2\2\2}\u0181\3\2\2\2\177"+
		"\u0187\3\2\2\2\u0081\u018f\3\2\2\2\u0083\u01ae\3\2\2\2\u0085\u01b0\3\2"+
		"\2\2\u0087\u01b7\3\2\2\2\u0089\u01c1\3\2\2\2\u008b\u01cf\3\2\2\2\u008d"+
		"\u01e0\3\2\2\2\u008f\u0090\t\2\2\2\u0090\4\3\2\2\2\u0091\u0092\t\3\2\2"+
		"\u0092\6\3\2\2\2\u0093\u0094\t\4\2\2\u0094\b\3\2\2\2\u0095\u0096\t\5\2"+
		"\2\u0096\n\3\2\2\2\u0097\u0098\t\6\2\2\u0098\f\3\2\2\2\u0099\u009a\t\7"+
		"\2\2\u009a\16\3\2\2\2\u009b\u009c\t\b\2\2\u009c\20\3\2\2\2\u009d\u009e"+
		"\t\t\2\2\u009e\22\3\2\2\2\u009f\u00a0\t\n\2\2\u00a0\24\3\2\2\2\u00a1\u00a2"+
		"\t\13\2\2\u00a2\26\3\2\2\2\u00a3\u00a4\t\f\2\2\u00a4\30\3\2\2\2\u00a5"+
		"\u00a6\t\r\2\2\u00a6\32\3\2\2\2\u00a7\u00a8\t\16\2\2\u00a8\34\3\2\2\2"+
		"\u00a9\u00aa\t\17\2\2\u00aa\36\3\2\2\2\u00ab\u00ac\t\20\2\2\u00ac \3\2"+
		"\2\2\u00ad\u00ae\t\21\2\2\u00ae\"\3\2\2\2\u00af\u00b0\t\22\2\2\u00b0$"+
		"\3\2\2\2\u00b1\u00b2\t\23\2\2\u00b2&\3\2\2\2\u00b3\u00b4\t\24\2\2\u00b4"+
		"(\3\2\2\2\u00b5\u00b6\t\25\2\2\u00b6*\3\2\2\2\u00b7\u00b8\t\26\2\2\u00b8"+
		",\3\2\2\2\u00b9\u00ba\t\27\2\2\u00ba.\3\2\2\2\u00bb\u00bc\t\30\2\2\u00bc"+
		"\60\3\2\2\2\u00bd\u00be\t\31\2\2\u00be\62\3\2\2\2\u00bf\u00c0\t\32\2\2"+
		"\u00c0\64\3\2\2\2\u00c1\u00c2\t\33\2\2\u00c2\66\3\2\2\2\u00c3\u00c4\t"+
		"\34\2\2\u00c48\3\2\2\2\u00c5\u00c6\7^\2\2\u00c6\u00c7\t\35\2\2\u00c7:"+
		"\3\2\2\2\u00c8\u00c9\7*\2\2\u00c9<\3\2\2\2\u00ca\u00cb\7+\2\2\u00cb>\3"+
		"\2\2\2\u00cc\u00cd\7}\2\2\u00cd@\3\2\2\2\u00ce\u00cf\7\177\2\2\u00cfB"+
		"\3\2\2\2\u00d0\u00d1\7.\2\2\u00d1D\3\2\2\2\u00d2\u00d3\7=\2\2\u00d3F\3"+
		"\2\2\2\u00d4\u00d5\7?\2\2\u00d5H\3\2\2\2\u00d6\u00d7\5\23\n\2\u00d7\u00d8"+
		"\5\35\17\2\u00d8\u00d9\5)\25\2\u00d9\u00da\5\13\6\2\u00da\u00db\5\17\b"+
		"\2\u00db\u00dc\5\13\6\2\u00dc\u00dd\5%\23\2\u00ddJ\3\2\2\2\u00de\u00df"+
		"\5%\23\2\u00df\u00e0\5\13\6\2\u00e0\u00e1\5\3\2\2\u00e1\u00e2\5\31\r\2"+
		"\u00e2L\3\2\2\2\u00e3\u00e4\5\5\3\2\u00e4\u00e5\5\37\20\2\u00e5\u00e6"+
		"\5\37\20\2\u00e6\u00e7\5\31\r\2\u00e7\u00e8\5\13\6\2\u00e8\u00e9\5\3\2"+
		"\2\u00e9\u00ea\5\35\17\2\u00eaN\3\2\2\2\u00eb\u00ec\5\'\24\2\u00ec\u00ed"+
		"\5)\25\2\u00ed\u00ee\5%\23\2\u00ee\u00ef\5\23\n\2\u00ef\u00f0\5\35\17"+
		"\2\u00f0\u00f1\5\17\b\2\u00f1P\3\2\2\2\u00f2\u00f3\5\t\5\2\u00f3\u00f4"+
		"\5\3\2\2\u00f4\u00f5\5)\25\2\u00f5\u00f6\5\13\6\2\u00f6R\3\2\2\2\u00f7"+
		"\u00f8\5\7\4\2\u00f8\u00f9\5%\23\2\u00f9\u00fa\5\13\6\2\u00fa\u00fb\5"+
		"\3\2\2\u00fb\u00fc\5)\25\2\u00fc\u00fd\5\13\6\2\u00fdT\3\2\2\2\u00fe\u00ff"+
		"\5\t\5\2\u00ff\u0100\5\3\2\2\u0100\u0101\5)\25\2\u0101\u0102\5\3\2\2\u0102"+
		"V\3\2\2\2\u0103\u0104\5\t\5\2\u0104\u0105\5%\23\2\u0105\u0106\5\37\20"+
		"\2\u0106\u0107\5!\21\2\u0107X\3\2\2\2\u0108\u0109\5\33\16\2\u0109\u010a"+
		"\5\37\20\2\u010a\u010b\5\t\5\2\u010b\u010c\5\13\6\2\u010c\u010d\5\31\r"+
		"\2\u010dZ\3\2\2\2\u010e\u010f\5\23\n\2\u010f\u0110\5\35\17\2\u0110\u0111"+
		"\5\'\24\2\u0111\u0112\5\13\6\2\u0112\u0113\5%\23\2\u0113\u0114\5)\25\2"+
		"\u0114\\\3\2\2\2\u0115\u0116\5\23\n\2\u0116\u0117\5\35\17\2\u0117\u0118"+
		"\5)\25\2\u0118\u0119\5\37\20\2\u0119^\3\2\2\2\u011a\u011b\5\35\17\2\u011b"+
		"\u011c\5\13\6\2\u011c\u011d\5+\26\2\u011d\u011e\5%\23\2\u011e\u011f\5"+
		"\3\2\2\u011f\u0120\5\31\r\2\u0120\u0121\5\35\17\2\u0121\u0122\5\13\6\2"+
		"\u0122\u0123\5)\25\2\u0123\u0124\5/\30\2\u0124\u0125\5\37\20\2\u0125\u0126"+
		"\5%\23\2\u0126\u0127\5\27\f\2\u0127`\3\2\2\2\u0128\u0129\5\35\17\2\u0129"+
		"\u012a\5\13\6\2\u012a\u012b\5+\26\2\u012b\u012c\5%\23\2\u012c\u012d\5"+
		"\3\2\2\u012d\u012e\5\31\r\2\u012e\u012f\5\35\17\2\u012f\u0130\5\13\6\2"+
		"\u0130\u0131\5)\25\2\u0131\u0132\5/\30\2\u0132\u0133\5\37\20\2\u0133\u0134"+
		"\5%\23\2\u0134\u0135\5\27\f\2\u0135\u0136\5\'\24\2\u0136b\3\2\2\2\u0137"+
		"\u0138\5%\23\2\u0138\u0139\5+\26\2\u0139\u013a\5\35\17\2\u013ad\3\2\2"+
		"\2\u013b\u013c\5\'\24\2\u013c\u013d\5\21\t\2\u013d\u013e\5\37\20\2\u013e"+
		"\u013f\5/\30\2\u013ff\3\2\2\2\u0140\u0141\5\'\24\2\u0141\u0142\5)\25\2"+
		"\u0142\u0143\5\3\2\2\u0143\u0144\5)\25\2\u0144\u0145\5+\26\2\u0145\u0146"+
		"\5\'\24\2\u0146h\3\2\2\2\u0147\u0148\5)\25\2\u0148\u0149\5%\23\2\u0149"+
		"\u014a\5\3\2\2\u014a\u014b\5\23\n\2\u014b\u014c\5\35\17\2\u014cj\3\2\2"+
		"\2\u014d\u014e\5-\27\2\u014e\u014f\5\3\2\2\u014f\u0150\5\31\r\2\u0150"+
		"\u0151\5+\26\2\u0151\u0152\5\13\6\2\u0152\u0153\5\'\24\2\u0153l\3\2\2"+
		"\2\u0154\u0155\5)\25\2\u0155\u0156\5%\23\2\u0156\u0157\5+\26\2\u0157\u0158"+
		"\5\13\6\2\u0158n\3\2\2\2\u0159\u015a\5\r\7\2\u015a\u015b\5\3\2\2\u015b"+
		"\u015c\5\31\r\2\u015c\u015d\5\'\24\2\u015d\u015e\5\13\6\2\u015ep\3\2\2"+
		"\2\u015f\u0160\5\35\17\2\u0160\u0161\5\37\20\2\u0161\u0162\5)\25\2\u0162"+
		"r\3\2\2\2\u0163\u0164\5\35\17\2\u0164\u0165\5+\26\2\u0165\u0166\5\31\r"+
		"\2\u0166\u0167\5\31\r\2\u0167t\3\2\2\2\u0168\u0169\5\13\6\2\u0169\u016a"+
		"\5\61\31\2\u016a\u016b\5\23\n\2\u016b\u016c\5\'\24\2\u016c\u016d\5)\25"+
		"\2\u016d\u016e\5\'\24\2\u016ev\3\2\2\2\u016f\u0170\5\23\n\2\u0170\u0171"+
		"\5\r\7\2\u0171x\3\2\2\2\u0172\u0173\5\31\r\2\u0173\u0174\5\23\n\2\u0174"+
		"\u0175\5\27\f\2\u0175\u0176\5\13\6\2\u0176z\3\2\2\2\u0177\u0178\5\31\r"+
		"\2\u0178\u0179\5\13\6\2\u0179\u017a\5\3\2\2\u017a\u017b\5%\23\2\u017b"+
		"\u017c\5\35\17\2\u017c\u017d\5%\23\2\u017d\u017e\5+\26\2\u017e\u017f\5"+
		"\31\r\2\u017f\u0180\5\13\6\2\u0180|\3\2\2\2\u0181\u0182\5\23\n\2\u0182"+
		"\u0183\5\35\17\2\u0183\u0184\5!\21\2\u0184\u0185\5+\26\2\u0185\u0186\5"+
		")\25\2\u0186~\3\2\2\2\u0187\u0188\5\37\20\2\u0188\u0189\5+\26\2\u0189"+
		"\u018a\5)\25\2\u018a\u018b\5!\21\2\u018b\u018c\5+\26\2\u018c\u018d\5)"+
		"\25\2\u018d\u0080\3\2\2\2\u018e\u0190\t\36\2\2\u018f\u018e\3\2\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u0192\3\2\2\2\u0191\u0193\5\67\34\2\u0192\u0191\3"+
		"\2\2\2\u0193\u0194\3\2\2\2\u0194\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195"+
		"\u0082\3\2\2\2\u0196\u0198\t\36\2\2\u0197\u0196\3\2\2\2\u0197\u0198\3"+
		"\2\2\2\u0198\u019a\3\2\2\2\u0199\u019b\5\67\34\2\u019a\u0199\3\2\2\2\u019b"+
		"\u019c\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\3\2"+
		"\2\2\u019e\u01a2\7\60\2\2\u019f\u01a1\5\67\34\2\u01a0\u019f\3\2\2\2\u01a1"+
		"\u01a4\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01af\3\2"+
		"\2\2\u01a4\u01a2\3\2\2\2\u01a5\u01a7\t\36\2\2\u01a6\u01a5\3\2\2\2\u01a6"+
		"\u01a7\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01aa\7\60\2\2\u01a9\u01ab\5"+
		"\67\34\2\u01aa\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ac"+
		"\u01ad\3\2\2\2\u01ad\u01af\3\2\2\2\u01ae\u0197\3\2\2\2\u01ae\u01a6\3\2"+
		"\2\2\u01af\u0084\3\2\2\2\u01b0\u01b4\t\37\2\2\u01b1\u01b3\t \2\2\u01b2"+
		"\u01b1\3\2\2\2\u01b3\u01b6\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b4\u01b5\3\2"+
		"\2\2\u01b5\u0086\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b7\u01bc\7$\2\2\u01b8"+
		"\u01bb\59\35\2\u01b9\u01bb\n!\2\2\u01ba\u01b8\3\2\2\2\u01ba\u01b9\3\2"+
		"\2\2\u01bb\u01be\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd"+
		"\u01bf\3\2\2\2\u01be\u01bc\3\2\2\2\u01bf\u01c0\7$\2\2\u01c0\u0088\3\2"+
		"\2\2\u01c1\u01c2\7\61\2\2\u01c2\u01c3\7,\2\2\u01c3\u01c7\3\2\2\2\u01c4"+
		"\u01c6\13\2\2\2\u01c5\u01c4\3\2\2\2\u01c6\u01c9\3\2\2\2\u01c7\u01c8\3"+
		"\2\2\2\u01c7\u01c5\3\2\2\2\u01c8\u01ca\3\2\2\2\u01c9\u01c7\3\2\2\2\u01ca"+
		"\u01cb\7,\2\2\u01cb\u01cc\7\61\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01ce\bE"+
		"\2\2\u01ce\u008a\3\2\2\2\u01cf\u01d0\7\61\2\2\u01d0\u01d1\7\61\2\2\u01d1"+
		"\u01d5\3\2\2\2\u01d2\u01d4\n\"\2\2\u01d3\u01d2\3\2\2\2\u01d4\u01d7\3\2"+
		"\2\2\u01d5\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d9\3\2\2\2\u01d7"+
		"\u01d5\3\2\2\2\u01d8\u01da\7\17\2\2\u01d9\u01d8\3\2\2\2\u01d9\u01da\3"+
		"\2\2\2\u01da\u01db\3\2\2\2\u01db\u01dc\7\f\2\2\u01dc\u01dd\3\2\2\2\u01dd"+
		"\u01de\bF\3\2\u01de\u008c\3\2\2\2\u01df\u01e1\t#\2\2\u01e0\u01df\3\2\2"+
		"\2\u01e1\u01e2\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e4"+
		"\3\2\2\2\u01e4\u01e5\bG\4\2\u01e5\u008e\3\2\2\2\22\2\u018f\u0194\u0197"+
		"\u019c\u01a2\u01a6\u01ac\u01ae\u01b4\u01ba\u01bc\u01c7\u01d5\u01d9\u01e2";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}