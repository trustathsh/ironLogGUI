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

public class FileSearcher extends SimpleFileVisitor<Path> {
	
	private static final Logger LOGGER = Logger.getLogger(FileSearcher.class.getName());

	private final PathMatcher mMatcher;
	private int mNumMatches = 0;
	private List<Path> mPathes = new ArrayList<Path>();

	public FileSearcher(String pattern) {
		mMatcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
	}

	// Compares the glob pattern against
	// the file or directory name.
	public void find(Path file) {
		Path name = file.getFileName();
		if (name != null && mMatcher.matches(name)) {
			mNumMatches++;
			LOGGER.info("Matched file: " + file.getFileName());
			mPathes.add(file);
		}
	}

	// Prints the total number of
	// matches to standard out.
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