package br.com.wfcreations.annms.core.loader;

import br.com.wfcreations.annms.api.neuralnetwork.INeuralNetwork;
import br.com.wfcreations.annms.api.neuralnetwork.ISupervisedLearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.IUnsupervisedLearningRule;

public interface IAlgorithmsLoader {

	public void loadNeuralNetwoks();
	
	public void loadSupervisedLearningRules();
	
	public void loadUnsupervisedLearningRules();
	
	public INeuralNetwork instantiateNeuralNetwork(String id);
	
	public ISupervisedLearningRule instantiateSupervisedLearningRule(String id);
	
	public IUnsupervisedLearningRule instantiateUnsupervisedLearningRule(String id);
}
