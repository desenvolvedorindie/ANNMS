package br.com.wfcreations.annms.core.transport.message;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.ANNMSAPI;
import br.com.wfcreations.annms.core.ANNMS;

public class ShowStatusResultMessage extends ResultMessage {

	@Override
	public Object toThriftResult() {
		Map<String, String> param = new HashMap<>();
		param.put("SERVER_VERSION", ANNMS.VERSION);
		param.put("SQLANN_VERSION", ANNMS.SQLANN_VERSION);
		param.put("API_VERSION", ANNMSAPI.VERSION);
		return param;
	}

	@Override
	public String getType() {
		return "SHOW STATUS";
	}

}
