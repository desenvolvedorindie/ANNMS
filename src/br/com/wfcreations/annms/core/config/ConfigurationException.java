package br.com.wfcreations.annms.core.config;

import br.com.wfcreations.annms.core.exception.ANNMSException;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;

public class ConfigurationException extends ANNMSException {

	private static final long serialVersionUID = 1L;

	protected ConfigurationException(String msg) {
		super(ANNMSExceptionCode.CONFIG_ERROR, msg);
	}
	
	protected ConfigurationException(String msg, Throwable e) {
		super(ANNMSExceptionCode.CONFIG_ERROR, msg, e);
	}
}
