package br.com.wfcreations.annms.api.data.representation;

import br.com.wfcreations.annms.api.data.IValue;

public interface IRepresentation {

	public IValue[] encode(IValue value);

	public IValue decode(IValue[] values);
}
