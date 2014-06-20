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

public class DataManager {

	private static DataManager instance;

	private Map<String, Data> dataList = new ConcurrentHashMap<String, Data>();

	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}

	private DataManager() {
	}

}
