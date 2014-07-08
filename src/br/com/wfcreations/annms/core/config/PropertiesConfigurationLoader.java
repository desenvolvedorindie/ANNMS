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
package br.com.wfcreations.annms.core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesConfigurationLoader implements IConfigurationLoader {

	public final String PREFIX = "annms";

	public final String TIMEOUT_KEY = "timeout";

	public final String USER_FILE_KEY = "user.file";

	public final String USER_MAX_KEY = "user.max";

	public final String USER_ADMIN_USERNAME_KEY = "user.admin.username";

	public final String USER_ADMIN_PASSWORD_KEY = "user.admin.password";

	public final String THRIFT_SERVER_KEY = "thrift.server";

	public final String THRIFT_SERVER_SIMPLE = "SimpleServer";

	public final String THRIFT_SERVER_THREADPOOL = "ThreadPoolServer";

	public final String THRIFT_PORT_KEY = "thrift.port";

	public final String DATA_PATH = "data.path";

	public final String NEURALNETWORKS_PATH = "neuralnetworks.path";

	private String path;

	public PropertiesConfigurationLoader(String path) {
		this.path = path;
	}

	@Override
	public Config load() throws ConfigurationException {
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(path));
		} catch (IOException e) {
			throw new ConfigurationException(e.getMessage(), e);
		}

		Config config = new Config();

		Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<Object, Object> element = iterator.next();
			String key = (String) element.getKey();
			String value = (String) element.getValue();

			if (key.equals(PREFIX + "." + TIMEOUT_KEY)) {
				try {
					config.timeout = Integer.parseInt(value);
				} catch (NumberFormatException e) {
					throw new ConfigurationException(String.format("Invalid %s format", key), e);
				}
			} else if (key.equals(PREFIX + "." + THRIFT_SERVER_KEY)) {
				if (value.equals(THRIFT_SERVER_SIMPLE) || value.equals(THRIFT_SERVER_THREADPOOL))
					config.thrift_server = value;
				else
					throw new ConfigurationException(String.format("%s is a invalid Thrift server type", value));
			} else if (key.equals(PREFIX + "." + THRIFT_PORT_KEY)) {
				try {
					config.thirft_port = Integer.parseInt(value);
				} catch (NumberFormatException e) {
					throw new ConfigurationException(String.format("Invalid %s format", key), e);
				}
			} else if (key.equals(PREFIX + "." + USER_FILE_KEY)) {
				config.user_file = value;
			} else if (key.equals(PREFIX + "." + USER_MAX_KEY)) {
				try {
					config.user_max = Integer.parseInt(value);
				} catch (NumberFormatException e) {
					throw new ConfigurationException(String.format("Invalid %s format", key), e);
				}
			} else if (key.equals(PREFIX + "." + USER_ADMIN_USERNAME_KEY)) {
				config.user_admin_username = value;
			} else if (key.equals(PREFIX + "." + USER_ADMIN_PASSWORD_KEY)) {
				config.user_admin_password = value;
			} else if (key.equals(PREFIX + "." + DATA_PATH)) {
				config.data_path = value;
			} else if (key.equals(PREFIX + "." + NEURALNETWORKS_PATH)) {
				config.neuralnetworks_path = value;
			} else {
				throw new ConfigurationException("Invalid " + element.getKey() + " property with value " + value);
			}
		}
		return config;
	}
}