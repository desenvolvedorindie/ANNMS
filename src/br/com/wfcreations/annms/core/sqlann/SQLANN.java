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

import java.util.ArrayList;

import org.antlr.v4.runtime.misc.NotNull;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.Param;
import br.com.wfcreations.annms.api.data.type.IType;
import br.com.wfcreations.annms.api.data.type.Date;
import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.type.Primitive;
import br.com.wfcreations.annms.api.data.value.Bool;
import br.com.wfcreations.annms.api.data.value.ComplexList;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.IParamValue;
import br.com.wfcreations.annms.api.data.value.IValue;
import br.com.wfcreations.annms.api.data.value.Int;
import br.com.wfcreations.annms.api.data.value.Null;
import br.com.wfcreations.annms.api.data.value.Real;
import br.com.wfcreations.annms.api.data.value.Str;
import br.com.wfcreations.annms.core.sqlann.statements.CreateDataStatement;
import br.com.wfcreations.annms.core.sqlann.statements.CreateNeuralNetworkStatement;
import br.com.wfcreations.annms.core.sqlann.statements.DropDataStatement;
import br.com.wfcreations.annms.core.sqlann.statements.DropNeuralNetworkStatement;
import br.com.wfcreations.annms.core.sqlann.statements.InsertIntoStatement;
import br.com.wfcreations.annms.core.sqlann.statements.RunStatement;
import br.com.wfcreations.annms.core.sqlann.statements.ShowDataStatement;
import br.com.wfcreations.annms.core.sqlann.statements.ShowDataStatusStatement;
import br.com.wfcreations.annms.core.sqlann.statements.ShowNeuralNetworkStatusStatement;
import br.com.wfcreations.annms.core.sqlann.statements.ShowNeuralNetworksStatement;
import br.com.wfcreations.annms.core.sqlann.statements.ShowStatusStatement;
import br.com.wfcreations.annms.core.sqlann.statements.TrainStatement;

public class SQLANN extends SQLANNBaseVisitor<Object> {

	private SQLANNStatement[] statements;

	public SQLANNStatement[] statements() {
		return this.statements;
	}

	@Override
	public Void visitStatements(@NotNull SQLANNParser.StatementsContext ctx) {
		if (ctx != null) {
			ArrayList<SQLANNStatement> stms = new ArrayList<>();
			SQLANNStatement stm = null;
			for (int i = 0; i < ctx.statement().size(); i++) {
				stm = (SQLANNStatement) visit(ctx.statement(i));
				if (stm != null) {
					stms.add(stm);
				}
			}
			statements = stms.toArray(new SQLANNStatement[stms.size()]);
		}
		return null;
	}

	@Override
	public CreateDataStatement visitCreateDataStatement(@NotNull SQLANNParser.CreateDataStatementContext ctx) {
		if (ctx != null && ctx.ID() != null && ctx.CREATE() != null && ctx.DATA() != null && ctx.ID().size() > 0) {
			String name = ctx.ID(0).getText();

			Attribute[] attributes = null;
			if (ctx.dataAttributes() != null) {
				attributes = (Attribute[]) visit(ctx.dataAttributes());
			} else
				attributes = new Attribute[0];

			boolean ifNotExists = ctx.IF() != null && ctx.NOT() != null && ctx.EXISTS() != null;

			String copy = ctx.ID().size() == 2 ? ctx.ID().get(1).getText() : null;

			String query = ctx.getText();

			return new CreateDataStatement(name, attributes, ifNotExists, copy, query);
		}
		return null;
	};

	@Override
	public CreateNeuralNetworkStatement visitCreateNeuralNetworkStatement(@NotNull SQLANNParser.CreateNeuralNetworkStatementContext ctx) {
		if (ctx != null && ctx.CREATE() != null && ctx.NEURALNETWORK() != null && ctx.ID() != null && ctx.ID().size() > 0) {
			String name = ctx.ID(0).getText();

			Param[] params = null;
			if (ctx.params() != null)
				params = (Param[]) visit(ctx.params());

			if (params == null)
				params = new Param[0];

			String model = ctx.ID().size() == 2 && ctx.LIKE() == null ? ctx.ID(1).getText().toUpperCase() : null;

			boolean ifNotExists = ctx.IF() != null && ctx.NOT() != null && ctx.EXISTS() != null;

			String copy = ctx.ID().size() == 2 && ctx.LIKE() != null ? ctx.ID(1).getText() : null;

			String query = ctx.getText();

			return new CreateNeuralNetworkStatement(name, params, model, ifNotExists, copy, query);
		}
		return null;
	}

