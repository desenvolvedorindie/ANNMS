package br.com.wfcreations.annms.core.transport.message;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.value.ID;

public class CreateNeuralnetworkResultMessage extends ResultMessage {

	private ID name;

	private String model;

	public CreateNeuralnetworkResultMessage(ID name, String model) {
		this.name = name;
		this.model = model;
	}

	@Override
	public Object toThriftResult() {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		if (this.name != null) {
			param.put("NAME", this.name.getValue());
			param.put("MODEL", this.model);
		}
		return param;
	}

	@Override
	public String getType() {
		return "CREATE NEURALNETWORK";
	}
}