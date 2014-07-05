package br.com.wfcreations.annms.core.transport.message;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateDataResultMessage extends ResultMessage {

	private String name;

	public CreateDataResultMessage(String name) {
		this.name = name;
	}

	@Override
	public Object toThriftResult() {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		if (name != null)
			param.put("NAME", this.name);
		return param;
	}

	@Override
	public String getType() {
		return "CREATE DATA";
	}
}