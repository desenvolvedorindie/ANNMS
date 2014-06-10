package br.com.wfcreations.annms.core.resources;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.core.resources.config.ConfigResource;
import br.com.wfcreations.annms.core.resources.config.ConfigurationException;
import br.com.wfcreations.annms.core.resources.user.DefaultUserResource;

public class Bootstrap {

	private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

	public void run() {
		try {
			ConfigResource.getInstance().init();
		} catch (ConfigurationException e) {
			LOGGER.error("Configuration Init error, cause: " + e.getMessage());
		}

		DefaultUserResource.getInstance().init();
	}
}
