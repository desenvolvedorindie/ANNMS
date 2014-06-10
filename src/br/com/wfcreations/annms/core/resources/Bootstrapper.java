package br.com.wfcreations.annms.core.resources;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Bootstrapper {

	private Application application;

	private Map<String, Resource> resources = new ConcurrentHashMap<String, Resource>();

	private Map<String, Boolean> started = new ConcurrentHashMap<String, Boolean>();
	
	public Bootstrapper(Application application) {
		this.application = application;
	}

	public void registerPluginResource(Resource resource) {
		resource.setBootstrap(this);
		String name = resource.getClass().getSimpleName();
		this.resources.put(name, resource);
	}

	public void unregisterPluginResource(Resource resource) {
		String name = resource.getClass().getSimpleName();
		this.resources.remove(name);
	}

	public boolean hasPluginResource(String name) {
		return this.resources.containsKey(name);
	}

	public Resource getPluginResource(String name) {
		return this.resources.get(name);
	}

	public Resource[] getPluginResources() {
		return resources.values().toArray(new Resource[resources.size()]);
	}

	public String[] getPluginResourcesName() {
		return resources.keySet().toArray(new String[resources.size()]);
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public void bootstrap() throws BootstrapException {
		for (Entry<String, Resource> entry : resources.entrySet()) {
			executeResource(entry.getValue());
		}
	}
	
	protected void executeResource(Resource resource) throws BootstrapException {
		String name = resource.getClass().getSimpleName();
		if(this.started.containsKey(name) && this.started.get(name)) {
			throw new BootstrapException("Circular resource dependency detected");
		}
		
		this.started.put(name, true);
		resource.init();
	}
	
	public abstract void run();
}