package br.com.wfcreations.annms.core.auth;

import br.com.wfcreations.annms.core.exception.ANNMSException;
import br.com.wfcreations.annms.core.exception.ANNMSExceptionCode;

public class AuthenticationException extends ANNMSException {

	public static final int FAILURE = 0;

	public static final int USER_NOT_FOUND = 1;

	public static final int USER_AMBIGUOUS = 2;

	public static final int PASSWORD_INVALID = 3;

	private static final long serialVersionUID = 1L;

	protected int code;

	public AuthenticationException(int code, String msg) {
		super(ANNMSExceptionCode.AUTHENTICATION, msg);
		this.code = code;
	}

	public AuthenticationException(int code, String msg, Throwable cause) {
		super(ANNMSExceptionCode.AUTHENTICATION, msg, cause);
		this.code = code;
	}
}
