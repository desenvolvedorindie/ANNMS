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

import br.com.wfcreations.annms.api.data.values.Value;
import br.com.wfcreations.annms.api.data.values.IntegerValue;
import br.com.wfcreations.annms.api.data.values.RealValue;

public class BetweenValidate extends ValidateAbstract {

	protected static final String NOT_BETWEEN = "notBetween";

	protected static final String NOT_BETWEEN_STRICT = "notBetweenStrict";

	protected static final String INVALID = "invalid";

	protected static Map<String, String> messageTemplates = new HashMap<String, String>();

	static {
		messageTemplates.put(NOT_BETWEEN, "%value is not between min and max, inclusively");
		messageTemplates.put(NOT_BETWEEN, "%value is not strictly between min and max");
		messageTemplates.put(INVALID, "Invalid type given. IntegerValue, RealValue or IValue[] expected");
	}

	protected int min;

	protected int max;

	protected boolean inclusive;

	public BetweenValidate(int min, int max, boolean inclusive) {
		this.setMin(min).setMax(max).setInclusive(inclusive);
	}

	@Override
	public boolean isValid(Object value) {
		this.setValue(value);

		boolean result = true;

		if (value instanceof IntegerValue) {
			if (this.inclusive) {
				if (this.min > ((IntegerValue) value).getValue() || ((IntegerValue) value).getValue() > this.max) {
					this.error(messageTemplates, NOT_BETWEEN, null);
					return false;
				}
			} else {
				if (this.min >= ((IntegerValue) value).getValue() || ((IntegerValue) value).getValue() >= this.max) {
					this.error(messageTemplates, NOT_BETWEEN_STRICT, null);
					return false;
				}
			}

		} else if (value instanceof RealValue) {
			if (this.inclusive) {
				if (this.min > ((RealValue) value).getValue() || ((RealValue) value).getValue() > this.max) {
					this.error(messageTemplates, NOT_BETWEEN, null);
					return false;
				}
			} else {
				if (this.min >= ((RealValue) value).getValue() || ((RealValue) value).getValue() >= this.max) {
					this.error(messageTemplates, NOT_BETWEEN_STRICT, null);
					return false;
				}
			}
		} else if (value instanceof Value[]) {
			Value[] values = (Value[]) value;
			for (Value v : values) {
				result = result && isValid(v);
			}
		} else {
			this.error(messageTemplates, INVALID, value);
			return false;
		}

		return result;
	}

	public int getMin() {
		return min;
	}

	public BetweenValidate setMin(int min) {
		this.min = min;
		return this;
	}

	public int getMax() {
		return max;
	}

	public BetweenValidate setMax(int max) {
		this.max = max;
		return this;
	}

	public boolean isInclusive() {
		return inclusive;
	}

	public BetweenValidate setInclusive(boolean inclusive) {
		this.inclusive = inclusive;
		return this;
	}
}