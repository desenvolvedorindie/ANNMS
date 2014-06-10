package br.com.wfcreations.annms.core.resources.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;

import br.com.wfcreations.annms.core.data.Data;

public class DataResource {

    public static String DATA_FOLDER = "data";

    public static String DATA_EXTENSION = ".data";

    private static DataResource instance;

    private Map<String, Data> dataList = new ConcurrentHashMap<String, Data>();

    public static DataResource getInstance() {
	if (instance == null) {
	    instance = new DataResource();
	}
	return instance;
    }

    private DataResource() {
    }

    public void init() {
	load();
    }

    public String[] getDataList() {
	String[] list = new String[this.dataList.size()];
	int i = 0;
	for (Map.Entry<String, Data> entry : this.dataList.entrySet()) {
	    list[i++] = entry.getKey();
	}
	return list;
    }

    public void create(Data data) throws FileNotFoundException, IOException {
	if (!validateName(data.getName())) {
	    throw new IllegalArgumentException("Invalid " + data.getName() + " for data name");
	}
	else if (this.has(data.getName())) {
	    throw new IllegalArgumentException("Data already exists");
	}

	FileOutputStream fos = new FileOutputStream(getPath(data.getName()));
	data.save(fos);
	fos.close();
	dataList.put(data.getName(), data);
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

    public Data get(String name) throws ClassNotFoundException, FileNotFoundException, IOException {
	if (this.has(name)) {
	    if (this.dataList.get(name) == null) {
		FileInputStream fis = new FileInputStream(getPath(name));
		this.dataList.put(name, new Data(fis));
		fis.close();
	    }
	    return this.dataList.get(name);
	}
	return null;
    }

    public void saveOpenedData() throws FileNotFoundException, IOException {
	FileOutputStream fos;
	for (Map.Entry<String, Data> entry : dataList.entrySet()) {
	    fos = new FileOutputStream(getPath(entry.getKey()));
	    entry.getValue().save(fos);
	    fos.close();
	}
	dataList = new ConcurrentHashMap<String, Data>();
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
