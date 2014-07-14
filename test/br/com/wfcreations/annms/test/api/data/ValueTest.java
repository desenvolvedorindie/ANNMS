package br.com.wfcreations.annms.test.api.data;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.wfcreations.annms.api.data.value.Bool;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.Int;
import br.com.wfcreations.annms.api.data.value.Null;
import br.com.wfcreations.annms.api.data.value.Real;
import br.com.wfcreations.annms.api.data.value.Str;

public class ValueTest {

	@Test
	public void testBooleanValue() {
		assertEquals(true, Bool.TRUE.getValue());
		assertEquals(false, Bool.FALSE.getValue());
		assertEquals(Bool.TRUE, Bool.TRUE);
		assertEquals(Bool.FALSE, Bool.FALSE);
		assertNotEquals(Bool.TRUE, Bool.FALSE);
		assertEquals("TRUE", Bool.TRUE.toString());
		assertEquals("FALSE", Bool.FALSE.toString());
		assertTrue(Bool.getValueFor(Bool.TRUE));
		assertFalse(Bool.getValueFor(Bool.FALSE));
	}

	@Test
	public void testComplexListValue() {

	}

	@Test
	public void testIDValue() {
		ID idv1 = new ID("class1");
		ID idv2 = new ID("class2");

		assertEquals("CLASS1", idv1.getValue());
		assertEquals("CLASS2", idv2.getValue());
		assertEquals("CLASS1", idv1.toString());
		assertEquals("CLASS2", idv2.toString());
		assertEquals(new ID("class1"), idv1);
		assertEquals(new ID("CLASS1"), idv1);
		assertEquals(new ID("ClAsS1"), idv1);
		assertEquals("CLASS1", ID.getValueFor(idv1));
	}

	@Test
	public void testIntegervalue() {
		Int iv1 = new Int();
		Int iv2 = new Int(-10);
		Int iv3 = new Int(0);
		Int iv4 = new Int(10);

		assertTrue(0 == iv1.getValue());
		assertTrue(-10 == iv2.getValue());
		assertTrue(0 == iv3.getValue());
		assertTrue(10 == iv4.getValue());
		assertEquals(iv1, iv3);
		assertEquals("0", iv1.toString());
		assertEquals("-10", iv2.toString());
		assertEquals("10", iv4.toString());
		assertEquals(0, Int.getValueFor(iv1));
		assertEquals(-10, Int.getValueFor(iv2));
	}

	@Test
	public void testInvalidIDFormat() {
		try {
			new ID("");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}

		try {
			new ID("asdsad a sd");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
	}

	@Test
	public void testNullValue() {
		assertNull(Null.VALUE.getValue());
		assertEquals("NULL", Null.VALUE.toString());
	}

	@Test
	public void testRealValue() {
		Real rv1 = new Real();
		Real rv2 = new Real(-10.5);
		Real rv3 = new Real(0);
		Real rv4 = new Real(10.5);

		assertTrue(0 == rv1.getValue());
		assertTrue(-10.5 == rv2.getValue());
		assertTrue(0 == rv3.getValue());
		assertTrue(10.5 == rv4.getValue());
		assertEquals(rv1, rv3);
		assertEquals("0.0", rv1.toString());
		assertEquals("-10.5", rv2.toString());
		assertEquals("10.5", rv4.toString());
		assertEquals(0, Real.getValueFor(rv1), 0);
		assertEquals(-10.5, Real.getValueFor(rv2), 0);
	}

	@Test
	public void testStringValue() {
		Str sv1 = new Str();
		Str sv2 = new Str("string 1");
		Str sv3 = new Str("string 2");
		assertEquals("", sv1.getValue());
		assertEquals("string 1", sv2.getValue());
		assertEquals("string 2", sv3.getValue());
		assertEquals(new Str("string 1"), sv2);
		assertNotEquals(new Str("String 1"), sv2);
		assertEquals("string 2", sv3.toString());
		assertNotEquals("String 2", sv3.toString());
	}
}