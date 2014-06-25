package br.com.wfcreations.annms.core.transport.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowDataResultMessage extends ResultMessage {
	String dataNames[];

	public ShowDataResultMessage(String[] dataNames) {
		this.dataNames = dataNames;
	}

	@Override
	public void toThriftResult(List<Object> resultMessages) {
		List<String> list = new ArrayList<String>();
		for (String dataName : dataNames)
			list.add(dataName);
		Map<String, Object> data = new HashMap<>();
		data.put("SHOW DATA", list);
		resultMessages.add(data);
	}
}
