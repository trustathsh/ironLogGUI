package de.hshannover.f4.trust.ironloggui.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import de.hshannover.f4.trust.ironloggui.windows.MainWindow;

public class LogFileWorker implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(LogFileWorker.class.getName());

	private File mLogFile;
	private MainWindow mMainWindow;
	private String mLogFileName;

	public LogFileWorker(MainWindow mainWindow, String logFileName, String logFilePath) {
		mLogFile = new File(logFilePath);
		mMainWindow = mainWindow;
		mLogFileName = logFileName;
		mainWindow.addTab(mLogFileName);
	}

	@Override
	public void run() {
		String line = "";
		long skipCounter = 0;

		try {
			while (true) {
				BufferedReader b = new BufferedReader(new FileReader(mLogFile));
				b.skip(skipCounter);
				while ((line = b.readLine()) != null) {
					skipCounter += line.length() + System.getProperty("line.separator").length();
					mMainWindow.appendTextInTab(mLogFileName, line + "\n");
				}
				b.close();
				Thread.sleep(1 * 1000);
			}
		} catch (IOException | InterruptedException e) {
			LOGGER.error("Error Loading log file " + e);
		}
	}
}
