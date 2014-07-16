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
package br.com.wfcreations.annms.api.data.sample;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.utils.AttributeUtils;

public class SplintSampling implements ISampling {

	protected float percent;

	protected boolean shuffle;

	public SplintSampling(float percent, boolean shuffle) {
		this.percent = percent;
		this.shuffle = shuffle;
	}

	@Override
	public List<Data> sample(Data data) {
		List<Data> dataList = new Vector<>(2);
		int data1PatternCount = (int) (data.getPatternsNum() * percent);
		int data2PatternCount = data.getPatternsNum() - data1PatternCount;

		List<Integer> randomIndices = new Vector<Integer>(data.getPatternsNum());
		for (int i = 0; i < data.getPatternsNum(); i++) {
			randomIndices.add(i);
		}

		if (this.isShuffle())
			Collections.shuffle(randomIndices);

		Data data1 = new Data(data.getName() + "_1", AttributeUtils.cloneAttributes(data));

		for (int i = 0; i < data1PatternCount; i++)
			data1.add(data.getPatternAt(randomIndices.get(i)).clone());

		Data data2 = new Data(data.getName() + "_2", AttributeUtils.cloneAttributes(data1));

		int totalPatternCount = data1PatternCount + data2PatternCount;

		for (int i = data2PatternCount; i < totalPatternCount; i++)
			data2.add(data.getPatternAt(randomIndices.get(i)).clone());

		dataList.add(data1);
		dataList.add(data2);
		return dataList;
	}

	public float getPercent() {
		return this.percent;
	}

	public SplintSampling setPercent(float percent) {
		this.percent = percent;
		return this;
	}

	public boolean isShuffle() {
		return shuffle;
	}

	public void setShuffle(boolean shuffle) {
		this.shuffle = shuffle;
	}
}