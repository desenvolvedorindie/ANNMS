package br.com.wfcreations.annms.core.transport.message;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.Param;
import br.com.wfcreations.annms.api.data.value.ID;

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
		
		param.put("ID", this.id);
		param.put("MODEL", this.model);

		
		for(Param statusParam:this.status){
			status.put(statusParam.getID().getValue(), statusParam.getValues());
		}
		
		param.put("STATUS", status);
		return null;
	}

	@Override
	public String getType() {
		return "SHOW NEURALNETWORK STATUS";
	}

}
