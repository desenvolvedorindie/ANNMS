package br.com.wfcreations.annms.client.config;

public interface IClientConfigurationLoader {

	public ClientConfiguration load() throws ClientConfigurationException;
}
