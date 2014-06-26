package br.com.wfcreations.annms.api.data.validate;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.values.RealValue;

public class RealValidate extends ValidateAbstract {

	protected static final String INVALID = "invalid";

	protected static Map<String, String> messageTemplates = new HashMap<String, String>();

	static {
		messageTemplates.put(INVALID, "Invalid type given. RealValue expected");
	}

	protected boolean isArray = false;

	public RealValidate() {
	}

	public RealValidate(boolean isArray) {
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
		} else if (!(value instanceof RealValue)) {
			error(messageTemplates, INVALID, null);
			return false;
		}
		return true;
	}
}