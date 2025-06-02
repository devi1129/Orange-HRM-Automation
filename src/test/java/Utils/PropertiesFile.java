package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertiesFile {

	private static final Logger LOG = LogManager.getLogger(PropertiesFile.class);

	private static FileInputStream fis;
	private static Properties prop = null;

	public static String getProperty(String key) {

		try {
			fis = new FileInputStream(
					new File(System.getProperty("user.dir") + "//src//test//resources//global.properties"));
			prop = new Properties();
			prop.load(fis);

		} catch (FileNotFoundException e) {
			LOG.error("Problem with file path");
		} catch (IOException e) {
			LOG.error("Problem with loading properties file");
		}

		return prop.getProperty(key);

	}

}
