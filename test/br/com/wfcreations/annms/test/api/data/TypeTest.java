package br.com.wfcreations.annms.test.api.data;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.type.Primitive;
import br.com.wfcreations.annms.api.data.values.Bool;
import br.com.wfcreations.annms.api.data.values.ID;
import br.com.wfcreations.annms.api.data.values.Int;
import br.com.wfcreations.annms.api.data.values.Null;
import br.com.wfcreations.annms.api.data.values.Real;
import br.com.wfcreations.annms.api.data.values.Str;

public class TypeTest {

	public static String L_VALUE1 = "value1";

	public static String L_VALUE2 = "value2";

	public static String L_VALUE3 = "value3";

	@Test
	public void testPrimitiveBooleanType() {
		assertTrue(Primitive.BOOLEAN.valid(Bool.TRUE));
		assertTrue(Primitive.BOOLEAN.valid(Bool.FALSE));
		assertFalse(Primitive.BOOLEAN.valid(Null.VALUE));
		assertEquals(Bool.class, Primitive.BOOLEAN.getRepresentation());
		assertEquals("BOOLEAN", Primitive.BOOLEAN.toString());
	}

	@Test
	public void testPrimitiveIntegerType() {
		assertTrue(Primitive.INTEGER.valid(new Int()));
		assertTrue(Primitive.INTEGER.valid(new Int(10)));
		assertTrue(Primitive.INTEGER.valid(new Int(-10)));
		assertTrue(Primitive.INTEGER.valid(new Int(0)));
		assertFalse(Primitive.INTEGER.valid(new Real()));
		assertFalse(Primitive.INTEGER.valid(Null.VALUE));
		assertEquals(Int.class, Primitive.INTEGER.getRepresentation());
		assertEquals("INTEGER", Primitive.INTEGER.toString());
	}

	@Test
	public void testPrimitiveRealType() {
		assertTrue(Primitive.REAL.valid(new Real()));
		assertTrue(Primitive.REAL.valid(new Real(10)));
		assertTrue(Primitive.REAL.valid(new Real(-10)));
		assertTrue(Primitive.REAL.valid(new Real(0)));
		assertFalse(Primitive.REAL.valid(new Int()));
		assertEquals(Real.class, Primitive.REAL.getRepresentation());
		assertEquals("REAL", Primitive.REAL.toString());
	}

	@Test
	public void testPrimitiveString() {
		assertTrue(Primitive.STRING.valid(new Str()));
		assertTrue(Primitive.STRING.valid(new Str("value")));
		assertEquals(Str.class, Primitive.STRING.getRepresentation());
		assertEquals("STRING", Primitive.STRING.toString());
	}

	@Test
	public void testListType() {
		ListType list = new ListType(new ID[] { new ID(L_VALUE1), new ID(L_VALUE2), new ID(L_VALUE3) });

		assertEquals(3, list.getListValuesNum());
		assertEquals(new ID(L_VALUE1), list.getValuesAt(0));
		assertEquals(new ID(L_VALUE2), list.getValuesAt(1));
		assertEquals(new ID(L_VALUE3), list.getValuesAt(2));
	}

	@Test
	public void testListTypeEquals() {
		ListType l1 = new ListType(new ID[] { new ID(L_VALUE1), new ID(L_VALUE2), new ID(L_VALUE3) });
		ListType l2 = new ListType(new ID[] { new ID(L_VALUE1), new ID(L_VALUE2), new ID(L_VALUE3) });
		ListType l3 = new ListType(new ID[] { new ID(L_VALUE1), new ID(L_VALUE2) });

		assertTrue(l1.equals(l2));
		assertFalse(l1.equals(l3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListEmptyValues() {
		new ListType(new ID[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDuplicatedValue() {
		new ListType(new ID[] { new ID("v"), new ID("v") });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListNullValue() {
		new ListType(new ID[] { new ID("v"), null });
	}
}