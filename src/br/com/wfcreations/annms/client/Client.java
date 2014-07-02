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

import br.com.wfcreations.annms.api.thrift.ANNMSService;
import br.com.wfcreations.annms.api.thrift.AuthorizationException;
import br.com.wfcreations.annms.api.thrift.SQLANNResults;
import br.com.wfcreations.annms.api.thrift.TimedOutException;
import br.com.wfcreations.annms.client.config.ClientConfiguration;
import br.com.wfcreations.annms.client.config.ClientPropertiesConfigLoader;

public class Client {

	public static String VERSION = "1.0.0-dev";

	public static String CONFIG_FILE_PATH = "conf/client.config";

	static TTransport transport;

	static ANNMSService.Client client;

	public static ClientConfiguration configuration;

	public static void connect(String host, int port) {
		if (transport != null) {
			transport.close();
		}
		transport = new TSocket("localhost", 9090);
		try {
			transport.open();
		} catch (TTransportException e) {
			String error = (e.getCause() == null) ? e.getMessage() : e.getCause().getMessage();
			throw new RuntimeException("Exception connecting to " + host + ":" + port + ". " + error + ".");
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new ANNMSService.Client(protocol);
	}

	public static void disconnect() {
		if (transport != null) {
			transport.close();
			transport = null;
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

			System.out.println(String.format("Welcome to ANNMS CLI version %s", VERSION));
			
			SQLANNResults result = client.execute("SHOW STATUS");
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(result.data);
			
			String severVersion = actualObj.get(0).get("SHOW STATUS").get("SERVER_VERSION").asText();
			String apiVersion = actualObj.get(0).get("SHOW STATUS").get("API_VERSION").asText();
			System.out.println(String.format("Server Version: %s (%s)", severVersion, apiVersion));
			
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
							actualObj = mapper.readTree(result.data);
							String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(actualObj);
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
