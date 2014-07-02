package br.com.wfcreations.annms.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.Attribute;
import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.DataType;
import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.Pattern;
import br.com.wfcreations.annms.api.data.select.Select;
import br.com.wfcreations.annms.api.data.values.RealValue;
import br.com.wfcreations.annms.api.data.values.StringValue;

public class DataTest extends TestCase {

	private final static String FILE_NAME = "iris.data";

	private static Data data;

	@BeforeClass
	public void setUp() {
		Attribute[] attr = new Attribute[5];
		attr[0] = new Attribute("sepallenght", DataType.Native.REAL, true);
		attr[1] = new Attribute("sepalwidth", DataType.Native.REAL, true);
		attr[2] = new Attribute("petallenght", DataType.Native.REAL, true);
		attr[3] = new Attribute("petalwidth", DataType.Native.REAL, true);
		attr[4] = new Attribute("class", new DataType.ListDataType(new String[] { "Iris-setosa", "Iris-versicolor", "Iris-virginica" }), true);
		data = new Data("iris", attr);
	}

	private void addData() {
		data.add(new Pattern(new IValue[] { new RealValue(5.1), new RealValue(3.5), new RealValue(1.4), new RealValue(0.2), new StringValue("Iris-setosa") }));
		data.add(new Pattern(new IValue[] { new RealValue(7.0), new RealValue(3.2), new RealValue(4.7), new RealValue(1.4), new StringValue("Iris-versicolor") }));
		data.add(new Pattern(new IValue[] { new RealValue(6.3), new RealValue(3.3), new RealValue(6.0), new RealValue(2.5), new StringValue("Iris-virginica") }));
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
		assertEquals("Iris-setosa", ((DataType.ListDataType) data.getAttributeAt(4).getType()).getListValuesAt(0));
		assertEquals("Iris-versicolor", ((DataType.ListDataType) data.getAttributeAt(4).getType()).getListValuesAt(1));
		assertEquals("Iris-virginica", ((DataType.ListDataType) data.getAttributeAt(4).getType()).getListValuesAt(2));
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

	@Test
	public void testIfSaveAndLoadCorrectly() throws FileNotFoundException, IOException, ClassNotFoundException {
		addData();

		File file = new File(FILE_NAME);

		FileOutputStream fos = new FileOutputStream(file);
		data.save(fos);
		fos.close();

		FileInputStream fis = new FileInputStream(file);
		Data dataLoaded = new Data(fis);
		fis.close();
		data.removeAllPatterns();

		assertEquals("iris", dataLoaded.getName());
		assertEquals("sepallenght", dataLoaded.getAttributeAt(0).getName());
		assertEquals("sepalwidth", dataLoaded.getAttributeAt(1).getName());
		assertEquals("petallenght", dataLoaded.getAttributeAt(2).getName());
		assertEquals("petalwidth", dataLoaded.getAttributeAt(3).getName());
		assertEquals("class", dataLoaded.getAttributeAt(4).getName());
		// Class
		assertEquals("Iris-setosa", ((DataType.ListDataType) dataLoaded.getAttributeAt(4).getType()).getListValuesAt(0));
		assertEquals("Iris-versicolor", ((DataType.ListDataType) dataLoaded.getAttributeAt(4).getType()).getListValuesAt(1));
		assertEquals("Iris-virginica", ((DataType.ListDataType) dataLoaded.getAttributeAt(4).getType()).getListValuesAt(2));

		assertEquals(3, dataLoaded.getPatternsNum());
		for (int i = 0; i < dataLoaded.getPatternsNum(); i++) {
			assertEquals(5, dataLoaded.getPatternAt(i).getValuesNum());
		}

		assertEquals(5.1, dataLoaded.getPatternAt(0).getValueAt(0).getValue());
		assertEquals(3.5, dataLoaded.getPatternAt(0).getValueAt(1).getValue());
		assertEquals(1.4, dataLoaded.getPatternAt(0).getValueAt(2).getValue());
		assertEquals(0.2, dataLoaded.getPatternAt(0).getValueAt(3).getValue());
		assertEquals("Iris-setosa", dataLoaded.getPatternAt(0).getValueAt(4).getValue());

		assertEquals(7.0, dataLoaded.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, dataLoaded.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, dataLoaded.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, dataLoaded.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", dataLoaded.getPatternAt(1).getValueAt(4).getValue());

		assertEquals(7.0, dataLoaded.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, dataLoaded.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, dataLoaded.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, dataLoaded.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", dataLoaded.getPatternAt(1).getValueAt(4).getValue());

		assertEquals(7.0, dataLoaded.getPatternAt(1).getValueAt(0).getValue());
		assertEquals(3.2, dataLoaded.getPatternAt(1).getValueAt(1).getValue());
		assertEquals(4.7, dataLoaded.getPatternAt(1).getValueAt(2).getValue());
		assertEquals(1.4, dataLoaded.getPatternAt(1).getValueAt(3).getValue());
		assertEquals("Iris-versicolor", dataLoaded.getPatternAt(1).getValueAt(4).getValue());

		assertTrue(file.delete());
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
		assertEquals("Iris-setosa", ((DataType.ListDataType) dataClone.getAttributeAt(4).getType()).getListValuesAt(0));
		assertEquals("Iris-versicolor", ((DataType.ListDataType) dataClone.getAttributeAt(4).getType()).getListValuesAt(1));
		assertEquals("Iris-virginica", ((DataType.ListDataType) dataClone.getAttributeAt(4).getType()).getListValuesAt(2));

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
		Data data2 = new Data("classes", new Attribute[] { new Attribute("class", new DataType.ListDataType(new String[] { "Iris-setosa", "Iris-versicolor", "Iris-virginica" }), true) });
		data2.add(new Pattern(new IValue[] { new StringValue("Iris-setosa") }));
		data2.add(new Pattern(new IValue[] { new StringValue("Iris-versicolor") }));
		data2.add(new Pattern(new IValue[] { new StringValue("Iris-virginica") }));

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
		assertEquals("Iris-setosa", ((DataType.ListDataType) marged.getAttributeAt(4).getType()).getListValuesAt(0));
		assertEquals("Iris-versicolor", ((DataType.ListDataType) marged.getAttributeAt(4).getType()).getListValuesAt(1));
		assertEquals("Iris-virginica", ((DataType.ListDataType) marged.getAttributeAt(4).getType()).getListValuesAt(2));

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
