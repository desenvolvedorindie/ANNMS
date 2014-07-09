package br.com.wfcreations.annms.core.transport.message;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.values.ID;

public class ShowDataStatusResultMessage extends ResultMessage {

	private String name;

	private Attribute[] attributes;

	public ShowDataStatusResultMessage(String name, Attribute[] attributesDescriptor) {
		this.name = name;
		this.attributes = attributesDescriptor;
	}

	@Override
	public Object toThriftResult() {
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> attrs = new ArrayList<Map<String, Object>>();
		Map<String, Object> properties;
		ID[] listValues;
		int i;

		param.put("NAME", this.name);
		for (Attribute attribute : attributes) {
			properties = new LinkedHashMap<String, Object>();
			properties.put("NAME", attribute.getID());
			if (attribute.getType() instanceof ListType) {
				ListType listDataType = (ListType) attribute.getType();
				listValues = new ID[listDataType.getListValuesNum()];
				for (i = 0; i < listDataType.getListValuesNum(); i++)
					listValues[i] = listDataType.getValuesAt(i);

				properties.put("TYPE", listValues);
			} else {
				properties.put("TYPE", attribute.getType().toString());
			}
			properties.put("NOTNULL", String.valueOf(attribute.isNotNull()).toUpperCase());
			attrs.add(properties);
		}
		param.put("ATTRIBUTES", attrs);
		return param;
	}

	@Override
	public String getType() {
		return "SHOW DATA STATUS";
	}
}
