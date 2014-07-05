package br.com.wfcreations.annms.core.service;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import br.com.wfcreations.annms.core.concurrent.NamedThreadFactory;

public class StorageService {

	//private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

	public static final ScheduledThreadPoolExecutor scheduledTasks = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("ScheduledTasks"));

	public static final ScheduledThreadPoolExecutor tasks = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("NonPeriodicTasks"));

	public static final StorageService instance = new StorageService();

	static {
		tasks.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
	}
}