	@Override
	public DropDataStatement visitDropDataStatement(@NotNull SQLANNParser.DropDataStatementContext ctx) {
		if (ctx != null && ctx.DROP() != null && ctx.DATA() != null && ctx.ID() != null) {
			String[] dataList = new String[ctx.ID().size()];

			for (int i = 0; i < ctx.ID().size(); i++) {
				dataList[i] = ctx.ID(i).getText();
			}

			boolean ifExits = ctx.IF() != null && ctx.EXISTS() != null;

			String query = ctx.getText();

			return new DropDataStatement(dataList, ifExits, query);
		}
		return null;
	}

	@Override
	public DropNeuralNetworkStatement visitDropNeuralNetworkStatement(@NotNull SQLANNParser.DropNeuralNetworkStatementContext ctx) {
		if (ctx != null && ctx.DROP() != null && ctx.NEURALNETWORKS() != null && ctx.ID() != null) {
			String[] neuralnetworkList = new String[ctx.ID().size()];

			for (int i = 0; i < ctx.ID().size(); i++) {
				neuralnetworkList[i] = ctx.ID(i).getText().toUpperCase();
			}

			boolean ifExists = ctx.IF() != null && ctx.EXISTS() != null;

			String query = ctx.getText();

			return new DropNeuralNetworkStatement(neuralnetworkList, ifExists, query);
		}
		return null;
	}

	@Override
	public InsertIntoStatement visitInsertIntoStatement(@NotNull SQLANNParser.InsertIntoStatementContext ctx) {
		if (ctx != null && ctx.INSERT() != null && ctx.INTO() != null && ctx.VALUES() != null && ctx.OPEN_PARENTHESIS() != null && ctx.CLOSE_PARENTHESIS() != null && ctx.ID() != null && ctx.values() != null) {

			String dataName = ctx.ID().getText();

			IValue[] values = (IValue[]) visit(ctx.values());

			String query = ctx.getText();

			return new InsertIntoStatement(dataName, values, query);
		}
		return null;
	}

	@Override
	public RunStatement visitRunStatement(@NotNull SQLANNParser.RunStatementContext ctx) {
		if (ctx != null && ctx.RUN() != null && ctx.VALUES() != null && ctx.OPEN_PARENTHESIS() != null && ctx.CLOSE_PARENTHESIS() != null && ctx.ID() != null && ctx.values() != null) {
			String modelName = ctx.ID().getText().toUpperCase();

			IValue[] values = (IValue[]) visit(ctx.values());

			String query = ctx.getText();

			return new RunStatement(modelName, values, query);
		}
		return null;
	}

	@Override
	public ShowDataStatement visitShowDataStatement(@NotNull SQLANNParser.ShowDataStatementContext ctx) {
		if (ctx != null && ctx.SHOW() != null && ctx.DATA() != null) {
			String query = ctx.getText();

			return new ShowDataStatement(query);
		}
		return null;
	}

	@Override
	public ShowDataStatusStatement visitShowDataStatusStatement(@NotNull SQLANNParser.ShowDataStatusStatementContext ctx) {
		if (ctx != null && ctx.SHOW() != null && ctx.DATA() != null && ctx.STATUS() != null && ctx.ID() != null) {
			String name = ctx.ID().getText();

			String query = ctx.getText();

			return new ShowDataStatusStatement(name, query);
		}
		return null;
	}

	@Override
	public ShowNeuralNetworksStatement visitShowNeuralNetworksStatement(@NotNull SQLANNParser.ShowNeuralNetworksStatementContext ctx) {
		if (ctx != null && ctx.SHOW() != null && ctx.NEURALNETWORKS() != null) {
			String query = ctx.getText();

			return new ShowNeuralNetworksStatement(query);
		}
		return null;
	}

