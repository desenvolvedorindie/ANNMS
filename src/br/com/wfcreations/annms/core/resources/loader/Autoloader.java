package br.com.wfcreations.annms.core.resources.loader;

import br.com.wfcreations.annms.core.resources.Resource;

public interface Autoloader {

	public Class<? extends Resource> autoload();
	
}
