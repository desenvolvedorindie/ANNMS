package br.com.wfcreations.annms.api.data.select;

import java.util.Map;

public class Select {

	Map<String, String> columns;

	public Select() {
	}

	public void columns(Map<String, String> columns) {
		this.columns = columns;
	}
}
