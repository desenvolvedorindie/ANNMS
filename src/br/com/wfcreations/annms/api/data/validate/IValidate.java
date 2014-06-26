package br.com.wfcreations.annms.api.data.validate;

import java.util.Map;

public interface IValidate {

	public boolean isValid(Object value);

	public Map<String, Object> getMessage();
}
