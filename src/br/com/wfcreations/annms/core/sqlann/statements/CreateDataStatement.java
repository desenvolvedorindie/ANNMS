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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;
import br.com.wfcreations.annms.core.exception.ANNMSRequestExecutionException;
import br.com.wfcreations.annms.core.exception.ANNMSRequestValidationException;
import br.com.wfcreations.annms.core.service.Schema;
import br.com.wfcreations.annms.core.sqlann.SQLANNStatement;
import br.com.wfcreations.annms.core.transport.message.CreateDataResultMessage;
import br.com.wfcreations.annms.core.transport.message.ResultMessage;

public class CreateDataStatement implements SQLANNStatement {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateDataStatement.class);

	public final ID id;

	public final Attribute[] attributes;

	public final boolean ifNotExists;

	public final ID copy;

	public final String query;

	public CreateDataStatement(ID id, Attribute[] attributes, boolean ifNotExists, ID copy, String query) {
		this.id = id;
		this.attributes = attributes;
		this.ifNotExists = ifNotExists;
		this.copy = copy;
		this.query = query;
	}

	@Override
	public void checkAccess() {
	}

	@Override
	public void validate() throws ANNMSRequestValidationException {
	}

	@Override
	public ResultMessage execute() throws ANNMSRequestExecutionException {
		if (Schema.instance.getDataInstance(id) != null)
			if (ifNotExists)
				return new CreateDataResultMessage(null);
			else
				throw new ANNMSRequestExecutionException(ANNMSExceptionCode.DATA, String.format("Data already %s exist", this.id));

		Data data;
		if (copy != null) {
			data = Schema.instance.getDataInstance(copy);
			if (data == null)
				throw new ANNMSRequestExecutionException(ANNMSExceptionCode.STORAGE, String.format("Data %s doesn't exist", copy));

			data = data.clone();
			data.setID(id);
		} else {
			data = new Data(this.id, this.attributes);
		}
		Schema.instance.storeDataInstance(data);
		LOGGER.info("Data {} created", this.id);
		return new CreateDataResultMessage(this.id);
	}
}