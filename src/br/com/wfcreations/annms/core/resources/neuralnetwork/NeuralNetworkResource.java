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

import br.com.wfcreations.annms.api.neuralnetwork.INeuralNetwork;

public class NeuralNetworkResource {

	public static String DATA_FOLDER = "neuralnetwork";

	public static String DATA_EXTENSION = ".net";

	private static NeuralNetworkResource instance;

	private Map<String, INeuralNetwork> dataList = new ConcurrentHashMap<String, INeuralNetwork>();

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

	public void create(INeuralNetwork data) throws FileNotFoundException, IOException {

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

	public INeuralNetwork get(String name) throws ClassNotFoundException, FileNotFoundException, IOException {
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
		for (Map.Entry<String, INeuralNetwork> entry : dataList.entrySet()) {
			fos = new FileOutputStream(getPath(entry.getKey()));
			// entry.getValue().save(fos);
			fos.close();
		}
		dataList = new ConcurrentHashMap<String, INeuralNetwork>();
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
