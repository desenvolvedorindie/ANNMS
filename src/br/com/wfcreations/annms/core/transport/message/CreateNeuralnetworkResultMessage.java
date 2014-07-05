package br.com.wfcreations.annms.core.transport.message;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateNeuralnetworkResultMessage extends ResultMessage {

	private String name;

	private String model;

	public CreateNeuralnetworkResultMessage(String name, String model) {
		this.name = name;
		this.model = model;
	}

	@Override
	public Object toThriftResult() {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		if (this.name != null) {
			param.put("NAME", this.name);
			param.put("MODEL", this.model);
		}
		return param;
	}

	@Override
	public String getType() {
		return "CREATE NEURALNETWORK";
	}
}