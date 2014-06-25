package br.com.wfcreations.annms.core.data;

import java.util.Map;

public class Select {

	Map<String, String> columns;

	public Select() {
	}

	public void columns(Map<String, String> columns) {
		this.columns = columns;
	}
}
