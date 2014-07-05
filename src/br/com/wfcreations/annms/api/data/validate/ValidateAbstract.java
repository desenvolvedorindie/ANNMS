package br.com.wfcreations.annms.api.data.validate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ValidateAbstract implements IValidate {

	protected Map<String, Object> messages = new LinkedHashMap<String, Object>();

	protected ArrayList<String> errors = new ArrayList<String>();

	private Object value;

	protected void error(Map<String, String> messageTemplates, String key, Object value) {
		errors.add(key);
		if (value == null)
			value = this.value;
		//this.messages.put(key, messageTemplates.get(key).replace("%value", value.toString()));
	}

	public Map<String, Object> getMessage() {
		return this.messages;
	}

	public int messageLength() {
		return this.messages.size();
	}

	public Object getValue() {
		return value;
	}

	public ValidateAbstract setValue(Object value) {
		this.value = value;
		return this;
	}
}