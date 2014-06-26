package br.com.wfcreations.annms.api.data.validate;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.values.BooleanValue;

public class BooleanValidate extends ValidateAbstract {

	protected static final String INVALID = "invalid";

	protected static Map<String, String> messageTemplates = new HashMap<String, String>();

	static {
		messageTemplates.put(INVALID, "Invalid type given. BooleanValue expected");
	}

	protected boolean isArray = false;

	public BooleanValidate() {
	}

	public BooleanValidate(boolean isArray) {
		this.isArray = isArray;
	}

	@Override
	public boolean isValid(Object value) {
		this.setValue(value);

		boolean result = true;
		if ((value instanceof IValue[]) && this.isArray) {
			IValue[] values = (IValue[]) value;
			for (IValue v : values) {
				result = result && isValid(v);
			}
		} else if (!(value instanceof BooleanValue)) {
			error(messageTemplates, INVALID, null);
			return false;
		}
		return result;
	}

}
