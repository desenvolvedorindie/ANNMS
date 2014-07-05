package br.com.wfcreations.annms.core.transport.message;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.DataType;
import br.com.wfcreations.annms.api.data.DataType.ListDataType;

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
		String[] listValues;
		int i;

		param.put("NAME", this.name);
		for (Attribute attribute : attributes) {
			properties = new LinkedHashMap<String, Object>();
			properties.put("NAME", attribute.getName());
			if (attribute.getType() instanceof DataType.ListDataType) {
				DataType.ListDataType listDataType = (ListDataType) attribute.getType();
				listValues = new String[listDataType.getListValuesNum()];
				for (i = 0; i < listDataType.getListValuesNum(); i++)
					listValues[i] = listDataType.getListValuesAt(i);

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
