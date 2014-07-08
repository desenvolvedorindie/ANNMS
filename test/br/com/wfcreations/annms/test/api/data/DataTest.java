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

import java.util.LinkedHashMap;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.Pattern;
import br.com.wfcreations.annms.api.data.Select;
import br.com.wfcreations.annms.api.data.type.ListType;
import br.com.wfcreations.annms.api.data.type.PrimitiveType;
import br.com.wfcreations.annms.api.data.values.Value;
import br.com.wfcreations.annms.api.data.values.RealValue;
import br.com.wfcreations.annms.api.data.values.StringValue;

public class DataTest extends TestCase {

	private static Data data;

	@BeforeClass
	public void setUp() {
		Attribute[] attr = new Attribute[5];
		attr[0] = new Attribute("sepallenght", PrimitiveType.REAL, true);
		attr[1] = new Attribute("sepalwidth", PrimitiveType.REAL, true);
		attr[2] = new Attribute("petallenght", PrimitiveType.REAL, true);
		attr[3] = new Attribute("petalwidth", PrimitiveType.REAL, true);
		attr[4] = new Attribute("class", new ListType(new String[] { "Iris-setosa", "Iris-versicolor", "Iris-virginica" }), true);
		data = new Data("iris", attr);
	}

	private void addData() {
		data.add(new Pattern(new Value[] { new RealValue(5.1), new RealValue(3.5), new RealValue(1.4), new RealValue(0.2), new StringValue("Iris-setosa") }));
		data.add(new Pattern(new Value[] { new RealValue(7.0), new RealValue(3.2), new RealValue(4.7), new RealValue(1.4), new StringValue("Iris-versicolor") }));
		data.add(new Pattern(new Value[] { new RealValue(6.3), new RealValue(3.3), new RealValue(6.0), new RealValue(2.5), new StringValue("Iris-virginica") }));
	}

	@Test
	public void testIfIsDefinedCorrectly() {
		assertNotNull(data);

		assertEquals("iris", data.getName());
		assertEquals(5, data.getAttributesNum());
		assertEquals("sepallenght", data.getAttributeAt(0).getName());
		assertEquals("sepalwidth", data.getAttributeAt(1).getName());
		assertEquals("petallenght", data.getAttributeAt(2).getName());
		assertEquals("petalwidth", data.getAttributeAt(3).getName());
		assertEquals("class", data.getAttributeAt(4).getName());
		// Class
		assertEquals("Iris-setosa", ((ListType) data.getAttributeAt(4).getType()).getListValuesAt(0));
		assertEquals("Iris-versicolor", ((ListType) data.getAttributeAt(4).getType()).getListValuesAt(1));
		assertEquals("Iris-virginica", ((ListType) data.getAttributeAt(4).getType()).getListValuesAt(2));
	}

