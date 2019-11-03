package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
	public String readPropertiesFile(String key) throws FileNotFoundException {
		String fileLocation = System.getProperty("user.dir")+"/src/test/resources/config/config.properties";
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(new File(fileLocation));
		try {
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}
}
