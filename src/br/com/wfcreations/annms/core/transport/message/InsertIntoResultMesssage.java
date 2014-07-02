package br.com.wfcreations.annms.core.transport.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertIntoResultMesssage extends ResultMessage {

	private final int affected;
	
	public InsertIntoResultMesssage(int affected) {
		this.affected = affected;
	}
	
	@Override
	public Object toThriftResult(List<Object> resultMessages) {
		Map<String, String> param = new HashMap<>();
		param.put("AFFECTED", String.valueOf(affected));
		return param;
	}

	@Override
	public String getType() {
		return "INSERT INTO";
	}

}
