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
package br.com.wfcreations.annms.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ANNMSDaemon {
	private static final Logger LOGGER = LoggerFactory.getLogger(ANNMSDaemon.class);

	private static final ANNMSDaemon instance = new ANNMSDaemon();

	// private Bootstrap bootstrap = new Bootstrap();

	public static ANNMSDaemon getInstance() {
		return instance;
	}

	public static void stop(String[] args) {
		instance.deactivate();
	}

	public static void start(String[] args) {
		instance.activate();
	}

	private Server thriftServer;

	protected void setup() {
		//thriftServer = new ThriftServer();
	}

	private void boostrap() {
		//bootstrap.run();
	}

	public void start() {
		thriftServer.start();
	}

	public void stop() {
		thriftServer.stop();
	}

	public void destroy() {
	}

	public void activate() {
		boostrap();
		setup();
		start();
	}

	public void deactivate() {
		stop();
		destroy();
	}

	public interface Server {
		public void start();

		public void stop();

		public boolean isRunning();
	}
}