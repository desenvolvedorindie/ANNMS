package br.com.wfcreations.annms.test.api.data;

import org.junit.Before;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.Param;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.IParamValue;

public class ParamTest {

	Param param1;

	private static String PARAM1_ID = "param1ID";

	@Before
	public void setUp() throws Exception {
		param1 = new Param(ID.create(PARAM1_ID), new IParamValue[] {});
	}

	@Test
	public void test() {

	}
}