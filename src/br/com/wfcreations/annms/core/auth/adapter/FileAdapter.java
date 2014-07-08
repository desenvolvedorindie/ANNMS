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
package br.com.wfcreations.annms.core.auth.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

import br.com.wfcreations.annms.core.auth.AuthenticationException;
import br.com.wfcreations.annms.core.auth.AuthenticationException.ErrorType;
import br.com.wfcreations.annms.core.auth.User;

public class FileAdapter implements AdapterInterface {

	protected String identity;

	protected String credential;

	protected String filePath;

	@Override
	public synchronized User authenticate() throws AuthenticationException {
		try {
			File file = new File(filePath);
			if (!file.exists())
				throw new AuthenticationException(ErrorType.USER_NOT_FOUND, "User not found");
			FileInputStream fis = new FileInputStream(file);
			if (fis.available() > 0) {
				try (ObjectInputStream ois = new ObjectInputStream(fis)) {
					User[] users = (User[]) ois.readObject();
					if (isUserAmbiguous(users))
						throw new AuthenticationException(ErrorType.USER_AMBIGUOUS, "Duplicated User");
					for (User user : users) {
						if (user.getUsername() == getIdentity()) {
							if (user.getPassword() == getCredential()) {
								return user;
							} else {
								throw new AuthenticationException(ErrorType.PASSWORD_INVALID, "Invalid Password");
							}
						}
					}
				}
			} else {
				throw new AuthenticationException(ErrorType.USER_NOT_FOUND, "No users");
			}
			throw new AuthenticationException(ErrorType.USER_NOT_FOUND, "User not found");
		} catch (IOException e) {
			throw new AuthenticationException(ErrorType.FAILURE, "IOException", e);
		} catch (ClassNotFoundException e) {
			throw new AuthenticationException(ErrorType.FAILURE, "ClassNotFoundException", e);
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