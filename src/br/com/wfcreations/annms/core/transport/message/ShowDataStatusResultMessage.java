package br.com.wfcreations.annms.core.transport.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.wfcreations.annms.api.data.Attribute;

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

		public AttributeDescriptor(Attribute attribute) {
			this.name = attribute.getName();
			this.type = attribute.getType().toString();
			this.notNull = attribute.isNotNull();
		}
	}

	private AttributeDescriptor[] attributesDescriptor;

	public ShowDataStatusResultMessage(AttributeDescriptor[] attributesDescriptor) {
		this.attributesDescriptor = attributesDescriptor;
	}

	@Override
	public Object toThriftResult(List<Object> resultMessages) {
		Map<String, Object> param = new HashMap<>();
		Map<String, String> properties;
		for (AttributeDescriptor attributeDescriptor : attributesDescriptor) {
			properties = new HashMap<>();
			properties.put("TYPE", attributeDescriptor.type);
			properties.put("NOTNULL", String.valueOf(attributeDescriptor.notNull).toUpperCase());
			param.put(attributeDescriptor.name, properties);
		}
		return param;
	}

	@Override
	public String getType() {
		return "SHOW DATA STATUS";
	}
}
