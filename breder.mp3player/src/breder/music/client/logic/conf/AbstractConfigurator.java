package breder.music.client.logic.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AbstractConfigurator {

	protected final Properties properties;

	private String filename;

	public AbstractConfigurator(String prefix) {
		this.properties = new Properties();
		this.filename = String.format(".%s%s.properties", "breder.music.", prefix);
		try {
			File file = new File(filename);
			if (file.exists()) {
				FileInputStream input = new FileInputStream(file);
				try {
					this.properties.load(input);
				} finally {
					input.close();
				}
			}
		} catch (Exception e) {
		}
	}

	public void save() throws IOException {
		FileOutputStream output = new FileOutputStream(filename);
		try {
			this.properties.store(output, null);
		} finally {
			output.close();
		}
	}

	public String check(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException();
		}
		return value;
	}

	public String get(String key) {
		return properties.getProperty(key);
	}

	public void set(String key, String value) {
		if (value == null) {
			properties.remove(key);
		} else {
			properties.setProperty(key, value);
		}
	}

}
