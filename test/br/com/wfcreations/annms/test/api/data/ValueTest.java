package br.com.wfcreations.annms.test.api.data;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.wfcreations.annms.api.data.values.BooleanValue;
import br.com.wfcreations.annms.api.data.values.IDValue;
import br.com.wfcreations.annms.api.data.values.IntegerValue;
import br.com.wfcreations.annms.api.data.values.NullValue;
import br.com.wfcreations.annms.api.data.values.RealValue;
import br.com.wfcreations.annms.api.data.values.StringValue;

public class ValueTest {

	@Test
	public void testBooleanValue() {
		assertEquals(true, BooleanValue.TRUE.getValue());
		assertEquals(false, BooleanValue.FALSE.getValue());
		assertEquals(BooleanValue.TRUE, BooleanValue.TRUE);
		assertEquals(BooleanValue.FALSE, BooleanValue.FALSE);
		assertNotEquals(BooleanValue.TRUE, BooleanValue.FALSE);
		assertEquals("TRUE", BooleanValue.TRUE.toString());
		assertEquals("FALSE", BooleanValue.FALSE.toString());
		assertTrue(BooleanValue.getValueFor(BooleanValue.TRUE));
		assertFalse(BooleanValue.getValueFor(BooleanValue.FALSE));
	}

	@Test
	public void testComplexListValue() {

	}

	@Test
	public void testIDValue() {
		IDValue idv1 = new IDValue("class1");
		IDValue idv2 = new IDValue("class2");

		assertEquals("CLASS1", idv1.getValue());
		assertEquals("CLASS2", idv2.getValue());
		assertEquals("CLASS1", idv1.toString());
		assertEquals("CLASS2", idv2.toString());
		assertEquals(new IDValue("class1"), idv1);
		assertEquals(new IDValue("CLASS1"), idv1);
		assertEquals(new IDValue("ClAsS1"), idv1);
		assertEquals("CLASS1", IDValue.getValueFor(idv1));
	}

	@Test
	public void testIntegervalue() {
		IntegerValue iv1 = new IntegerValue();
		IntegerValue iv2 = new IntegerValue(-10);
		IntegerValue iv3 = new IntegerValue(0);
		IntegerValue iv4 = new IntegerValue(10);

		assertTrue(0 == iv1.getValue());
		assertTrue(-10 == iv2.getValue());
		assertTrue(0 == iv3.getValue());
		assertTrue(10 == iv4.getValue());
		assertEquals(iv1, iv3);
		assertEquals("0", iv1.toString());
		assertEquals("-10", iv2.toString());
		assertEquals("10", iv4.toString());
		assertEquals(0, IntegerValue.getValueFor(iv1));
		assertEquals(-10, IntegerValue.getValueFor(iv2));
	}

	@Test
	public void testInvalidIDFormat() {
		try {
			new IDValue("");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}

		try {
			new IDValue("asdsad a sd");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
	}

	@Test
	public void testNullValue() {
		assertNull(NullValue.VALUE.getValue());
		assertEquals("NULL", NullValue.VALUE.toString());
	}

	@Test
	public void testRealValue() {
		RealValue rv1 = new RealValue();
		RealValue rv2 = new RealValue(-10.5);
		RealValue rv3 = new RealValue(0);
		RealValue rv4 = new RealValue(10.5);

		assertTrue(0 == rv1.getValue());
		assertTrue(-10.5 == rv2.getValue());
		assertTrue(0 == rv3.getValue());
		assertTrue(10.5 == rv4.getValue());
		assertEquals(rv1, rv3);
		assertEquals("0.0", rv1.toString());
		assertEquals("-10.5", rv2.toString());
		assertEquals("10.5", rv4.toString());
		assertEquals(0, RealValue.getValueFor(rv1), 0);
		assertEquals(-10.5, RealValue.getValueFor(rv2), 0);
	}

	@Test
	public void testStringValue() {
		StringValue sv1 = new StringValue();
		StringValue sv2 = new StringValue("string 1");
		StringValue sv3 = new StringValue("string 2");
		assertEquals("", sv1.getValue());
		assertEquals("string 1", sv2.getValue());
		assertEquals("string 2", sv3.getValue());
		assertEquals(new StringValue("string 1"), sv2);
		assertNotEquals(new StringValue("String 1"), sv2);
		assertEquals("string 2", sv3.toString());
		assertNotEquals("String 2", sv3.toString());
	}
}