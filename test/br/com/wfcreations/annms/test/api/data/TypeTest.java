package br.com.wfcreations.annms.test.api.data;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.type.Primitive;
import br.com.wfcreations.annms.api.data.value.Bool;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.Int;
import br.com.wfcreations.annms.api.data.value.Null;
import br.com.wfcreations.annms.api.data.value.Real;
import br.com.wfcreations.annms.api.data.value.Str;

public class TypeTest {

	public static String L_VALUE1 = "value1";

	public static String L_VALUE2 = "value2";

	public static String L_VALUE3 = "value3";

	@Test
	public void testPrimitiveBooleanType() {
		assertTrue(Primitive.BOOL.valid(Bool.TRUE));
		assertTrue(Primitive.BOOL.valid(Bool.FALSE));
		assertFalse(Primitive.BOOL.valid(Null.VALUE));
		assertEquals("BOOLEAN", Primitive.BOOL.toString());
	}

	@Test
	public void testPrimitiveIntegerType() {
		assertTrue(Primitive.INT.valid(new Int()));
		assertTrue(Primitive.INT.valid(new Int(10)));
		assertTrue(Primitive.INT.valid(new Int(-10)));
		assertTrue(Primitive.INT.valid(new Int(0)));
		assertFalse(Primitive.INT.valid(new Real()));
		assertFalse(Primitive.INT.valid(Null.VALUE));
		assertEquals("INTEGER", Primitive.INT.toString());
	}

	@Test
	public void testPrimitiveRealType() {
		assertTrue(Primitive.REAL.valid(new Real()));
		assertTrue(Primitive.REAL.valid(new Real(10)));
		assertTrue(Primitive.REAL.valid(new Real(-10)));
		assertTrue(Primitive.REAL.valid(new Real(0)));
		assertFalse(Primitive.REAL.valid(new Int()));
		assertEquals("REAL", Primitive.REAL.toString());
	}

	@Test
	public void testPrimitiveString() {
		assertTrue(Primitive.STR.valid(new Str()));
		assertTrue(Primitive.STR.valid(new Str("value")));
		assertEquals("STRING", Primitive.STR.toString());
	}

	@Test
	public void testListType() {
		ListType list = new ListType(new ID[] { ID.create(L_VALUE1), ID.create(L_VALUE2), ID.create(L_VALUE3) });

		assertEquals(3, list.getListValuesNum());
		assertEquals(ID.create(L_VALUE1), list.getValuesAt(0));
		assertEquals(ID.create(L_VALUE2), list.getValuesAt(1));
		assertEquals(ID.create(L_VALUE3), list.getValuesAt(2));
	}

	@Test
	public void testListTypeEquals() {
		ListType l1 = new ListType(new ID[] { ID.create(L_VALUE1), ID.create(L_VALUE2), ID.create(L_VALUE3) });
		ListType l2 = new ListType(new ID[] { ID.create(L_VALUE1), ID.create(L_VALUE2), ID.create(L_VALUE3) });
		ListType l3 = new ListType(new ID[] { ID.create(L_VALUE1), ID.create(L_VALUE2) });

		assertTrue(l1.equals(l2));
		assertFalse(l1.equals(l3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListEmptyValues() {
		new ListType(new ID[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDuplicatedValue() {
		new ListType(new ID[] { ID.create("v"), ID.create("v") });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListNullValue() {
		new ListType(new ID[] { ID.create("v"), null });
	}
}