	@Override
	public ShowNeuralNetworkStatusStatement visitShowNeuralNetworkStatusStatement(@NotNull SQLANNParser.ShowNeuralNetworkStatusStatementContext ctx) {
		if (ctx != null && ctx.SHOW() != null && ctx.NEURALNETWORK() != null && ctx.STATUS() != null && ctx.ID() != null) {
			String name = ctx.ID().getText();

			String query = ctx.getText().toUpperCase();

			return new ShowNeuralNetworkStatusStatement(name, query);
		}
		return null;
	}

	@Override
	public ShowStatusStatement visitShowStatusStatement(@NotNull SQLANNParser.ShowStatusStatementContext ctx) {
		if (ctx != null && ctx.SHOW() != null && ctx.STATUS() != null) {
			String query = ctx.getText();

			return new ShowStatusStatement(query);
		}
		return null;
	}

	@Override
	public TrainStatement visitTrainStatement(@NotNull SQLANNParser.TrainStatementContext ctx) {
		if (ctx != null && ctx.TRAIN() != null && ctx.LEARNINGRULE() != null && ctx.DATA() != null && ctx.INPUT() != null && ctx.OUTPUT() != null && ctx.ID() != null && ctx.ID().size() > 2 && ctx.list().size() > 0) {
			String neuralNetworkName = ctx.ID(1).getText();

			Param[] params = (Param[]) visit(ctx.params());

			String learningRule = ctx.ID(1).getText();

			String dataName = ctx.ID(2).getText();

			ID[] inputs = (ID[]) visit(ctx.list(0));

			ID[] outputs = (ID[]) visit(ctx.list(1));

			String query = ctx.getText().toUpperCase();

			return new TrainStatement(neuralNetworkName, params, learningRule, dataName, inputs, outputs, query);
		}
		return null;
	}

	@Override
	public Attribute[] visitDataAttributes(@NotNull SQLANNParser.DataAttributesContext ctx) {
		if (ctx != null && ctx.COMMA() != null && ctx.dataAttribute() != null) {
			ArrayList<Attribute> attributes = new ArrayList<>();
			for (int i = 0; i < ctx.dataAttribute().size(); i++) {
				Attribute attribute = (Attribute) visit(ctx.dataAttribute(i));
				if (attribute != null)
					attributes.add(attribute);
			}
			return attributes.toArray(new Attribute[attributes.size()]);
		}
		return null;
	}

	@Override
	public Attribute visitDataAttribute(@NotNull SQLANNParser.DataAttributeContext ctx) {
		if (ctx != null && ctx.ID() != null && ctx.dataType() != null) {
			IType dataType = (IType) visit(ctx.dataType());
			boolean notNull = false;
			if (ctx.NOT() != null && ctx.NULL() != null)
				notNull = true;
			else if (ctx.NOT() != null) {
				return null;
			}
			return new Attribute(ID.create(ctx.ID().getText()), dataType, notNull);
		}
		return null;
	}

	@Override
	public Primitive visitBooleanDataType(@NotNull SQLANNParser.BooleanDataTypeContext ctx) {
		if (ctx != null && ctx.BOOLEAN() != null) {
			return Primitive.BOOL;
		}
		return null;
	}

	@Override
	public Primitive visitIntegerDataType(@NotNull SQLANNParser.IntegerDataTypeContext ctx) {
		if (ctx != null && ctx.INTEGER() != null) {
			return Primitive.INT;
		}
		return null;
	}

	@Override
	public Primitive visitRealDataType(@NotNull SQLANNParser.RealDataTypeContext ctx) {
		if (ctx != null && ctx.REAL() != null) {
			return Primitive.REAL;
		}
		return null;
	}

	@Override
	public Primitive visitStringDataType(@NotNull SQLANNParser.StringDataTypeContext ctx) {
		if (ctx != null && ctx.STRING() != null) {
			return Primitive.STR;
		}
		return null;
	}

	@Override
	public Date visitDateDataType(@NotNull SQLANNParser.DateDataTypeContext ctx) {
		if (ctx != null && ctx.DATE() != null) {
			if (ctx.String() != null)
				return new Date(SQLANNUtils.formatString(ctx.String().getText()));
			else
				return new Date(SQLANNUtils.formatString(Date.DEFAULT_DATE_FORMAT));
		}
		System.out.println(ctx.getText().toString());
		return null;
	}

