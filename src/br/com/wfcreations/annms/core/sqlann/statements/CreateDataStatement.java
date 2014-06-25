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
package br.com.wfcreations.annms.core.sqlann.statements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.core.config.Schema;
import br.com.wfcreations.annms.core.data.Attribute;
import br.com.wfcreations.annms.core.data.Data;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;
import br.com.wfcreations.annms.core.exception.ANNMSRequestExecutionException;
import br.com.wfcreations.annms.core.exception.ANNMSRequestValidationException;
import br.com.wfcreations.annms.core.sqlann.SQLANNStatement;
import br.com.wfcreations.annms.core.transport.message.DataCreateResultMessage;
import br.com.wfcreations.annms.core.transport.message.ResultMessage;

public class CreateDataStatement implements SQLANNStatement {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateDataStatement.class);

	public final String name;

	public final Attribute[] attributes;

	public final boolean ifNotExists;

	public final String copy;

	public final String query;

	public CreateDataStatement(String name, Attribute[] attributes, boolean ifNotExists, String copy, String query) {
		this.name = name;
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
	public ResultMessage execute() throws ANNMSRequestValidationException, ANNMSRequestExecutionException {
		if (Schema.instance.getDataInstance(name) != null)
			throw new ANNMSRequestExecutionException(ANNMSExceptionCode.STORAGE, String.format("Data already %s exist", name));

		Data data;
		if (copy != null && copy != "") {
			data = Schema.instance.getDataInstance(copy);
			if (data == null) {
				throw new ANNMSRequestExecutionException(ANNMSExceptionCode.STORAGE, String.format("Data %s doesn't exist", copy));
			} else {
				data = data.clone();
				data.setName(name);
			}
		} else {
			data = new Data(this.name, this.attributes);
		}
		Schema.instance.storeDataInstance(data);
		LOGGER.info("Data {} created", this.name);
		return new DataCreateResultMessage(this.name);
	}
}