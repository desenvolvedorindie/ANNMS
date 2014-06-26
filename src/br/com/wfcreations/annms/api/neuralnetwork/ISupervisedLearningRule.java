package br.com.wfcreations.annms.api.neuralnetwork;

import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.values.IDValue;

public interface ISupervisedLearningRule extends ILearningRule {

	public INeuralNetwork train(INeuralNetwork network, Data data, IDValue[] inputs, IDValue[] outputs) throws Exception;
}
