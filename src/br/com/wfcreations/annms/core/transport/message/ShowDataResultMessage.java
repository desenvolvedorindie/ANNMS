package br.com.wfcreations.annms.core.transport.message;

import java.util.ArrayList;
import java.util.List;

import br.com.wfcreations.annms.api.data.value.ID;

public class ShowDataResultMessage extends ResultMessage {

	ID dataNames[];

	public ShowDataResultMessage(ID[] dataNames) {
		this.dataNames = dataNames;
	}

	@Override
	public Object toThriftResult() {
		List<String> list = new ArrayList<>();
		for (ID dataName : dataNames)
			list.add(dataName.getValue());
		return list;
	}

	@Override
	public String getType() {
		return "SHOW DATA";
	}
}