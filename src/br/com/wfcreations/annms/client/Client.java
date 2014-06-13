package br.com.wfcreations.annms.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import br.com.wfcreations.annms.core.thrift.ANNMSService;

public class Client {

	public static void main(String[] args) {
		try {
			TTransport transport;

			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ANNMSService.Client client = new ANNMSService.Client(protocol);

			client.connect("admin", "12345");
			//client.execute("CREATE DATA iris (sepallenght REAL, sepalwidth REAL, petallenght REAL, petalwidth REAL, class {Iris-setosa,Iris-versicolor,Iris-virginica});");

			transport.close();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

}
