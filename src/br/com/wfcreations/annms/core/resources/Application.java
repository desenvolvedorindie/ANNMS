package br.com.wfcreations.annms.core.resources;

public class Application {

	private Bootstrapper bootstrap;

	public Application(Bootstrapper bootstrap) {
		this.bootstrap = bootstrap;
	}

	public Bootstrapper getBootstrap() {
		return bootstrap;
	}

	public void setBootstrap(Bootstrapper bootstrap) {
		this.bootstrap = bootstrap;
	}
	
	public void bootstrap() throws BootstrapException {
		this.bootstrap.bootstrap();
	}
}
