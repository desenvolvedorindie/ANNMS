package br.com.wfcreations.annms.core.auth.adapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

import br.com.wfcreations.annms.core.auth.AuthenticationException;
import br.com.wfcreations.annms.core.auth.User;

public class FileAdapter implements AdapterInterface {

	protected String identity;

	protected String credential;

	protected String filePath;

	@Override
	public User authenticate() throws AuthenticationException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
			User[] users = (User[]) ois.readObject();
			ois.close();
			if (isUserAmbiguous(users))
				throw new AuthenticationException(AuthenticationException.USER_AMBIGUOUS, "Duplicated User");
			for (User user : users) {
				if (user.getUsername() == getIdentity()) {
					if (user.getPassword() == getCredential())
						return user;
					else
						throw new AuthenticationException(AuthenticationException.PASSWORD_INVALID, "Invalid Password");
				}
			}
			throw new AuthenticationException(AuthenticationException.USER_NOT_FOUND, "User not found");
		} catch (IOException e) {
			throw new AuthenticationException(AuthenticationException.FAILURE, "IOException", e);
		} catch (ClassNotFoundException e) {
			throw new AuthenticationException(AuthenticationException.FAILURE, "ClassNotFoundException", e);
		}
	}

	protected boolean isUserAmbiguous(User[] users) {
		Arrays.sort(users);
		for (int i = 1; i < users.length; i++)
			if (users[i].getUsername().equals(users[i - 1].getUsername()))
				return true;
		return false;
	}

	@Override
	public String getIdentity() {
		return identity;
	}

	@Override
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Override
	public String getCredential() {
		return credential;
	}

	@Override
	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
