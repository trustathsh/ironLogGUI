package de.hshannover.f4.trust.ironloggui.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import de.hshannover.f4.trust.ironloggui.windows.MainWindow;

public class LogFileWorker2 implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(LogFileWorker2.class.getName());

	private File mLogFile;
	private MainWindow mMainWindow;
	private String mLogFileName;

	public LogFileWorker2(MainWindow mainWindow, String logFileName, String logFilePath) {
		mLogFile = new File(logFilePath);
		mMainWindow = mainWindow;
		mLogFileName = logFileName;
		mainWindow.addTab(mLogFileName);
	}

	@Override
	public void run() {
		String line = null;

		try {

			BufferedReader b = new BufferedReader(new FileReader(mLogFile));

			while (true) {
				line = b.readLine();
				if (line == null) {
					// wait until there is more of the file for us to read
					Thread.sleep(1000);
				} else {
					mMainWindow.appendTextInTab(mLogFileName, line + "\n");
				}

			}

		} catch (IOException | InterruptedException e) {
			LOGGER.error("Error Loading log file " + e);
		}
	}
}
