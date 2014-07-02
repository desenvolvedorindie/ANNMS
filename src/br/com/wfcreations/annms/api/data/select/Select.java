package br.com.wfcreations.annms.api.data.select;

import java.util.LinkedHashMap;

public class Select {

	protected LinkedHashMap<String, String> columns;

	public Select() {
	}

	public void columns(LinkedHashMap<String, String> columns) {
		this.columns = columns;
	}

	public LinkedHashMap<String, String> columns() {
		return this.columns;
	}
}