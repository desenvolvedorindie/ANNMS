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
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SQLANNParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SQLANNVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code integerDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerDataType(@NotNull SQLANNParser.IntegerDataTypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code showNeuralNetworksStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowNeuralNetworksStatement(@NotNull SQLANNParser.ShowNeuralNetworksStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code runStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRunStatement(@NotNull SQLANNParser.RunStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQLANNParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(@NotNull SQLANNParser.ParamContext ctx);

	/**
	 * Visit a parse tree produced by the {@code stringValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(@NotNull SQLANNParser.StringValueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code dropNeuralNetworkStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropNeuralNetworkStatement(@NotNull SQLANNParser.DropNeuralNetworkStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code booleanValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValue(@NotNull SQLANNParser.BooleanValueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code createNeuralNetworkStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateNeuralNetworkStatement(@NotNull SQLANNParser.CreateNeuralNetworkStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code booleanDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanDataType(@NotNull SQLANNParser.BooleanDataTypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code values}
	 * labeled alternative in {@link SQLANNParser#dataTypestatementstatementvaluestatementdataTypestatementvaluedataTypestatementstatementvaluestatementdataTypestatementstatementvaluevaluedataTypestatementstatementstatementvaluedataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValues(@NotNull SQLANNParser.ValuesContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQLANNParser#dataAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataAttribute(@NotNull SQLANNParser.DataAttributeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQLANNParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(@NotNull SQLANNParser.StatementsContext ctx);

	/**
	 * Visit a parse tree produced by the {@code nullValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullValue(@NotNull SQLANNParser.NullValueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerValue(@NotNull SQLANNParser.IntegerValueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code listDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListDataType(@NotNull SQLANNParser.ListDataTypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code showDataStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowDataStatement(@NotNull SQLANNParser.ShowDataStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code realValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealValue(@NotNull SQLANNParser.RealValueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code trainStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrainStatement(@NotNull SQLANNParser.TrainStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code idValue}
	 * labeled alternative in {@link SQLANNParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdValue(@NotNull SQLANNParser.IdValueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code showStatusStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowStatusStatement(@NotNull SQLANNParser.ShowStatusStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code stringDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringDataType(@NotNull SQLANNParser.StringDataTypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code dateDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDateDataType(@NotNull SQLANNParser.DateDataTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQLANNParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(@NotNull SQLANNParser.ListContext ctx);

	/**
	 * Visit a parse tree produced by the {@code dropDataStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropDataStatement(@NotNull SQLANNParser.DropDataStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQLANNParser#dataAttributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataAttributes(@NotNull SQLANNParser.DataAttributesContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQLANNParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(@NotNull SQLANNParser.ParamsContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQLANNParser#paramValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamValue(@NotNull SQLANNParser.ParamValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQLANNParser#complexList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexList(@NotNull SQLANNParser.ComplexListContext ctx);

	/**
	 * Visit a parse tree produced by the {@code insertIntoStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertIntoStatement(@NotNull SQLANNParser.InsertIntoStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code createDataStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDataStatement(@NotNull SQLANNParser.CreateDataStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code showNeuralNetworkStatusStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowNeuralNetworkStatusStatement(@NotNull SQLANNParser.ShowNeuralNetworkStatusStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code showDataStatusStatement}
	 * labeled alternative in {@link SQLANNParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowDataStatusStatement(@NotNull SQLANNParser.ShowDataStatusStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code realDataType}
	 * labeled alternative in {@link SQLANNParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealDataType(@NotNull SQLANNParser.RealDataTypeContext ctx);
}