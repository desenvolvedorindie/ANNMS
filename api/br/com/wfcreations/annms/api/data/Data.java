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

import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.lang.ArrayUtils;

public final class Data implements Serializable {

	private static final long serialVersionUID = 1L;

	ID id;

	List<Attribute> attributes = new ArrayList<>();

	List<Pattern> patterns = new ArrayList<>();

	public Data(ID id, Attribute[] attributes) {
		if (ArrayUtils.hasDuplicate(attributes))
			throw new IllegalArgumentException("Attributes are not unique!");
		this.id = id;
		for (Attribute attr : attributes)
			this.attributes.add(attr);
	}

	public void validatePattern(Pattern pattern) throws IllegalArgumentException {
		if (pattern.getValuesNum() != this.attributes.size())
			throw new IllegalArgumentException("Invalid values numbers");
		for (int i = 0; i < this.attributes.size(); i++)
			if (!this.attributes.get(i).validate(pattern.getValueAt(i)))
				throw new IllegalArgumentException("Invalid value type for attribute " + attributes.get(i).getID());
	}

	public ID getID() {
		return this.id;
	}

	public Data setID(ID name) {
		this.id = name;
		return this;
	}

	public int getAttributesNum() {
		return attributes.size();
	}

	public Attribute getAttributeAt(int index) {
		return attributes.get(index);
	}

	public Attribute getAttributeByID(ID id) {
		for (Attribute attribute : attributes)
			if (attribute.getID().equals(id))
				return attribute;
		return null;
	}

	public Data renameAttribute(int index, ID newID) {
		for (int i = 0; i < getAttributesNum(); i++) {
			if (i == index)
				continue;
			if (attributes.get(i).getID().equals(newID))
				throw new IllegalArgumentException("Attribute name '" + id + "' already present at position #" + i);
		}
		this.attributes.set(index, new Attribute(newID, attributes.get(index).getType(), attributes.get(index).isNotNull()));
		return this;
	}

	public int indexOfAttribute(ID id) {
		for (int i = 0; i < attributes.size(); i++)
			if (attributes.get(i).getID().equals(id))
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
		Data data = new Data(id, newAttributes.toArray(new Attribute[newAttributes.size()]));
		List<Pattern> newPatterns = new ArrayList<>(patterns.size());
		for (Pattern pattern : patterns)
			newPatterns.add(new Pattern(Arrays.copyOfRange(pattern.values, from, to)));
		data.patterns = newPatterns;
		return data;
	}

	public Data slice(int index) {
		return slice(index, index + 1);
	}

	@Override
	public Data clone() {
		Data clone = new Data(this.id, new ArrayList<Attribute>(this.attributes).toArray(new Attribute[this.attributes.size()]));
		clone.patterns = new ArrayList<Pattern>(this.patterns);
		return clone;
	}
}