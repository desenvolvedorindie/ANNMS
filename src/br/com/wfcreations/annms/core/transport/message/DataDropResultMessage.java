package br.com.wfcreations.annms.core.transport.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDropResultMessage extends ResultMessage {
	int count;

	public DataDropResultMessage(int count) {
		this.count = count;
	}

	@Override
	public void toThriftResult(List<Object> resultMessages) {
		Map<String, String> param = new HashMap<>();
		param.put("COUNT", String.valueOf(count));
		Map<String, Object> data = new HashMap<>();
		data.put("DROP DATA", param);
		resultMessages.add(data);
	}

}
