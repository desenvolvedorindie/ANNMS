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

import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

import br.com.wfcreations.annms.api.thrift.ANNMSService;

public class TServerFactory {

	public static class Args {
		public InetSocketAddress address;
		public IServerHandler handler;
		public ANNMSService.Processor<?> processor;
		public TProtocolFactory transportProtocolFactory;
		public TTransportFactory inTransportFactory;
		public TTransportFactory outTransportFactory;
	}

	public enum ServerType {
		SimpleServer, ThreadPoolServer
	}

	public static TServer createServer(ServerType type, Args args) {
		try {
			TServer server;
			TServerTransport serverTransport = new TServerSocket(args.address);
			if (type == ServerType.ThreadPoolServer) {
				TThreadPoolServer.Args arg = new TThreadPoolServer.Args(serverTransport).processor(args.processor);
				server = new TThreadPoolServer(arg);
				server.setServerEventHandler(args.handler);
				return server;
			} else {
				TServer.Args arg = new TServer.Args(serverTransport).processor(args.processor);
				server = new TSimpleServer(arg);
				server.setServerEventHandler(args.handler);
				return server;
			}
		} catch (TTransportException e) {
		}
		return null;
	}
}
