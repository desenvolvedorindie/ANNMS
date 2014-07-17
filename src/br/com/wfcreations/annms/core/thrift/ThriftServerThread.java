package br.com.wfcreations.annms.core.thrift;

import java.net.InetSocketAddress;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.wfcreations.annms.api.thrift.ANNMSService;
import br.com.wfcreations.annms.core.thrift.TServerFactory.ServerType;

class ThriftServerThread extends Thread {
	private TServer serverEngine;

	private static final Logger LOGGER = LoggerFactory.getLogger(ThriftServerThread.class);

	public ThriftServerThread(String thriftServer, int port) {
		TServerFactory.Args args = new TServerFactory.Args();
		args.transportProtocolFactory = new TBinaryProtocol.Factory(true, true);
		args.address = new InetSocketAddress(port);
		args.handler = new ANNMSHandler();
		args.processor = new ANNMSService.Processor<>(args.handler);
		ServerType type = thriftServer == "ThreadPoolServer" ? TServerFactory.ServerType.ThreadPoolServer : TServerFactory.ServerType.SimpleServer;
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