package br.com.wfcreations.annms.core.thrift;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.wfcreations.annms.api.thrift.AuthenticationException;
import br.com.wfcreations.annms.core.ANNMS;
import br.com.wfcreations.annms.core.auth.Auth;
import br.com.wfcreations.annms.core.auth.User;
import br.com.wfcreations.annms.core.auth.adapter.FileAdapter;
import br.com.wfcreations.annms.core.service.ClientState;

public class ThriftSessionManager {
	private static ThriftSessionManager instance;

	public static ThriftSessionManager getInstance() {
		if (instance == null) {
			instance = new ThriftSessionManager();
		}
		return instance;
	}

	private final ThreadLocal<SocketAddress> remoteSocket = new ThreadLocal<SocketAddress>();

	private final Map<SocketAddress, ClientState> activeSocketSessions = new ConcurrentHashMap<SocketAddress, ClientState>();

	public void setCurrentSocket(SocketAddress socket) {
		remoteSocket.set(socket);
	}
	
	public SocketAddress getCurrentSocket() {
		return remoteSocket.get();
	}


	public ClientState currentSession() {
		SocketAddress socket = remoteSocket.get();
		ClientState clientState = activeSocketSessions.get(socket);
		if (clientState == null) {
			clientState = new ClientState();
			activeSocketSessions.put(socket, clientState);
		}
		return clientState;
	}

	public void complete(SocketAddress socket) {
		activeSocketSessions.remove(socket);
		remoteSocket.remove();
	}

	public int getConnectedClients() {
		return activeSocketSessions.size();
	}

	public static User authenticate(String username, String password) throws AuthenticationException {
		FileAdapter adapter = new FileAdapter();
		adapter.setIdentity(username);
		adapter.setCredential(password);
		adapter.setFilePath(ANNMS.instance.configuration.user_file);
		try {
			return Auth.getInstance().authenticate(adapter);
		} catch (br.com.wfcreations.annms.core.auth.AuthenticationException e) {
			throw new AuthenticationException(e.getType().ordinal(), e.getMessage());
		}
	}
}