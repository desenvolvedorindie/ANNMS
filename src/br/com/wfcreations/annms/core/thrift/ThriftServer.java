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

import java.net.InetSocketAddress;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.api.thrift.ANNMSService;
import br.com.wfcreations.annms.core.ANNMS;
import br.com.wfcreations.annms.core.service.IServer;
import br.com.wfcreations.annms.core.thrift.TServerFactory.ServerType;

public class ThriftServer implements IServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThriftServer.class);

	private volatile ThriftServerThread server;

	public ThriftServer() {
	}

	@Override
	public void start() {
		if (server == null) {
			server = new ThriftServerThread();
			server.start();
		}
	}

	@Override
	public void stop() {
		if (server != null) {
			server.stopServer();
			try {
				server.join();
			} catch (InterruptedException e) {
				LOGGER.error("Interrupted while waiting thrift server to stop", e);
			}
			server = null;
		}
	}

	@Override
	public boolean isRunning() {
		return server != null;
	}

	private static class ThriftServerThread extends Thread {
		private TServer serverEngine;

		public ThriftServerThread() {
			TServerFactory.Args args = new TServerFactory.Args();
			args.transportProtocolFactory = new TBinaryProtocol.Factory(true, true);
			args.address = new InetSocketAddress(ANNMS.instance.configuration.thirft_port);
			args.handler = new ANNMSHandler();
			args.processor = new ANNMSService.Processor<>(args.handler);
			ServerType type = ANNMS.instance.configuration.thrift_server == "ThreadPoolServer" ? TServerFactory.ServerType.ThreadPoolServer : TServerFactory.ServerType.SimpleServer;
			serverEngine = TServerFactory.createServer(type, args);

			if (serverEngine == null)
				LOGGER.error("Thrift Server can't start");
			else
				LOGGER.info("Binding thrift service to localhost:{}", args.address.getPort());
		}

		public void run() {
			if (serverEngine != null)
				serverEngine.serve();
		}

		public void stopServer() {
			if (serverEngine != null) {
				LOGGER.info("Stop listening to thrift clients");
				serverEngine.stop();
			}
		}
	}
}