package br.com.wfcreations.annms.core.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.wfcreations.annms.core.data.Data;
import br.com.wfcreations.annms.core.data.NeuralNetwork;

public class Schema {

	public static final Schema instance = new Schema();

	private final Map<String, Data> dataKeyspaceInstances = new ConcurrentHashMap<String, Data>();

	private final Map<String, NeuralNetwork> networkKeyspaceInstances = new ConcurrentHashMap<String, NeuralNetwork>();

	private Schema() {
	}

	public Data getDataKeyspaceInstance(String name) {
		return dataKeyspaceInstances.get(name);
	}

	public void storeDataKeyspaceInstance(Data data) {
		if (dataKeyspaceInstances.containsKey(data.getName()))
			throw new IllegalArgumentException(String.format("Data %s was already initialized.", data.getName()));
		dataKeyspaceInstances.put(data.getName(), data);
	}

	public Data removeDataKeyspaceInstance(String name) {
		return dataKeyspaceInstances.remove(name);
	}

	public NeuralNetwork getNetworkKeyspaceInstance(String name) {
		return networkKeyspaceInstances.get(name);
	}

	public void storeNetworkKeyspaceInstance(NeuralNetwork data) {
		if (dataKeyspaceInstances.containsKey(data.getName()))
			throw new IllegalArgumentException(String.format("Neural Network %s was already initialized.", data.getName()));
		networkKeyspaceInstances.put(data.getName(), data);
	}

	public NeuralNetwork removeNetworkKeyspaceInstance(String name) {
		return networkKeyspaceInstances.remove(name);
	}

	public void clearDataKeyspace() {

	}
}