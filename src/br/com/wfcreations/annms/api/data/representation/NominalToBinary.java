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
package br.com.wfcreations.annms.api.data.representation;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.values.IDValue;
import br.com.wfcreations.annms.api.data.values.Value;
import br.com.wfcreations.annms.api.data.values.RealValue;

public class NominalToBinary implements IRepresentator {

	private static final long serialVersionUID = 1L;

	private Map<String, Value[]> map = new HashMap<String, Value[]>();

	private String[] classes;

	public NominalToBinary(ListType listType) {
		if (listType.getListValuesNum() < 2)
			throw new IllegalArgumentException("Must have more than 1 class");

		this.classes = new String[listType.getListValuesNum()];
		String c;

		for (int i = 0; i < listType.getListValuesNum(); i++) {
			c = listType.getListValuesAt(i);
			this.classes[i] = c;
			map.put(c, generate(classes.length, i));
		}
	}

	private static Value[] generate(int size, int index) {
		Value[] value = new RealValue[size];
		for (int i = 0; i < size; i++) {
			if (i == index)
				value[i] = new RealValue(1);
			else
				value[i] = new RealValue();
		}
		return value;
	}

	@Override
	public Value[] encode(Value value) {
		if (!(value instanceof IDValue))
			throw new IllegalArgumentException("Must be mominal");
		Value[] values = map.get(IDValue.getValueFor(value));
		if (values == null)
			throw new IllegalArgumentException("Class not found");
		return values;
	}

	@Override
	public Value decode(Value[] values) {
		if (values.length != map.size())
			throw new IllegalArgumentException("Invalid values lenght");

		int oneCount = 0;
		int zeroCount = 0;
		int pos = 0;
		double v = 0;

		for (int i = 0; i < values.length; i++) {
			if (!(values[i] instanceof RealValue))
				throw new IllegalArgumentException("Invalid data type");
			v = RealValue.getValueFor(values[i]);
			if (v == 1) {
				oneCount++;
				pos = i;
			} else if (v == 0)
				zeroCount++;
			else
				throw new IllegalArgumentException(String.format("Invalid value %s", v));
		}
		if (oneCount != 1)
			throw new IllegalArgumentException("Invalid 1's count");
		if (zeroCount + 1 != map.size())
			throw new IllegalArgumentException("Invalid 0's count");
		return new IDValue(classes[pos]);
	}

	@Override
	public int getLength() {
		return classes.length;
	}

	public String getClassAt(int index) {
		return classes[index];
	}

	public int getClassesCount() {
		return classes.length;
	}
}