package br.com.wfcreations.annms.api.data.validate;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.values.IntegerValue;
import br.com.wfcreations.annms.api.data.values.RealValue;

public class BetweenValidate extends ValidateAbstract {

	protected static final String NOT_BETWEEN = "notBetween";

	protected static final String NOT_BETWEEN_STRICT = "notBetweenStrict";

	protected static final String INVALID = "invalid";

	protected static Map<String, String> messageTemplates = new HashMap<String, String>();

	static {
		messageTemplates.put(NOT_BETWEEN, "%value is not between min and max, inclusively");
		messageTemplates.put(NOT_BETWEEN, "%value is not strictly between min and max");
		messageTemplates.put(INVALID, "Invalid type given. IntegerValue, RealValue or IValue[] expected");
	}

	protected int min;

	protected int max;

	protected boolean inclusive;

	public BetweenValidate(int min, int max, boolean inclusive) {
		this.setMin(min).setMax(max).setInclusive(inclusive);
	}

	@Override
	public boolean isValid(Object value) {
		this.setValue(value);

		boolean result = true;

		if (value instanceof IntegerValue) {
			if (this.inclusive) {
				if (this.min > ((IntegerValue) value).getValue() || ((IntegerValue) value).getValue() > this.max) {
					this.error(messageTemplates, NOT_BETWEEN, null);
					return false;
				}
			} else {
				if (this.min >= ((IntegerValue) value).getValue() || ((IntegerValue) value).getValue() >= this.max) {
					this.error(messageTemplates, NOT_BETWEEN_STRICT, null);
					return false;
				}
			}

		} else if (value instanceof RealValue) {
			if (this.inclusive) {
				if (this.min > ((RealValue) value).getValue() || ((RealValue) value).getValue() > this.max) {
					this.error(messageTemplates, NOT_BETWEEN, null);
					return false;
				}
			} else {
				if (this.min >= ((RealValue) value).getValue() || ((RealValue) value).getValue() >= this.max) {
					this.error(messageTemplates, NOT_BETWEEN_STRICT, null);
					return false;
				}
			}
		} else if (value instanceof IValue[]) {
			IValue[] values = (IValue[]) value;
			for (IValue v : values) {
				result = result && isValid(v);
			}
		} else {
			this.error(messageTemplates, INVALID, value);
			return false;
		}

		return result;
	}

	public int getMin() {
		return min;
	}

	public BetweenValidate setMin(int min) {
		this.min = min;
		return this;
	}

	public int getMax() {
		return max;
	}

	public BetweenValidate setMax(int max) {
		this.max = max;
		return this;
	}

	public boolean isInclusive() {
		return inclusive;
	}

	public BetweenValidate setInclusive(boolean inclusive) {
		this.inclusive = inclusive;
		return this;
	}
}