package br.com.wfcreations.annms.client.config;

public class ClientConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;

	protected ClientConfigurationException(String msg) {
		super(msg);
	}

	protected ClientConfigurationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
