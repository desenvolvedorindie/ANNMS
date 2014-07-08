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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.wfcreations.annms.api.data.type.IType;
import br.com.wfcreations.annms.api.lang.ArrayUtils;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Data merge(Data first, Data second) {
		if (first.getPatternsNum() != second.getPatternsNum())
			throw new IllegalArgumentException("Patterns must be of the same size");
		if (checkDuplicateAttribute(first.attributes, second.attributes))
			throw new IllegalArgumentException("Data 1 has the same attribute of Data 2");

		List<Attribute> attr = new ArrayList<>(first.getAttributesNum() + second.getAttributesNum());
		attr.addAll(first.attributes);
		attr.addAll(second.attributes);

		List<Pattern> patterns = new ArrayList<Pattern>(first.getPatternsNum());
		for (int i = 0; i < first.getPatternsNum(); i++)
			patterns.add(new Pattern(org.apache.commons.lang3.ArrayUtils.addAll(first.patterns.get(i).values, second.patterns.get(i).values)));

		return new Data(first.getName() + "_" + second.getName(), attr.toArray(new Attribute[attr.size()])).addAll(patterns);
	}

	private static boolean checkDuplicateAttribute(List<Attribute> attributes1, List<Attribute> attributes2) {
		for (Attribute attr1 : attributes1)
			for (Attribute attr2 : attributes2)
				if (attr1.getName().equals(attr2.getName()))
					return true;
		return false;
	}

	protected String name;

	protected List<Attribute> attributes = new ArrayList<Attribute>();

	protected List<Pattern> patterns = new ArrayList<Pattern>();

	public Data(String name, Attribute[] attributes) {
		if (ArrayUtils.hasDuplicate(attributes))
			throw new IllegalArgumentException("Attributes are not unique!");
		this.name = name;
		for (Attribute attr : attributes)
			this.attributes.add(attr);
	}

	protected void validatePattern(Pattern pattern) throws IllegalArgumentException {
		if (pattern.getValuesNum() != attributes.size())
			throw new IllegalArgumentException("Invalid values numbers");
		for (int i = 0; i < attributes.size(); i++)
			if (!attributes.get(i).validate(pattern.getValueAt(i)))
				throw new IllegalArgumentException("Invalid value type for attribute " + attributes.get(i).getName());
	}

	public String getName() {
		return this.name;
	}

	public Data setName(String name) {
		this.name = name;
		return this;
	}

	public int getAttributesNum() {
		return attributes.size();
	}

	public String[] getAttributesNames() {
		String[] attributeNames = new String[attributes.size()];
		int i = 0;
		for (Attribute attr : attributes)
			attributeNames[i++] = attr.getName();
		return attributeNames;
	}

	public boolean hasAttributeType(IType dataType) {
		for (Attribute attribute : attributes)
			if (attribute.getType().equals(dataType))
				return true;
		return false;
	}

	public Attribute getAttributeAt(int index) {
		return attributes.get(index);
	}

	public Attribute getAttributeByName(String name) {
		for (Attribute attribute : attributes)
			if (attribute.getName().equals(name))
				return attribute;
		return null;
	}

	public Data renameAttribute(int index, String newName) {
		for (int i = 0; i < getAttributesNum(); i++) {
			if (i == index)
				continue;
			if (attributes.get(i).getName().equals(newName))
				throw new IllegalArgumentException("Attribute name '" + name + "' already present at position #" + i);
		}
		this.attributes.set(index, new Attribute(newName, attributes.get(index).getType(), attributes.get(index).isNotNull()));
		return this;
	}

	public int indexOfAttribute(String name) {
		for (int i = 0; i < attributes.size(); i++)
			if (attributes.get(i).getName().equals(name))
				return i;
		return -1;
	}

	public int getPatternsNum() {
		return patterns.size();
	}

	public int indexOfPattern(Pattern pattern) {
		return patterns.indexOf(pattern);
	}

	public Pattern getPatternAt(int index) {
		return patterns.get(index);
	}

	public Pattern setPatternAt(int index, Pattern pattern) {
		validatePattern(pattern);
		return patterns.set(index, pattern);
	}

	public Data swap(int i, int j) {
		Collections.swap(patterns, i, j);
		return this;
	}

	public Data add(Pattern pattern) throws IllegalArgumentException {
		validatePattern(pattern);
		patterns.add(pattern);
		return this;
	}

	public Data addAll(List<Pattern> patterns) {
		for (Pattern p : patterns)
			this.add(p);
		return this;
	}

	public Data addPatternAt(int index, Pattern pattern) {
		validatePattern(pattern);
		patterns.add(index, pattern);
		return this;
	}

	public Data removePatternAt(int index) {
		patterns.remove(index);
		return this;
	}

	public Data removeAllPatterns() {
		patterns.clear();
		return this;
	}

	public Data slice(int from, int to) {
		List<Attribute> newAttributes = attributes.subList(from, to);
		Data data = new Data(name, newAttributes.toArray(new Attribute[newAttributes.size()]));
		List<Pattern> newPatterns = new ArrayList<>(patterns.size());
		for (Pattern pattern : patterns)
			newPatterns.add(new Pattern(Arrays.copyOfRange(pattern.values, from, to)));
		data.patterns = newPatterns;
		return data;
	}

	public Data slice(int index) {
		return slice(index, index + 1);
	}

	public Data fetch(Select where) {
		Data data = null;
		if (where == null)
			data = this.clone();
		else {
			Data data2 = null;
			int index;

			String[] key = where.columns().keySet().toArray(new String[where.columns().size()]);
			String[] value = where.columns().values().toArray(new String[where.columns().size()]);

			index = this.indexOfAttribute(key[0]);
			if (index < 0)
				throw new IllegalArgumentException(String.format("Attribute % not found", key[0]));

			data = this.slice(index);
			data.renameAttribute(0, value[0]);

			for (int i = 1; i < where.columns().size(); i++) {
				index = this.indexOfAttribute(key[i]);
				if (index < 0)
					throw new IllegalArgumentException(String.format("Attribute %s not found", key[i]));

				data2 = this.slice(index);
				data2.renameAttribute(0, value[i]);
				data = merge(data, data2);

			}
			data.setName(this.name);
		}
		return data;
	}

	@Override
	public Data clone() {
		Data clone = new Data(this.name, new ArrayList<>(this.attributes).toArray(new Attribute[this.attributes.size()]));
		clone.patterns = new ArrayList<>(this.patterns);
		return clone;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append('#');
		sb.append(getName());
		sb.append(String.format("%n"));
		for (int i = 0; i < getAttributesNum(); i++) {
			sb.append(attributes.get(i).getName());
			sb.append('(');
			sb.append(attributes.get(i).getType());
			sb.append(')');
			if (i != getAttributesNum() - 1) {
				sb.append(", ");
			}
		}
		sb.append(String.format("%n"));
		int ptrnMax = patterns.size() - 1;
		int attrMax = attributes.size() - 1;
		for (int i = 0; i < patterns.size(); i++) {
			for (int j = 0; j < attributes.size(); j++) {
				sb.append(patterns.get(i).getValueAt(j).getValue().toString());
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
}