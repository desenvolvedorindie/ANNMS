/*
 * Copyright (c) 2013, Welsiton Ferreira (wfcreations@gmail.com)
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
package br.com.wfcreations.annms.core.resources.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesConfigLoader implements IConfigLoader {

    public final String FILE_EXTENSION = ".properties";
    
    public final String FILE_NAME = "annms";
    
    public final String DEFAULT_PATH = FILE_NAME + FILE_EXTENSION;

    public final String PREFIX = "annms";

    private String path = DEFAULT_PATH;

    public PropertiesConfigLoader() {
    }

    public PropertiesConfigLoader(String path) {
	this.path = path;
    }

    @Override
    public Config load() throws FileNotFoundException, IOException, ConfigurationException {
	Properties properties = new Properties();
	
	properties.load(new FileInputStream(path));

	Config config = new Config();

	Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();

	while (iterator.hasNext()) {
	    Entry<Object, Object> element = iterator.next();
	    String key = (String) element.getKey();
	    String value = (String) element.getValue();
	    if (key.equals(PREFIX + ".port")) {
		try {
		    config.port = Integer.parseInt(value);
		}
		catch (NumberFormatException e) {
		    throw new ConfigurationException("Invalid " + key + " format", e);
		}
	    }
	    else if (key.equals(PREFIX + ".root.username")) {
		String root_username = value;
		if (!root_username.matches("[a-zA-Z$][a-zA-Z0-9$_[-]]*")) {
		    throw new ConfigurationException("Invalid " + key + " format");
		}
		else {
		    config.root_username = root_username;
		}
	    }
	    else if (key.equals(PREFIX + ".root.password")) {
		config.root_password = value;
	    }
	    else if (key.equals(PREFIX + ".usersfile")) {
		config.usersfile = value;
	    }
	    else if (key.equals(PREFIX + ".maxuser")) {
		try {
		    config.maxuser = Integer.parseInt(value);
		}
		catch (NumberFormatException e) {
		    throw new ConfigurationException("Invalid " + key + "format", e);
		}
	    }
	    else if (key.equals(PREFIX + ".timeout")) {
		try {
		    config.timeout = Integer.parseInt(value);
		}
		catch (NumberFormatException e) {
		    throw new ConfigurationException("Invalid " + key + "format", e);
		}
	    }
	    else if (key.equals(PREFIX + ".thrift.server")) {
		if (value.equals("SimpleServer")) {
		    config.thrift_server = "SimpleServer";
		}
		else if (value.equals("ThreadPoolServer")) {
		    config.thrift_server = "ThreadPoolServer";
		}
		else {
		    throw new ConfigurationException(value + " is a invalid Thrift server type");
		}
	    }
	    else if (key.equals(PREFIX + ".data.path")) {
		File file = new File(value);
		if (!file.isDirectory()) {
		    throw new ConfigurationException("'" + file.getAbsolutePath() + "' is not a valid directory");
		}
		else {
		    config.data_path = value;
		}
	    }
	    else if (key.equals(PREFIX + ".neuralnetworks.path")) {
		File file = new File(value);
		if (!file.isDirectory()) {
		    throw new ConfigurationException("'" + file.getAbsolutePath() + "' is not a valid directory");
		}
		else {
		    config.data_path = value;
		}
	    }
	    else {
		throw new ConfigurationException("Invalid " + element.getKey() + " property with value " + value);
	    }
	}
	return config;
    }
}