/*
 * Copyright (c) 2013, Welsiton Ferreira (wfcreations@gmail.com)
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

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.type.Primitive;
import br.com.wfcreations.annms.api.data.value.IValue;

public class Range implements IAttributeFilter {

	private static final long serialVersionUID = 1L;

	protected double minimum = 0;

	protected double maximum = 1;

	public Range() {
	}

	public Range(double minimum, double maximum) {
		if (minimum >= maximum)
			throw new IllegalArgumentException("Maximum must be greater than minimum");
		this.minimum = minimum;
		this.maximum = maximum;
	}

	@Override
	public boolean acceptAttribute(Attribute attribute) {
		return attribute.getType().equals(Primitive.INT) || attribute.getType().equals(Primitive.REAL);
	}

	public void normalize(Object set) {
		/*
		 * if (set.lenght() < 1) throw new
		 * IllegalArgumentException("Empty data set");
		 * 
		 * double[] maximumValues = new double[set.inputsNum()]; double[]
		 * minimumValues = new double[set.inputsNum()];
		 * 
		 * for (int i = 0; i < set.inputsNum(); i++) { maximumValues[i] =
		 * Double.MIN_VALUE; minimumValues[i] = Double.MAX_VALUE; }
		 * 
		 * for (UnsupervisedPattern pattern : set.getPatterns()) { for (int i =
		 * 0; i < set.inputsNum(); i++) { if (pattern.getInputAt(i) >
		 * maximumValues[i]) maximumValues[i] = pattern.getInputAt(i); if
		 * (pattern.getInputAt(i) < minimumValues[i]) minimumValues[i] =
		 * pattern.getInputAt(i); } }
		 * 
		 * for (UnsupervisedPattern patterns : set.getPatterns()) { double[]
		 * normalizedVector = new double[set.inputsNum()]; for (int i = 0; i <
		 * patterns.getInputs().length; i++) normalizedVector[i] = this.minimum
		 * + ((patterns.getInputAt(i) - minimumValues[i]) * (this.maximum -
		 * this.minimum)) / (maximumValues[i] - minimumValues[i]);
		 * patterns.setInputs(normalizedVector); }
		 */
	}

	@Override
	public IValue[] encode(IValue value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IValue decode(IValue[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLength() {
		return 1;
	}
}