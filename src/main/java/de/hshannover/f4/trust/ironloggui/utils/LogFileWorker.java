/*
 * #%L
 * =====================================================
 *   _____                _     ____  _   _       _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \| | | | ___ | | | |
 *    | | | '__| | | / __| __|/ / _` | |_| |/ __|| |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _  |\__ \|  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_| |_||___/|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Hochschule Hannover
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.f4.hs-hannover.de/
 * 
 * This file is part of ironloggui, version 0.0.1,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2015 Trust@HsH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package de.hshannover.f4.trust.ironloggui.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import de.hshannover.f4.trust.ironloggui.windows.MainWindow;

/**
 * This class reads a logfile and updates the text in a tab of main window.
 * 
 * @author Marius Rohde
 * 
 */
public class LogFileWorker implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(LogFileWorker.class.getName());

	private File mLogFile;
	private MainWindow mMainWindow;
	private String mLogFileName;

	/**
	 * Constructor creates a new matcher with specified filename search pattern
	 * 
	 * @param mainWindow
	 *            for inserting Tabs
	 * @param logFileName
	 *            for identify the right tab
	 * @param logFilePath
	 *            for reading log text
	 */
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
