// Generated from SQLANN.g4 by ANTLR 4.3

/*
 * Copyright (c) Welsiton Ferreira (wfcreations@gmail.com)
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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLANNParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPEN_PARENTHESIS=1, CLOSE_PARENTHESIS=2, OPEN_BRACKETS=3, CLOSE_BRACKETS=4, 
		COMMA=5, CLAUSE_END=6, EQUALS=7, INTEGER=8, REAL=9, BOOLEAN=10, STRING=11, 
		DATE=12, CREATE=13, DATA=14, DROP=15, MODEL=16, INSERT=17, INTO=18, NEURALNETWORK=19, 
		NEURALNETWORKS=20, RUN=21, SHOW=22, STATUS=23, TRAIN=24, VALUES=25, TRUE=26, 
		FALSE=27, NOT=28, NULL=29, EXISTS=30, IF=31, LIKE=32, LEARNINGRULE=33, 
		INPUT=34, OUTPUT=35, Integer=36, Real=37, ID=38, String=39, COMMENT=40, 
		LINE_COMMENT=41, WS=42;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "'{'", "'}'", "','", "';'", "'='", "INTEGER", 
		"REAL", "BOOLEAN", "STRING", "DATE", "CREATE", "DATA", "DROP", "MODEL", 
		"INSERT", "INTO", "NEURALNETWORK", "NEURALNETWORKS", "RUN", "SHOW", "STATUS", 
		"TRAIN", "VALUES", "TRUE", "FALSE", "NOT", "NULL", "EXISTS", "IF", "LIKE", 
		"LEARNINGRULE", "INPUT", "OUTPUT", "Integer", "Real", "ID", "String", 
		"COMMENT", "LINE_COMMENT", "WS"
	};
	public static final int
		RULE_statements = 0, RULE_statement = 1, RULE_dataAttributes = 2, RULE_dataAttribute = 3, 
		RULE_dataType = 4, RULE_list = 5, RULE_params = 6, RULE_param = 7, RULE_paramValue = 8, 
		RULE_value = 9, RULE_values = 10, RULE_complexList = 11;
	public static final String[] ruleNames = {
		"statements", "statement", "dataAttributes", "dataAttribute", "dataType", 
		"list", "params", "param", "paramValue", "value", "values", "complexList"
	};

	@Override
	public String getGrammarFileName() { return "SQLANN.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLANNParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public TerminalNode CLAUSE_END(int i) {
			return getToken(SQLANNParser.CLAUSE_END, i);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> CLAUSE_END() { return getTokens(SQLANNParser.CLAUSE_END); }
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statements);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(24); statement();
			setState(29);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(25); match(CLAUSE_END);
					setState(26); statement();
					}
					} 
				}
				setState(31);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(33);
			_la = _input.LA(1);
			if (_la==CLAUSE_END) {
				{
				setState(32); match(CLAUSE_END);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CreateNeuralNetworkStatementContext extends StatementContext {
		public TerminalNode MODEL() { return getToken(SQLANNParser.MODEL, 0); }
		public TerminalNode IF() { return getToken(SQLANNParser.IF, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode CREATE() { return getToken(SQLANNParser.CREATE, 0); }
		public TerminalNode EXISTS() { return getToken(SQLANNParser.EXISTS, 0); }
		public TerminalNode EQUALS() { return getToken(SQLANNParser.EQUALS, 0); }
		public List<TerminalNode> ID() { return getTokens(SQLANNParser.ID); }
		public TerminalNode NOT() { return getToken(SQLANNParser.NOT, 0); }
		public TerminalNode NEURALNETWORK() { return getToken(SQLANNParser.NEURALNETWORK, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(SQLANNParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode LIKE() { return getToken(SQLANNParser.LIKE, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(SQLANNParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode ID(int i) {
			return getToken(SQLANNParser.ID, i);
		}
		public CreateNeuralNetworkStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterCreateNeuralNetworkStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitCreateNeuralNetworkStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitCreateNeuralNetworkStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TrainStatementContext extends StatementContext {
		public TerminalNode TRAIN() { return getToken(SQLANNParser.TRAIN, 0); }
		public TerminalNode INPUT() { return getToken(SQLANNParser.INPUT, 0); }
		public List<TerminalNode> EQUALS() { return getTokens(SQLANNParser.EQUALS); }
		public List<TerminalNode> ID() { return getTokens(SQLANNParser.ID); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(SQLANNParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLANNParser.COMMA, i);
		}
		public TerminalNode EQUALS(int i) {
			return getToken(SQLANNParser.EQUALS, i);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public List<ListContext> list() {
			return getRuleContexts(ListContext.class);
		}
		public TerminalNode DATA() { return getToken(SQLANNParser.DATA, 0); }
		public ListContext list(int i) {
			return getRuleContext(ListContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLANNParser.COMMA); }
		public TerminalNode OUTPUT() { return getToken(SQLANNParser.OUTPUT, 0); }
		public TerminalNode LEARNINGRULE() { return getToken(SQLANNParser.LEARNINGRULE, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(SQLANNParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode ID(int i) {
			return getToken(SQLANNParser.ID, i);
		}
		public TrainStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterTrainStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitTrainStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitTrainStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ShowNeuralNetworksStatementContext extends StatementContext {
		public TerminalNode NEURALNETWORKS() { return getToken(SQLANNParser.NEURALNETWORKS, 0); }
		public TerminalNode SHOW() { return getToken(SQLANNParser.SHOW, 0); }
		public ShowNeuralNetworksStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterShowNeuralNetworksStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitShowNeuralNetworksStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitShowNeuralNetworksStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ShowStatusStatementContext extends StatementContext {
		public TerminalNode STATUS() { return getToken(SQLANNParser.STATUS, 0); }
		public TerminalNode SHOW() { return getToken(SQLANNParser.SHOW, 0); }
		public ShowStatusStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterShowStatusStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitShowStatusStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitShowStatusStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InsertIntoStatementContext extends StatementContext {
		public TerminalNode ID() { return getToken(SQLANNParser.ID, 0); }
		public TerminalNode INTO() { return getToken(SQLANNParser.INTO, 0); }
		public TerminalNode INSERT() { return getToken(SQLANNParser.INSERT, 0); }
		public ValuesContext values() {
			return getRuleContext(ValuesContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(SQLANNParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode VALUES() { return getToken(SQLANNParser.VALUES, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(SQLANNParser.OPEN_PARENTHESIS, 0); }
		public InsertIntoStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterInsertIntoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitInsertIntoStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitInsertIntoStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RunStatementContext extends StatementContext {
		public TerminalNode ID() { return getToken(SQLANNParser.ID, 0); }
		public ValuesContext values() {
			return getRuleContext(ValuesContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(SQLANNParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode VALUES() { return getToken(SQLANNParser.VALUES, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(SQLANNParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode RUN() { return getToken(SQLANNParser.RUN, 0); }
		public RunStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterRunStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitRunStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitRunStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CreateDataStatementContext extends StatementContext {
		public TerminalNode IF() { return getToken(SQLANNParser.IF, 0); }
		public TerminalNode CREATE() { return getToken(SQLANNParser.CREATE, 0); }
		public DataAttributesContext dataAttributes() {
			return getRuleContext(DataAttributesContext.class,0);
		}
		public TerminalNode EXISTS() { return getToken(SQLANNParser.EXISTS, 0); }
		public TerminalNode DATA() { return getToken(SQLANNParser.DATA, 0); }
		public List<TerminalNode> ID() { return getTokens(SQLANNParser.ID); }
		public TerminalNode NOT() { return getToken(SQLANNParser.NOT, 0); }
		public TerminalNode LIKE() { return getToken(SQLANNParser.LIKE, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(SQLANNParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(SQLANNParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode ID(int i) {
			return getToken(SQLANNParser.ID, i);
		}
		public CreateDataStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterCreateDataStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitCreateDataStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitCreateDataStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropNeuralNetworkStatementContext extends StatementContext {
		public TerminalNode IF() { return getToken(SQLANNParser.IF, 0); }
		public TerminalNode EXISTS() { return getToken(SQLANNParser.EXISTS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SQLANNParser.COMMA); }
		public List<TerminalNode> ID() { return getTokens(SQLANNParser.ID); }
		public TerminalNode NEURALNETWORKS() { return getToken(SQLANNParser.NEURALNETWORKS, 0); }
		public TerminalNode DROP() { return getToken(SQLANNParser.DROP, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLANNParser.COMMA, i);
		}
		public TerminalNode ID(int i) {
			return getToken(SQLANNParser.ID, i);
		}
		public DropNeuralNetworkStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterDropNeuralNetworkStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitDropNeuralNetworkStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitDropNeuralNetworkStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ShowNeuralNetworkStatusStatementContext extends StatementContext {
		public TerminalNode ID() { return getToken(SQLANNParser.ID, 0); }
		public TerminalNode NEURALNETWORK() { return getToken(SQLANNParser.NEURALNETWORK, 0); }
		public TerminalNode STATUS() { return getToken(SQLANNParser.STATUS, 0); }
		public TerminalNode SHOW() { return getToken(SQLANNParser.SHOW, 0); }
		public ShowNeuralNetworkStatusStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterShowNeuralNetworkStatusStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitShowNeuralNetworkStatusStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitShowNeuralNetworkStatusStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropDataStatementContext extends StatementContext {
		public TerminalNode IF() { return getToken(SQLANNParser.IF, 0); }
		public TerminalNode EXISTS() { return getToken(SQLANNParser.EXISTS, 0); }
		public TerminalNode DATA() { return getToken(SQLANNParser.DATA, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SQLANNParser.COMMA); }
		public List<TerminalNode> ID() { return getTokens(SQLANNParser.ID); }
		public TerminalNode DROP() { return getToken(SQLANNParser.DROP, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLANNParser.COMMA, i);
		}
		public TerminalNode ID(int i) {
			return getToken(SQLANNParser.ID, i);
		}
		public DropDataStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterDropDataStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitDropDataStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitDropDataStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ShowDataStatementContext extends StatementContext {
		public TerminalNode DATA() { return getToken(SQLANNParser.DATA, 0); }
		public TerminalNode SHOW() { return getToken(SQLANNParser.SHOW, 0); }
		public ShowDataStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterShowDataStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitShowDataStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitShowDataStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ShowDataStatusStatementContext extends StatementContext {
		public TerminalNode DATA() { return getToken(SQLANNParser.DATA, 0); }
		public TerminalNode ID() { return getToken(SQLANNParser.ID, 0); }
		public TerminalNode STATUS() { return getToken(SQLANNParser.STATUS, 0); }
		public TerminalNode SHOW() { return getToken(SQLANNParser.SHOW, 0); }
		public ShowDataStatusStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterShowDataStatusStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitShowDataStatusStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitShowDataStatusStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			setState(168);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				_localctx = new CreateDataStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(35); match(CREATE);
				setState(36); match(DATA);
				setState(40);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(37); match(IF);
					setState(38); match(NOT);
					setState(39); match(EXISTS);
					}
				}

				setState(42); match(ID);
				setState(49);
				switch (_input.LA(1)) {
				case OPEN_PARENTHESIS:
					{
					setState(43); match(OPEN_PARENTHESIS);
					setState(44); dataAttributes();
					setState(45); match(CLOSE_PARENTHESIS);
					}
					break;
				case LIKE:
					{
					setState(47); match(LIKE);
					setState(48); match(ID);
					}
					break;
				case EOF:
				case CLAUSE_END:
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;

			case 2:
				_localctx = new CreateNeuralNetworkStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(51); match(CREATE);
				setState(52); match(NEURALNETWORK);
				setState(56);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(53); match(IF);
					setState(54); match(NOT);
					setState(55); match(EXISTS);
					}
				}

				setState(58); match(ID);
				setState(72);
				switch (_input.LA(1)) {
				case OPEN_PARENTHESIS:
				case MODEL:
					{
					setState(63);
					_la = _input.LA(1);
					if (_la==OPEN_PARENTHESIS) {
						{
						setState(59); match(OPEN_PARENTHESIS);
						setState(60); params();
						setState(61); match(CLOSE_PARENTHESIS);
						}
					}

					setState(65); match(MODEL);
					setState(67);
					_la = _input.LA(1);
					if (_la==EQUALS) {
						{
						setState(66); match(EQUALS);
						}
					}

					setState(69); match(ID);
					}
					break;
				case LIKE:
					{
					setState(70); match(LIKE);
					setState(71); match(ID);
					}
					break;
				case EOF:
				case CLAUSE_END:
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;

			case 3:
				_localctx = new DropDataStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(74); match(DROP);
				setState(75); match(DATA);
				setState(78);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(76); match(IF);
					setState(77); match(EXISTS);
					}
				}

				setState(80); match(ID);
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(81); match(COMMA);
					setState(82); match(ID);
					}
					}
					setState(87);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;

			case 4:
				_localctx = new DropNeuralNetworkStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(88); match(DROP);
				setState(89); match(NEURALNETWORKS);
				setState(92);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(90); match(IF);
					setState(91); match(EXISTS);
					}
				}

				setState(94); match(ID);
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(95); match(COMMA);
					setState(96); match(ID);
					}
					}
					setState(101);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;

			case 5:
				_localctx = new InsertIntoStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(102); match(INSERT);
				setState(103); match(INTO);
				setState(104); match(ID);
				setState(105); match(VALUES);
				setState(106); match(OPEN_PARENTHESIS);
				setState(107); values();
				setState(108); match(CLOSE_PARENTHESIS);
				}
				break;

			case 6:
				_localctx = new RunStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(110); match(RUN);
				setState(111); match(ID);
				setState(112); match(VALUES);
				setState(113); match(OPEN_PARENTHESIS);
				setState(114); values();
				setState(115); match(CLOSE_PARENTHESIS);
				}
				break;

			case 7:
				_localctx = new ShowDataStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(117); match(SHOW);
				setState(118); match(DATA);
				}
				break;

			case 8:
				_localctx = new ShowDataStatusStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(119); match(SHOW);
				setState(120); match(DATA);
				setState(121); match(STATUS);
				setState(122); match(ID);
				}
				break;

			case 9:
				_localctx = new ShowNeuralNetworksStatementContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(123); match(SHOW);
				setState(124); match(NEURALNETWORKS);
				}
				break;

			case 10:
				_localctx = new ShowNeuralNetworkStatusStatementContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(125); match(SHOW);
				setState(126); match(NEURALNETWORK);
				setState(127); match(STATUS);
				setState(128); match(ID);
				}
				break;

			case 11:
				_localctx = new ShowStatusStatementContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(129); match(SHOW);
				setState(130); match(STATUS);
				}
				break;

			case 12:
				_localctx = new TrainStatementContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(131); match(TRAIN);
				setState(132); match(ID);
				setState(137);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESIS) {
					{
					setState(133); match(OPEN_PARENTHESIS);
					setState(134); params();
					setState(135); match(CLOSE_PARENTHESIS);
					}
				}

				setState(139); match(LEARNINGRULE);
				setState(141);
				_la = _input.LA(1);
				if (_la==EQUALS) {
					{
					setState(140); match(EQUALS);
					}
				}

				setState(143); match(ID);
				setState(145);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(144); match(COMMA);
					}
				}

				setState(147); match(DATA);
				setState(149);
				_la = _input.LA(1);
				if (_la==EQUALS) {
					{
					setState(148); match(EQUALS);
					}
				}

				setState(151); match(ID);
				setState(153);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(152); match(COMMA);
					}
				}

				setState(155); match(INPUT);
				setState(157);
				_la = _input.LA(1);
				if (_la==EQUALS) {
					{
					setState(156); match(EQUALS);
					}
				}

				setState(159); list();
				setState(166);
				_la = _input.LA(1);
				if (_la==COMMA || _la==OUTPUT) {
					{
					setState(161);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(160); match(COMMA);
						}
					}

					setState(163); match(OUTPUT);
					{
					setState(164); match(EQUALS);
					}
					setState(165); list();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataAttributesContext extends ParserRuleContext {
		public List<DataAttributeContext> dataAttribute() {
			return getRuleContexts(DataAttributeContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLANNParser.COMMA); }
		public DataAttributeContext dataAttribute(int i) {
			return getRuleContext(DataAttributeContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(SQLANNParser.COMMA, i);
		}
		public DataAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterDataAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitDataAttributes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitDataAttributes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataAttributesContext dataAttributes() throws RecognitionException {
		DataAttributesContext _localctx = new DataAttributesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_dataAttributes);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(170); dataAttribute();
			setState(175);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(171); match(COMMA);
					setState(172); dataAttribute();
					}
					} 
				}
				setState(177);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			setState(179);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(178); match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataAttributeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQLANNParser.ID, 0); }
		public TerminalNode NOT() { return getToken(SQLANNParser.NOT, 0); }
		public TerminalNode NULL() { return getToken(SQLANNParser.NULL, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public DataAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterDataAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitDataAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitDataAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataAttributeContext dataAttribute() throws RecognitionException {
		DataAttributeContext _localctx = new DataAttributeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dataAttribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); match(ID);
			setState(182); dataType();
			setState(186);
			switch (_input.LA(1)) {
			case NOT:
				{
				setState(183); match(NOT);
				setState(184); match(NULL);
				}
				break;
			case NULL:
				{
				setState(185); match(NULL);
				}
				break;
			case CLOSE_PARENTHESIS:
			case COMMA:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataTypeContext extends ParserRuleContext {
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
	 
		public DataTypeContext() { }
		public void copyFrom(DataTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntegerDataTypeContext extends DataTypeContext {
		public TerminalNode INTEGER() { return getToken(SQLANNParser.INTEGER, 0); }
		public IntegerDataTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterIntegerDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitIntegerDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitIntegerDataType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanDataTypeContext extends DataTypeContext {
		public TerminalNode BOOLEAN() { return getToken(SQLANNParser.BOOLEAN, 0); }
		public BooleanDataTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterBooleanDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitBooleanDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitBooleanDataType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringDataTypeContext extends DataTypeContext {
		public TerminalNode STRING() { return getToken(SQLANNParser.STRING, 0); }
		public StringDataTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterStringDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitStringDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitStringDataType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DateDataTypeContext extends DataTypeContext {
		public TerminalNode String() { return getToken(SQLANNParser.String, 0); }
		public TerminalNode DATE() { return getToken(SQLANNParser.DATE, 0); }
		public DateDataTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterDateDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitDateDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitDateDataType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ListDataTypeContext extends DataTypeContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ListDataTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterListDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitListDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitListDataType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RealDataTypeContext extends DataTypeContext {
		public TerminalNode REAL() { return getToken(SQLANNParser.REAL, 0); }
		public RealDataTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterRealDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitRealDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitRealDataType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_dataType);
		try {
			setState(195);
			switch (_input.LA(1)) {
			case BOOLEAN:
				_localctx = new BooleanDataTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(188); match(BOOLEAN);
				}
				break;
			case INTEGER:
				_localctx = new IntegerDataTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(189); match(INTEGER);
				}
				break;
			case REAL:
				_localctx = new RealDataTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(190); match(REAL);
				}
				break;
			case STRING:
				_localctx = new StringDataTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(191); match(STRING);
				}
				break;
			case DATE:
				_localctx = new DateDataTypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(192); match(DATE);
				setState(193); match(String);
				}
				break;
			case OPEN_BRACKETS:
				_localctx = new ListDataTypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(194); list();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKETS() { return getToken(SQLANNParser.OPEN_BRACKETS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SQLANNParser.COMMA); }
		public List<TerminalNode> ID() { return getTokens(SQLANNParser.ID); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(SQLANNParser.CLOSE_BRACKETS, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLANNParser.COMMA, i);
		}
		public TerminalNode ID(int i) {
			return getToken(SQLANNParser.ID, i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197); match(OPEN_BRACKETS);
			setState(198); match(ID);
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(199); match(COMMA);
				setState(200); match(ID);
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(206); match(CLOSE_BRACKETS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLANNParser.COMMA); }
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(SQLANNParser.COMMA, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_params);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(208); param();
			setState(213);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(209); match(COMMA);
					setState(210); param();
					}
					} 
				}
				setState(215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			setState(217);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(216); match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public ParamValueContext paramValue(int i) {
			return getRuleContext(ParamValueContext.class,i);
		}
		public TerminalNode ID() { return getToken(SQLANNParser.ID, 0); }
		public List<ParamValueContext> paramValue() {
			return getRuleContexts(ParamValueContext.class);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219); match(ID);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_BRACKETS) | (1L << TRUE) | (1L << FALSE) | (1L << NULL) | (1L << Integer) | (1L << Real) | (1L << ID) | (1L << String))) != 0)) {
				{
				{
				setState(220); paramValue();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamValueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ComplexListContext complexList() {
			return getRuleContext(ComplexListContext.class,0);
		}
		public ParamValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterParamValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitParamValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitParamValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamValueContext paramValue() throws RecognitionException {
		ParamValueContext _localctx = new ParamValueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paramValue);
		try {
			setState(228);
			switch (_input.LA(1)) {
			case TRUE:
			case FALSE:
			case NULL:
			case Integer:
			case Real:
			case ID:
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(226); value();
				}
				break;
			case OPEN_BRACKETS:
				enterOuterAlt(_localctx, 2);
				{
				setState(227); complexList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	 
		public ValueContext() { }
		public void copyFrom(ValueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdValueContext extends ValueContext {
		public TerminalNode ID() { return getToken(SQLANNParser.ID, 0); }
		public IdValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterIdValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitIdValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitIdValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerValueContext extends ValueContext {
		public TerminalNode Integer() { return getToken(SQLANNParser.Integer, 0); }
		public IntegerValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitIntegerValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitIntegerValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullValueContext extends ValueContext {
		public TerminalNode NULL() { return getToken(SQLANNParser.NULL, 0); }
		public NullValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterNullValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitNullValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitNullValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringValueContext extends ValueContext {
		public TerminalNode String() { return getToken(SQLANNParser.String, 0); }
		public StringValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterStringValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitStringValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitStringValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanValueContext extends ValueContext {
		public TerminalNode TRUE() { return getToken(SQLANNParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(SQLANNParser.FALSE, 0); }
		public BooleanValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterBooleanValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitBooleanValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitBooleanValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RealValueContext extends ValueContext {
		public TerminalNode Real() { return getToken(SQLANNParser.Real, 0); }
		public RealValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterRealValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitRealValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitRealValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_value);
		int _la;
		try {
			setState(236);
			switch (_input.LA(1)) {
			case TRUE:
			case FALSE:
				_localctx = new BooleanValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			case NULL:
				_localctx = new NullValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(231); match(NULL);
				}
				break;
			case Integer:
				_localctx = new IntegerValueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(232); match(Integer);
				}
				break;
			case Real:
				_localctx = new RealValueContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(233); match(Real);
				}
				break;
			case String:
				_localctx = new StringValueContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(234); match(String);
				}
				break;
			case ID:
				_localctx = new IdValueContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(235); match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValuesContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLANNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLANNParser.COMMA, i);
		}
		public ValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterValues(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitValues(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitValues(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValuesContext values() throws RecognitionException {
		ValuesContext _localctx = new ValuesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238); value();
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(239); match(COMMA);
				setState(240); value();
				}
				}
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComplexListContext extends ParserRuleContext {
		public ParamValueContext paramValue(int i) {
			return getRuleContext(ParamValueContext.class,i);
		}
		public TerminalNode OPEN_BRACKETS() { return getToken(SQLANNParser.OPEN_BRACKETS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SQLANNParser.COMMA); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(SQLANNParser.CLOSE_BRACKETS, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLANNParser.COMMA, i);
		}
		public List<ParamValueContext> paramValue() {
			return getRuleContexts(ParamValueContext.class);
		}
		public ComplexListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complexList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).enterComplexList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLANNListener ) ((SQLANNListener)listener).exitComplexList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLANNVisitor ) return ((SQLANNVisitor<? extends T>)visitor).visitComplexList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComplexListContext complexList() throws RecognitionException {
		ComplexListContext _localctx = new ComplexListContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_complexList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246); match(OPEN_BRACKETS);
			setState(247); paramValue();
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(248); match(COMMA);
				setState(249); paramValue();
				}
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(255); match(CLOSE_BRACKETS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3,\u0104\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\7\2\36\n\2\f\2\16\2!\13\2\3\2\5\2$\n\2"+
		"\3\3\3\3\3\3\3\3\3\3\5\3+\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\64\n\3\3"+
		"\3\3\3\3\3\3\3\3\3\5\3;\n\3\3\3\3\3\3\3\3\3\3\3\5\3B\n\3\3\3\3\3\5\3F"+
		"\n\3\3\3\3\3\3\3\5\3K\n\3\3\3\3\3\3\3\3\3\5\3Q\n\3\3\3\3\3\3\3\7\3V\n"+
		"\3\f\3\16\3Y\13\3\3\3\3\3\3\3\3\3\5\3_\n\3\3\3\3\3\3\3\7\3d\n\3\f\3\16"+
		"\3g\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\5\3\u008c\n\3\3\3\3\3\5\3\u0090\n\3\3\3\3\3\5\3\u0094\n\3\3\3"+
		"\3\3\5\3\u0098\n\3\3\3\3\3\5\3\u009c\n\3\3\3\3\3\5\3\u00a0\n\3\3\3\3\3"+
		"\5\3\u00a4\n\3\3\3\3\3\3\3\5\3\u00a9\n\3\5\3\u00ab\n\3\3\4\3\4\3\4\7\4"+
		"\u00b0\n\4\f\4\16\4\u00b3\13\4\3\4\5\4\u00b6\n\4\3\5\3\5\3\5\3\5\3\5\5"+
		"\5\u00bd\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00c6\n\6\3\7\3\7\3\7\3\7"+
		"\7\7\u00cc\n\7\f\7\16\7\u00cf\13\7\3\7\3\7\3\b\3\b\3\b\7\b\u00d6\n\b\f"+
		"\b\16\b\u00d9\13\b\3\b\5\b\u00dc\n\b\3\t\3\t\7\t\u00e0\n\t\f\t\16\t\u00e3"+
		"\13\t\3\n\3\n\5\n\u00e7\n\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00ef\n"+
		"\13\3\f\3\f\3\f\7\f\u00f4\n\f\f\f\16\f\u00f7\13\f\3\r\3\r\3\r\3\r\7\r"+
		"\u00fd\n\r\f\r\16\r\u0100\13\r\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\2\3\3\2\34\35\u012d\2\32\3\2\2\2\4\u00aa\3\2\2\2\6\u00ac\3\2"+
		"\2\2\b\u00b7\3\2\2\2\n\u00c5\3\2\2\2\f\u00c7\3\2\2\2\16\u00d2\3\2\2\2"+
		"\20\u00dd\3\2\2\2\22\u00e6\3\2\2\2\24\u00ee\3\2\2\2\26\u00f0\3\2\2\2\30"+
		"\u00f8\3\2\2\2\32\37\5\4\3\2\33\34\7\b\2\2\34\36\5\4\3\2\35\33\3\2\2\2"+
		"\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 #\3\2\2\2!\37\3\2\2\2\"$\7\b\2"+
		"\2#\"\3\2\2\2#$\3\2\2\2$\3\3\2\2\2%&\7\17\2\2&*\7\20\2\2\'(\7!\2\2()\7"+
		"\36\2\2)+\7 \2\2*\'\3\2\2\2*+\3\2\2\2+,\3\2\2\2,\63\7(\2\2-.\7\3\2\2."+
		"/\5\6\4\2/\60\7\4\2\2\60\64\3\2\2\2\61\62\7\"\2\2\62\64\7(\2\2\63-\3\2"+
		"\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\u00ab\3\2\2\2\65\66\7\17\2\2\66:\7"+
		"\25\2\2\678\7!\2\289\7\36\2\29;\7 \2\2:\67\3\2\2\2:;\3\2\2\2;<\3\2\2\2"+
		"<J\7(\2\2=>\7\3\2\2>?\5\16\b\2?@\7\4\2\2@B\3\2\2\2A=\3\2\2\2AB\3\2\2\2"+
		"BC\3\2\2\2CE\7\22\2\2DF\7\t\2\2ED\3\2\2\2EF\3\2\2\2FG\3\2\2\2GK\7(\2\2"+
		"HI\7\"\2\2IK\7(\2\2JA\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\u00ab\3\2\2\2LM\7\21"+
		"\2\2MP\7\20\2\2NO\7!\2\2OQ\7 \2\2PN\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RW\7(\2"+
		"\2ST\7\7\2\2TV\7(\2\2US\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\u00ab\3"+
		"\2\2\2YW\3\2\2\2Z[\7\21\2\2[^\7\26\2\2\\]\7!\2\2]_\7 \2\2^\\\3\2\2\2^"+
		"_\3\2\2\2_`\3\2\2\2`e\7(\2\2ab\7\7\2\2bd\7(\2\2ca\3\2\2\2dg\3\2\2\2ec"+
		"\3\2\2\2ef\3\2\2\2f\u00ab\3\2\2\2ge\3\2\2\2hi\7\23\2\2ij\7\24\2\2jk\7"+
		"(\2\2kl\7\33\2\2lm\7\3\2\2mn\5\26\f\2no\7\4\2\2o\u00ab\3\2\2\2pq\7\27"+
		"\2\2qr\7(\2\2rs\7\33\2\2st\7\3\2\2tu\5\26\f\2uv\7\4\2\2v\u00ab\3\2\2\2"+
		"wx\7\30\2\2x\u00ab\7\20\2\2yz\7\30\2\2z{\7\20\2\2{|\7\31\2\2|\u00ab\7"+
		"(\2\2}~\7\30\2\2~\u00ab\7\26\2\2\177\u0080\7\30\2\2\u0080\u0081\7\25\2"+
		"\2\u0081\u0082\7\31\2\2\u0082\u00ab\7(\2\2\u0083\u0084\7\30\2\2\u0084"+
		"\u00ab\7\31\2\2\u0085\u0086\7\32\2\2\u0086\u008b\7(\2\2\u0087\u0088\7"+
		"\3\2\2\u0088\u0089\5\16\b\2\u0089\u008a\7\4\2\2\u008a\u008c\3\2\2\2\u008b"+
		"\u0087\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008f\7#"+
		"\2\2\u008e\u0090\7\t\2\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0091\3\2\2\2\u0091\u0093\7(\2\2\u0092\u0094\7\7\2\2\u0093\u0092\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\7\20\2\2\u0096"+
		"\u0098\7\t\2\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2"+
		"\2\2\u0099\u009b\7(\2\2\u009a\u009c\7\7\2\2\u009b\u009a\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\7$\2\2\u009e\u00a0\7\t"+
		"\2\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a8\5\f\7\2\u00a2\u00a4\7\7\2\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2"+
		"\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\7%\2\2\u00a6\u00a7\7\t\2\2\u00a7"+
		"\u00a9\5\f\7\2\u00a8\u00a3\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2"+
		"\2\2\u00aa%\3\2\2\2\u00aa\65\3\2\2\2\u00aaL\3\2\2\2\u00aaZ\3\2\2\2\u00aa"+
		"h\3\2\2\2\u00aap\3\2\2\2\u00aaw\3\2\2\2\u00aay\3\2\2\2\u00aa}\3\2\2\2"+
		"\u00aa\177\3\2\2\2\u00aa\u0083\3\2\2\2\u00aa\u0085\3\2\2\2\u00ab\5\3\2"+
		"\2\2\u00ac\u00b1\5\b\5\2\u00ad\u00ae\7\7\2\2\u00ae\u00b0\5\b\5\2\u00af"+
		"\u00ad\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b6\7\7\2\2\u00b5"+
		"\u00b4\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\7\3\2\2\2\u00b7\u00b8\7(\2\2"+
		"\u00b8\u00bc\5\n\6\2\u00b9\u00ba\7\36\2\2\u00ba\u00bd\7\37\2\2\u00bb\u00bd"+
		"\7\37\2\2\u00bc\u00b9\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd\3\2\2\2"+
		"\u00bd\t\3\2\2\2\u00be\u00c6\7\f\2\2\u00bf\u00c6\7\n\2\2\u00c0\u00c6\7"+
		"\13\2\2\u00c1\u00c6\7\r\2\2\u00c2\u00c3\7\16\2\2\u00c3\u00c6\7)\2\2\u00c4"+
		"\u00c6\5\f\7\2\u00c5\u00be\3\2\2\2\u00c5\u00bf\3\2\2\2\u00c5\u00c0\3\2"+
		"\2\2\u00c5\u00c1\3\2\2\2\u00c5\u00c2\3\2\2\2\u00c5\u00c4\3\2\2\2\u00c6"+
		"\13\3\2\2\2\u00c7\u00c8\7\5\2\2\u00c8\u00cd\7(\2\2\u00c9\u00ca\7\7\2\2"+
		"\u00ca\u00cc\7(\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb"+
		"\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0"+
		"\u00d1\7\6\2\2\u00d1\r\3\2\2\2\u00d2\u00d7\5\20\t\2\u00d3\u00d4\7\7\2"+
		"\2\u00d4\u00d6\5\20\t\2\u00d5\u00d3\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2"+
		"\2\2\u00da\u00dc\7\7\2\2\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\17\3\2\2\2\u00dd\u00e1\7(\2\2\u00de\u00e0\5\22\n\2\u00df\u00de\3\2\2"+
		"\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\21"+
		"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e7\5\24\13\2\u00e5\u00e7\5\30\r"+
		"\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2\u00e7\23\3\2\2\2\u00e8\u00ef"+
		"\t\2\2\2\u00e9\u00ef\7\37\2\2\u00ea\u00ef\7&\2\2\u00eb\u00ef\7\'\2\2\u00ec"+
		"\u00ef\7)\2\2\u00ed\u00ef\7(\2\2\u00ee\u00e8\3\2\2\2\u00ee\u00e9\3\2\2"+
		"\2\u00ee\u00ea\3\2\2\2\u00ee\u00eb\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed"+
		"\3\2\2\2\u00ef\25\3\2\2\2\u00f0\u00f5\5\24\13\2\u00f1\u00f2\7\7\2\2\u00f2"+
		"\u00f4\5\24\13\2\u00f3\u00f1\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3"+
		"\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\27\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8"+
		"\u00f9\7\5\2\2\u00f9\u00fe\5\22\n\2\u00fa\u00fb\7\7\2\2\u00fb\u00fd\5"+
		"\22\n\2\u00fc\u00fa\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff\u0101\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\7\6"+
		"\2\2\u0102\31\3\2\2\2#\37#*\63:AEJPW^e\u008b\u008f\u0093\u0097\u009b\u009f"+
		"\u00a3\u00a8\u00aa\u00b1\u00b5\u00bc\u00c5\u00cd\u00d7\u00db\u00e1\u00e6"+
		"\u00ee\u00f5\u00fe";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}