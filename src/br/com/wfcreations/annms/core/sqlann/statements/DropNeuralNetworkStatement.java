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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.lang.ArrayUtils;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;
import br.com.wfcreations.annms.core.exception.ANNMSRequestExecutionException;
import br.com.wfcreations.annms.core.exception.ANNMSRequestValidationException;
import br.com.wfcreations.annms.core.service.Schema;
import br.com.wfcreations.annms.core.sqlann.SQLANNStatement;
import br.com.wfcreations.annms.core.transport.message.DropDataResultMessage;
import br.com.wfcreations.annms.core.transport.message.ResultMessage;

public class DropNeuralNetworkStatement implements SQLANNStatement {

	private static final Logger LOGGER = LoggerFactory.getLogger(DropDataStatement.class);

	public final String[] neuralnetworkList;

	public final boolean ifExists;

	public final String query;

	public DropNeuralNetworkStatement(String[] neuralnetworkList, boolean ifExists, String query) {
		this.neuralnetworkList = neuralnetworkList;
		this.ifExists = ifExists;
		this.query = query;
	}

	@Override
	public void checkAccess() {
	}

	@Override
	public void validate() throws ANNMSRequestValidationException {
		ID[] ids = new ID[this.neuralnetworkList.length];
		int i = 0;
		for (String dataID : this.neuralnetworkList)
			ids[i++] = ID.create(dataID);
		if (ArrayUtils.hasDuplicate(ids))
			throw new ANNMSRequestValidationException(ANNMSExceptionCode.STORAGE, "data list has duplicated data");
	}

	@Override
	public ResultMessage execute() throws ANNMSRequestExecutionException {
		List<ID> nonExistent = new ArrayList<ID>();
		List<ID> removed = new ArrayList<ID>();
		ID id;

		for (String neuralnetworkID : neuralnetworkList) {
			id = ID.create(neuralnetworkID);
			if (Schema.instance.getNeuralnetworkInstance(id) != null) {
				Schema.instance.removeNeuralnetworkInstance(id);
				removed.add(id);
			} else
				nonExistent.add(id);
		}
		if (removed.size() > 0)
			LOGGER.info("Dropped neuralnetwork list: {}", Arrays.toString(new ID[removed.size()]));
		if (nonExistent.size() > 0 && !ifExists)
			throw new ANNMSRequestExecutionException(ANNMSExceptionCode.STORAGE, String.format("Non existent neuralnetwork: %s", Arrays.toString(nonExistent.toArray(new ID[nonExistent.size()]))));
		return new DropDataResultMessage(removed.size());
	}
}