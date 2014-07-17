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
package br.com.wfcreations.annms.core.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.core.neuralnetwork.NeuralnetworkWrapper;

public class Schema {

	public static final Schema instance = new Schema();

	private final Map<ID, Data> dataInstances = new ConcurrentHashMap<>();

	private final Map<ID, NeuralnetworkWrapper> neuralnetworkInstances = new ConcurrentHashMap<>();

	private Schema() {
	}

	public ID[] getDataNames() {
		return dataInstances.keySet().toArray(new ID[dataInstances.keySet().size()]);
	}

	public Data getDataInstance(ID id) {
		if (id == null)
			throw new IllegalArgumentException("ID can't be empty or null");
		return dataInstances.get(id);
	}

	public void storeDataInstance(Data data) {
		if (dataInstances.containsKey(data.getID()))
			throw new IllegalArgumentException(String.format("Data %s was already initialized.", data.getID()));
		dataInstances.put(data.getID(), data);
	}

	public Data removeDataInstance(ID id) {
		return dataInstances.remove(id);
	}

	public void clearDataInstance() {
		dataInstances.clear();
	}

	public String[] getNeuralnetworksName() {
		return neuralnetworkInstances.keySet().toArray(new String[dataInstances.keySet().size()]);
	}

	public NeuralnetworkWrapper getNeuralnetworkInstance(ID id) {
		if (id == null)
			throw new IllegalArgumentException("ID can't be empty or null");
		return neuralnetworkInstances.get(id);
	}

	public void storeNeuralnetworkInstance(ID id, NeuralnetworkWrapper neuralnetwork) {
		if (neuralnetworkInstances.containsKey(id))
			throw new IllegalArgumentException(String.format("Data %s was already initialized.", id));
		neuralnetworkInstances.put(id, neuralnetwork);
	}

	public NeuralnetworkWrapper removeNeuralnetworkInstance(ID id) {
		return neuralnetworkInstances.remove(id);
	}

	public void clearNeuralNetworkInstance() {
		neuralnetworkInstances.clear();
	}
}