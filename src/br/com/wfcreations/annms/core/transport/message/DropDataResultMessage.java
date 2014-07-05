package br.com.wfcreations.annms.core.transport.message;

import java.util.LinkedHashMap;
import java.util.Map;

public class DropDataResultMessage extends ResultMessage {

	int count;

	public DropDataResultMessage(int count) {
		this.count = count;
	}

	@Override
	public Object toThriftResult() {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("COUNT", String.valueOf(count));
		return param;
	}

	@Override
	public String getType() {
		return "DROP DATA";
	}

}
