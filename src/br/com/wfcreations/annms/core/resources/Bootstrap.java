package br.com.wfcreations.annms.core.resources;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.ANNMS;
import br.com.wfcreations.annms.core.auth.Auth;
import br.com.wfcreations.annms.core.auth.User;
import br.com.wfcreations.annms.core.config.ConfigurationException;
import br.com.wfcreations.annms.core.config.PropertiesConfigLoader;

public class Bootstrap extends Bootstrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

	public Bootstrap() {
		super();
	}

	@Override
	public void init() {
		try {
			ANNMS.instance.configuration = new PropertiesConfigLoader(ANNMS.instance.CONFIG_FILE_PATH).load();
		} catch (ConfigurationException e) {
			LOGGER.error("Configuration Init error, cause: " + e.getMessage());
		}
		initFilesAndFolders();
		initUsers();
		initData();
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
		if (!f.exists() || !f.isAbsolute()) {
			if (f.mkdir())
				LOGGER.info(String.format("Created neuralnetworks folder: (%s)", f.getAbsolutePath()));
			else
				LOGGER.error(String.format("Can't create neuralnetworks folder (%s)", f.getAbsolutePath()));
		}
	}

	protected void initUsers() {
		Auth.admin = new User(ANNMS.instance.configuration.user_admin_username, ANNMS.instance.configuration.user_admin_password);
	}

	protected void initData() {
		
	}

	protected void initNeuralNetworks() {

	}
}