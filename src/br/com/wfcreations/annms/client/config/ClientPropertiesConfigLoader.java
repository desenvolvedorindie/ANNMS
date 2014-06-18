package br.com.wfcreations.annms.client.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;

public class ClientPropertiesConfigLoader implements IClientConfigurationLoader {

	public final String PREFIX = "client";

	public final String HOST_KEY = "host";

	public final String PORT_KEY = "port";

	public final String USER_USERNAME_KEY = "user.username";

	public final String USER_PASSWORD_KEY = "user.password";

	protected String path;

	public ClientPropertiesConfigLoader(String path) {
		this.path = path;
	}

	@Override
	public ClientConfiguration load() throws ClientConfigurationException {
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(path));
		} catch (IOException e) {
			throw new ClientConfigurationException(e.getMessage(), e);
		}

		ClientConfiguration config = new ClientConfiguration();

		Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<Object, Object> element = iterator.next();
			String key = (String) element.getKey();
			String value = (String) element.getValue();

			if (key.equals(PREFIX + "." + HOST_KEY)) {
				config.host = value;
			} else if (key.equals(PREFIX + "." + PORT_KEY)) {
				try {
					config.port = Integer.parseInt(value);
				} catch (NumberFormatException e) {
					throw new ClientConfigurationException(String.format("Invalid %s format", key), e);
				}
			} else if (key.equals(PREFIX + "." + USER_USERNAME_KEY)) {
				config.user_username = value;
			} else if (key.equals(PREFIX + "." + USER_PASSWORD_KEY)) {
				config.user_password = value;
			} else {
				throw new ClientConfigurationException("Invalid " + element.getKey() + " property with value " + value);
			}
		}

		return config;
	}

}
