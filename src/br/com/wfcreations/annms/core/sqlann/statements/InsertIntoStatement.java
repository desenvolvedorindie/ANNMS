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
package br.com.wfcreations.annms.core.sqlann.statements;

import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.Pattern;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.IValue;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;
import br.com.wfcreations.annms.core.exception.ANNMSRequestExecutionException;
import br.com.wfcreations.annms.core.exception.ANNMSRequestValidationException;
import br.com.wfcreations.annms.core.service.Schema;
import br.com.wfcreations.annms.core.sqlann.SQLANNStatement;
import br.com.wfcreations.annms.core.transport.message.InsertIntoResultMesssage;
import br.com.wfcreations.annms.core.transport.message.ResultMessage;

public class InsertIntoStatement implements SQLANNStatement {

	public final String name;

	public final IValue[] values;

	public final String query;

	public InsertIntoStatement(String name, IValue[] values, String query) {
		this.name = name;
		this.values = values;
		this.query = query;
	}

	@Override
	public void checkAccess() {

	}

	@Override
	public void validate() throws ANNMSRequestValidationException {
		if (!ID.valid(this.name))
			throw new ANNMSRequestValidationException(ANNMSExceptionCode.DATA, "Invalid data id");
	}

	@Override
	public ResultMessage execute() throws ANNMSRequestExecutionException {
		ID id = ID.create(this.name);
		Data data = Schema.instance.getDataInstance(id);
		if (data == null)
			throw new ANNMSRequestExecutionException(ANNMSExceptionCode.STORAGE, String.format("Data %s doesn't exist", id.getValue()));
		try {
			data.add(new Pattern(values));
		} catch (IllegalArgumentException e) {
			throw new ANNMSRequestExecutionException(ANNMSExceptionCode.STORAGE, e.getMessage());
		}
		return new InsertIntoResultMesssage(1);
	}
}