package br.com.wfcreations.annms.core.transport.message;

import java.util.ArrayList;
import java.util.List;

public class ShowDataResultMessage extends ResultMessage {
	
	String dataNames[];

	public ShowDataResultMessage(String[] dataNames) {
		this.dataNames = dataNames;
	}

	@Override
	public Object toThriftResult() {
		List<String> list = new ArrayList<String>();
		for (String dataName : dataNames)
			list.add(dataName);
		return list;
	}

	@Override
	public String getType() {
		return "SHOW DATA";
	}
}