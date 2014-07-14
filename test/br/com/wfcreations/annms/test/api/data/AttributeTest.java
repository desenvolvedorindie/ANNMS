package br.com.wfcreations.annms.test.api.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.type.Primitive;
import br.com.wfcreations.annms.api.data.value.Bool;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.Int;
import br.com.wfcreations.annms.api.data.value.Null;

public class AttributeTest {

	Attribute attr1, attr2;

	ID id1, id2;

	final static String ATTR1_ID = "id1";

	final static String ATTR2_ID = "id2";

	@Before
	public void setUp() throws Exception {
		id1 = new ID(ATTR1_ID);
		id2 = new ID(ATTR2_ID);

		attr1 = new Attribute(id1, Primitive.BOOL, true);
		attr2 = new Attribute(id2, Primitive.INT, false);
	}

	@Test
	public void testAttributes() {
		assertEquals(id1, attr1.getID());
		assertEquals(Primitive.BOOL, attr1.getType());
		assertTrue(attr1.isNotNull());
		assertEquals("Attribute [name=ID1, type=BOOLEAN, notNull=true]", attr1.toString());
	}

	@Test
	public void testAttributeValidate() {
		assertTrue(attr1.validate(Bool.TRUE));
		assertTrue(attr1.validate(Bool.FALSE));
		assertFalse(attr1.validate(Null.VALUE));

		assertTrue(attr2.validate(new Int()));
		assertTrue(attr2.validate(Null.VALUE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAttributeNullID() {
		new Attribute(null, Primitive.BOOL, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAttributeTypeNull() {
		new Attribute(ID.create("tes"), null, false);
	}
}
