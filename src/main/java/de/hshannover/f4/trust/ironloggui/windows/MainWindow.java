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
package de.hshannover.f4.trust.ironloggui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * This class generates the mainwindow and provides functions to add tabs to the
 * mainwindow.
 * 
 * @author Marius Rohde
 * 
 */

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7034670749972011389L;

	private JTabbedPane mTabbedPane;

	private HashMap<String, JTextArea> mTextAreas = new HashMap<String, JTextArea>();

	/**
	 * Constructor for window creation
	 */
	public MainWindow() {
		init();
		initMenu();
	}

	/**
	 * Init method to define window size and behavior
	 */
	public void init() {
		this.setTitle("IronLogGUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setResizable(true);
		mTabbedPane = new JTabbedPane();
		this.getContentPane().add(mTabbedPane);
	}

	/**
	 * Init method to generate a menu
	 */
	public void initMenu() {

		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();

		menu = new JMenu("File");
		menuBar.add(menu);

		menuItem = new JMenuItem("Exit", null);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		menu.add(menuItem);

		this.setJMenuBar(menuBar);
	}

	/**
	 * Method to add a new tab to the main window
	 */
	public synchronized void addTab(String logFileName) {
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane jsp = new JScrollPane(textArea);
		mTabbedPane.addTab(logFileName, null, jsp, logFileName);
		mTextAreas.put(logFileName, textArea);
	}

	/**
	 * Method to add a text in a existing tab
	 */
	public synchronized void appendTextInTab(String logFileName, String text) {
		JTextArea textArea = mTextAreas.get(logFileName);
		textArea.append(text);
	}

}
