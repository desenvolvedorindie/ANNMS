package br.com.wfcreations.annms.core.resources;

public abstract class Resource {

	private Bootstrapper bootstrap;

	public Bootstrapper getBootstrap() {
		return bootstrap;
	}

	public void setBootstrap(Bootstrapper bootstrap) {
		this.bootstrap = bootstrap;
	}
	
	public abstract Object init();
}
