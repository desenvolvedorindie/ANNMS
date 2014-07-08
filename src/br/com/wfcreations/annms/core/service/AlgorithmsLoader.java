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
package br.com.wfcreations.annms.core.service;

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

import br.com.wfcreations.annms.api.neuralnetwork.INeuralNetwork;
import br.com.wfcreations.annms.api.neuralnetwork.ISupervisedLearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.IUnsupervisedLearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.LearningRule;
import br.com.wfcreations.annms.api.neuralnetwork.NeuralNetwork;

public class AlgorithmsLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlgorithmsLoader.class);

	public static final AlgorithmsLoader instance = new AlgorithmsLoader();

	protected Map<String, Class<? extends INeuralNetwork>> neuralNetworksAlgorithms = new ConcurrentHashMap<String, Class<? extends INeuralNetwork>>();

	protected Map<String, Class<? extends ISupervisedLearningRule>> supervisedLearningRuleAlgorithms = new ConcurrentHashMap<String, Class<? extends ISupervisedLearningRule>>();

	protected Map<String, Class<? extends IUnsupervisedLearningRule>> unsupervisedLearningRuleAlgorithms = new ConcurrentHashMap<String, Class<? extends IUnsupervisedLearningRule>>();

	private Reflections reflections;

	private AlgorithmsLoader() {
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.addScanners(new SubTypesScanner());
		config.setUrls(ClasspathHelper.forClassLoader());
		reflections = new Reflections(config);
	}

	public void loadNeuralNetwoks() {
		neuralNetworksAlgorithms.clear();
		Set<Class<? extends INeuralNetwork>> networks = reflections.getSubTypesOf(INeuralNetwork.class);

		String id = null;
		Iterator<Class<? extends INeuralNetwork>> iteratorNetworks = networks.iterator();
		int loaded = 0;
		while (iteratorNetworks.hasNext()) {
			Class<? extends INeuralNetwork> neuralNetworkClass = iteratorNetworks.next();
			if (neuralNetworkClass.isInterface())
				continue;

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

	public void loadSupervisedLearningRules() {
		supervisedLearningRuleAlgorithms.clear();

		Set<Class<? extends ISupervisedLearningRule>> learningRules = reflections.getSubTypesOf(ISupervisedLearningRule.class);

		String id = null;
		int loaded = 0;
		Iterator<Class<? extends ISupervisedLearningRule>> iteratorLearningRule = learningRules.iterator();
		while (iteratorLearningRule.hasNext()) {
			Class<? extends ISupervisedLearningRule> learningRuleClass = iteratorLearningRule.next();
			if (learningRuleClass.isInterface())
				continue;

			Annotation annotation = learningRuleClass.getAnnotation(LearningRule.class);
			id = ((LearningRule) annotation).value().toUpperCase();
			if (id == null || id == "") {
				LOGGER.warn("ID did not define for learning rule {}", learningRuleClass.getName());
				continue;
			} else if (!validID(id)) {
				LOGGER.warn("Invalid learning rule id for {}", learningRuleClass.getName());
				continue;
			} else if (supervisedLearningRuleAlgorithms.containsKey(id)) {
				LOGGER.error("{} supervised learning rule already registered", id);
			}

			supervisedLearningRuleAlgorithms.put(id, (Class<? extends ISupervisedLearningRule>) learningRuleClass);
			LOGGER.info("Supervised Learning Rule {} loaded", id);
			loaded++;
		}
		LOGGER.info("Total Supervised Learning Rules Loaded: {}", loaded);
	}

	public void loadUnsupervisedLearningRules() {
		unsupervisedLearningRuleAlgorithms.clear();

		Set<Class<? extends IUnsupervisedLearningRule>> learningRules = reflections.getSubTypesOf(IUnsupervisedLearningRule.class);

		String id = null;
		int loaded = 0;
		Iterator<Class<? extends IUnsupervisedLearningRule>> iteratorLearningRule = learningRules.iterator();
		while (iteratorLearningRule.hasNext()) {
			Class<? extends IUnsupervisedLearningRule> learningRuleClass = iteratorLearningRule.next();
			if (learningRuleClass.isInterface())
				continue;

			Annotation annotation = learningRuleClass.getAnnotation(LearningRule.class);
			id = ((LearningRule) annotation).value().toUpperCase();
			if (id == null || id == "") {
				LOGGER.warn("ID did not define for learning rule {}", learningRuleClass.getName());
				continue;
			} else if (!validID(id)) {
				LOGGER.warn("Invalid learning rule id for {}", learningRuleClass.getName());
				continue;
			} else if (supervisedLearningRuleAlgorithms.containsKey(id)) {
				LOGGER.error("{} unsupervised learning rule already registered", id);
			}

			unsupervisedLearningRuleAlgorithms.put(id, (Class<? extends IUnsupervisedLearningRule>) learningRuleClass);
			LOGGER.info("Unsupervised Learning Rule {} loaded", id);
			loaded++;
		}
		LOGGER.info("Total Unsupervised Learning Rules Loaded: {}", loaded);
	}

	private boolean validID(String id) {
		return true;
	}

	public INeuralNetwork instantiateNeuralNetwork(String id) {
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

	public ISupervisedLearningRule instantiateSupervisedLearningRule(String id) {
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

	public IUnsupervisedLearningRule instantiateUnsupervisedLearningRule(String id) {
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