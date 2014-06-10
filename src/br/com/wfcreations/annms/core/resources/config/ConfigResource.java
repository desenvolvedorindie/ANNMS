package br.com.wfcreations.annms.core.resources.config;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigResource {

	private static ConfigResource instance;

	private Config configuration;

	public static ConfigResource getInstance() {
		if (instance == null) {
			instance = new ConfigResource();
		}
		return instance;
	}

	private ConfigResource() {
	}

	public void init() throws ConfigurationException {
		try {
			this.configuration = new PropertiesConfigLoader().load();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Config getConfig() {
		return this.configuration;
	}
}
