package br.com.wfcreations.annms.api.data.representation;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.values.IDValue;
import br.com.wfcreations.annms.api.data.values.IntegerValue;

public class NominalToOrdinal implements IRepresentation {

	private String[] classes;

	private Map<String, IValue[]> map = new HashMap<>();

	public NominalToOrdinal(String[] classes) {
		this.classes = classes;
		for (int i = 0; i < classes.length; i++)
			map.put(classes[i], new IValue[] { new IntegerValue(i) });
	}

	@Override
	public IValue[] encode(IValue value) {
		if (!(value instanceof IDValue))
			throw new IllegalArgumentException("Must be mominal");
		IValue[] values = map.get(IDValue.getValueFor(value));
		if (values == null)
			throw new IllegalArgumentException("Class not found");
		return values;
	}

	@Override
	public IValue decode(IValue[] values) {
		if (values.length != 1)
			throw new IllegalArgumentException("Invalid value");
		if (!(values[0] instanceof IntegerValue))
			throw new IllegalArgumentException("Invalid type");

		int v = IntegerValue.getValueFor(values[0]);
		if (v < 0 && v > classes.length - 1)
			throw new IllegalArgumentException("Invalid range");

		return new IDValue(classes[v]);
	}
}