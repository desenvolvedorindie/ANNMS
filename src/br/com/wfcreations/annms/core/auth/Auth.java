package br.com.wfcreations.annms.core.auth;

import br.com.wfcreations.annms.core.auth.adapter.AdapterInterface;

public class Auth {

	private static Auth instance;

	public static Auth getInstance() {
		if (instance == null) {
			instance = new Auth();
		}
		return instance;
	}

	protected User admin;

	private Auth() {
	}

	public User authenticate(AdapterInterface adapter) throws AuthenticationException {
		return adapter.authenticate();
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}
}
