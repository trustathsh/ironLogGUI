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

import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import de.hshannover.f4.trust.ironcommon.properties.Properties;
import de.hshannover.f4.trust.ironcommon.properties.PropertyException;
import de.hshannover.f4.trust.ironloggui.windows.MainWindow;

public final class IronLogGui {
	
	private static final String VERSION = "${project.version}";

	private static final String FILENAME = "/config/configuration.yml";

	private static final Logger LOGGER = Logger.getLogger(IronLogGui.class.getName());
	
	private static final String ROOTDIRFORSEARCH = "ironloggui.rootdir";
	private static final String SEARCHFORFILES = "ironloggui.searchfor";
	private static final String EXPLICITFILES = "ironloggui.explicitfiles";
	
	
	/**
	 * Death constructor for code convention -> final class because utility
	 * class
	 */
	private IronLogGui() {
	}
	
	
	/**
	 * The main method loads or initialize the Configuration and logging.
	 * After that it calls the initialize method of the main window
	 * @throws InterruptedException 
	 * 
	 */
	
	public static void main(String[] args) throws InterruptedException {
		
		LOGGER.info("Starting IronLogGui version " + VERSION);

		LOGGER.info("Loading configuration file: " + FILENAME);
		String config = IronLogGui.class.getResource(FILENAME).getPath();
		Properties configuration = new Properties(config);

		try {
			String rootdir = configuration.getString(ROOTDIRFORSEARCH);
		} catch (PropertyException e) {
			LOGGER.warn("Couldnt load " +ROOTDIRFORSEARCH+ " correctly: " + e);
		}		
		try {
			@SuppressWarnings("unchecked")
			List<String> searchForFileNames = (List<String>) configuration.getValue(SEARCHFORFILES);
		} catch (PropertyException e) {
			LOGGER.warn("Couldnt load " +SEARCHFORFILES+ " correctly: " + e);
		}
		try {
			@SuppressWarnings("unchecked")
			List<String> explicitFileNames = (List<String>) configuration.getValue(EXPLICITFILES);
		} catch (PropertyException e) {
			LOGGER.warn("Couldnt load " +EXPLICITFILES+ " correctly: " + e);
		}
		LOGGER.info("Loading configuration file done");
		
		
		MainWindow mainW = new MainWindow();
		mainW.setVisible(true);		
		
		JTextArea one = mainW.addTab("irond");
		JTextArea two = mainW.addTab("visit");
		one.setText("blub");
		two.setText("blab");

	}

}
