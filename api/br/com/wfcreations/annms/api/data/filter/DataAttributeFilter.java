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
package br.com.wfcreations.annms.api.data.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.Pattern;
import br.com.wfcreations.annms.api.data.filter.attribute.IAttributeFilter;
import br.com.wfcreations.annms.api.data.value.IValue;
import br.com.wfcreations.annms.api.lang.ArrayUtils;

public class DataAttributeFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Data data;

	protected IAttributeFilter[] representators;

	public DataAttributeFilter(Data data, IAttributeFilter[] representators) {
		this.data = data;
		this.representators = representators;
	}

	public List<Pattern> encode() {
		if (getRepresentators().length != getData().getAttributesNum())
			throw new IllegalArgumentException("Representators ivalid lenght");

		IValue[] tmp;
		List<Pattern> patternsList = new Vector<Pattern>(getData().getPatternsNum());

		for (int i = 0; i < getData().getPatternsNum(); i++) {
			tmp = new IValue[0];
			for (int j = 0; j < getData().getAttributesNum(); j++)
				tmp = ArrayUtils.addAll(tmp, getData().getPatternAt(i).getValueAt(j));
			patternsList.add(new Pattern(tmp));
		}

		return patternsList;
	}

	public List<Pattern> decode(List<Pattern> patterns) {
		List<Pattern> patternsList = new Vector<Pattern>(patterns.size());
		// TODO
		return patternsList;
	}

	public int calculateAttributeNum() {
		int count = 0;
		for (IAttributeFilter representator : representators)
			count += representator.getLength();
		return count;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public IAttributeFilter[] getRepresentators() {
		return representators;
	}

	public void setRepresentators(IAttributeFilter[] representators) {
		this.representators = representators;
	}
}