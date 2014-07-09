package br.com.wfcreations.annms.test.api.data;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.type.PrimitiveType;
import br.com.wfcreations.annms.api.data.values.BooleanValue;
import br.com.wfcreations.annms.api.data.values.IDValue;
import br.com.wfcreations.annms.api.data.values.IntegerValue;
import br.com.wfcreations.annms.api.data.values.NullValue;
import br.com.wfcreations.annms.api.data.values.RealValue;
import br.com.wfcreations.annms.api.data.values.StringValue;

public class TypeTest {

	public static String L_VALUE1 = "value1";

	public static String L_VALUE2 = "value2";

	public static String L_VALUE3 = "value3";

	@Test
	public void testPrimitiveBooleanType() {
		assertTrue(PrimitiveType.BOOLEAN.valid(BooleanValue.TRUE));
		assertTrue(PrimitiveType.BOOLEAN.valid(BooleanValue.FALSE));
		assertFalse(PrimitiveType.BOOLEAN.valid(NullValue.VALUE));
		assertEquals(BooleanValue.class, PrimitiveType.BOOLEAN.getRepresentation());
		assertEquals("BOOLEAN", PrimitiveType.BOOLEAN.toString());
	}

	@Test
	public void testPrimitiveIntegerType() {
		assertTrue(PrimitiveType.INTEGER.valid(new IntegerValue()));
		assertTrue(PrimitiveType.INTEGER.valid(new IntegerValue(10)));
		assertTrue(PrimitiveType.INTEGER.valid(new IntegerValue(-10)));
		assertTrue(PrimitiveType.INTEGER.valid(new IntegerValue(0)));
		assertFalse(PrimitiveType.INTEGER.valid(new RealValue()));
		assertFalse(PrimitiveType.INTEGER.valid(NullValue.VALUE));
		assertEquals(IntegerValue.class, PrimitiveType.INTEGER.getRepresentation());
		assertEquals("INTEGER", PrimitiveType.INTEGER.toString());
	}

	@Test
	public void testPrimitiveRealType() {
		assertTrue(PrimitiveType.REAL.valid(new RealValue()));
		assertTrue(PrimitiveType.REAL.valid(new RealValue(10)));
		assertTrue(PrimitiveType.REAL.valid(new RealValue(-10)));
		assertTrue(PrimitiveType.REAL.valid(new RealValue(0)));
		assertFalse(PrimitiveType.REAL.valid(new IntegerValue()));
		assertEquals(RealValue.class, PrimitiveType.REAL.getRepresentation());
		assertEquals("REAL", PrimitiveType.REAL.toString());
	}

	@Test
	public void testPrimitiveString() {
		assertTrue(PrimitiveType.STRING.valid(new StringValue()));
		assertTrue(PrimitiveType.STRING.valid(new StringValue("value")));
		assertEquals(StringValue.class, PrimitiveType.STRING.getRepresentation());
		assertEquals("STRING", PrimitiveType.STRING.toString());
	}

	@Test
	public void testListType() {
		ListType list = new ListType(new IDValue[] { new IDValue(L_VALUE1), new IDValue(L_VALUE2), new IDValue(L_VALUE3) });

		assertEquals(3, list.getListValuesNum());
		assertEquals(L_VALUE1, list.getValuesAt(0));
		assertEquals(L_VALUE2, list.getValuesAt(1));
		assertEquals(L_VALUE3, list.getValuesAt(2));
	}

	@Test
	public void testListTypeEquals() {
		ListType l1 = new ListType(new IDValue[] { new IDValue(L_VALUE1), new IDValue(L_VALUE2), new IDValue(L_VALUE3) });
		ListType l2 = new ListType(new IDValue[] { new IDValue(L_VALUE1), new IDValue(L_VALUE2), new IDValue(L_VALUE3) });
		ListType l3 = new ListType(new IDValue[] { new IDValue(L_VALUE1), new IDValue(L_VALUE2) });

		assertTrue(l1.equals(l2));
		assertFalse(l1.equals(l3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListEmptyValues() {
		new ListType(new IDValue[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListDuplicatedValue() {
		new ListType(new IDValue[] { new IDValue("v"), new IDValue("v") });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testListNullValue() {
		new ListType(new IDValue[] { new IDValue("v"), null });
	}
}