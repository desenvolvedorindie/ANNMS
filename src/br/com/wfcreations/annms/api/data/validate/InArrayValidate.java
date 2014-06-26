package br.com.wfcreations.annms.api.data.validate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.values.IDValue;
import br.com.wfcreations.annms.api.data.values.StringValue;

public class InArrayValidate extends ValidateAbstract {

	protected static final String INVALID = "invalid";

	protected static Map<String, String> messageTemplates = new HashMap<String, String>();

	static {
		messageTemplates.put(INVALID, "Invalid type given. BooleanValue expected");
	}

	protected boolean isArray = false;

	protected final List<String> values;

	public InArrayValidate(String[] values) {
		this(values, false);
	}

	public InArrayValidate(String[] values, boolean isArray) {
		this.values = Arrays.asList(values);
		this.isArray = isArray;
	}

	@Override
	public boolean isValid(Object value) {
		String v = "";
		boolean result = true;
		if (value instanceof IDValue) {
			v = IDValue.getValueFor((IValue) value);
		} else if (value instanceof StringValue) {
			v = StringValue.getValueFor((IValue) value);
		} else if ((value instanceof IValue[]) && this.isArray) {
			IValue[] values = (IValue[]) value;
			for (IValue va : values) {
				result = result && isValid(va);
			}
		} else {
			result = false;
		}
		return values.contains(v) && result;
	}

}
