package br.com.wfcreations.annms.api.data.validate;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.values.IntegerValue;

public class IntValidate extends ValidateAbstract {

	protected static final String INVALID = "invalid";

	protected static Map<String, String> messageTemplates = new HashMap<String, String>();

	static {
		messageTemplates.put(INVALID, "Invalid type given. IntegerValue expected");
	}

	protected boolean isArray = false;

	public IntValidate() {
	}

	public IntValidate(boolean isArray) {
		this.isArray = isArray;
	}

	@Override
	public boolean isValid(Object value) {
		this.setValue(value);
		if (value instanceof IValue[] && this.isArray) {
			IValue[] values = (IValue[]) value;
			boolean result = true;
			for (IValue v : values) {
				result = result && isValid(v);
			}
			return result;
		} else if (!(value instanceof IntegerValue)) {
			error(messageTemplates, INVALID, null);
			return false;
		}
		return true;
	}
}
