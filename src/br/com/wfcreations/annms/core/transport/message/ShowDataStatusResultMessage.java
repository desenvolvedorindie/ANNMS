package br.com.wfcreations.annms.core.transport.message;

import java.util.List;

public class ShowDataStatusResultMessage extends ResultMessage {
	public static class AttributeDescriptor {
		public final String name;

		public final String type;

		public final boolean notNull;

		public AttributeDescriptor(String name, String type, boolean notNull) {
			this.name = name;
			this.type = type;
			this.notNull = notNull;
		}
	}

	private AttributeDescriptor[] attributesDescriptor;

	public ShowDataStatusResultMessage(AttributeDescriptor[] attributesDescriptor) {
		this.attributesDescriptor = attributesDescriptor;
	}

	@Override
	public Object toThriftResult(List<Object> resultMessages) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		return "SHOW DATA STATUS";
	}
}
