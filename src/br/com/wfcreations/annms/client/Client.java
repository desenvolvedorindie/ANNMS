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
package br.com.wfcreations.annms.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wfcreations.annms.api.thrift.ANNMSConstants;
import br.com.wfcreations.annms.api.thrift.ANNMSService;
import br.com.wfcreations.annms.api.thrift.AuthorizationException;
import br.com.wfcreations.annms.api.thrift.SQLANNResults;
import br.com.wfcreations.annms.api.thrift.TimedOutException;
import br.com.wfcreations.annms.client.config.ClientConfiguration;
import br.com.wfcreations.annms.client.config.ClientPropertiesConfigLoader;

public class Client {

	public static final short MAJOR = 1;

	public static final short MINOR = 0;

	public static final short REVISION = 0;

	public static String VERSION = String.format("%s.%s.%s", MAJOR, MINOR, REVISION);

	public static String CONFIG_FILE_PATH = "conf/client.config";

	static TTransport transport;

	static ANNMSService.Client client;

	public static ClientConfiguration configuration;

	public static void connect(String host, int port) {
		if (transport != null) {
			transport.close();
		}
		transport = new TSocket(host, port);
		try {
			transport.open();
		} catch (TTransportException e) {
			String error = (e.getCause() == null) ? e.getMessage() : e.getCause().getMessage();
			throw new RuntimeException("Exception connecting to " + host + ":" + port + ". " + error + ".");
		}
		TProtocol protocol = new TBinaryProtocol(transport);
		client = new ANNMSService.Client.Factory().getClient(protocol);
	}

	public static void disconnect() {
		if (transport != null) {
			transport.close();
			transport = null;
			client = null;
		}
	}

	public static boolean isConnected() {
		return client != null;
	}

	public static void main(String[] args) {
		try {
			System.out.println("Starting ANNMS Client");

			configuration = new ClientPropertiesConfigLoader(CONFIG_FILE_PATH).load();

			connect(configuration.host, configuration.port);

			client.connect(configuration.user_username, configuration.user_password);

			System.out.println(String.format("Connected to %s:%s", configuration.host, configuration.port));

			System.out.println(String.format("Welcome to ANNMS CLI version %s (%s)", VERSION, ANNMSConstants.VERSION));

			SQLANNResults result = client.execute("SHOW STATUS");

			ObjectMapper mapper = new ObjectMapper();
			JsonNode showStatus = mapper.readTree(result.data).get(0).get("SHOW STATUS");

			String severVersion = showStatus.get("SERVER_VERSION").asText();
			String sqlannVersion = showStatus.get("SQLANN_VERSION").asText();
			String apiVersion = showStatus.get("API_VERSION").asText();
			String apiThrift = showStatus.get("API_THRIFT").asText();
			System.out.println(String.format("Server Version: %s (API: %s / Thrift: %s) ", severVersion, apiVersion, apiThrift));
			System.out.println(String.format("SQLANN Version: %s", sqlannVersion));

			System.out.println(String.format("%s@%s", configuration.user_username, "localhost"));

			printPrompt();

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line;
			StringBuilder sb = new StringBuilder();
			while (true) {
				try {
					line = br.readLine();
					if (line.isEmpty()) {
						try {
							result = client.execute(sb.toString());
							mapper = new ObjectMapper();
							showStatus = mapper.readTree(result.data);
							String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(showStatus);
							System.out.println(json);
						} catch (AuthorizationException e1) {
							System.out.println(e1.getMessage());
						} catch (TimedOutException e2) {
							System.out.println(e2.getMessage());
						}
						sb.setLength(0);
						printPrompt();
					} else {
						sb.append(line);
						sb.append('\n');
					}
				} catch (IOException ioe) {
					System.exit(1);
				}
			}
		} catch (Exception e) {
			disconnect();
			System.out.println(e.getMessage());
		}
	}

	private static void printPrompt() {
		System.out.println();
		System.out.print("sqlann> ");
	}
}
