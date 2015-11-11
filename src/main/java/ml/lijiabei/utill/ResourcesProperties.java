package ml.lijiabei.utill;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class ResourcesProperties {

	private Map<String, String> resourcesProperties;

	private static ResourcesProperties rp = null;

	private ResourcesProperties() {
	}

	public synchronized static ResourcesProperties getInstance() {
		if (rp == null) {
			rp = new ResourcesProperties();
		}
		return rp;
	}

	public Map<String, String> getResourcesPropreties() {
		if (resourcesProperties == null || resourcesProperties.isEmpty()) {
			resourcesProperties = getAllPropertiesFromResources("resources.properties");
		}
		return resourcesProperties;
	}

	public static Map<String, String> getAllPropertiesFromResources(
			String filePath) {
		InputStream in = null;
		Map<String, String> m = null;
		try {
			in = new ClassPathResource(
					filePath == null ? "resources.properties" : filePath)
					.getInputStream();
			Properties p = new Properties();
			p.load(in);
			Enumeration<?> en = p.propertyNames();
			m = new HashMap<String, String>();
			String key = null;
			while (en.hasMoreElements()) {
				key = (String) en.nextElement();
				m.put(key, p.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
}
