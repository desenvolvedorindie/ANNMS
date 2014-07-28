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
package br.com.wfcreations.annms.api.data.filter.attribute;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.IValue;
import br.com.wfcreations.annms.api.data.value.Int;

public class NominalToOrdinal implements IAttributeFilter {

	private static final long serialVersionUID = 1L;

	private ID[] classes;

	private Map<ID, IValue[]> map = new HashMap<ID, IValue[]>();

	public NominalToOrdinal(ListType listType) {
		this.classes = new ID[listType.getListValuesNum()];
		ID c;

		for (int i = 0; i < listType.getListValuesNum(); i++) {
			c = listType.getValuesAt(i);
			this.classes[i] = c;
			map.put(c, new IValue[] { new Int(i) });
		}
	}

	@Override
	public boolean acceptAttribute(Attribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IValue[] encode(IValue value) {
		if (!(value instanceof ID))
			throw new IllegalArgumentException("Must be mominal");
		IValue[] values = map.get(ID.getValueFor(value));
		if (values == null)
			throw new IllegalArgumentException("Class not found");
		return values;
	}

	@Override
	public IValue decode(IValue[] values) {
		if (values.length != 1)
			throw new IllegalArgumentException("Invalid value");
		if (!(values[0] instanceof Int))
			throw new IllegalArgumentException("Invalid type");

		int v = Int.getValueFor(values[0]);
		if (v < 0 && v > classes.length - 1)
			throw new IllegalArgumentException("Invalid range");

		return classes[v];
	}

	@Override
	public int getLength() {
		return 1;
	}
}