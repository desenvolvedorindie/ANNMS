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
package br.com.wfcreations.annms.test.api.data;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.representation.NominalToBinary;
import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.values.IDValue;
import br.com.wfcreations.annms.api.data.values.Value;
import br.com.wfcreations.annms.api.data.values.RealValue;

public class EncodingTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void TestForBinaryEncoding() {
		String[] classes = new String[] { "class1", "class2", "class3" };

		ListType listType = new ListType(classes);
		
		NominalToBinary b = new NominalToBinary(listType);
		System.out.println(Arrays.toString(b.encode(new IDValue(classes[0]))));
		System.out.println(Arrays.toString(b.encode(new IDValue(classes[1]))));
		System.out.println(Arrays.toString(b.encode(new IDValue(classes[2]))));
		System.out.println(b.decode(new Value[] { new RealValue(1), new RealValue(0), new RealValue(0) }));
		System.out.println(b.decode(new Value[] { new RealValue(0), new RealValue(1), new RealValue(0) }));
		System.out.println(b.decode(new Value[] { new RealValue(0), new RealValue(0), new RealValue(1) }));
	}

}
