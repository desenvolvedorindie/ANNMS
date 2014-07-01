package br.com.wfcreations.annms.core.transport.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataCreateResultMessage extends ResultMessage {
	String name;

	public DataCreateResultMessage(String name) {
		this.name = name;
	}

	@Override
	public Object toThriftResult(List<Object> resultMessages) {
		Map<String, String> param = new HashMap<>();
		param.put("NAME", name);
		return param;
	}

	@Override
	public String getType() {
		return "CREATE DATA";
	}
}