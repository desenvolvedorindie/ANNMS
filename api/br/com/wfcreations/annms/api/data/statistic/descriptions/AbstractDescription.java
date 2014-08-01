package br.com.wfcreations.annms.api.data.statistic.descriptions;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.Data;

public abstract class AbstractDescription implements IDescription {

	private static final long serialVersionUID = 1L;

	@Override
	public void describe(Data data, int attributeIndex) {
		if (!acceptAttribute(data.getAttributeAt(attributeIndex)))
			throw new IllegalArgumentException("This statistic algorithm don't accept this attribute");
	}

	public abstract boolean acceptAttribute(Attribute attribute);
}