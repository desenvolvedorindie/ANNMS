package br.com.wfcreations.annms.core.transport.message;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.wfcreations.annms.api.data.Param;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.IParamValue;

public class ShowNeuralnetworkStatusResultMessage extends ResultMessage {

	ID id;

	String model;

	Param[] status;

	public ShowNeuralnetworkStatusResultMessage(ID id, String model, Param[] status) {
		this.id = id;
		this.model = model;
		this.status = status;
	}

	@Override
	public Object toThriftResult() {
		Map<String, Object> param = new LinkedHashMap<>();
		Map<String, Object> status = new LinkedHashMap<>();
		List<Object> values;

		param.put("ID", this.id);
		param.put("MODEL", this.model);

		for (Param statusParam : this.status) {
			values = new ArrayList<>(statusParam.getValues().length);

			for (IParamValue paramValue : statusParam.getValues()) {
				values.add(paramValue);
			}

			status.put(statusParam.getID().getValue(), values);
		}

		param.put("STATUS", status);
		return param;
	}
	
	

	@Override
	public String getType() {
		return "SHOW NEURALNETWORK STATUS";
	}

}
