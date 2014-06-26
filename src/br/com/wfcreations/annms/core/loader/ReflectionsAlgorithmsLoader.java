package br.com.wfcreations.annms.core.loader;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.api.neuralnetwork.ILearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.INeuralNetwork;
import br.com.wfcreations.annms.api.neuralnetwork.ISupervisedLearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.IUnsupervisedLearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.LearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.NeuralNetwork;

public class ReflectionsAlgorithmsLoader implements IAlgorithmsLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionsAlgorithmsLoader.class);

	protected Map<String, Class<? extends INeuralNetwork>> neuralNetworksAlgorithms = new ConcurrentHashMap<String, Class<? extends INeuralNetwork>>();

	protected Map<String, Class<? extends ISupervisedLearningRule>> supervisedLearningRuleAlgorithms = new ConcurrentHashMap<String, Class<? extends ISupervisedLearningRule>>();

	protected Map<String, Class<? extends IUnsupervisedLearningRule>> unsupervisedLearningRuleAlgorithms = new ConcurrentHashMap<String, Class<? extends IUnsupervisedLearningRule>>();

	private Reflections reflections;

	public ReflectionsAlgorithmsLoader() {
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.addScanners(new SubTypesScanner());
		config.setUrls(ClasspathHelper.forClassLoader());
		reflections = new Reflections(config);
	}

	public void loadNeuralNetwoks() {
		neuralNetworksAlgorithms.clear();
		Set<Class<? extends INeuralNetwork>> networks = reflections.getSubTypesOf(INeuralNetwork.class);
		LOGGER.info("Total of neural networks models found: {}", networks.size());

		String id = null;
		Iterator<Class<? extends INeuralNetwork>> iteratorNetworks = networks.iterator();
		int loaded = 0;
		while (iteratorNetworks.hasNext()) {
			Class<? extends INeuralNetwork> neuralNetworkClass = iteratorNetworks.next();
			Annotation annotation = neuralNetworkClass.getAnnotation(NeuralNetwork.class);
			if (annotation == null) {
				LOGGER.warn("Neural Network annotation did not found for {}", neuralNetworkClass.getName());
				continue;
			}
			id = ((NeuralNetwork) annotation).value().toUpperCase();
			if (id == null || id == "") {
				LOGGER.warn("ID did not define for neural network model {}", neuralNetworkClass.getName());
				continue;
			} else if (!validID(id)) {
				LOGGER.warn("Invalid neural network model id for {}", neuralNetworkClass.getName());
				continue;
			} else if (neuralNetworksAlgorithms.containsKey(id)) {
				LOGGER.warn("{} neural network model already registered", id);
				continue;
			}
			neuralNetworksAlgorithms.put(id, neuralNetworkClass);
			LOGGER.info("Neural networks model {} loaded", id);
			loaded++;
			id = null;
		}
		LOGGER.info("Total neural networks model loaded: {}", loaded);
	}

	@SuppressWarnings("unchecked")
	public void loadLearningRules() {
		Set<Class<? extends ILearningRule>> learningRules = reflections.getSubTypesOf(ILearningRule.class);

		LOGGER.info("Total learning rules found {}", learningRules.size() - 2);

		String id = null;
		boolean supervised = false;
		int loaded = 0;
		Iterator<Class<? extends ILearningRule>> iteratorLearningRule = learningRules.iterator();
		while (iteratorLearningRule.hasNext()) {
			Class<? extends ILearningRule> learningRuleClass = iteratorLearningRule.next();
			if (learningRuleClass.isInterface())
				continue;
			try {
				learningRuleClass.asSubclass(ISupervisedLearningRule.class);
				supervised = true;
			} catch (ClassCastException e) {
				try {
					learningRuleClass.asSubclass(ISupervisedLearningRule.class);
					supervised = false;
				} catch (ClassCastException e2) {
					LOGGER.error("Invalid subtype class for learning rule {}", learningRuleClass.getName());
					continue;
				}
			}

			Annotation annotation = learningRuleClass.getAnnotation(LearningRule.class);
			id = ((LearningRule) annotation).value().toUpperCase();
			if (id == null || id == "") {
				LOGGER.warn("ID did not define for learning rule {}", learningRuleClass.getName());
				continue;
			} else if (!validID(id)) {
				LOGGER.warn("Invalid learning rule id for {}", learningRuleClass.getName());
				continue;
			} else if (supervised && supervisedLearningRuleAlgorithms.containsKey(id)) {
				LOGGER.warn("{} Supervised learning rule already registered", id);
				continue;
			} else if (!supervised && unsupervisedLearningRuleAlgorithms.containsKey(id)) {
				LOGGER.error("{} Unsupervised learning rule already registered", id);
			}

			if (supervised) {
				supervisedLearningRuleAlgorithms.put(id, (Class<? extends ISupervisedLearningRule>) learningRuleClass);
				LOGGER.info("Supervised Learning Rule {} loaded", id);
			} else {
				unsupervisedLearningRuleAlgorithms.put(id, (Class<? extends IUnsupervisedLearningRule>) learningRuleClass);
				LOGGER.info("Unsupervised Learning Rule {} loaded", id);
			}

			loaded++;
		}
		LOGGER.info("Total Learning Rules Loaded: {}", loaded);
	}

	private boolean validID(String id) {
		return true;
	}

	public INeuralNetwork createNeuralNetwork(String id) {
		INeuralNetwork neuralNetwork = null;
		if (neuralNetworksAlgorithms.containsKey(id.toUpperCase())) {
			Class<? extends INeuralNetwork> networkClass = neuralNetworksAlgorithms.get(id);
			try {
				neuralNetwork = networkClass.newInstance();
				return neuralNetwork;
			} catch (InstantiationException | IllegalAccessException e1) {
				LOGGER.error("Can't instantiate neural network for id {}", id);
			}
		}
		return neuralNetwork;
	}

	public ISupervisedLearningRule createSupervisedLearningRule(String id) {
		ISupervisedLearningRule supervisedLearninRule = null;
		if (supervisedLearningRuleAlgorithms.containsKey(id.toUpperCase())) {
			Class<? extends ISupervisedLearningRule> supervisedLearningRuleClass = supervisedLearningRuleAlgorithms.get(id);
			try {
				supervisedLearninRule = supervisedLearningRuleClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				LOGGER.error("Can't instantiate supervised learning rule for id {}", id);
			}
		}
		return supervisedLearninRule;
	}

	public IUnsupervisedLearningRule createUnsupervisedLearningRule(String id) {
		IUnsupervisedLearningRule unsupervisedLearninRule = null;
		if (supervisedLearningRuleAlgorithms.containsKey(id.toUpperCase())) {
			Class<? extends IUnsupervisedLearningRule> unsupervisedLearningRuleClass = unsupervisedLearningRuleAlgorithms.get(id);
			try {
				unsupervisedLearninRule = unsupervisedLearningRuleClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				LOGGER.error("Can't instantiate supervised learning rule for id {}", id);
			}
		}
		return unsupervisedLearninRule;
	}
}
