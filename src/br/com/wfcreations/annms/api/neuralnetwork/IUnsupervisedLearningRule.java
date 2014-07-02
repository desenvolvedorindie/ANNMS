package br.com.wfcreations.annms.api.neuralnetwork;

import br.com.wfcreations.annms.api.data.Data;

public interface IUnsupervisedLearningRule extends ILearningRule {

	public INeuralNetwork train(INeuralNetwork network, Data inputs) throws Exception;
}
