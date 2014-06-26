package br.com.wfcreations.annms.core.config;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import br.com.wfcreations.annms.api.data.Data;

public class Schema {

	public static final Schema instance = new Schema();

	private final Map<String, Data> dataInstances = new ConcurrentHashMap<String, Data>();

	// private final Map<String, NeuralNetwork> networkKeyspaceInstances = new
	// ConcurrentHashMap<String, NeuralNetwork>();

	private Schema() {
	}

	public Set<String> getDataNames(){
		return dataInstances.keySet();
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
}