	@Override
	public ListType visitListDataType(@NotNull SQLANNParser.ListDataTypeContext ctx) {
		if (ctx != null && ctx.list() != null) {
			ID[] listValues = (ID[]) visit(ctx.list());
			return new ListType(listValues);
		}
		return null;
	}

	@Override
	public ID[] visitList(@NotNull SQLANNParser.ListContext ctx) {
		if (ctx != null && ctx.OPEN_BRACKETS() != null && ctx.CLOSE_BRACKETS() != null && ctx.ID() != null && ctx.ID().size() > 0) {
			ArrayList<ID> ids = new ArrayList<>();
			for (int i = 0; i < ctx.ID().size(); i++) {
				ids.add(ID.create(ctx.ID(i).getText()));
			}
			return ids.toArray(new ID[ctx.ID().size()]);
		}
		return null;
	}

	@Override
	public Param[] visitParams(@NotNull SQLANNParser.ParamsContext ctx) {
		if (ctx != null && ctx.param() != null) {
			Param[] params = new Param[ctx.param().size()];
			for (int i = 0; i < ctx.param().size(); i++) {
				params[i] = (Param) visit(ctx.param(i));
			}
			return params;
		}
		return null;
	}

	@Override
	public Param visitParam(@NotNull SQLANNParser.ParamContext ctx) {
		if (ctx != null && ctx.ID() != null) {
			IParamValue[] values = new IParamValue[ctx.paramValue() == null ? 0 : ctx.paramValue().size()];
			for (int i = 0; i < ctx.paramValue().size(); i++) {
				values[i] = (IParamValue) visit(ctx.paramValue(i));
			}

			String id = ctx.ID().getText();

			return new Param(ID.create(id), values);
		}
		return null;
	}

	@Override
	public Bool visitBooleanValue(@NotNull SQLANNParser.BooleanValueContext ctx) {
		if (ctx != null && (ctx.TRUE() != null || ctx.FALSE() != null)) {
			if (ctx.TRUE() != null) {
				return Bool.TRUE;
			} else {
				return Bool.FALSE;
			}
		}
		return null;
	}

	@Override
	public Null visitNullValue(@NotNull SQLANNParser.NullValueContext ctx) {
		if (ctx != null && ctx.NULL() != null) {
			return Null.VALUE;
		}
		return null;
	}

	@Override
	public Int visitIntegerValue(@NotNull SQLANNParser.IntegerValueContext ctx) {
		if (ctx != null && ctx.Integer() != null) {
			return new Int(Integer.parseInt(ctx.Integer().getText()));
		}
		return null;
	}

	@Override
	public Real visitRealValue(@NotNull SQLANNParser.RealValueContext ctx) {
		if (ctx != null && ctx.Real() != null) {
			return new Real(Double.parseDouble(ctx.Real().getText()));
		}
		return null;
	}

	@Override
	public Str visitStringValue(@NotNull SQLANNParser.StringValueContext ctx) {
		if (ctx != null && ctx.String() != null) {
			return new Str(SQLANNUtils.formatString(ctx.String().getText()));
		}
		return null;
	}

	@Override
	public ID visitIdValue(@NotNull SQLANNParser.IdValueContext ctx) {
		if (ctx != null && ctx.ID() != null) {
			return ID.create(ctx.ID().getText());
		}
		return null;
	}

	@Override
	public IValue[] visitValues(@NotNull SQLANNParser.ValuesContext ctx) {
		if (ctx != null && ctx.value() != null && ctx.value().size() > 0) {
			IValue[] values = new IValue[ctx.value().size()];
			for (int i = 0; i < ctx.value().size(); i++) {
				values[i] = (IValue) visit(ctx.value(i));
			}
			return values;
		}
		return null;
	}

	@Override
	public ComplexList visitComplexList(@NotNull SQLANNParser.ComplexListContext ctx) {
		if (ctx != null && ctx.paramValue() != null && ctx.OPEN_BRACKETS() != null && ctx.CLOSE_BRACKETS() != null && ctx.paramValue().size() > 0) {
			IValue[] values = new IValue[ctx.paramValue().size()];
			for (int i = 0; i < ctx.paramValue().size(); i++) {
				values[i] = (IValue) visit(ctx.paramValue(i));
			}
			return new ComplexList(values);
		}
		return null;
	}
}