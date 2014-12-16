/*
 * Copyright (c) Welsiton Ferreira (wfcreations@gmail.com)
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
package br.com.wfcreations.annms.core.resources;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Bootstrapper {

	private Map<String, Resource> resources = new ConcurrentHashMap<String, Resource>();

	private Map<String, Boolean> started = new ConcurrentHashMap<String, Boolean>();
	
	public Bootstrapper() {
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

	public Bootstrapper bootstrap() throws BootstrapException {
		for (Entry<String, Resource> entry : resources.entrySet()) {
			executeResource(entry.getValue());
		}
		return this;
	}
	
	protected void executeResource(Resource resource) throws BootstrapException {
		String name = resource.getClass().getSimpleName();
		if(this.started.containsKey(name) && this.started.get(name)) {
			throw new BootstrapException("Circular resource dependency detected");
		}
		
		this.started.put(name, true);
		resource.init();
	}
	
	public abstract void init();
	
	public abstract void finish();
}