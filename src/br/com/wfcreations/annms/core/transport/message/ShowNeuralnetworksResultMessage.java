package br.com.wfcreations.annms.core.transport.message;

import java.util.ArrayList;
import java.util.List;

import br.com.wfcreations.annms.api.data.value.ID;

public class ShowNeuralnetworksResultMessage extends ResultMessage {

	ID neuralnetworksIDs[];
	
	public ShowNeuralnetworksResultMessage(ID[] neuralnetworksIDs) {
		this.neuralnetworksIDs = neuralnetworksIDs;
	}
	
	@Override
	public Object toThriftResult() {
		List<String> list = new ArrayList<>();
		for (ID neuralnetworkName : neuralnetworksIDs)
			list.add(neuralnetworkName.getValue());
		return list;
	}

	@Override
	public String getType() {
		return "SHOW NEURALNETWORKS";
	}

}