	@Test
	public void testIfAddedPatternsCorrectly() {
		addData();

		assertEquals(3, data.getPatternsNum());
		for (int i = 0; i < data.getPatternsNum(); i++) {
			assertEquals(5, data.getPatternAt(i).getValuesNum());
		}

		assertEquals(5.1, data.getPatternAt(0).getValueAt(0).getValue());
		assertEquals(3.5, data.getPatternAt(0).getValueAt(1).getValue());
		assertEquals(1.4, data.getPatternAt(0).getValueAt(2).getValue());
		assertEquals(0.2, data.getPatternAt(0).getValueAt(3).getValue());
		assertEquals("Iris-setosa", data.getPatternAt(0).getValueAt(4).getValue());

		assertEquals(7.0, data.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, data.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, data.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, data.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", data.getPatternAt(1).getValueAt(4).getValue());

		assertEquals(7.0, data.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, data.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, data.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, data.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", data.getPatternAt(1).getValueAt(4).getValue());

		assertEquals(7.0, data.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, data.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, data.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, data.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", data.getPatternAt(1).getValueAt(4).getValue());
		data.removeAllPatterns();
	}

	public void testIfCloneCorrectly() {
		addData();
		Data dataClone = data.clone();

		assertEquals("iris", dataClone.getName());
		assertEquals("sepallenght", dataClone.getAttributeAt(0).getName());
		assertEquals("sepalwidth", dataClone.getAttributeAt(1).getName());
		assertEquals("petallenght", dataClone.getAttributeAt(2).getName());
		assertEquals("petalwidth", dataClone.getAttributeAt(3).getName());
		assertEquals("class", dataClone.getAttributeAt(4).getName());
		// Class
		assertEquals("Iris-setosa", ((ListType) dataClone.getAttributeAt(4).getType()).getListValuesAt(0));
		assertEquals("Iris-versicolor", ((ListType) dataClone.getAttributeAt(4).getType()).getListValuesAt(1));
		assertEquals("Iris-virginica", ((ListType) dataClone.getAttributeAt(4).getType()).getListValuesAt(2));

		assertEquals(3, dataClone.getPatternsNum());
		for (int i = 0; i < dataClone.getPatternsNum(); i++) {
			assertEquals(5, dataClone.getPatternAt(i).getValuesNum());
		}

		assertEquals(5.1, dataClone.getPatternAt(0).getValueAt(0).getValue());
		assertEquals(3.5, dataClone.getPatternAt(0).getValueAt(1).getValue());
		assertEquals(1.4, dataClone.getPatternAt(0).getValueAt(2).getValue());
		assertEquals(0.2, dataClone.getPatternAt(0).getValueAt(3).getValue());
		assertEquals("Iris-setosa", dataClone.getPatternAt(0).getValueAt(4).getValue());

		assertEquals(7.0, dataClone.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, dataClone.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, dataClone.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, dataClone.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", dataClone.getPatternAt(1).getValueAt(4).getValue());

		assertEquals(7.0, dataClone.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, dataClone.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, dataClone.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, dataClone.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", dataClone.getPatternAt(1).getValueAt(4).getValue());

		assertEquals(7.0, dataClone.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, dataClone.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, dataClone.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, dataClone.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", dataClone.getPatternAt(1).getValueAt(4).getValue());

		data.removeAllPatterns();
	}

	public void testIfSliceCorrectly() {
		addData();
		Data dataSlice = data.slice(0, 4);
		assertEquals("iris", dataSlice.getName());
		assertEquals(4, dataSlice.getAttributesNum());

		assertEquals("sepallenght", dataSlice.getAttributeAt(0).getName());
		assertEquals("sepalwidth", dataSlice.getAttributeAt(1).getName());
		assertEquals("petallenght", dataSlice.getAttributeAt(2).getName());
		assertEquals("petalwidth", dataSlice.getAttributeAt(3).getName());

		assertEquals(3, dataSlice.getPatternsNum());
		for (int i = 0; i < dataSlice.getPatternsNum(); i++) {
			assertEquals(4, dataSlice.getPatternAt(i).getValuesNum());
		}

		assertEquals(5.1, dataSlice.getPatternAt(0).getValueAt(0).getValue());
		assertEquals(3.5, dataSlice.getPatternAt(0).getValueAt(1).getValue());
		assertEquals(1.4, dataSlice.getPatternAt(0).getValueAt(2).getValue());
		assertEquals(0.2, dataSlice.getPatternAt(0).getValueAt(3).getValue());

		assertEquals(7.0, dataSlice.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, dataSlice.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, dataSlice.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, dataSlice.getPatternAt(1).getValueAt(3).getValue());

		assertEquals(7.0, dataSlice.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, dataSlice.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, dataSlice.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, dataSlice.getPatternAt(1).getValueAt(3).getValue());

		assertEquals(7.0, dataSlice.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, dataSlice.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, dataSlice.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, dataSlice.getPatternAt(1).getValueAt(3).getValue());

		data.removeAllPatterns();
	}

	public void testIfMargeCorrectly() {
		addData();
		Data data2 = new Data("classes", new Attribute[] { new Attribute("class", new ListType(new String[] { "Iris-setosa", "Iris-versicolor", "Iris-virginica" }), true) });
		data2.add(new Pattern(new Value[] { new StringValue("Iris-setosa") }));
		data2.add(new Pattern(new Value[] { new StringValue("Iris-versicolor") }));
		data2.add(new Pattern(new Value[] { new StringValue("Iris-virginica") }));

		Data dataSlice = data.slice(0, 4);

		Data marged = Data.merge(dataSlice, data2);

		assertEquals("iris_classes", marged.getName());
		assertEquals(5, data.getAttributesNum());
		assertEquals("sepallenght", marged.getAttributeAt(0).getName());
		assertEquals("sepalwidth", marged.getAttributeAt(1).getName());
		assertEquals("petallenght", marged.getAttributeAt(2).getName());
		assertEquals("petalwidth", marged.getAttributeAt(3).getName());
		assertEquals("class", marged.getAttributeAt(4).getName());
		// Class
		assertEquals("Iris-setosa", ((ListType) marged.getAttributeAt(4).getType()).getListValuesAt(0));
		assertEquals("Iris-versicolor", ((ListType) marged.getAttributeAt(4).getType()).getListValuesAt(1));
		assertEquals("Iris-virginica", ((ListType) marged.getAttributeAt(4).getType()).getListValuesAt(2));

		assertEquals(3, marged.getPatternsNum());
		for (int i = 0; i < marged.getPatternsNum(); i++) {
			assertEquals(5, marged.getPatternAt(i).getValuesNum());
		}

		assertEquals(5.1, marged.getPatternAt(0).getValueAt(0).getValue());
		assertEquals(3.5, marged.getPatternAt(0).getValueAt(1).getValue());
		assertEquals(1.4, marged.getPatternAt(0).getValueAt(2).getValue());
		assertEquals(0.2, marged.getPatternAt(0).getValueAt(3).getValue());
		assertEquals("Iris-setosa", marged.getPatternAt(0).getValueAt(4).getValue());

		assertEquals(7.0, marged.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, marged.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, marged.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, marged.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", marged.getPatternAt(1).getValueAt(4).getValue());

		assertEquals(7.0, marged.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, marged.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, marged.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, marged.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", marged.getPatternAt(1).getValueAt(4).getValue());

		assertEquals(7.0, marged.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, marged.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, marged.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, marged.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", marged.getPatternAt(1).getValueAt(4).getValue());
		data.removeAllPatterns();
	}

	public void testForDataSelect() {
		addData();

		Select where = new Select();
		LinkedHashMap<String, String> col = new LinkedHashMap<String, String>();
		col.put("sepallenght", "ls");
		col.put("sepalwidth", "sw");
		col.put("class", "c");
		where.columns(col);
		Data dataSelected = data.fetch(where);

		System.out.println(dataSelected);

		data.removeAllPatterns();
	}
}
