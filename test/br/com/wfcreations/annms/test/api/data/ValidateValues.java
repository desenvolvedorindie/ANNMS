package br.com.wfcreations.annms.test.api.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.validate.BetweenValidate;
import br.com.wfcreations.annms.api.data.validate.IntValidate;
import br.com.wfcreations.annms.api.data.validate.RealValidate;
import br.com.wfcreations.annms.api.data.validate.ValidateAbstract;
import br.com.wfcreations.annms.api.data.values.IntegerValue;
import br.com.wfcreations.annms.api.data.values.RealValue;

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
		IntegerValue integer = new IntegerValue(5);
		RealValue real = new RealValue(5);
		IValue[] values = new IValue[] { integer, real };
		assertTrue(validate.isValid(new IntegerValue(2)));
		assertFalse(validate.isValid(new RealValue(10)));
		assertTrue(validate.isValid(values));
	}

	@Test
	public void intTest() {
		ValidateAbstract validate = new IntValidate(true);
		IntegerValue integer = new IntegerValue(9);
		RealValue real = new RealValue(5.2);
		IValue[] values = new IValue[] { integer, new IntegerValue(2) };
		assertTrue(validate.isValid(integer));
		assertFalse(validate.isValid(real));
		assertTrue(validate.isValid(values));
	}

	@Test
	public void realTest() {
		ValidateAbstract validate = new RealValidate(true);
		IntegerValue integer = new IntegerValue(10);
		RealValue real = new RealValue(5.2);
		IValue[] values = new IValue[] { real, new RealValue(2.5) };
		assertFalse(validate.isValid(integer));
		assertTrue(validate.isValid(real));
		assertTrue(validate.isValid(values));
	}
}