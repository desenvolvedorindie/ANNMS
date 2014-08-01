/*
 * Copyright (c) Welsiton Ferreira (wfcreations@gmail.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice, this
 *  list of conditions and the following disclaimer in the documentation and/or
 *  other materials provided with the distribution.
 *
 *  Neither the name of the WFCreation nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.wfcreations.annms.api.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.lang.ArrayUtils;

public abstract class DataUtils {

	public static String dump(Data data) {
		StringBuffer sb = new StringBuffer();
		sb.append('#');
		sb.append(data.getID());
		sb.append(String.format("%n"));
		for (int i = 0; i < data.getAttributesNum(); i++) {
			sb.append(data.getAttributeAt(i).getID());
			sb.append('(');
			sb.append(data.getAttributeAt(i).getType());
			sb.append(')');
			if (i != data.getAttributesNum() - 1) {
				sb.append(", ");
			}
		}
		sb.append(String.format("%n"));
		int ptrnMax = data.getPatternsNum() - 1;
		int attrMax = data.getAttributesNum() - 1;
		for (int i = 0; i < data.getPatternsNum(); i++) {
			for (int j = 0; j < data.getAttributesNum(); j++) {
				sb.append(data.getPatternAt(i).getValueAt(j).getValue().toString());
				if (j != attrMax) {
					sb.append(", ");
				}
			}
			if (i != ptrnMax) {
				sb.append(String.format("%n"));
			}
		}
		return sb.toString();
	}

	public static Data merge(Data first, Data second) {
		Attribute[] attr1 = AttributeUtils.cloneAttributes(first);
		Attribute[] attr2 = AttributeUtils.cloneAttributes(second);

		if (first.getPatternsNum() != second.getPatternsNum())
			throw new IllegalArgumentException("Patterns must be of the same size");
		if (AttributeUtils.checkDuplicateAttribute(attr1, attr2))
			throw new IllegalArgumentException("Data 1 has the same attribute of Data 2");

		List<Pattern> patterns = new ArrayList<Pattern>(first.getPatternsNum());
		for (int i = 0; i < first.getPatternsNum(); i++)
			patterns.add(new Pattern(ArrayUtils.addAll(first.getPatternAt(i).cloneValues(), first.getPatternAt(i).cloneValues())));

		return new Data(ID.create(first.getID() + "_" + second.getID()), ArrayUtils.addAll(attr1, attr2)).addAll(patterns);
	}

	public static Data fetch(Data dataSource, LinkedHashMap<ID, ID> columns) {
		Data data = null;
		if (columns == null)
			data = dataSource.clone();
		else {
			Data data2 = null;
			int index;

			ID[] key = columns.keySet().toArray(new ID[columns.size()]);
			ID[] value = columns.values().toArray(new ID[columns.size()]);

			index = dataSource.indexOfAttribute(key[0]);
			if (index < 0)
				throw new IllegalArgumentException(String.format("Attribute % not found", key[0]));

			data = dataSource.slice(index);
			data.renameAttribute(0, value[0]);

			for (int i = 1; i < columns.size(); i++) {
				index = dataSource.indexOfAttribute(key[i]);
				if (index < 0)
					throw new IllegalArgumentException(String.format("Attribute %s not found", key[i]));

				data2 = dataSource.slice(index);
				data2.renameAttribute(0, value[i]);
				data = DataUtils.merge(data, data2);
			}
			data.setID(dataSource.getID());
		}
		return data;
	}
}