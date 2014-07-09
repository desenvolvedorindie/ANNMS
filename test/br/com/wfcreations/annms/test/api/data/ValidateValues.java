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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.validate.BetweenValidate;
import br.com.wfcreations.annms.api.data.validate.IntValidate;
import br.com.wfcreations.annms.api.data.validate.RealValidate;
import br.com.wfcreations.annms.api.data.validate.ValidateAbstract;
import br.com.wfcreations.annms.api.data.values.IValue;
import br.com.wfcreations.annms.api.data.values.Int;
import br.com.wfcreations.annms.api.data.values.Real;

public class ValidateValues {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void betweenTest() {
		ValidateAbstract validate = new BetweenValidate(1, 5, true);
		Int integer = new Int(5);
		Real real = new Real(5);
		IValue[] values = new IValue[] { integer, real };
		assertTrue(validate.isValid(new Int(2)));
		assertFalse(validate.isValid(new Real(10)));
		assertTrue(validate.isValid(values));
	}

	@Test
	public void intTest() {
		ValidateAbstract validate = new IntValidate(true);
		Int integer = new Int(9);
		Real real = new Real(5.2);
		IValue[] values = new IValue[] { integer, new Int(2) };
		assertTrue(validate.isValid(integer));
		assertFalse(validate.isValid(real));
		assertTrue(validate.isValid(values));
	}

	@Test
	public void realTest() {
		ValidateAbstract validate = new RealValidate(true);
		Int integer = new Int(10);
		Real real = new Real(5.2);
		IValue[] values = new IValue[] { real, new Real(2.5) };
		assertFalse(validate.isValid(integer));
		assertTrue(validate.isValid(real));
		assertTrue(validate.isValid(values));
	}
}