package de.hshannover.f4.trust.ironloggui;

import java.util.List;

import org.apache.log4j.Logger;

import de.hshannover.f4.trust.ironcommon.properties.Properties;
import de.hshannover.f4.trust.ironcommon.properties.PropertyException;

public class Configuration {

	private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

	private static final String FILENAME = "/config/configuration.yml";

	private static final String ROOTDIRFORSEARCH = "ironloggui.rootdir";
	private static final String SEARCHFORFILES = "ironloggui.searchfor";
	private static final String EXPLICITFILES = "ironloggui.explicitfiles";

	private static Properties mConfiguration;

	public static void init() {

		LOGGER.info("Loading configuration file: " + FILENAME);
		mConfiguration = new Properties(IronLogGui.class.getResource(FILENAME).getPath());
		LOGGER.info("Loading configuration file done");

	}

	public static String getRootDir() throws PropertyException {
		return mConfiguration.getString(ROOTDIRFORSEARCH);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getFilenamesForSearch() throws PropertyException {
		return (List<String>) mConfiguration.getValue(SEARCHFORFILES);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getExplicitFileNames() throws PropertyException {
		return (List<String>) mConfiguration.getValue(EXPLICITFILES);
	}
}
