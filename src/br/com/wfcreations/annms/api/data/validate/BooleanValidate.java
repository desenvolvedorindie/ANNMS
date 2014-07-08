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
package br.com.wfcreations.annms.api.data.validate;

import java.util.HashMap;
import java.util.Map;

import br.com.wfcreations.annms.api.data.values.BooleanValue;
import br.com.wfcreations.annms.api.data.values.Value;

public class BooleanValidate extends ValidateAbstract {

	protected static final String INVALID = "invalid";

	protected static Map<String, String> messageTemplates = new HashMap<String, String>();

	static {
		messageTemplates.put(INVALID, "Invalid type given. BooleanValue expected");
	}

	protected boolean isArray = false;

	public BooleanValidate() {
	}

	public BooleanValidate(boolean isArray) {
		this.isArray = isArray;
	}

	@Override
	public boolean isValid(Object value) {
		this.setValue(value);

		boolean result = true;
		if ((value instanceof Value[]) && this.isArray) {
			Value[] values = (Value[]) value;
			for (Value v : values) {
				result = result && isValid(v);
			}
		} else if (!(value instanceof BooleanValue)) {
			error(messageTemplates, INVALID, null);
			return false;
		}
		return result;
	}

}
