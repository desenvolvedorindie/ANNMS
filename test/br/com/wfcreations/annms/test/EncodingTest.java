package br.com.wfcreations.annms.test;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.IValue;
import br.com.wfcreations.annms.api.data.representation.NominalToBinary;
import br.com.wfcreations.annms.api.data.values.IDValue;
import br.com.wfcreations.annms.api.data.values.RealValue;

public class EncodingTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void TestForBinaryEncoding() {
		String[] classes = new String[] { "class1", "class2", "class3" };

		NominalToBinary b = new NominalToBinary(classes);
		System.out.println(Arrays.toString(b.encode(new IDValue(classes[0]))));
		System.out.println(Arrays.toString(b.encode(new IDValue(classes[1]))));
		System.out.println(Arrays.toString(b.encode(new IDValue(classes[2]))));
		System.out.println(b.decode(new IValue[] { new RealValue(1), new RealValue(0), new RealValue(0) }));
		System.out.println(b.decode(new IValue[] { new RealValue(0), new RealValue(1), new RealValue(0) }));
		System.out.println(b.decode(new IValue[] { new RealValue(0), new RealValue(0), new RealValue(1) }));
	}

}
