package br.com.wfcreations.annms.core.service;

import java.util.UUID;

public class QueryState {

	ClientState client;

	private volatile long clock;

	private volatile UUID preparedTracingSession;

	public QueryState(ClientState client) {
		this.client = client;
	}

	public long getTimestamp() {
		long current = System.currentTimeMillis() * 1000;
		clock = clock >= current ? clock + 1 : current;
		return clock;
	}

	public void prepareTracingSession(UUID sessionId) {
		this.preparedTracingSession = sessionId;
	}
}
