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

import br.com.wfcreations.annms.core.resources.Application;
import br.com.wfcreations.annms.core.resources.Bootstrap;
import br.com.wfcreations.annms.core.resources.Bootstrapper;

public class ANNMSDaemon extends Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(ANNMSDaemon.class);

	private static final ANNMSDaemon instance = new ANNMSDaemon(new Bootstrap());
	
	protected Thread shutdownThread = new Thread() {
		@Override
		public void run() {
			instance.deactivate(null);
		}
	};

	protected String[] args;

	private ANNMSDaemon(Bootstrapper bootstrap) {
		super(bootstrap);
	}

	public static ANNMSDaemon getInstance() {
		return instance;
	}

	public static void stop(String[] args) {
		instance.deactivate(args);
	}

	public static void main(String[] args) {
		instance.activate(args);
	}

	private Server thriftServer;

	protected void boostrap() {
		this.bootstrap.run();
	}

	protected void setup() {
		// thriftServer = new ThriftServer();
	}

	public void start() {
		// thriftServer.start();
	}

	public void stop() {
		// thriftServer.stop();
	}

	public void destroy() {
		LOGGER.info("BYE");
	}

	public void activate(String[] args) {
		boostrap();
		setup();
		start();
		Runtime.getRuntime().addShutdownHook(shutdownThread);
	}

	public void deactivate(String[] args) {
		stop();
		destroy();
	}

	public interface Server {
		public void start();

		public void stop();

		public boolean isRunning();
	}
}