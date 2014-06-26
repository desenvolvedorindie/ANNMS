package br.com.wfcreations.annms.test;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wfcreations.annms.api.data.Param;
import br.com.wfcreations.annms.api.neuralnetwork.INeuralNetwork;
import br.com.wfcreations.annms.api.neuralnetwork.ISupervisedLearningRule;
import br.com.wfcreations.annms.core.loader.IAlgorithmsLoader;
import br.com.wfcreations.annms.core.loader.ReflectionsAlgorithmsLoader;

public class LoaderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		IAlgorithmsLoader loader = new ReflectionsAlgorithmsLoader();
		loader.loadNeuralNetwoks();
		loader.loadLearningRules();
		INeuralNetwork network = loader.createNeuralNetwork("MLP");
		if (network != null) {
			network.create(new Param[0]);
		} else {
			System.out.println("Neural Network Model not found");
		}
		ISupervisedLearningRule supervisedLearninRule = loader.createSupervisedLearningRule("BACKPROPAGATION");
		if (supervisedLearninRule != null) {
			supervisedLearninRule.create(new Param[0]);
			supervisedLearninRule.train(network, null, null, null);
		} else {
			System.out.println("Learnin Rule not found");
		}
	}
}