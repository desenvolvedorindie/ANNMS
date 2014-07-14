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

import br.com.wfcreations.annms.api.data.type.IType;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.IValue;
import br.com.wfcreations.annms.api.data.value.Null;

public class Attribute implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ID id;

	private final IType type;

	private final boolean notNull;

	public Attribute(ID id, IType type, boolean notNull) {
		if (id == null)
			throw new IllegalArgumentException("ID can't be null");
		if (type == null)
			throw new IllegalArgumentException("Type can't be null");
		this.id = id;
		this.type = type;
		this.notNull = notNull;
	};

	public ID getID() {
		return id;
	}

	public IType getType() {
		return type;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public boolean validate(IValue value) {
		return (!notNull && value instanceof Null) || type.valid(value);
	}

	@Override
	public String toString() {
		return String.format("Attribute [name=%s, type=%s, notNull=%s]", this.id.toString(), this.type.toString(), this.notNull);
	}
}