/*
 * Copyright (c) 2013, Welsiton Ferreira (wfcreations@gmail.com)
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
package br.com.wfcreations.annms.core.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ANNMSHandler implements IServerHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ANNMSHandler.class);

	@Override
	public void preServe() {
		LOGGER.info("Listening for thrift clients...");
	}

	@Override
	public ServerContext createContext(TProtocol input, TProtocol output) {
		ThriftSessionManager.getInstance().setCurrentSocket(((TSocket) input.getTransport()).getSocket().getRemoteSocketAddress());
		return null;
	}

	@Override
	public void connect(String username, String password) throws AuthenticationException, TException {
		ThriftSessionManager.authenticate(username, password);
		ThriftSessionManager.getInstance().currentSession().setUsername(username);
		ThriftSessionManager.getInstance().currentSession().setPassword(password);
	}

	@Override
	public SQLANNResults execute(String query) throws AuthorizationException, TException {
		ThriftSessionManager.authenticate(ThriftSessionManager.getInstance().currentSession().getUsername(), ThriftSessionManager.getInstance().currentSession().getPassword());
		return new SQLANNResults("");
	}

	@Override
	public void deleteContext(ServerContext serverContext, TProtocol input, TProtocol output) {
		ThriftSessionManager.getInstance().complete(((TSocket) input.getTransport()).getSocket().getRemoteSocketAddress());
	}

	@Override
	public void processContext(ServerContext serverContext, TTransport inputTransport, TTransport outputTransport) {
		// NOTHING
	}
}
