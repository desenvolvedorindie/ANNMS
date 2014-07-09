package br.com.wfcreations.annms.test.api.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.Param;
import br.com.wfcreations.annms.api.data.values.ID;
import br.com.wfcreations.annms.api.data.values.IParamValue;

public class ParamTest {

	Param param1;

	private static String PARAM1_ID = "param1ID";

	@Before
	public void setUp() throws Exception {
		param1 = new Param(new ID(PARAM1_ID), new IParamValue[] {});
	}

	@Test
	public void test() {

	}

}
