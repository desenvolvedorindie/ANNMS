// Generated from SQLANN.g4 by ANTLR 4.3

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

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQLANNParser}.
 */
public interface SQLANNListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code integerDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterIntegerDataType(@NotNull SQLANNParser.IntegerDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitIntegerDataType(@NotNull SQLANNParser.IntegerDataTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code showNeuralNetworksStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowNeuralNetworksStatement(@NotNull SQLANNParser.ShowNeuralNetworksStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showNeuralNetworksStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowNeuralNetworksStatement(@NotNull SQLANNParser.ShowNeuralNetworksStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code runStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRunStatement(@NotNull SQLANNParser.RunStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code runStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRunStatement(@NotNull SQLANNParser.RunStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQLANNParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(@NotNull SQLANNParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLANNParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(@NotNull SQLANNParser.ParamContext ctx);

	/**
	 * Enter a parse tree produced by the {@code stringValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(@NotNull SQLANNParser.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(@NotNull SQLANNParser.StringValueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code dropNeuralNetworkStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDropNeuralNetworkStatement(@NotNull SQLANNParser.DropNeuralNetworkStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropNeuralNetworkStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDropNeuralNetworkStatement(@NotNull SQLANNParser.DropNeuralNetworkStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code booleanValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBooleanValue(@NotNull SQLANNParser.BooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBooleanValue(@NotNull SQLANNParser.BooleanValueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code createNeuralNetworkStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCreateNeuralNetworkStatement(@NotNull SQLANNParser.CreateNeuralNetworkStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code createNeuralNetworkStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCreateNeuralNetworkStatement(@NotNull SQLANNParser.CreateNeuralNetworkStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code booleanDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterBooleanDataType(@NotNull SQLANNParser.BooleanDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitBooleanDataType(@NotNull SQLANNParser.BooleanDataTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code values}
	 * labeled alternative in {@link SQLANNParser#dataTypestatementstatementvaluestatementdataTypestatementvaluedataTypestatementstatementvaluestatementdataTypestatementstatementvaluevaluedataTypestatementstatementstatementvaluedataType}.
	 * @param ctx the parse tree
	 */
	void enterValues(@NotNull SQLANNParser.ValuesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code values}
	 * labeled alternative in {@link SQLANNParser#dataTypestatementstatementvaluestatementdataTypestatementvaluedataTypestatementstatementvaluestatementdataTypestatementstatementvaluevaluedataTypestatementstatementstatementvaluedataType}.
	 * @param ctx the parse tree
	 */
	void exitValues(@NotNull SQLANNParser.ValuesContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQLANNParser#dataAttribute}.
	 * @param ctx the parse tree
	 */
	void enterDataAttribute(@NotNull SQLANNParser.DataAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLANNParser#dataAttribute}.
	 * @param ctx the parse tree
	 */
	void exitDataAttribute(@NotNull SQLANNParser.DataAttributeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQLANNParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(@NotNull SQLANNParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLANNParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(@NotNull SQLANNParser.StatementsContext ctx);

	/**
	 * Enter a parse tree produced by the {@code nullValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void enterNullValue(@NotNull SQLANNParser.NullValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void exitNullValue(@NotNull SQLANNParser.NullValueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(@NotNull SQLANNParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(@NotNull SQLANNParser.IntegerValueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code listDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterListDataType(@NotNull SQLANNParser.ListDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitListDataType(@NotNull SQLANNParser.ListDataTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code showDataStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowDataStatement(@NotNull SQLANNParser.ShowDataStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showDataStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowDataStatement(@NotNull SQLANNParser.ShowDataStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code realValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void enterRealValue(@NotNull SQLANNParser.RealValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code realValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void exitRealValue(@NotNull SQLANNParser.RealValueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code trainStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterTrainStatement(@NotNull SQLANNParser.TrainStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trainStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitTrainStatement(@NotNull SQLANNParser.TrainStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code idValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void enterIdValue(@NotNull SQLANNParser.IdValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 */
	void exitIdValue(@NotNull SQLANNParser.IdValueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code showStatusStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowStatusStatement(@NotNull SQLANNParser.ShowStatusStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showStatusStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowStatusStatement(@NotNull SQLANNParser.ShowStatusStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code stringDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterStringDataType(@NotNull SQLANNParser.StringDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitStringDataType(@NotNull SQLANNParser.StringDataTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code dateDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDateDataType(@NotNull SQLANNParser.DateDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDateDataType(@NotNull SQLANNParser.DateDataTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQLANNParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(@NotNull SQLANNParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLANNParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(@NotNull SQLANNParser.ListContext ctx);

	/**
	 * Enter a parse tree produced by the {@code dropDataStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDropDataStatement(@NotNull SQLANNParser.DropDataStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropDataStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDropDataStatement(@NotNull SQLANNParser.DropDataStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQLANNParser#dataAttributes}.
	 * @param ctx the parse tree
	 */
	void enterDataAttributes(@NotNull SQLANNParser.DataAttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLANNParser#dataAttributes}.
	 * @param ctx the parse tree
	 */
	void exitDataAttributes(@NotNull SQLANNParser.DataAttributesContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQLANNParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(@NotNull SQLANNParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLANNParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(@NotNull SQLANNParser.ParamsContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQLANNParser#paramValue}.
	 * @param ctx the parse tree
	 */
	void enterParamValue(@NotNull SQLANNParser.ParamValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLANNParser#paramValue}.
	 * @param ctx the parse tree
	 */
	void exitParamValue(@NotNull SQLANNParser.ParamValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQLANNParser#complexList}.
	 * @param ctx the parse tree
	 */
	void enterComplexList(@NotNull SQLANNParser.ComplexListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLANNParser#complexList}.
	 * @param ctx the parse tree
	 */
	void exitComplexList(@NotNull SQLANNParser.ComplexListContext ctx);

	/**
	 * Enter a parse tree produced by the {@code insertIntoStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterInsertIntoStatement(@NotNull SQLANNParser.InsertIntoStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code insertIntoStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitInsertIntoStatement(@NotNull SQLANNParser.InsertIntoStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code createDataStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCreateDataStatement(@NotNull SQLANNParser.CreateDataStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code createDataStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCreateDataStatement(@NotNull SQLANNParser.CreateDataStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code showNeuralNetworkStatusStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowNeuralNetworkStatusStatement(@NotNull SQLANNParser.ShowNeuralNetworkStatusStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showNeuralNetworkStatusStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowNeuralNetworkStatusStatement(@NotNull SQLANNParser.ShowNeuralNetworkStatusStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code showDataStatusStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowDataStatusStatement(@NotNull SQLANNParser.ShowDataStatusStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showDataStatusStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowDataStatusStatement(@NotNull SQLANNParser.ShowDataStatusStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code realDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterRealDataType(@NotNull SQLANNParser.RealDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code realDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitRealDataType(@NotNull SQLANNParser.RealDataTypeContext ctx);
}