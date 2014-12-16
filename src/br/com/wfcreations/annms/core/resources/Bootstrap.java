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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.api.data.Data;
import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.core.ANNMS;
import br.com.wfcreations.annms.core.auth.Auth;
import br.com.wfcreations.annms.core.auth.User;
import br.com.wfcreations.annms.core.config.ConfigurationException;
import br.com.wfcreations.annms.core.config.PropertiesConfigurationLoader;
import br.com.wfcreations.annms.core.neuralnetwork.NeuralnetworkWrapper;
import br.com.wfcreations.annms.core.service.AlgorithmsLoader;
import br.com.wfcreations.annms.core.service.Schema;
import br.com.wfcreations.annms.core.service.StorageService;

public class Bootstrap extends Bootstrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

	private static final String DATA_EXT = "DATA";

	private static final String NETWORK_EXT = "NET";

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
		String[] dataList = StorageService.listFiles(ANNMS.instance.configuration.data_path, DATA_EXT);
		for (String file : dataList) {
			try {
				LOGGER.info("Loading {} file...", file, DATA_EXT);
				FileInputStream readFile = new FileInputStream(ANNMS.instance.configuration.data_path + File.separator + file);
				ObjectInputStream restore = new ObjectInputStream(readFile);
				Object obj = restore.readObject();
				Schema.instance.storeDataInstance((Data) obj);
				restore.close();
			} catch (IOException | ClassNotFoundException e) {
				LOGGER.info("Error to load {}.{} file, cause: {}", file, DATA_EXT, e.getMessage());
				continue;
			}
			LOGGER.info("{} loaded", file, DATA_EXT);
		}
	}

	protected void initAlgorithms() {
		AlgorithmsLoader.instance.loadNeuralNetwoks();
		AlgorithmsLoader.instance.loadSupervisedLearningRules();
		AlgorithmsLoader.instance.loadUnsupervisedLearningRules();
	}

	protected void initNeuralNetworks() {
		String[] dataList = StorageService.listFiles(ANNMS.instance.configuration.neuralnetworks_path, NETWORK_EXT);
		for (String file : dataList) {
			try {
				LOGGER.info("Loading {} file...", file, NETWORK_EXT);
				FileInputStream readFile = new FileInputStream(ANNMS.instance.configuration.neuralnetworks_path + File.separator + file);
				ObjectInputStream restore = new ObjectInputStream(readFile);
				Object obj = restore.readObject();
				Schema.instance.storeNeuralnetworkInstance((NeuralnetworkWrapper) obj);
				restore.close();
			} catch (IOException | ClassNotFoundException e) {
				LOGGER.info("Error to load {}.{} file, cause: {}", file, NETWORK_EXT, e.getMessage());
				continue;
			}
			LOGGER.info("{} loaded", file, NETWORK_EXT);
		}
	}

	@Override
	public void finish() {
		saveData();
		saveModels();
	}

	protected void saveData() {
		try {
			FileUtils.cleanDirectory(new File(ANNMS.instance.configuration.data_path));
		} catch (IOException e1) {
			LOGGER.info("Error to clean data folder");
		}
		ID[] ids = Schema.instance.getDataIDs();
		for (ID id : ids) {
			LOGGER.info("Saving {}.{} file...", id.getValue(), DATA_EXT);
			try {
				FileOutputStream saveFile = new FileOutputStream(ANNMS.instance.configuration.data_path + File.separator + id.getValue() + "." + DATA_EXT);
				ObjectOutputStream save = new ObjectOutputStream(saveFile);
				save.writeObject(Schema.instance.getDataInstance(id));
				save.close();
			} catch (IOException e) {
				LOGGER.info("Error to save {}.{} file, cause: {}", id.getValue(), DATA_EXT, e.getMessage());
				continue;
			}
			LOGGER.info("{}.{} saved", id.getValue(), DATA_EXT);
		}
	}

	protected void saveModels() {
		try {
			FileUtils.cleanDirectory(new File(ANNMS.instance.configuration.neuralnetworks_path));
		} catch (IOException e1) {
			LOGGER.info("Error to clean data folder");
		}
		ID[] ids = Schema.instance.getNeuralnetworksIDs();
		for (ID id : ids) {
			LOGGER.info("Saving {}.{} file...", id.getValue(), NETWORK_EXT);
			try {
				FileOutputStream saveFile = new FileOutputStream(ANNMS.instance.configuration.neuralnetworks_path + File.separator + id.getValue() + "." + NETWORK_EXT);
				ObjectOutputStream save = new ObjectOutputStream(saveFile);
				save.writeObject(Schema.instance.getNeuralnetworkInstance(id));
				save.close();
			} catch (IOException e) {
				LOGGER.info("Error to save {}.{} file, cause: {}", id.getValue(), DATA_EXT, e.getMessage());
				continue;
			}
			LOGGER.info("{}.{} saved", id.getValue(), DATA_EXT);
		}
	}
}