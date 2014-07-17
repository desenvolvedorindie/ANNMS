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

import br.com.wfcreations.annms.api.data.Param;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.neuralnetwork.INeuralNetwork;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;
import br.com.wfcreations.annms.core.exception.ANNMSRequestExecutionException;
import br.com.wfcreations.annms.core.exception.ANNMSRequestValidationException;
import br.com.wfcreations.annms.core.neuralnetwork.NeuralnetworkWrapper;
import br.com.wfcreations.annms.core.service.AlgorithmsLoader;
import br.com.wfcreations.annms.core.service.Schema;
import br.com.wfcreations.annms.core.sqlann.SQLANNStatement;
import br.com.wfcreations.annms.core.transport.message.CreateNeuralnetworkResultMessage;
import br.com.wfcreations.annms.core.transport.message.ResultMessage;

public class CreateNeuralNetworkStatement implements SQLANNStatement {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateNeuralNetworkStatement.class);

	public final String name;

	public final Param[] params;

	public final String model;

	public final boolean ifNotExists;

	public final String copy;

	public final String query;

	public CreateNeuralNetworkStatement(String id, Param[] params, String model, boolean ifNotExists, String copyID, String query) {
		this.name = id;
		this.params = params;
		this.model = model;
		this.ifNotExists = ifNotExists;
		this.copy = copyID;
		this.query = query;
	}

	@Override
	public void checkAccess() {
	}

	@Override
	public void validate() throws ANNMSRequestValidationException {
		if (!ID.valid(this.name))
			throw new ANNMSRequestValidationException(ANNMSExceptionCode.DATA, "Invalid neuralnetwork id");
		if (this.copy != null && !ID.valid(this.copy))
			throw new ANNMSRequestValidationException(ANNMSExceptionCode.DATA, "Invalid copy id");
	}

	@Override
	public ResultMessage execute() throws ANNMSRequestExecutionException {
		ID id = ID.create(this.name);
		if (Schema.instance.getNeuralnetworkInstance(id) != null)
			if (ifNotExists)
				return new CreateNeuralnetworkResultMessage(null, null);
			else
				throw new ANNMSRequestExecutionException(ANNMSExceptionCode.NEURALNETWORK, String.format("Neural Network already %s exist", this.name));

		Param[] paramsNew = null;
		String modelNew = null;
		INeuralNetwork neuralnetwork = null;

		if (this.copy != null) {
			ID copyID = ID.create(this.copy);
			NeuralnetworkWrapper wrapper = Schema.instance.getNeuralnetworkInstance(copyID);
			if (wrapper == null)
				throw new ANNMSRequestExecutionException(ANNMSExceptionCode.NEURALNETWORK, String.format("Neuralnetwork %s doesn't exist", copy));

			paramsNew = wrapper.getParams();
			modelNew = wrapper.getModel();
		} else {
			paramsNew = this.params;
			modelNew = this.model;
		}

		neuralnetwork = AlgorithmsLoader.instance.instantiateNeuralNetwork(modelNew);
		if (neuralnetwork == null)
			throw new ANNMSRequestExecutionException(ANNMSExceptionCode.NEURALNETWORK, "Neural network model not found");

		try {
			neuralnetwork.create(paramsNew);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = e.getMessage() != null ? e.getMessage() : e.getCause().getMessage();
			throw new ANNMSRequestExecutionException(ANNMSExceptionCode.NEURALNETWORK, String.format("Neural network creation error cause: %s", msg));
		}

		Schema.instance.storeNeuralnetworkInstance(id, new NeuralnetworkWrapper(modelNew, paramsNew, neuralnetwork));

		LOGGER.info("Neuralnetwork {} of {} model created", this.name, modelNew);
		return new CreateNeuralnetworkResultMessage(id, modelNew);
	}
}