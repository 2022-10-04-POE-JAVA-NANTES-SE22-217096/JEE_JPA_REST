package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

	public static String getConfig(String configName) {
	  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	  InputStream ficProps = classLoader.getResourceAsStream("/config/config.properties");
	  Properties properties = new Properties();
	  
	  try {
		  properties.load(ficProps);
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
		
		return properties.getProperty(configName);
	}

}
