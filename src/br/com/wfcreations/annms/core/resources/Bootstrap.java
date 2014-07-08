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
package br.com.wfcreations.annms.core.resources;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.core.ANNMS;
import br.com.wfcreations.annms.core.auth.Auth;
import br.com.wfcreations.annms.core.auth.User;
import br.com.wfcreations.annms.core.config.ConfigurationException;
import br.com.wfcreations.annms.core.config.PropertiesConfigurationLoader;
import br.com.wfcreations.annms.core.service.AlgorithmsLoader;

public class Bootstrap extends Bootstrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

	public Bootstrap() {
		super();
	}

	@Override
	public void init() {
		try {
			ANNMS.instance.configuration = new PropertiesConfigurationLoader(ANNMS.instance.CONFIG_FILE_PATH).load();
		} catch (ConfigurationException e) {
			LOGGER.error("Configuration Init error, cause: " + e.getMessage());
		}
		initFilesAndFolders();
		initUsers();
		initData();
		initAlgorithms();
		initNeuralNetworks();
		initLearningRules();
	}

	protected void initFilesAndFolders() {
		File f = new File(ANNMS.instance.configuration.user_file);
		if (!f.exists() || f.isDirectory()) {
			try {
				if (f.createNewFile())
					LOGGER.info(String.format("Creating user file: (%s)", f.getAbsolutePath()));
			} catch (IOException e) {
				LOGGER.error(String.format("Can't create user file (%s)", f.getAbsolutePath()));
			}
		}

		f = new File(ANNMS.instance.configuration.data_path);
		if (!f.exists() || !f.isDirectory()) {
			if (f.mkdir())
				LOGGER.info(String.format("Created data folder  (%s)", f.getAbsolutePath()));
			else
				LOGGER.error(String.format("Can't create data folder (%s)", f.getAbsolutePath()));
		}

		f = new File(ANNMS.instance.configuration.neuralnetworks_path);
		if (!f.exists() || !f.isDirectory()) {
			if (f.mkdir())
				LOGGER.info(String.format("Created neuralnetworks folder: (%s)", f.getAbsolutePath()));
			else
				LOGGER.error(String.format("Can't create neuralnetworks folder (%s)", f.getAbsolutePath()));
		}
	}

	protected void initUsers() {
		Auth.admin = new User(ANNMS.instance.configuration.user_admin_username, ANNMS.instance.configuration.user_admin_password);
		LOGGER.info(String.format("Admin username: %s", ANNMS.instance.configuration.user_admin_username));
	}

	protected void initData() {

	}

	protected void initAlgorithms() {
		AlgorithmsLoader.instance.loadNeuralNetwoks();
		AlgorithmsLoader.instance.loadSupervisedLearningRules();
		AlgorithmsLoader.instance.loadUnsupervisedLearningRules();
	}

	protected void initNeuralNetworks() {

	}

	protected void initLearningRules() {

	}
}