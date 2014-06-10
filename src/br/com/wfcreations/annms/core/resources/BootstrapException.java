package br.com.wfcreations.annms.core.resources;

import br.com.wfcreations.annms.core.exception.ANNMSException;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;

public class BootstrapException extends ANNMSException {

	private static final long serialVersionUID = 1L;
	
	public BootstrapException(String msg) {
		super(ANNMSExceptionCode.BOOTSTRAPPING, msg);
	}

	public BootstrapException(String msg, Throwable e) {
		super(ANNMSExceptionCode.BOOTSTRAPPING, msg, e);
	}
}
