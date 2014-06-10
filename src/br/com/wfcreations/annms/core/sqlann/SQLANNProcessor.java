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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import br.com.wfcreations.annms.core.exception.RequestExecutionException;
import br.com.wfcreations.annms.core.exception.RequestValidationException;
import br.com.wfcreations.annms.core.transport.message.ResultMessage;

public class SQLANNProcessor {

	private static SQLANNProcessor instance;

	public static SQLANNProcessor getInstance() {
		if (instance == null) {
			instance = new SQLANNProcessor();
		}
		return instance;
	}

	private SQLANNProcessor() {
	}

	public ResultMessage[] process(String query) throws RequestExecutionException, RequestValidationException {
		SQLANNStatement[] statements = getStatements(query);
		return processStatements(statements);
	}

	public SQLANNStatement[] getStatements(String query) throws RequestValidationException {
		SQLANNStatement[] statements = parseStatements(query);
		return statements;
	}

	public SQLANNStatement[] parseStatements(String query) throws SQLANNSyntaxException {
		try {
			ANTLRInputStream input = new ANTLRInputStream(new ByteArrayInputStream(query.getBytes()));

			SQLANNLexer lexer = new SQLANNLexer(input);

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			SQLANNErrorListener errorListener = new SQLANNErrorListener();

			SQLANNParser parser = new SQLANNParser(tokens);
			parser.removeErrorListeners();
			parser.addErrorListener(errorListener);

			ParseTree tree = parser.statements();

			SQLANN sqlann = new SQLANN();
			sqlann.visit(tree);

			if (errorListener.getErrors().size() > 0) {
				throw new SQLANNSyntaxException(errorListener.getErrors().get(0).getMsg());
			}

			return sqlann.statements();
		} catch (IOException e) {
			throw new SQLANNSyntaxException(e.getMessage());
		}
	}

	public ResultMessage[] processStatements(SQLANNStatement[] statements) throws RequestExecutionException, RequestValidationException {
		ArrayList<ResultMessage> resultMessages = new ArrayList<>();

		for (int i = 0; i < statements.length; i++) {
			statements[i].checkAccess();
			statements[i].validate();
			resultMessages.add(statements[i].execute());
		}

		return resultMessages.toArray(new ResultMessage[resultMessages.size()]);
	}
}
