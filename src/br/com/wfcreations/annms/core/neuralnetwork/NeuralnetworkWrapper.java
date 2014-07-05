package br.com.wfcreations.annms.core.neuralnetwork;

import java.io.Serializable;

import br.com.wfcreations.annms.api.data.Param;
import br.com.wfcreations.annms.api.neuralnetwork.INeuralNetwork;

public class NeuralnetworkWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String model;

	private final Param[] params;

	private final INeuralNetwork neuralnetwork;

	public NeuralnetworkWrapper(String model, Param[] params, INeuralNetwork neuralnetwork) {
		this.model = model;
		this.params = params;
		this.neuralnetwork = neuralnetwork;
	}

	public String getModel() {
		return model;
	}

	public Param[] getParams() {
		return params;
	}

	public INeuralNetwork getNeuralnetwork() {
		return neuralnetwork;
	}
}