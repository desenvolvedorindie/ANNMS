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
	public void toThriftResult(List<Object> resultMessages) {
		Map<String, String> param = new HashMap<>();
		param.put("NAME", name);
		Map<String, Object> data = new HashMap<>();
		data.put("CREATE DATA", param);
		resultMessages.add(data);
	}
}