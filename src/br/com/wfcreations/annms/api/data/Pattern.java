package br.com.wfcreations.annms.api.data;

import java.io.Serializable;

public class Pattern implements Serializable {

	private static final long serialVersionUID = 1L;

	protected final IValue[] values;

	public Pattern(IValue[] values) {
		this.values = values.clone();
	}

	public IValue getValueAt(int index) {
		return values[index];
	}

	public int getValuesNum() {
		return this.values.length;
	}

	@Override
	public Pattern clone() {
		IValue[] newValues = new IValue[values.length];
		System.arraycopy(values, 0, newValues, 0, values.length);
		return new Pattern(newValues);
	}
}
