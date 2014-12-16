package br.com.wfcreations.annms.core.transport.message;

public class TrainResultMessage extends ResultMessage {

	public TrainResultMessage() {
		
	}
	
	@Override
	public Object toThriftResult() {
		return null;
	}

	@Override
	public String getType() {
		return "TRAIN";
	}

}
