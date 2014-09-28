package br.com.wfcreations.annms.core.transport.message;

import br.com.wfcreations.annms.api.data.value.IValue;

public class RunResultMessage extends ResultMessage {

	private IValue[] outputs;

	public RunResultMessage(IValue[] outputs) {
		this.outputs = outputs;
	}

	@Override
	public Object toThriftResult() {
		return this.outputs;
	}

	@Override
	public String getType() {
		return "RUN";
	}

}
