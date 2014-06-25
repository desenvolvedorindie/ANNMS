package br.com.wfcreations.annms.core.loader;

import java.io.FileInputStream;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.reflections.Reflections;
import org.xeustechnologies.jcl.JarClassLoader;

import br.com.wfcreations.annms.api.Algorithm.Instance;

public class AlgorithmLoader {

	JarClassLoader classLoader = new JarClassLoader();

	public AlgorithmLoader(String folder) {
		classLoader.add(folder);
		Reflections reflections = new Reflections("my.project.prefix");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Instance.class);
		System.out.println(annotated.size());
	}

	public Class<?> loadClass(String className) throws ClassNotFoundException {
		return classLoader.loadClass(className);
	}

}
