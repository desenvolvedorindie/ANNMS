package br.com.wfcreations.annms.test.api.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.type.DateType;
import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.type.PrimitiveType;
import br.com.wfcreations.annms.api.data.values.IDValue;

public class AttributeTest {

	Attribute attr1, attr2, attr3, attr4, attr5, attr6;

	IDValue id1, id2, id3, id4, id5, id6;

	final static String ATTR1_ID = "id1";

	final static String ATTR2_ID = "id2";

	final static String ATTR3_ID = "id3";

	final static String ATTR4_ID = "id4";

	final static String ATTR5_ID = "id5";

	final static String ATTR6_ID = "id6";

	@Before
	public void setUp() throws Exception {
		id1 = new IDValue(ATTR1_ID);
		id2 = new IDValue(ATTR2_ID);
		id3 = new IDValue(ATTR3_ID);
		id4 = new IDValue(ATTR4_ID);
		id5 = new IDValue(ATTR5_ID);
		id6 = new IDValue(ATTR6_ID);

		attr1 = new Attribute(id1, PrimitiveType.BOOLEAN, true);
		attr2 = new Attribute(id2, PrimitiveType.INTEGER, false);
		attr3 = new Attribute(id3, PrimitiveType.REAL, true);
		attr4 = new Attribute(id4, PrimitiveType.STRING, false);
		attr5 = new Attribute(id5, new DateType(""), true);
		attr6 = new Attribute(id6, new ListType(new IDValue[] {}), false);
	}

	@Test
	public void test() {

	}

}
