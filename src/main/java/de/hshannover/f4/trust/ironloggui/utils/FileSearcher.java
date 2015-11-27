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

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * This callback class is a util to search files recursive in directory.
 * 
 * @author Marius Rohde
 * 
 */

public class FileSearcher extends SimpleFileVisitor<Path> {

	private static final Logger LOGGER = Logger.getLogger(FileSearcher.class.getName());

	private final PathMatcher mMatcher;
	private int mNumMatches = 0;
	private List<Path> mPathes = new ArrayList<Path>();

	/**
	 * Constructor creates a new matcher with specified filename search pattern
	 * 
	 * @param pattern
	 *            String for pattern matching
	 */
	public FileSearcher(String pattern) {
		mMatcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
	}

	/**
	 * Compares the glob pattern against the file or directory name.
	 * 
	 * @param file
	 *            current file in directory
	 */
	public void find(Path file) {
		Path name = file.getFileName();
		if (name != null && mMatcher.matches(name)) {
			mNumMatches++;
			LOGGER.info("Matched file: " + file.getFileName());
			mPathes.add(file);
		}
	}

	/**
	 * Prints the total number of matches to standard out.
	 * 
	 * @return List with path to found files
	 */
	public List<Path> done() {
		LOGGER.info("Matched files: " + mNumMatches);
		return mPathes;
	}

	// Invoke the pattern matching
	// method on each file.
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		find(file);
		return FileVisitResult.CONTINUE;
	}

	// Invoke the pattern matching
	// method on each directory.
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		find(dir);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		LOGGER.error("File Failed " + exc);
		return FileVisitResult.CONTINUE;
	}
}
