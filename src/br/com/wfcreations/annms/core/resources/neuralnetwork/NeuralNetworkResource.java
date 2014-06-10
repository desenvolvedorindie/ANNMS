package br.com.wfcreations.annms.core.resources.neuralnetwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;

import br.com.wfcreations.annms.core.neuralnetwork.learnrule.IArtificialNeuralNetwork;

public class NeuralNetworkResource {

	public static String DATA_FOLDER = "neuralnetwork";

	public static String DATA_EXTENSION = ".net";

	private static NeuralNetworkResource instance;

	private Map<String, IArtificialNeuralNetwork> dataList = new ConcurrentHashMap<String, IArtificialNeuralNetwork>();

	public static NeuralNetworkResource getInstance() {
		if (instance == null) {
			instance = new NeuralNetworkResource();
		}
		return instance;
	}

	private NeuralNetworkResource() {
	}

	public void init() {
		load();
	}

	public String[] getNeuralNetworkList() {
		return null;
	}

	public void create(IArtificialNeuralNetwork data) throws FileNotFoundException, IOException {

	}

	public boolean has(String name) {
		if (!validateName(name)) {
			throw new IllegalArgumentException("Invalid \"" + name + "\" name");
		}

		if (dataList.containsKey(name)) {
			return true;
		}
		return false;
	}

	public IArtificialNeuralNetwork get(String name) throws ClassNotFoundException, FileNotFoundException, IOException {
		if (this.has(name)) {
			if (this.dataList.get(name) == null) {
				FileInputStream fis = new FileInputStream(getPath(name));
				// this.dataList.put(name, new Data(fis));
				fis.close();
			}
			return this.dataList.get(name);
		}
		return null;
	}

	public void saveOpenedData() throws FileNotFoundException, IOException {
		FileOutputStream fos;
		for (Map.Entry<String, IArtificialNeuralNetwork> entry : dataList.entrySet()) {
			fos = new FileOutputStream(getPath(entry.getKey()));
			// entry.getValue().save(fos);
			fos.close();
		}
		dataList = new ConcurrentHashMap<String, IArtificialNeuralNetwork>();
		load();
	}

	public boolean drop(String name) {
		if (this.has(name)) {
			File file = new File(getPath(name));
			if (file.delete()) {
				dataList.remove(name);
				return true;
			}
		}
		return false;
	}

	private void load() {
		File folder = new File(DATA_FOLDER);
		File[] files = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(DATA_EXTENSION);
			}
		});
		for (File file : files) {
			System.out.println(dataList);
			dataList.put(FilenameUtils.removeExtension(file.getName()), null);
		}
	}

	private String getPath(String name) {
		return DATA_FOLDER + "/" + name + DATA_EXTENSION;
	}

	private boolean validateName(String name) {
		return name.matches("[a-zA-Z$][a-zA-Z0-9$_[-]]*");
	}
}
