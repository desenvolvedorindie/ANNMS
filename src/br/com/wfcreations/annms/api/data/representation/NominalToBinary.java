package br.com.wfcreations.annms.api.data.representation;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.values.IDValue;
import br.com.wfcreations.annms.api.data.values.RealValue;

public class NominalToBinary implements IRepresentation {

	private Map<String, IValue[]> map = new HashMap<String, IValue[]>();

	private String[] classes;

	public NominalToBinary(String[] classes) {
		if (classes.length < 2)
			throw new IllegalArgumentException("Must have more than 1 class");
		for (int i = 0; i < classes.length; i++) {
			map.put(classes[i], generate(classes.length, i));
		}
		this.classes = classes;
	}

	private static IValue[] generate(int size, int index) {
		IValue[] value = new RealValue[size];
		for (int i = 0; i < size; i++) {
			if (i == index)
				value[i] = new RealValue(1);
			else
				value[i] = new RealValue();
		}
		return value;
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
		if (values.length != map.size())
			throw new IllegalArgumentException("Invalid values lenght");

		int oneCount = 0;
		int zeroCount = 0;
		int pos = 0;
		double v = 0;

		for (int i = 0; i < values.length; i++) {
			if (!(values[i] instanceof RealValue))
				throw new IllegalArgumentException("Invalid data type");
			v = RealValue.getValueFor(values[i]);
			if (v == 1) {
				oneCount++;
				pos = i;
			} else if (v == 0)
				zeroCount++;
			else
				throw new IllegalArgumentException(String.format("Invalid value %s", v));
		}
		if (oneCount != 1)
			throw new IllegalArgumentException("Invalid 1's count");
		if (zeroCount + 1 != map.size())
			throw new IllegalArgumentException("Invalid 0's count");
		return new IDValue(classes[pos]);
	}

	public String getClassAt(int index) {
		return classes[index];
	}

	public int classesCount() {
		return classes.length;
	}
}