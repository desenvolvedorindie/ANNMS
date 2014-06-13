package br.com.wfcreations.annms.core.auth.adapter;

import br.com.wfcreations.annms.core.auth.AuthenticationException;
import br.com.wfcreations.annms.core.auth.User;

public interface AdapterInterface {

	public User authenticate() throws AuthenticationException;

	public String getIdentity();

	public void setIdentity(String identity);

	public String getCredential();

	public void setCredential(String credential);

}
