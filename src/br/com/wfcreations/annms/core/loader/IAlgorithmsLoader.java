package br.com.wfcreations.annms.core.loader;

import br.com.wfcreations.annms.api.neuralnetwork.INeuralNetwork;
import br.com.wfcreations.annms.api.neuralnetwork.ISupervisedLearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.IUnsupervisedLearningRule;

public interface IAlgorithmsLoader {

	public void loadNeuralNetwoks();
	
	public void loadLearningRules();
	
	public INeuralNetwork createNeuralNetwork(String id);
	
	public ISupervisedLearningRule createSupervisedLearningRule(String id);
	
	public IUnsupervisedLearningRule createUnsupervisedLearningRule(String id);
}
