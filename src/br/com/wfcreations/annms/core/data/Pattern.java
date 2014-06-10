package br.com.wfcreations.annms.core.data;

import java.io.Serializable;

public class Pattern implements Serializable {

	private static final long serialVersionUID = 1L;

	protected IValue[] values;

	public Pattern(IValue[] values) {
		this.values = values.clone();
	}

	public IValue getElement(int index) {
		return values[index];
	}

	public int size() {
		return this.values.length;
	}

	@Override
	public Pattern clone() {
		IValue[] newValues = new IValue[values.length];
		System.arraycopy(values, 0, newValues, 0, values.length);
		return new Pattern(newValues);
	}
}
