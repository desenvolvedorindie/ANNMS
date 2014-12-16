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
import br.com.wfcreations.annms.api.data.DataUtils;
import br.com.wfcreations.annms.api.data.Param;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.neuralnetwork.INeuralNetwork;
import br.com.wfcreations.annms.api.neuralnetwork.ISupervisedLearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.IUnsupervisedLearningRule;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;
import br.com.wfcreations.annms.core.exception.ANNMSRequestExecutionException;
import br.com.wfcreations.annms.core.exception.ANNMSRequestValidationException;
import br.com.wfcreations.annms.core.neuralnetwork.NeuralnetworkWrapper;
import br.com.wfcreations.annms.core.service.AlgorithmsLoader;
import br.com.wfcreations.annms.core.service.Schema;
import br.com.wfcreations.annms.core.sqlann.SQLANNStatement;
import br.com.wfcreations.annms.core.transport.message.ResultMessage;
import br.com.wfcreations.annms.core.transport.message.TrainResultMessage;

public class TrainStatement implements SQLANNStatement {

	public final String neuralNetworkName;

	public final Param[] params;

	public final String learnRule;

	public final String dataName;

	public final ID[] inputs;

	public final ID[] outputs;

	public final String query;

	public TrainStatement(String neuralNetworkName, Param[] params, String learnRule, String dataName, ID[] inputs, ID[] outputs, String query) {
		this.neuralNetworkName = neuralNetworkName;
		this.params = params;
		this.learnRule = learnRule;
		this.dataName = dataName;
		this.inputs = inputs;
		this.outputs = outputs;
		this.query = query;
	}

	@Override
	public void checkAccess() {
	}

	@Override
	public void validate() throws ANNMSRequestValidationException {
		if (!ID.valid(this.neuralNetworkName))
			throw new ANNMSRequestValidationException(ANNMSExceptionCode.NEURALNETWORK, "Invalid neuralnetwork id");
	}

	@Override
	public ResultMessage execute() throws ANNMSRequestExecutionException {
		ID neuralnetworkID = ID.create(this.neuralNetworkName);
		NeuralnetworkWrapper wrapper = Schema.instance.getNeuralnetworkInstance(neuralnetworkID);
		if (wrapper == null)
			throw new ANNMSRequestExecutionException(ANNMSExceptionCode.NEURALNETWORK, String.format("Neuralnetwork %s doesn't exist", this.neuralNetworkName));

		ID dataID = ID.create(this.dataName);
		Data data = Schema.instance.getDataInstance(dataID);
		if (data == null)
			throw new ANNMSRequestExecutionException(ANNMSExceptionCode.DATA, String.format("Data doesn't %s exist", this.dataName));

		if (inputs != null && inputs.length > 0 && outputs != null && outputs.length > 0) {
			ISupervisedLearningRule supervisedLearnRule = AlgorithmsLoader.instance.instantiateSupervisedLearningRule(learnRule);
			if (supervisedLearnRule == null)
				throw new ANNMSRequestExecutionException(ANNMSExceptionCode.LEARNINGRULE, String.format("Supervisied Learning Rule %s doesn't exist", this.learnRule));

			try {
				supervisedLearnRule.create(this.params);
			} catch (Exception e) {
				String msg = e.getMessage() != null ? e.getMessage() : e.getCause().getMessage();
				throw new ANNMSRequestExecutionException(ANNMSExceptionCode.LEARNINGRULE, String.format("Learning rule creation error cause: %s", msg));
			}

			
			Data inputsData = DataUtils.sliceByAttributess(data, inputs);
			Data outputsData = DataUtils.sliceByAttributess(data, outputs);
			try {
				INeuralNetwork neuralnetwork = supervisedLearnRule.train(wrapper.getNeuralnetwork(), inputsData, outputsData);
				if (neuralnetwork != null) {
					Schema.instance.removeNeuralnetworkInstance(neuralnetworkID);
					NeuralnetworkWrapper newWrapper = new NeuralnetworkWrapper(neuralnetworkID, wrapper.getModel(), wrapper.getParams(), neuralnetwork);
					Schema.instance.storeNeuralnetworkInstance(newWrapper);
				} else {
					throw new ANNMSRequestExecutionException(ANNMSExceptionCode.NEURALNETWORK, "Neuralnetwork not trained");
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = "";//e.getMessage() != null ? e.getMessage() : e.getCause().getMessage();
				throw new ANNMSRequestExecutionException(ANNMSExceptionCode.LEARNINGRULE, String.format("Learning rule training error cause: %s", msg));
			}

			return new TrainResultMessage();
		} else if (inputs != null && inputs.length > 0) {
			IUnsupervisedLearningRule unsupervisedLearnRule = AlgorithmsLoader.instance.instantiateUnsupervisedLearningRule(learnRule);
			if (unsupervisedLearnRule == null)
				throw new ANNMSRequestExecutionException(ANNMSExceptionCode.LEARNINGRULE, String.format("Unsupervised Learning Rule %s doesn't exist", this.learnRule));
			try {
				unsupervisedLearnRule.create(this.params);
				unsupervisedLearnRule.train(wrapper.getNeuralnetwork(), DataUtils.sliceByAttributess(data, inputs));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return new TrainResultMessage();
		}
		throw new ANNMSRequestExecutionException(ANNMSExceptionCode.SYNTAXE_ERROR, "Invalid train");
	}
}