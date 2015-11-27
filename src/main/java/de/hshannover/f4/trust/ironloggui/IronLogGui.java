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
package de.hshannover.f4.trust.ironloggui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import de.hshannover.f4.trust.ironcommon.properties.PropertyException;
import de.hshannover.f4.trust.ironloggui.utils.FileSearcher;
import de.hshannover.f4.trust.ironloggui.utils.LogFileWorker;
import de.hshannover.f4.trust.ironloggui.windows.MainWindow;

public final class IronLogGui {

	private static final String VERSION = "${project.version}";

	private static final Logger LOGGER = Logger.getLogger(IronLogGui.class.getName());

	/**
	 * Death constructor for code convention -> final class because utility class
	 */
	private IronLogGui() {
	}

	/**
	 * The main method loads or initialize the Configuration and logging. After that it calls the initialize method of
	 * the main window
	 * 
	 * @throws InterruptedException
	 * 
	 */

	public static void main(String[] args) throws InterruptedException {

		LOGGER.info("Starting IronLogGui version " + VERSION);

		Configuration.init();

		MainWindow mainW = new MainWindow();

		List<Path> files = new ArrayList<Path>();

		try {

			for (String filename : Configuration.getFilenamesForSearch()) {
				FileSearcher finder = new FileSearcher(filename);
				Path curdir = Paths.get("").toAbsolutePath();
				Path rootdir = Paths.get(curdir.toString(), Configuration.getRootDir());

				try {
					Files.walkFileTree(rootdir.normalize(), finder);
				} catch (IOException e) {
					LOGGER.error(e);
				}
				files.addAll(finder.done());
			}
		} catch (PropertyException e1) {
			LOGGER.error(e1);
		}
		
		try {
			for (String filename : Configuration.getExplicitFileNames()) {
				Path file = Paths.get(filename);
				files.add(file);
			}
		} catch (PropertyException e) {
			LOGGER.error(e);
		}
		
		Collections.sort(files);
		
		for (Path file : files) {
			LogFileWorker worker = new LogFileWorker(mainW, file.getFileName().toString(), file.toString());
			new Thread(worker).start();
		}
		mainW.setVisible(true);
	}
}
