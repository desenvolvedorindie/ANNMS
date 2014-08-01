package br.com.wfcreations.annms.api.data.statistic.descriptions;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.type.Primitive;

public class MinMax extends AbstractDescription {

	private static final long serialVersionUID = 1L;

	protected double minimum = Double.NaN;

	protected double maximum = Double.NaN;

	@Override
	public boolean acceptAttribute(Attribute attribute) {
		return attribute.getType().equals(Primitive.INT) || attribute.getType().equals(Primitive.REAL);
	}

	@Override
	public void describe(Data data, int attributeIndex) {
		super.describe(data, attributeIndex);
		if (data.getAttributesNum() == 0)
			throw new IllegalArgumentException("Don't have patterns to calculate");

		double v;
		v = (double) data.getPatternAt(0).getValueAt(attributeIndex).getValue();

		maximum = v;
		minimum = v;

		for (int i = 1; i < data.getPatternsNum(); i++) {
			v = (double) data.getPatternAt(i).getValueAt(attributeIndex).getValue();
			if (v > maximum)
				maximum = v;
			if (v < minimum)
				minimum = v;
		}
	}

	public double getMinimum() {
		return minimum;
	}

	public double getMaximum() {
		return maximum;
	}
}