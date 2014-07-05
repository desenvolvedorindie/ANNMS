package br.com.wfcreations.annms.core.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.core.neuralnetwork.NeuralnetworkWrapper;

public class Schema {

	public static final Schema instance = new Schema();

	private final Map<String, Data> dataInstances = new ConcurrentHashMap<String, Data>();

	private final Map<String, NeuralnetworkWrapper> neuralnetworkInstances = new ConcurrentHashMap<String, NeuralnetworkWrapper>();

	private Schema() {
	}

	public String[] getDataNames() {
		return dataInstances.keySet().toArray(new String[dataInstances.keySet().size()]);
	}

	public Data getDataInstance(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name can't be empty or null");
		return dataInstances.get(name);
	}

	public void storeDataInstance(Data data) {
		if (dataInstances.containsKey(data.getName()))
			throw new IllegalArgumentException(String.format("Data %s was already initialized.", data.getName()));
		dataInstances.put(data.getName(), data);
	}

	public Data removeDataInstance(String name) {
		return dataInstances.remove(name);
	}

	public void clearDataInstance() {
		dataInstances.clear();
	}

	public String[] getNeuralnetworksName() {
		return neuralnetworkInstances.keySet().toArray(new String[dataInstances.keySet().size()]);
	}

	public NeuralnetworkWrapper getNeuralnetworkInstance(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name can't be empty or null");
		return neuralnetworkInstances.get(name);
	}

	public void storeNeuralnetworkInstance(String name, NeuralnetworkWrapper neuralnetwork) {
		if (neuralnetworkInstances.containsKey(name))
			throw new IllegalArgumentException(String.format("Data %s was already initialized.", name));
		neuralnetworkInstances.put(name, neuralnetwork);
	}

	public NeuralnetworkWrapper removeNeuralnetworkInstance(String name) {
		return neuralnetworkInstances.remove(name);
	}

	public void clearNeuralNetworkInstance() {
		neuralnetworkInstances.clear();
	}
}