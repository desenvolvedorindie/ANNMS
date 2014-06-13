package br.com.wfcreations.annms.core.resources;

public class Application {

	protected Bootstrapper bootstrap;

	public Application(Bootstrapper bootstrap) {
		this.bootstrap = bootstrap;
	}

	public Bootstrapper getBootstrap() {
		return bootstrap;
	}

	public void setBootstrap(Bootstrapper bootstrap) {
		this.bootstrap = bootstrap;
	}
	
	public Bootstrapper bootstrap() throws BootstrapException {
		return this.bootstrap.bootstrap();
	}
}
