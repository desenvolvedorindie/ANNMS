package br.com.wfcreations.annms.api.neuralnetwork;

import java.io.Serializable;

import br.com.wfcreations.annms.api.data.Param;

public interface ILearningRule extends Serializable {

	public void create(Param[] params) throws Exception;
}
