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
package br.com.wfcreations.annms.api.data.type;

import br.com.wfcreations.annms.api.data.value.Bool;
import br.com.wfcreations.annms.api.data.value.IValue;
import br.com.wfcreations.annms.api.data.value.Int;
import br.com.wfcreations.annms.api.data.value.Real;
import br.com.wfcreations.annms.api.data.value.Str;

public enum Primitive implements IType {

	BOOL("BOOLEAN", Bool.class),

	INT("INTEGER", Int.class),

	REAL("REAL", Real.class),

	STR("STRING", Str.class);

	private static final long serialVersionUID = 1L;

	protected final String name;

	protected final Class<? extends IValue> representation;

	private Primitive(String name, Class<? extends IValue> representation) {
		this.name = name;
		this.representation = representation;
	}

	public Class<? extends IValue> getRepresentation() {
		return representation;
	}

	@Override
	public boolean valid(IValue value) {
		return (value.getClass().equals(getRepresentation()));
	}

	@Override
	public String toString() {
		return this.name;
	}
}