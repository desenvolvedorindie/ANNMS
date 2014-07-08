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
